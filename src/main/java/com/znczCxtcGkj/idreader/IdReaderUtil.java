package com.znczCxtcGkj.idreader;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.znczCxtcGkj.entity.DingDan;
import com.znczCxtcGkj.entity.DingDanZhuangTai;
import com.znczCxtcGkj.netty.HttpClient;
import com.znczCxtcGkj.netty.HttpClientHandler;
import com.znczCxtcGkj.util.*;

import io.netty.handler.codec.http.HttpRequest;

/**
 * 身份证读卡器工具类
 * @author lenovo
 *
 */
public class IdReaderUtil {

	static Logger logger = LoggerFactory.getLogger(IdReaderUtil.class);

	/**
	 *  链接身份证读卡器
	 */
	public static boolean openDevice() {
		boolean flag = false;
		long startTime = System.currentTimeMillis();
		
		String idReaderIp = LoadProperties.getIdReaderIp();
		String idReaderPort = LoadProperties.getIdReaderPort();
		String url = "http://"+idReaderIp+":"+idReaderPort+"/OpenDevice";
		Map<String, String> getData = new HashMap<String, String>();
//	        getData.put("tags", "806:938356;");
//	        getData.put("sort", "_p");

		Map<String, String> header = new HashMap<String, String>();
		header.put("Sec-Fetch-Dest", "empty");
		header.put("Sec-Fetch-Mode", "cors");
		header.put("Sec-Fetch-Site", "same-origin");
		HttpClient client = new HttpClient();
		HttpRequest get;
		try {
			get = client.getRequestMethod(header, getData, url, "get");
			HttpClientHandler handler = new HttpClientHandler();
			client.run(url, get, handler);

			// Thread.sleep(10000);
			logger.debug("the result:{}", handler.getResult());
			
			String result = handler.getResult();
			JSONObject parseObject = JSON.parseObject(result);
			// 姓名
			Integer resultFlag = (Integer) parseObject.get("resultFlag");
			if (resultFlag == 0) {
				logger.info("链接身份证读卡器成功 ");
				flag = true;
			} else {
				String errorMsg = (String) parseObject.get("errorMsg");
				logger.info("链接身份证读卡器失败： ", errorMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 *  返回读取的身份证信息
	 */
	public static String readData() {

		long startTime = System.currentTimeMillis();
		
		String idReaderIp = LoadProperties.getIdReaderIp();
		String idReaderPort = LoadProperties.getIdReaderPort();
		String url = "http://"+idReaderIp+":"+idReaderPort+"/readcard";
		Map<String, String> getData = new HashMap<String, String>();
//	        getData.put("tags", "806:938356;");
//	        getData.put("sort", "_p");

		Map<String, String> header = new HashMap<String, String>();
		header.put("Sec-Fetch-Dest", "empty");
		header.put("Sec-Fetch-Mode", "cors");
		header.put("Sec-Fetch-Site", "same-origin");
		HttpClient client = new HttpClient();
		HttpRequest get;
		try {
			get = client.getRequestMethod(header, getData, url, "get");
			HttpClientHandler handler = new HttpClientHandler();
			client.run(url, get, handler);

			// Thread.sleep(10000);
			logger.debug("the result:{}", handler.getResult());
			return handler.getResult();
			//Thread.sleep(10000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();// 记录结束时间
		logger.debug(((endTime - startTime) / 1000) + "");
		return null;
	}
	
	/**
	 * 根据身份证号进入排队中
	 * @param sfzh
	 */
	public static void paiDuiBySfzh(String sfzh) {
		try {
			//根据身份证号进入排队中start
			org.json.JSONObject resultJO=APIUtil.getDingDanBySfzhZt(sfzh,DingDanZhuangTai.YI_XIA_DAN_TEXT);
			String status = resultJO.getString("status");
			if("ok".equals(status)) {
				// 根据获取到订单信息，改变订单状态，并进行发卡(打印二维码)
				logger.debug("===========根据司机身份证号查询的订单信息============");
				logger.debug(resultJO.toString());
				org.json.JSONObject ddJO=resultJO.getJSONObject("dingDan");
				String ckcsStr = ddJO.getString("ckcs");
				Integer ckcs = null;
				if (StringUtils.isBlank(ckcsStr)) {
					ckcs = 0;
				} else {
					ckcs = Integer.parseInt(ckcsStr);
				}
				
				String jhysrq = ddJO.getString("jhysrq");
				DateUtil dateUtil = new DateUtil();
				Date jhysrqDate = dateUtil.parse(jhysrq);
				// 计划运输时间
				long jhysrqTime = jhysrqDate.getTime();
				
				DateUtil timeUtil = new DateUtil("yyyy-MM-dd HH:mm:ss");
				String currentDateStr = timeUtil.format(new Date());
				Date currentDate = timeUtil.parse(currentDateStr);
				// 当前时间
				long currentTime = currentDate.getTime();

				// 可以进入的最大时间
				long endTime = jhysrqTime + DateUtil.getTime(LoadProperties.getIntoTheFactoryDate());
				if (jhysrqTime <= currentTime && currentTime <= endTime) {
					// 可以进入厂区
					if(ckcs==0) {
						try {
							ckcs = ckcs + 1;//打印二维码相当于出卡了，出卡次数加1
							String ewm = ddJO.getString("ewm");
							logger.info("二维码地址： " + ewm);
							QrcodePrint.drawImage(ewm);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							logger.info("打印机打印二维码出错");
							e.printStackTrace();
						}
					}
					
					//更新订单状态为排队中
			    	DingDan dd=new DingDan();
			    	long ddId = ddJO.getLong("id");
			    	dd.setId(ddId);
			    	dd.setDdztMc(DingDanZhuangTai.PAI_DUI_ZHONG_TEXT);
			    	dd.setCkcs(ckcs);
			    	org.json.JSONObject eddResultJO = APIUtil.editDingDan(dd);
			    	if("ok".equals(eddResultJO.getString("message"))) {
			    		//编辑订单成功
			    		logger.info(eddResultJO.getString("info")+"状态为"+DingDanZhuangTai.PAI_DUI_ZHONG_TEXT);
			    		//生成排号信息
			    		org.json.JSONObject nhmResultJO = APIUtil.newHaoMa(ddId);
			        	if("ok".equals(nhmResultJO.getString("message"))) {
			        		logger.info(nhmResultJO.getString("info"));
			        	}
			        	else {
			        		logger.info(nhmResultJO.getString("info"));
			        	}
			    	}
			    	else {
			    		logger.info(eddResultJO.getString("info"));
			    	}
				}
				else {
					logger.info("计划运输日期不准确");
				}
			}
			else {
				// 语音播报
				logger.info("没有获取到当前用户的订单信息");
			}
			//根据身份证号进入排队中end
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String ewm="";
		QrcodePrint.drawImage(ewm);
	}
}

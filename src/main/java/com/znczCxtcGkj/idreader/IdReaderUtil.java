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
 * ���֤������������
 * @author lenovo
 *
 */
public class IdReaderUtil {

	static Logger logger = LoggerFactory.getLogger(IdReaderUtil.class);

	/**
	 *  �������֤������
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
			// ����
			Integer resultFlag = (Integer) parseObject.get("resultFlag");
			if (resultFlag == 0) {
				logger.info("�������֤�������ɹ� ");
				flag = true;
			} else {
				String errorMsg = (String) parseObject.get("errorMsg");
				logger.info("�������֤������ʧ�ܣ� ", errorMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 *  ���ض�ȡ�����֤��Ϣ
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

		long endTime = System.currentTimeMillis();// ��¼����ʱ��
		logger.debug(((endTime - startTime) / 1000) + "");
		return null;
	}
	
	/**
	 * �������֤�Ž����Ŷ���
	 * @param sfzh
	 */
	public static void paiDuiBySfzh(String sfzh) {
		try {
			//�������֤�Ž����Ŷ���start
			org.json.JSONObject resultJO=APIUtil.getDingDanBySfzhZt(sfzh,DingDanZhuangTai.YI_XIA_DAN_TEXT);
			String status = resultJO.getString("status");
			if("ok".equals(status)) {
				// ���ݻ�ȡ��������Ϣ���ı䶩��״̬�������з���(��ӡ��ά��)
				logger.debug("===========����˾�����֤�Ų�ѯ�Ķ�����Ϣ============");
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
				// �ƻ�����ʱ��
				long jhysrqTime = jhysrqDate.getTime();
				
				DateUtil timeUtil = new DateUtil("yyyy-MM-dd HH:mm:ss");
				String currentDateStr = timeUtil.format(new Date());
				Date currentDate = timeUtil.parse(currentDateStr);
				// ��ǰʱ��
				long currentTime = currentDate.getTime();

				// ���Խ�������ʱ��
				long endTime = jhysrqTime + DateUtil.getTime(LoadProperties.getIntoTheFactoryDate());
				if (jhysrqTime <= currentTime && currentTime <= endTime) {
					// ���Խ��볧��
					if(ckcs==0) {
						try {
							ckcs = ckcs + 1;//��ӡ��ά���൱�ڳ����ˣ�����������1
							String ewm = ddJO.getString("ewm");
							logger.info("��ά���ַ�� " + ewm);
							QrcodePrint.drawImage(ewm);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							logger.info("��ӡ����ӡ��ά�����");
							e.printStackTrace();
						}
					}
					
					//���¶���״̬Ϊ�Ŷ���
			    	DingDan dd=new DingDan();
			    	long ddId = ddJO.getLong("id");
			    	dd.setId(ddId);
			    	dd.setDdztMc(DingDanZhuangTai.PAI_DUI_ZHONG_TEXT);
			    	dd.setCkcs(ckcs);
			    	org.json.JSONObject eddResultJO = APIUtil.editDingDan(dd);
			    	if("ok".equals(eddResultJO.getString("message"))) {
			    		//�༭�����ɹ�
			    		logger.info(eddResultJO.getString("info")+"״̬Ϊ"+DingDanZhuangTai.PAI_DUI_ZHONG_TEXT);
			    		//�����ź���Ϣ
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
					logger.info("�ƻ��������ڲ�׼ȷ");
				}
			}
			else {
				// ��������
				logger.info("û�л�ȡ����ǰ�û��Ķ�����Ϣ");
			}
			//�������֤�Ž����Ŷ���end
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

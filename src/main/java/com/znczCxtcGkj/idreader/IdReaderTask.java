package com.znczCxtcGkj.idreader;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.znczCxtcGkj.entity.*;
import com.znczCxtcGkj.util.*;
import com.znczCxtcGkj.yz.*;

/**
 * 身份证读卡器线程任务
 * @author Administrator
 *
 */
public class IdReaderTask extends Thread {
	Logger logger = LoggerFactory.getLogger(IdReaderTask.class);

	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info("身份证读卡器启动");
		try {
			idReader();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logger.info("你们看不到我，看到我请重启");
		}
	}

	private void idReader() {

		while (true) {
			try {
				// 从 readData 中获取到身份证号码
				logger.debug("======读取的身份证信息-start =======");
				// 得到身份证信息的json
				String readData = IdReaderUtil.readData();
				logger.debug(readData);
				logger.debug("======读取的身份证信息-end=======");
				JSONObject parseObject = JSON.parseObject(readData);
				
				Integer resultFlag = (Integer) parseObject.get("resultFlag");
				if (-3 == resultFlag) {
					boolean flag = true;
					while (flag) {
						boolean openDevice = IdReaderUtil.openDevice();
						if (openDevice) {
							flag = false;
						}
					}
				}
				// 姓名
				String name = (String) parseObject.get("partyName");
				// 身份证号码
				String certNumber = (String) parseObject.get("certNumber");
				
				if (certNumber.length() > 14) {
					// 拿到身份证号， 可以在这里触发语音播报
					logger.info("身份识别已成功请拿走身份证" + certNumber);
					
					// 根据身份证号查询已下单订单
					org.json.JSONObject resultJO=APIUtil.getDingDanBySfzhZt(certNumber,DingDanZhuangTai.YI_XIA_DAN_TEXT);
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
				} else {
					// 没有拿到身份证号， 可以在这里触发语音播报
					logger.info("ip地址为： " + LoadProperties.getIp());
					logger.info("无身份证号");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				YinZhuUtil.sendMsg(ModBusUtil.get86(), 1500);
			} finally {
				sleepTime(5000);
				logger.info("睡眠5秒");
			}
		} // while 循环结束
	}

	/**
	 * 线程睡眠
	 */
	private void sleepTime(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

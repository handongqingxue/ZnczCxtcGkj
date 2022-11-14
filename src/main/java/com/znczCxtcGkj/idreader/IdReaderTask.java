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
					
					IdReaderUtil.paiDuiBySfzh(certNumber);
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

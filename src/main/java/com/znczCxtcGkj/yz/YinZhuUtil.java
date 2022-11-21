package com.znczCxtcGkj.yz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.znczCxtcGkj.util.*;

import gnu.io.SerialPort;

public class YinZhuUtil {
	
	static Logger logger = LoggerFactory.getLogger(YinZhuUtil.class);
	
	/**
	 * ∑¢ÀÕ÷∏¡Ó
	 * @param modBus
	 * @param sleepTime
	 * @return
	 */
	public static String sendMsg(String modBus, long sleepTime) {
		String executeOrder = null;
		SerialPort serialPort = null;
		try {
			logger.info("∑¢ÀÕ“Ù∆µ");
			String yinZhuCom = LoadProperties.getYinZhuCom();
			// ø™∆Ù¥Æø⁄
			serialPort = RXTXUtil.openSerialPort(yinZhuCom, 100);
			RXTXUtil.sendData(serialPort,modBus);
		} catch (Exception e) {
			logger.info("“Ù∆µ∑¢ÀÕ¥ÌŒÛ");
			e.printStackTrace();
		} finally {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RXTXUtil.closeSerialPort(serialPort);
		}
		return executeOrder;
	}
}

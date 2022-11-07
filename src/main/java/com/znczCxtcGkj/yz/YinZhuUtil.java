package com.znczCxtcGkj.yz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.znczCxtcGkj.util.*;

public class YinZhuUtil {
	
	static Logger logger = LoggerFactory.getLogger(YinZhuUtil.class);
	
	/**
	 * ����ָ��
	 * @param modBus
	 * @param sleepTime
	 * @return
	 */
	public static String sendMsg(String modBus, long sleepTime) {
		String executeOrder = null;
		try {
			logger.info("������Ƶ");
			String yinZhuCom = LoadProperties.getYinZhuCom();
			// ��������
			RXTXUtil.openSerialPort(yinZhuCom, 100);
			executeOrder = RXTXUtil.executeOrder(modBus, yinZhuCom, 1);
		} catch (Exception e) {
			logger.info("��Ƶ���ʹ���");
			e.printStackTrace();
		} finally {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RXTXUtil.closeSerialPort();
		}
		return executeOrder;
	}
}

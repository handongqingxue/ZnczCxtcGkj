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
 * ���֤�������߳�����
 * @author Administrator
 *
 */
public class IdReaderTask extends Thread {
	Logger logger = LoggerFactory.getLogger(IdReaderTask.class);

	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info("���֤����������");
		try {
			idReader();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logger.info("���ǿ������ң�������������");
		}
	}

	private void idReader() {

		while (true) {
			try {
				// �� readData �л�ȡ�����֤����
				logger.debug("======��ȡ�����֤��Ϣ-start =======");
				// �õ����֤��Ϣ��json
				String readData = IdReaderUtil.readData();
				logger.debug(readData);
				logger.debug("======��ȡ�����֤��Ϣ-end=======");
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
				// ����
				String name = (String) parseObject.get("partyName");
				// ���֤����
				String certNumber = (String) parseObject.get("certNumber");
				
				if (certNumber.length() > 14) {
					// �õ����֤�ţ� ���������ﴥ����������
					logger.info("���ʶ���ѳɹ����������֤" + certNumber);
					
					IdReaderUtil.paiDuiBySfzh(certNumber);
				} else {
					// û���õ����֤�ţ� ���������ﴥ����������
					logger.info("ip��ַΪ�� " + LoadProperties.getIp());
					logger.info("�����֤��");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				YinZhuUtil.sendMsg(ModBusUtil.get86(), 1500);
			} finally {
				sleepTime(5000);
				logger.info("˯��5��");
			}
		} // while ѭ������
	}

	/**
	 * �߳�˯��
	 */
	private void sleepTime(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

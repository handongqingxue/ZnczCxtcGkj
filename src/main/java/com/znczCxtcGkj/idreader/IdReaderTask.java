package com.znczCxtcGkj.idreader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
			//idReader();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logger.info("���ǿ������ң�������������");
		}
	}

}

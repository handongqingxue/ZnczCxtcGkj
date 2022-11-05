package com.znczCxtcGkj.idreader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
			//idReader();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logger.info("你们看不到我，看到我请重启");
		}
	}

}

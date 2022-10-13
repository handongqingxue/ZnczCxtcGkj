package com.znczCxtcGkj.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.znczCxtcGkj.jdq.BangFangJdq;
import com.znczCxtcGkj.jdq.JdqBf1Util;
import com.znczCxtcGkj.jdq.JdqZlUtil;
import com.znczCxtcGkj.jdq.WriteZhiLingConst;

public class BangFangTask {

	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) {
		// 车牌识别的线程任务
		//CpsbsxtTask cpsbsxtTask = new CpsbsxtTask();
		//cpsbsxtTask.start();
		
		BangFangJdq bfjdq=new BangFangJdq();
		JdqZlUtil.setBfjdq(bfjdq);
		
		//ClientSocket cs = new ClientSocket();
		//cs.connectServer();
		
		// 其他线程启动
		while (true) {
			// main程序一直运行
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			if(!cs.isServerOpend()) {
				System.out.println("与服务器端通讯断开了，正在重新建立连接,时间:"+sdf.format(new Date()));
				cs.connectServer();
			}
			*/
		}
	}
}

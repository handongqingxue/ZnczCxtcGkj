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
		// ����ʶ����߳�����
		//CpsbsxtTask cpsbsxtTask = new CpsbsxtTask();
		//cpsbsxtTask.start();
		
		BangFangJdq bfjdq=new BangFangJdq();
		JdqZlUtil.setBfjdq(bfjdq);
		
		//ClientSocket cs = new ClientSocket();
		//cs.connectServer();
		
		// �����߳�����
		while (true) {
			// main����һֱ����
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			if(!cs.isServerOpend()) {
				System.out.println("���������ͨѶ�Ͽ��ˣ��������½�������,ʱ��:"+sdf.format(new Date()));
				cs.connectServer();
			}
			*/
		}
	}
}

package com.znczCxtcGkj.jdq;

import com.znczCxtcGkj.util.LoadProperties;

public class JdqBf2Util {
	
	/**
	 * ̧���ϰ���բ
	 */
	public static void openShangBangDz() {
		try {
			BangFangJdq bfjdq = JdqZlUtil.getBfjdq();
			bfjdq.sendData(WriteZhiLingConst.KAI_JI_DIAN_QI1);
			int jdqMaiChong = LoadProperties.getJdqMaiChong();
			Thread.sleep(jdqMaiChong);
			bfjdq.sendData(WriteZhiLingConst.GUAN_JI_DIAN_QI1);//����ʱ�����ִ�и�λ����
			//Thread.sleep(1000);
			//yjjdq.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ̧���°���բ
	 */
	public static void openXiaBangDz() {
		try {
			BangFangJdq bfjdq = JdqZlUtil.getBfjdq();
			bfjdq.sendData(WriteZhiLingConst.KAI_JI_DIAN_QI1);
			int jdqMaiChong = LoadProperties.getJdqMaiChong();
			Thread.sleep(jdqMaiChong);
			bfjdq.sendData(WriteZhiLingConst.GUAN_JI_DIAN_QI1);//����ʱ�����ִ�и�λ����
			//Thread.sleep(1000);
			//yjjdq.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

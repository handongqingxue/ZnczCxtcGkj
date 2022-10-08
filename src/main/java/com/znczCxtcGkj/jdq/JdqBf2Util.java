package com.znczCxtcGkj.jdq;

import com.znczCxtcGkj.util.LoadProperties;

public class JdqBf2Util {
	
	/**
	 * 抬起上磅道闸
	 */
	public static void openShangBangDz() {
		try {
			BangFangJdq bfjdq = JdqZlUtil.getBfjdq();
			bfjdq.sendData(WriteZhiLingConst.KAI_JI_DIAN_QI1);
			int jdqMaiChong = LoadProperties.getJdqMaiChong();
			Thread.sleep(jdqMaiChong);
			bfjdq.sendData(WriteZhiLingConst.GUAN_JI_DIAN_QI1);//脉冲时间过后执行复位操作
			//Thread.sleep(1000);
			//yjjdq.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 抬起下磅道闸
	 */
	public static void openXiaBangDz() {
		try {
			BangFangJdq bfjdq = JdqZlUtil.getBfjdq();
			bfjdq.sendData(WriteZhiLingConst.KAI_JI_DIAN_QI1);
			int jdqMaiChong = LoadProperties.getJdqMaiChong();
			Thread.sleep(jdqMaiChong);
			bfjdq.sendData(WriteZhiLingConst.GUAN_JI_DIAN_QI1);//脉冲时间过后执行复位操作
			//Thread.sleep(1000);
			//yjjdq.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package com.znczCxtcGkj.jdq;

import com.znczCxtcGkj.util.LoadProperties;

public class JdqMGUtil {

	/**
	 * 抬起进厂道闸
	 */
	public static void openJinChangDz() {
		try {
        	System.out.println("抬起进厂道闸");
			MenGangJdq mgjdq = JdqZlUtil.getMgjdq();
			mgjdq.sendData(WriteZhiLingConst.KAI_JI_DIAN_QI1);
			int jdqMaiChong = LoadProperties.getJdqMaiChong();
			Thread.sleep(jdqMaiChong);
			mgjdq.sendData(WriteZhiLingConst.GUAN_JI_DIAN_QI1);//脉冲时间过后执行复位操作
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 抬起出厂道闸
	 */
	public static void openChuChangDz() {
		try {
        	System.out.println("抬起出厂道闸");
			MenGangJdq mgjdq = JdqZlUtil.getMgjdq();
			mgjdq.sendData(WriteZhiLingConst.KAI_JI_DIAN_QI2);
			int jdqMaiChong = LoadProperties.getJdqMaiChong();
			Thread.sleep(jdqMaiChong);
			mgjdq.sendData(WriteZhiLingConst.GUAN_JI_DIAN_QI2);//脉冲时间过后执行复位操作
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package com.znczCxtcGkj.jdq;

import com.znczCxtcGkj.util.LoadProperties;

public class JdqMGUtil {

	/**
	 * ̧�������բ
	 */
	public static void openJinChangDz() {
		try {
        	System.out.println("̧�������բ");
			MenGangJdq mgjdq = JdqZlUtil.getMgjdq();
			mgjdq.sendData(WriteZhiLingConst.KAI_JI_DIAN_QI1);
			int jdqMaiChong = LoadProperties.getJdqMaiChong();
			Thread.sleep(jdqMaiChong);
			mgjdq.sendData(WriteZhiLingConst.GUAN_JI_DIAN_QI1);//����ʱ�����ִ�и�λ����
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ̧�������բ
	 */
	public static void openChuChangDz() {
		try {
        	System.out.println("̧�������բ");
			MenGangJdq mgjdq = JdqZlUtil.getMgjdq();
			mgjdq.sendData(WriteZhiLingConst.KAI_JI_DIAN_QI2);
			int jdqMaiChong = LoadProperties.getJdqMaiChong();
			Thread.sleep(jdqMaiChong);
			mgjdq.sendData(WriteZhiLingConst.GUAN_JI_DIAN_QI2);//����ʱ�����ִ�и�λ����
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

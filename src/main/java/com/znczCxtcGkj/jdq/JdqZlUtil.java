package com.znczCxtcGkj.jdq;

/**
 * �̵���ָ�����
 * */
public class JdqZlUtil {
	
	private static BangFangJdq bfjdq;
	private static MenGangJdq mgjdq;

	public static BangFangJdq getBfjdq() {
		return bfjdq;
	}

	public static void setBfjdq(BangFangJdq bfjdq) {
		JdqZlUtil.bfjdq = bfjdq;
	}
	
	public static MenGangJdq getMgjdq() {
		return mgjdq;
	}

	public static void setMgjdq(MenGangJdq mgjdq) {
		JdqZlUtil.mgjdq = mgjdq;
	}

	/**
	 * ���������̵���
	 */
	public static void openBangFangJdq() {
    	System.out.println("���������̵���");
		JdqZlUtil.bfjdq.open();
	}

	/**
	 * �����Ÿڼ̵���
	 */
	public static void openMenGangJdq() {
    	System.out.println("�����Ÿڼ̵���");
		JdqZlUtil.mgjdq.open();
	}

	/**
	 * �رհ����̵���
	 */
	public static void closeBangFangJdq() {
    	System.out.println("�رհ����̵���");
		JdqZlUtil.bfjdq.close();
	}

	/**
	 * �ر��Ÿڼ̵���
	 */
	public static void closeMenGangJdq() {
    	System.out.println("�ر��Ÿڼ̵���");
		JdqZlUtil.mgjdq.close();
	}

	public static void main(String[] args) {
		BangFangJdq bfjdq=new BangFangJdq();
		JdqZlUtil.setBfjdq(bfjdq);
		JdqZlUtil.openBangFangJdq();
		JdqBf1Util.openShangBangDz();
		JdqZlUtil.closeBangFangJdq();
	}
}

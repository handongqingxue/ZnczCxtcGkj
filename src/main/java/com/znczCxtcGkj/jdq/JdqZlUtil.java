package com.znczCxtcGkj.jdq;

/**
 * �̵���ָ�����
 * */
public class JdqZlUtil {
	
	private static BangFangJdq bfjdq;
	
	public static BangFangJdq getBfjdq() {
		return bfjdq;
	}

	public static void setBfjdq(BangFangJdq bfjdq) {
		JdqZlUtil.bfjdq = bfjdq;
	}

	/**
	 * ���������̵���
	 */
	public static void openBangFangJdq() {
    	System.out.println("���������̵���");
		JdqZlUtil.bfjdq.open();
	}

	/**
	 * �رհ����̵���
	 */
	public static void closeBangFangJdq() {
		JdqZlUtil.bfjdq.close();
	}

	public static void main(String[] args) {
		BangFangJdq bfjdq=new BangFangJdq();
		JdqZlUtil.setBfjdq(bfjdq);
		JdqZlUtil.openBangFangJdq();
		JdqBf2Util.openXiaBangDz();
		JdqZlUtil.closeBangFangJdq();
	}
}

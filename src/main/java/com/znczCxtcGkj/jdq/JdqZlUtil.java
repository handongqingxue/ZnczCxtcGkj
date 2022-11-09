package com.znczCxtcGkj.jdq;

/**
 * 继电器指令工具类
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
	 * 开启磅房继电器
	 */
	public static void openBangFangJdq() {
    	System.out.println("开启磅房继电器");
		JdqZlUtil.bfjdq.open();
	}

	/**
	 * 开启门岗继电器
	 */
	public static void openMenGangJdq() {
    	System.out.println("开启门岗继电器");
		JdqZlUtil.mgjdq.open();
	}

	/**
	 * 关闭磅房继电器
	 */
	public static void closeBangFangJdq() {
    	System.out.println("关闭磅房继电器");
		JdqZlUtil.bfjdq.close();
	}

	/**
	 * 关闭门岗继电器
	 */
	public static void closeMenGangJdq() {
    	System.out.println("关闭门岗继电器");
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

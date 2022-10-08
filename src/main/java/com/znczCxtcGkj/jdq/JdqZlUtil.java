package com.znczCxtcGkj.jdq;

/**
 * 继电器指令工具类
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
	 * 开启磅房继电器
	 */
	public static void openBangFangJdq() {
    	System.out.println("开启磅房继电器");
		JdqZlUtil.bfjdq.open();
	}

	/**
	 * 关闭磅房继电器
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

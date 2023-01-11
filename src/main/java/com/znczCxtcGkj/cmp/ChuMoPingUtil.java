package com.znczCxtcGkj.cmp;

import com.znczCxtcGkj.util.*;

import gnu.io.SerialPort;

public class ChuMoPingUtil {
	
	public static final String ADDRESS="01";
	public static final String FUN_CODE="06";
	public static final String LOC_HIGHT="00";
	public static final String VAR_HIGHT="00";
	public static final String NO_ORDER="无订单";
	
	public static int getVarNumBySf(String sjc) {
		int varNum=0;
		switch (sjc) {
		case "京":
			varNum=1;
			break;
		case "津":
			varNum=2;
			break;
		case "冀":
			varNum=3;
			break;
		case "晋":
			varNum=4;
			break;
		case "蒙":
			varNum=5;
			break;
		case "辽":
			varNum=6;
			break;
		case "吉":
			varNum=7;
			break;
		case "黑":
			varNum=8;
			break;
		case "沪":
			varNum=9;
			break;
		case "苏":
			varNum=10;
			break;
		case "浙":
			varNum=11;
			break;
		case "皖":
			varNum=12;
			break;
		case "闽":
			varNum=13;
			break;
		case "赣":
			varNum=14;
			break;
		case "鲁":
			varNum=15;
			break;
		case "豫":
			varNum=16;
			break;
		case "鄂":
			varNum=17;
			break;
		case "湘":
			varNum=18;
			break;
		case "粤":
			varNum=19;
			break;
		case "桂":
			varNum=20;
			break;
		case "琼":
			varNum=21;
			break;
		case "渝":
			varNum=22;
			break;
		case "川":
			varNum=23;
			break;
		case "黔":
			varNum=24;
			break;
		case "滇":
			varNum=25;
			break;
		case "藏":
			varNum=26;
			break;
		case "陕":
			varNum=27;
			break;
		case "甘":
			varNum=28;
			break;
		case "青":
			varNum=29;
			break;
		case "宁":
			varNum=30;
			break;
		case "新":
			varNum=31;
			break;
		case "台":
			varNum=32;
			break;
		case "港":
			varNum=33;
			break;
		case "澳":
			varNum=34;
			break;
		case NO_ORDER:
			varNum=35;
			break;
		}
		return varNum;
	}

	public static int getVarNumBySzZm(String szZm) {
		int varNum=0;
		switch (szZm) {
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
			varNum=Integer.valueOf(szZm);
			break;
		case "A":
			varNum=10;
			break;
		case "B":
			varNum=11;
			break;
		case "C":
			varNum=12;
			break;
		case "D":
			varNum=13;
			break;
		case "E":
			varNum=14;
			break;
		case "F":
			varNum=15;
			break;
		case "G":
			varNum=16;
			break;
		case "H":
			varNum=17;
			break;
		case "I":
			varNum=18;
			break;
		case "J":
			varNum=19;
			break;
		case "K":
			varNum=20;
			break;
		case "L":
			varNum=21;
			break;
		case "M":
			varNum=22;
			break;
		case "N":
			varNum=23;
			break;
		case "O":
			varNum=24;
			break;
		case "P":
			varNum=25;
			break;
		case "Q":
			varNum=26;
			break;
		case "R":
			varNum=27;
			break;
		case "S":
			varNum=28;
			break;
		case "T":
			varNum=29;
			break;
		case "U":
			varNum=30;
			break;
		case "V":
			varNum=31;
			break;
		case "W":
			varNum=32;
			break;
		case "X":
			varNum=33;
			break;
		case "Y":
			varNum=34;
			break;
		case "Z":
			varNum=35;
			break;
		}
		return varNum;
	}
	
	/**
	 * 发送车牌号
	 * @param cph
	 */
	public static void sendCph(String cph) {
		SerialPort serialPort = null;
		try {
			System.out.println("发送车牌号");
			String chuMoPingCom = LoadProperties.getChuMoPingCom();
			// 开启串口
			serialPort = RXTXUtil.openSerialPort(chuMoPingCom, 9600);
			for (int i = 0; i < cph.length(); i++) {
				char cphChar = cph.charAt(i);
				int varNum;
				if(i==0) {//判断是省简称还是后面的数字或字母,调用相应的方法获得变量
					varNum = getVarNumBySf(cphChar+"");//获取第一位省简称十进制低位变量
				}
				else {
					varNum = getVarNumBySzZm(cphChar+"");//获取第一位之后的字母或数字十进制低位变量
				}
				String hexVarNum = HexadecimalUtil.get16Num(varNum);
				String hexLowVarNum=hexVarNum.length()<2?"0"+hexVarNum:hexVarNum;
				String djyZl=ADDRESS+FUN_CODE+LOC_HIGHT+"0"+i+VAR_HIGHT+hexLowVarNum;//待校验指令字符串
				String jym = CRCUtil.getCRC(djyZl);//获取校验码
				String yjyZl=djyZl+jym;//已校验指令
				RXTXUtil.sendData(serialPort,yjyZl);//发送指令(每次只能发车牌号里的一个字符)
			}
		} catch (Exception e) {
			System.out.println("车牌号发送错误");
			e.printStackTrace();
		} 
		finally {
			RXTXUtil.closeSerialPort(serialPort);
		}
	}
	
	/**
	 * 发送订单号
	 * @param ddh
	 */
	public static void sendDdh(String ddh) {
		SerialPort serialPort = null;
		try {
			System.out.println("发送订单号");
			String chuMoPingCom = LoadProperties.getChuMoPingCom();
			// 开启串口
			serialPort = RXTXUtil.openSerialPort(chuMoPingCom, 9600);
			for (int i = 0; i < ddh.length(); i++) {
				char ddhChar = ddh.charAt(i);
				int varNum = getVarNumBySzZm(ddhChar+"");
				String hexVarNum = HexadecimalUtil.get16Num(varNum);
				String hexLowVarNum=hexVarNum.length()<2?"0"+hexVarNum:hexVarNum;
				String djyZl=ADDRESS+FUN_CODE+LOC_HIGHT+"0"+i+VAR_HIGHT+hexLowVarNum;//待校验指令字符串
				String jym = CRCUtil.getCRC(djyZl);//获取校验码
				String yjyZl=djyZl+jym;//已校验指令
				RXTXUtil.sendData(serialPort,yjyZl);//发送指令(每次只能发车牌号里的一个字符)
			}
		} catch (Exception e) {
			System.out.println("订单号发送错误");
			e.printStackTrace();
		} 
		finally {
			RXTXUtil.closeSerialPort(serialPort);
		}
	}
	
	/**
	 * 发送排队号
	 * @param pdh
	 */
	public static void sendPdh(String pdh) {
		SerialPort serialPort = null;
		try {
			System.out.println("发送排队号");
			String chuMoPingCom = LoadProperties.getChuMoPingCom();
			// 开启串口
			serialPort = RXTXUtil.openSerialPort(chuMoPingCom, 9600);
			for (int i = 0; i < pdh.length(); i++) {
				char pdhChar = pdh.charAt(i);
				int varNum = getVarNumBySzZm(pdhChar+"");
				String hexVarNum = HexadecimalUtil.get16Num(varNum);
				String hexLowVarNum=hexVarNum.length()<2?"0"+hexVarNum:hexVarNum;
				String djyZl=ADDRESS+FUN_CODE+LOC_HIGHT+"0"+i+VAR_HIGHT+hexLowVarNum;//待校验指令字符串
				String jym = CRCUtil.getCRC(djyZl);//获取校验码
				String yjyZl=djyZl+jym;//已校验指令
				RXTXUtil.sendData(serialPort,yjyZl);//发送指令(每次只能发排队号里的一个字符)
			}
		} catch (Exception e) {
			System.out.println("排队号发送错误");
			e.printStackTrace();
		} 
		finally {
			RXTXUtil.closeSerialPort(serialPort);
		}
	}
	
	/**
	 * 发送没有找到订单
	 */
	public static void sendNoOrder() {
		SerialPort serialPort = null;
		try {
			System.out.println("发送没有找到订单");
			String chuMoPingCom = LoadProperties.getChuMoPingCom();
			// 开启串口
			serialPort = RXTXUtil.openSerialPort(chuMoPingCom, 9600);
			int varNum = getVarNumBySf(NO_ORDER);
			String hexVarNum = HexadecimalUtil.get16Num(varNum);
			String hexLowVarNum=hexVarNum.length()<2?"0"+hexVarNum:hexVarNum;
			String djyZl=ADDRESS+FUN_CODE+LOC_HIGHT+"00"+VAR_HIGHT+hexLowVarNum;//待校验指令字符串
			String jym = CRCUtil.getCRC(djyZl);//获取校验码
			String yjyZl=djyZl+jym;//已校验指令
			RXTXUtil.sendData(serialPort,yjyZl);//发送指令(没有找到订单仅对应一条指令)
		} catch (Exception e) {
			System.out.println("没有找到订单发送错误");
			e.printStackTrace();
		} 
		finally {
			RXTXUtil.closeSerialPort(serialPort);
		}
	}
	
	public static void main(String[] args) {
		/*
		SerialPort serialPort = RXTXUtil.openSerialPort("COM7", 9600);
		String str0=HexadecimalUtil.get16Num(getVarNumBySf("鲁"));
		System.out.println("str0==="+str0);
		String str=ADDRESS+FUN_CODE+LOC_HIGHT+"00"+VAR_HIGHT+(str0.length()<2?"0"+str0:str0);
		System.out.println("str==="+str);
		String jym = CRCUtil.getCRC(str);
		System.out.println("jym==="+jym);
		str+=jym;
		System.out.println("str==="+str);
		RXTXUtil.sendData(serialPort,str);
		
		str="";
		str0=HexadecimalUtil.get16Num(getVarNumBySzZm("B"));
		System.out.println("str0==="+str0);
		str=ADDRESS+FUN_CODE+LOC_HIGHT+"01"+VAR_HIGHT+(str0.length()<2?"0"+str0:str0);
		jym = CRCUtil.getCRC(str);
		System.out.println("jym==="+jym);
		str+=jym;
		System.out.println("str==="+str);
		RXTXUtil.sendData(serialPort,str);
		*/
		sendCph("鲁B92228");
		//sendNoOrder();
	}

}

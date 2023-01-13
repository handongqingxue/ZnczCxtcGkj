package com.znczCxtcGkj.cmp;

import com.znczCxtcGkj.util.*;

import gnu.io.SerialPort;

public class ChuMoPingUtil {
	
	public static final String ADDRESS="01";
	public static final String FUN_CODE="06";
	//public static final String LOC_HIGHT="00";
	//public static final String VAR_HIGHT="00";
	public static final int SF_LOC_NUM=0;
	public static final int CP1_LOC_NUM=1;
	public static final int CP2_LOC_NUM=2;
	public static final int CP3_LOC_NUM=3;
	public static final int CP4_LOC_NUM=4;
	public static final int CP5_LOC_NUM=5;
	public static final int CP6_LOC_NUM=6;
	public static final int YEAR_LOC_NUM=7;
	public static final int MD_LOC_NUM=8;
	public static final int SN_LOC_NUM=9;
	public static final int PD_LOC_NUM=10;
	public static final int YEAR_START_LOC_NUM=2;
	public static final int YEAR_END_LOC_NUM=6;
	public static final int MD_START_LOC_NUM=6;
	public static final int MD_END_LOC_NUM=10;
	public static final int SN_START_LOC_NUM=10;
	public static final int SN_END_LOC_NUM=14;
	public static final String NO_ORDER_CP="无订单";
	public static final String NO_ORDER_DDH="DD000000000000";
	public static final String NO_ORDER_PDH="00";
	
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
		case NO_ORDER_CP:
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
				//String hexVarNum = HexadecimalUtil.get16Num(varNum);
				//String hexLowVarNum=hexVarNum.length()<2?"0"+hexVarNum:hexVarNum;
				
				int locNum=0;
				switch (i) {
				case 0:
					locNum=SF_LOC_NUM;
					break;
				case 1:
					locNum=CP1_LOC_NUM;
					break;
				case 2:
					locNum=CP2_LOC_NUM;
					break;
				case 3:
					locNum=CP3_LOC_NUM;
					break;
				case 4:
					locNum=CP4_LOC_NUM;
					break;
				case 5:
					locNum=CP5_LOC_NUM;
					break;
				case 6:
					locNum=CP6_LOC_NUM;
					break;
				}
				String djyZl=ADDRESS+FUN_CODE+convertZhiLingStr(locNum)+convertZhiLingStr(varNum);//待校验指令字符串
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
			
			int ddhYearNum = Integer.valueOf(ddh.substring(YEAR_START_LOC_NUM, YEAR_END_LOC_NUM));
			String ddhYearZl=convertZhiLingStr(ddhYearNum);
			System.out.println("ddhYearZl==="+ddhYearZl);
			String djyYearZl=ADDRESS+FUN_CODE+convertZhiLingStr(YEAR_LOC_NUM)+ddhYearZl;//年份待校验指令字符串
			String jymYear = CRCUtil.getCRC(djyYearZl);//获取年份校验码
			String yjyYearZl=djyYearZl+jymYear;//年份已校验指令
			RXTXUtil.sendData(serialPort,yjyYearZl);//发送年份指令(每次只能发年份里的一个字符)
			
			int ddhMdNum = Integer.valueOf(ddh.substring(MD_START_LOC_NUM, MD_END_LOC_NUM));
			String ddhMdZl=convertZhiLingStr(ddhMdNum);
			System.out.println("ddhMdZl==="+ddhMdZl);
			String djyMdZl=ADDRESS+FUN_CODE+convertZhiLingStr(MD_LOC_NUM)+ddhMdZl;//月日待校验指令字符串
			String jymMd = CRCUtil.getCRC(djyMdZl);//获取月日校验码
			String yjyMdZl=djyMdZl+jymMd;//月日已校验指令
			RXTXUtil.sendData(serialPort,yjyMdZl);//发送月日指令(每次只能发月日里的一个字符)
			
			int ddhSnNum = Integer.valueOf(ddh.substring(SN_START_LOC_NUM, SN_END_LOC_NUM));
			String ddhSnZl=convertZhiLingStr(ddhSnNum);
			System.out.println("ddhSnZl==="+ddhSnZl);
			String djySnZl=ADDRESS+FUN_CODE+convertZhiLingStr(SN_LOC_NUM)+ddhSnZl;//序号待校验指令字符串
			String jymSn = CRCUtil.getCRC(djySnZl);//获取序号校验码
			String yjySnZl=djySnZl+jymSn;//序号已校验指令
			RXTXUtil.sendData(serialPort,yjySnZl);//发送序号指令(每次只能发序号里的一个字符)
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

			int pdhNum = Integer.valueOf(pdh);
			String pdhZl=convertZhiLingStr(pdhNum);
			System.out.println("pdhZl==="+pdhZl);
			String djyPdhZl=ADDRESS+FUN_CODE+convertZhiLingStr(PD_LOC_NUM)+pdhZl;//排队号待校验指令字符串
			String jymPdh = CRCUtil.getCRC(djyPdhZl);//获取排队号校验码
			String yjyPdhZl=djyPdhZl+jymPdh;//排队号已校验指令
			RXTXUtil.sendData(serialPort,yjyPdhZl);//发送排队号指令(每次只能发排队号里的一个字符)
		} catch (Exception e) {
			System.out.println("排队号发送错误");
			e.printStackTrace();
		} 
		finally {
			RXTXUtil.closeSerialPort(serialPort);
		}
	}
	
	/**
	 * 发送没有找到订单车牌号信息
	 */
	public static void sendNoOrderCp() {
		SerialPort serialPort = null;
		try {
			System.out.println("发送没有找到订单");
			String chuMoPingCom = LoadProperties.getChuMoPingCom();
			// 开启串口
			serialPort = RXTXUtil.openSerialPort(chuMoPingCom, 9600);
			int varNum = getVarNumBySf(NO_ORDER_CP);
			String djyZl=ADDRESS+FUN_CODE+convertZhiLingStr(SF_LOC_NUM)+convertZhiLingStr(varNum);//待校验指令字符串
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
	
	/**
	 * 发送没有找到订单信息
	 */
	public static void sendNoOrder() {
		sendNoOrderCp();
		sendDdh(NO_ORDER_DDH);
		sendPdh(NO_ORDER_PDH);
	}
	
	/**
	 * 转换指令字符串
	 * @param num
	 * @return
	 */
	public static String convertZhiLingStr(int num) {
		String hexStr = null;
		String hexStrPre = null;
		String hexNumStr = HexadecimalUtil.get16Num(num);
		if(hexNumStr.length()==1)
			hexStrPre = "000";
		else if(hexNumStr.length()==2)
			hexStrPre = "00";
		else if(hexNumStr.length()==3)
			hexStrPre = "0";
		else if(hexNumStr.length()==4)
			hexStrPre = "";
		hexStr=hexStrPre+hexNumStr;
		return hexStr.toUpperCase();
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
		
		//sendCph("鲁B92228");
		//sendDdh("DD202301130002");
		//sendPdh("02");
		sendNoOrder();
	}

}

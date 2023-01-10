package com.znczCxtcGkj.cmp;

import com.znczCxtcGkj.util.*;

import gnu.io.SerialPort;

public class ChuMoPingUtil {
	
	public static final String ADDRESS="01";
	public static final String FUN_CODE="06";
	public static final String LOC_HIGHT="00";
	public static final String VAR_HIGHT="00";
	public static final String NO_ORDER="�޶���";
	
	public static int getVarNumBySf(String sjc) {
		int varNum=0;
		switch (sjc) {
		case "��":
			varNum=1;
			break;
		case "��":
			varNum=2;
			break;
		case "��":
			varNum=3;
			break;
		case "��":
			varNum=4;
			break;
		case "��":
			varNum=5;
			break;
		case "��":
			varNum=6;
			break;
		case "��":
			varNum=7;
			break;
		case "��":
			varNum=8;
			break;
		case "��":
			varNum=9;
			break;
		case "��":
			varNum=10;
			break;
		case "��":
			varNum=11;
			break;
		case "��":
			varNum=12;
			break;
		case "��":
			varNum=13;
			break;
		case "��":
			varNum=14;
			break;
		case "³":
			varNum=15;
			break;
		case "ԥ":
			varNum=16;
			break;
		case "��":
			varNum=17;
			break;
		case "��":
			varNum=18;
			break;
		case "��":
			varNum=19;
			break;
		case "��":
			varNum=20;
			break;
		case "��":
			varNum=21;
			break;
		case "��":
			varNum=22;
			break;
		case "��":
			varNum=23;
			break;
		case "ǭ":
			varNum=24;
			break;
		case "��":
			varNum=25;
			break;
		case "��":
			varNum=26;
			break;
		case "��":
			varNum=27;
			break;
		case "��":
			varNum=28;
			break;
		case "��":
			varNum=29;
			break;
		case "��":
			varNum=30;
			break;
		case "��":
			varNum=31;
			break;
		case "̨":
			varNum=32;
			break;
		case "��":
			varNum=33;
			break;
		case "��":
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
	
	public static void main(String[] args) {
		SerialPort serialPort = RXTXUtil.openSerialPort("COM7", 9600);
		String str0=HexadecimalUtil.get16Num(getVarNumBySf("³"));
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
		
		str="";
		str0=HexadecimalUtil.get16Num(getVarNumBySzZm("B"));
		System.out.println("str0==="+str0);
		str=ADDRESS+FUN_CODE+LOC_HIGHT+"01"+VAR_HIGHT+(str0.length()<2?"0"+str0:str0);
		jym = CRCUtil.getCRC(str);
		System.out.println("jym==="+jym);
		str+=jym;
		System.out.println("str==="+str);
		RXTXUtil.sendData(serialPort,str);
		
		str="";
		str0=HexadecimalUtil.get16Num(getVarNumBySzZm("9"));
		System.out.println("str0==="+str0);
		str=ADDRESS+FUN_CODE+LOC_HIGHT+"02"+VAR_HIGHT+(str0.length()<2?"0"+str0:str0);
		jym = CRCUtil.getCRC(str);
		System.out.println("jym==="+jym);
		str+=jym;
		System.out.println("str==="+str);
		RXTXUtil.sendData(serialPort,str);
		
		str="";
		str0=HexadecimalUtil.get16Num(getVarNumBySzZm("0"));
		System.out.println("str0==="+str0);
		str=ADDRESS+FUN_CODE+LOC_HIGHT+"03"+VAR_HIGHT+(str0.length()<2?"0"+str0:str0);
		jym = CRCUtil.getCRC(str);
		System.out.println("jym==="+jym);
		str+=jym;
		System.out.println("str==="+str);
		RXTXUtil.sendData(serialPort,str);
		
		str="";
		str0=HexadecimalUtil.get16Num(getVarNumBySzZm("0"));
		System.out.println("str0==="+str0);
		str=ADDRESS+FUN_CODE+LOC_HIGHT+"04"+VAR_HIGHT+(str0.length()<2?"0"+str0:str0);
		jym = CRCUtil.getCRC(str);
		System.out.println("jym==="+jym);
		str+=jym;
		System.out.println("str==="+str);
		RXTXUtil.sendData(serialPort,str);
		
		str="";
		str0=HexadecimalUtil.get16Num(getVarNumBySzZm("8"));
		System.out.println("str0==="+str0);
		str=ADDRESS+FUN_CODE+LOC_HIGHT+"05"+VAR_HIGHT+(str0.length()<2?"0"+str0:str0);
		jym = CRCUtil.getCRC(str);
		System.out.println("jym==="+jym);
		str+=jym;
		System.out.println("str==="+str);
		RXTXUtil.sendData(serialPort,str);
	}

}

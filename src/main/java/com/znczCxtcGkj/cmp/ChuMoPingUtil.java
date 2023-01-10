package com.znczCxtcGkj.cmp;

import com.znczCxtcGkj.util.*;

import gnu.io.SerialPort;

public class ChuMoPingUtil {
	
	public static final String ADDRESS="01";
	public static final String FUN_CODE="06";
	public static final String LOC_HIGHT="00";
	public static final String VAR_HIGHT="00";
	public static final String NO_ORDER="ÎÞ¶©µ¥";
	
	public static int getVarNumBySf(String sjc) {
		int varNum=0;
		switch (sjc) {
		case "¾©":
			varNum=1;
			break;
		case "½ò":
			varNum=2;
			break;
		case "¼½":
			varNum=3;
			break;
		case "½ú":
			varNum=4;
			break;
		case "ÃÉ":
			varNum=5;
			break;
		case "ÁÉ":
			varNum=6;
			break;
		case "¼ª":
			varNum=7;
			break;
		case "ºÚ":
			varNum=8;
			break;
		case "»¦":
			varNum=9;
			break;
		case "ËÕ":
			varNum=10;
			break;
		case "Õã":
			varNum=11;
			break;
		case "Íî":
			varNum=12;
			break;
		case "Ãö":
			varNum=13;
			break;
		case "¸Ó":
			varNum=14;
			break;
		case "Â³":
			varNum=15;
			break;
		case "Ô¥":
			varNum=16;
			break;
		case "¶õ":
			varNum=17;
			break;
		case "Ïæ":
			varNum=18;
			break;
		case "ÔÁ":
			varNum=19;
			break;
		case "¹ð":
			varNum=20;
			break;
		case "Çí":
			varNum=21;
			break;
		case "Óå":
			varNum=22;
			break;
		case "´¨":
			varNum=23;
			break;
		case "Ç­":
			varNum=24;
			break;
		case "µá":
			varNum=25;
			break;
		case "²Ø":
			varNum=26;
			break;
		case "ÉÂ":
			varNum=27;
			break;
		case "¸Ê":
			varNum=28;
			break;
		case "Çà":
			varNum=29;
			break;
		case "Äþ":
			varNum=30;
			break;
		case "ÐÂ":
			varNum=31;
			break;
		case "Ì¨":
			varNum=32;
			break;
		case "¸Û":
			varNum=33;
			break;
		case "°Ä":
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
		String str0=HexadecimalUtil.get16Num(getVarNumBySf("Â³"));
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

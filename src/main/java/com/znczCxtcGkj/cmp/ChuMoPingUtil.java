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
	
	/**
	 * ���ͳ��ƺ�
	 * @param cph
	 */
	public static void sendCph(String cph) {
		SerialPort serialPort = null;
		try {
			System.out.println("���ͳ��ƺ�");
			String chuMoPingCom = LoadProperties.getChuMoPingCom();
			// ��������
			serialPort = RXTXUtil.openSerialPort(chuMoPingCom, 9600);
			for (int i = 0; i < cph.length(); i++) {
				char cphChar = cph.charAt(i);
				int varNum;
				if(i==0) {//�ж���ʡ��ƻ��Ǻ�������ֻ���ĸ,������Ӧ�ķ�����ñ���
					varNum = getVarNumBySf(cphChar+"");//��ȡ��һλʡ���ʮ���Ƶ�λ����
				}
				else {
					varNum = getVarNumBySzZm(cphChar+"");//��ȡ��һλ֮�����ĸ������ʮ���Ƶ�λ����
				}
				String hexVarNum = HexadecimalUtil.get16Num(varNum);
				String hexLowVarNum=hexVarNum.length()<2?"0"+hexVarNum:hexVarNum;
				String djyZl=ADDRESS+FUN_CODE+LOC_HIGHT+"0"+i+VAR_HIGHT+hexLowVarNum;//��У��ָ���ַ���
				String jym = CRCUtil.getCRC(djyZl);//��ȡУ����
				String yjyZl=djyZl+jym;//��У��ָ��
				RXTXUtil.sendData(serialPort,yjyZl);//����ָ��(ÿ��ֻ�ܷ����ƺ����һ���ַ�)
			}
		} catch (Exception e) {
			System.out.println("���ƺŷ��ʹ���");
			e.printStackTrace();
		} 
		finally {
			RXTXUtil.closeSerialPort(serialPort);
		}
	}
	
	/**
	 * ���Ͷ�����
	 * @param ddh
	 */
	public static void sendDdh(String ddh) {
		SerialPort serialPort = null;
		try {
			System.out.println("���Ͷ�����");
			String chuMoPingCom = LoadProperties.getChuMoPingCom();
			// ��������
			serialPort = RXTXUtil.openSerialPort(chuMoPingCom, 9600);
			for (int i = 0; i < ddh.length(); i++) {
				char ddhChar = ddh.charAt(i);
				int varNum = getVarNumBySzZm(ddhChar+"");
				String hexVarNum = HexadecimalUtil.get16Num(varNum);
				String hexLowVarNum=hexVarNum.length()<2?"0"+hexVarNum:hexVarNum;
				String djyZl=ADDRESS+FUN_CODE+LOC_HIGHT+"0"+i+VAR_HIGHT+hexLowVarNum;//��У��ָ���ַ���
				String jym = CRCUtil.getCRC(djyZl);//��ȡУ����
				String yjyZl=djyZl+jym;//��У��ָ��
				RXTXUtil.sendData(serialPort,yjyZl);//����ָ��(ÿ��ֻ�ܷ����ƺ����һ���ַ�)
			}
		} catch (Exception e) {
			System.out.println("�����ŷ��ʹ���");
			e.printStackTrace();
		} 
		finally {
			RXTXUtil.closeSerialPort(serialPort);
		}
	}
	
	/**
	 * �����ŶӺ�
	 * @param pdh
	 */
	public static void sendPdh(String pdh) {
		SerialPort serialPort = null;
		try {
			System.out.println("�����ŶӺ�");
			String chuMoPingCom = LoadProperties.getChuMoPingCom();
			// ��������
			serialPort = RXTXUtil.openSerialPort(chuMoPingCom, 9600);
			for (int i = 0; i < pdh.length(); i++) {
				char pdhChar = pdh.charAt(i);
				int varNum = getVarNumBySzZm(pdhChar+"");
				String hexVarNum = HexadecimalUtil.get16Num(varNum);
				String hexLowVarNum=hexVarNum.length()<2?"0"+hexVarNum:hexVarNum;
				String djyZl=ADDRESS+FUN_CODE+LOC_HIGHT+"0"+i+VAR_HIGHT+hexLowVarNum;//��У��ָ���ַ���
				String jym = CRCUtil.getCRC(djyZl);//��ȡУ����
				String yjyZl=djyZl+jym;//��У��ָ��
				RXTXUtil.sendData(serialPort,yjyZl);//����ָ��(ÿ��ֻ�ܷ��ŶӺ����һ���ַ�)
			}
		} catch (Exception e) {
			System.out.println("�ŶӺŷ��ʹ���");
			e.printStackTrace();
		} 
		finally {
			RXTXUtil.closeSerialPort(serialPort);
		}
	}
	
	/**
	 * ����û���ҵ�����
	 */
	public static void sendNoOrder() {
		SerialPort serialPort = null;
		try {
			System.out.println("����û���ҵ�����");
			String chuMoPingCom = LoadProperties.getChuMoPingCom();
			// ��������
			serialPort = RXTXUtil.openSerialPort(chuMoPingCom, 9600);
			int varNum = getVarNumBySf(NO_ORDER);
			String hexVarNum = HexadecimalUtil.get16Num(varNum);
			String hexLowVarNum=hexVarNum.length()<2?"0"+hexVarNum:hexVarNum;
			String djyZl=ADDRESS+FUN_CODE+LOC_HIGHT+"00"+VAR_HIGHT+hexLowVarNum;//��У��ָ���ַ���
			String jym = CRCUtil.getCRC(djyZl);//��ȡУ����
			String yjyZl=djyZl+jym;//��У��ָ��
			RXTXUtil.sendData(serialPort,yjyZl);//����ָ��(û���ҵ���������Ӧһ��ָ��)
		} catch (Exception e) {
			System.out.println("û���ҵ��������ʹ���");
			e.printStackTrace();
		} 
		finally {
			RXTXUtil.closeSerialPort(serialPort);
		}
	}
	
	public static void main(String[] args) {
		/*
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
		*/
		sendCph("³B92228");
		//sendNoOrder();
	}

}

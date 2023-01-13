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
	public static final String NO_ORDER_CP="�޶���";
	public static final String NO_ORDER_DDH="DD000000000000";
	public static final String NO_ORDER_PDH="00";
	
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
				String djyZl=ADDRESS+FUN_CODE+convertZhiLingStr(locNum)+convertZhiLingStr(varNum);//��У��ָ���ַ���
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
			
			int ddhYearNum = Integer.valueOf(ddh.substring(YEAR_START_LOC_NUM, YEAR_END_LOC_NUM));
			String ddhYearZl=convertZhiLingStr(ddhYearNum);
			System.out.println("ddhYearZl==="+ddhYearZl);
			String djyYearZl=ADDRESS+FUN_CODE+convertZhiLingStr(YEAR_LOC_NUM)+ddhYearZl;//��ݴ�У��ָ���ַ���
			String jymYear = CRCUtil.getCRC(djyYearZl);//��ȡ���У����
			String yjyYearZl=djyYearZl+jymYear;//�����У��ָ��
			RXTXUtil.sendData(serialPort,yjyYearZl);//�������ָ��(ÿ��ֻ�ܷ�������һ���ַ�)
			
			int ddhMdNum = Integer.valueOf(ddh.substring(MD_START_LOC_NUM, MD_END_LOC_NUM));
			String ddhMdZl=convertZhiLingStr(ddhMdNum);
			System.out.println("ddhMdZl==="+ddhMdZl);
			String djyMdZl=ADDRESS+FUN_CODE+convertZhiLingStr(MD_LOC_NUM)+ddhMdZl;//���մ�У��ָ���ַ���
			String jymMd = CRCUtil.getCRC(djyMdZl);//��ȡ����У����
			String yjyMdZl=djyMdZl+jymMd;//������У��ָ��
			RXTXUtil.sendData(serialPort,yjyMdZl);//��������ָ��(ÿ��ֻ�ܷ��������һ���ַ�)
			
			int ddhSnNum = Integer.valueOf(ddh.substring(SN_START_LOC_NUM, SN_END_LOC_NUM));
			String ddhSnZl=convertZhiLingStr(ddhSnNum);
			System.out.println("ddhSnZl==="+ddhSnZl);
			String djySnZl=ADDRESS+FUN_CODE+convertZhiLingStr(SN_LOC_NUM)+ddhSnZl;//��Ŵ�У��ָ���ַ���
			String jymSn = CRCUtil.getCRC(djySnZl);//��ȡ���У����
			String yjySnZl=djySnZl+jymSn;//�����У��ָ��
			RXTXUtil.sendData(serialPort,yjySnZl);//�������ָ��(ÿ��ֻ�ܷ�������һ���ַ�)
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

			int pdhNum = Integer.valueOf(pdh);
			String pdhZl=convertZhiLingStr(pdhNum);
			System.out.println("pdhZl==="+pdhZl);
			String djyPdhZl=ADDRESS+FUN_CODE+convertZhiLingStr(PD_LOC_NUM)+pdhZl;//�ŶӺŴ�У��ָ���ַ���
			String jymPdh = CRCUtil.getCRC(djyPdhZl);//��ȡ�ŶӺ�У����
			String yjyPdhZl=djyPdhZl+jymPdh;//�ŶӺ���У��ָ��
			RXTXUtil.sendData(serialPort,yjyPdhZl);//�����ŶӺ�ָ��(ÿ��ֻ�ܷ��ŶӺ����һ���ַ�)
		} catch (Exception e) {
			System.out.println("�ŶӺŷ��ʹ���");
			e.printStackTrace();
		} 
		finally {
			RXTXUtil.closeSerialPort(serialPort);
		}
	}
	
	/**
	 * ����û���ҵ��������ƺ���Ϣ
	 */
	public static void sendNoOrderCp() {
		SerialPort serialPort = null;
		try {
			System.out.println("����û���ҵ�����");
			String chuMoPingCom = LoadProperties.getChuMoPingCom();
			// ��������
			serialPort = RXTXUtil.openSerialPort(chuMoPingCom, 9600);
			int varNum = getVarNumBySf(NO_ORDER_CP);
			String djyZl=ADDRESS+FUN_CODE+convertZhiLingStr(SF_LOC_NUM)+convertZhiLingStr(varNum);//��У��ָ���ַ���
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
	
	/**
	 * ����û���ҵ�������Ϣ
	 */
	public static void sendNoOrder() {
		sendNoOrderCp();
		sendDdh(NO_ORDER_DDH);
		sendPdh(NO_ORDER_PDH);
	}
	
	/**
	 * ת��ָ���ַ���
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
		
		//sendCph("³B92228");
		//sendDdh("DD202301130002");
		//sendPdh("02");
		sendNoOrder();
	}

}

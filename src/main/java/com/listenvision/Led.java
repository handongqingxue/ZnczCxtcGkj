package com.listenvision;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

public class Led {
//	static 
//	{
//		System.loadLibrary("lv_led");  
//	}
	
	static 
	{
		System.load("c:\\weightdll\\led\\lv_led.dll");  
	}
	
//	static {
//		led INSTANCE = (led) Native.loadLibrary("D:\\weightdll\\led\\lv_led",
//			 led.class);
//	}
	/*
	 *****************************************************************************************************************
	 *													  LED SDK 6.0
	 *
	 *													       ��ΰ(HuWei)
	 *	
	 *
	 *										(C) Copyright 2010 - 2015, LISTEN VISION
	 *												   All Rights Reserved
	 *														  
	 *****************************************************************************************************************
	 */
	
	
	
	
	/********************************************************************************************
	 *	CreateProgram				������Ŀ���󣬳ɹ����ؽ�Ŀ������
	 *
	 *	����˵��
	 *				LedWidth		���Ŀ��
	 *				LedHeight		���ĸ߶�
	 *				ColorType		������ɫ 1.��ɫ  2.˫��ɫ  3.�߲�  4.ȫ��
	 *	����ֵ
	 *				0				������Ŀ����ʧ��
	 *				��0				������Ŀ����ɹ�
	 ********************************************************************************************/
	public native static int CreateProgram(int LedWidth,int LedHeight,int ColorType);
	
	/*********************************************************************************************
	 *	AddProgram					���һ����Ŀ
	 *	
	 *	����˵��
	 *				hProgram		��Ŀ������
	 *				ProgramNo		��Ŀ��
	 *				ProgramTime		��Ŀ����ʱ�� 0.��Ŀ����ʱ��  ��0.ָ������ʱ��
	 *				LoopCount		ѭ�����Ŵ���
	 *	����ֵ
	 *				0				�ɹ�
	 *				��0				ʧ��	
	 ********************************************************************************************/
	public native static int AddProgram(int hProgram,int ProgramNo,int ProgramTime,int LoopCount);
	
	/*********************************************************************************************
	 *	LV_AddImageTextArea				���һ��ͼ������
	 *	
	 *	����˵��
	 *				hProgram			��Ŀ������
	 *				ProgramNo			��Ŀ��
	 *				AreaNo				�����
	 *				l					�������ϽǺ�����
	 *				t					�������Ͻ�������
	 *				w					������
	 *				h					����߶�
	 *				IsBackgroundArea	�Ƿ�Ϊ��������0.ǰ������Ĭ�ϣ� 1.������
	 *	����ֵ
	 *				0					�ɹ�
	 *				��0					ʧ��	
	 ********************************************************************************************/
	public native static int AddImageTextArea(int hProgram,int ProgramNo,int AreaNo,int l,int t,int w,int h,int IsForegroundArea);
	
	/*********************************************************************************************
	 * AddFileToImageTextArea				���һ���ļ���ͼ����
	 *	
	 *	����˵��
	 *				hProgram				��Ŀ������
	 *				ProgramNo				��Ŀ��
	 *				AreaNo					�����
	 *				FilePath				�ļ�·����֧�ֵ��ļ������� txt  rtf  bmp  gif  png  jpg jpeg tiff
	 *				InStyle					�볡�ؼ�
	 *				nSpeed					�ؼ��ٶ�
	 *				DelayTime				ͣ��ʱ��
	 *	����ֵ
	 *				0						�ɹ�
	 *				��0						ʧ��	
	 ********************************************************************************************/
	public native static int AddFileToImageTextArea(int hProgram,int ProgramNo,int AreaNo,String FilePath,int InStyle,int nSpeed,int DelayTime);
	
	/*********************************************************************************************
	 * AddMultiLineTextToImageTextArea		���һ�������ı���ͼ����
	 *	
	 *	����˵��
	 *				hProgram				��Ŀ������
	 *				ProgramNo				��Ŀ��
	 *				AreaNo					�����
	 *				AddType					��ӵ�����  0.Ϊ�ַ���  1.�ļ���ֻ֧��txt��rtf�ļ���
	 *				AddStr					AddTypeΪ0��Ϊ�ַ�������,AddTypeΪ1��Ϊ�ļ�·��
	 *				FontName				������	
	 *				FontSize				�����С
	 *				FontColor				������ɫ   0xff ��ɫ  0xff00 ��ɫ  0xffff��ɫ
	 *				FontBold				�Ƿ�Ӵ� 0���Ӵ� 1�Ӵ�
	 *				FontItalic				�Ƿ���б��  0 ��б 1б
	 *				FontUnderline			�Ƿ��»��� 0�����»��� 1���»���
	 *				InStyle					�볡�ؼ�
	 *				nSpeed					�ؼ��ٶ�
	 *				DelayTime				ͣ��ʱ��
	 *				nAlignment				���Ҿ��ж��뷽ʽ
	 *				IsVCenter				�Ƿ�ֱ����
	 *	����ֵ
	 *				0						�ɹ�
	 *				��0						ʧ��
	 ********************************************************************************************/
	public native static int AddMultiLineTextToImageTextArea(int hProgram,int ProgramNo,int AreaNo,int AddType,String AddStr,String FontName,int FontSize,int FontColor,int FontBold,int FontItalic,int FontUnderline,int InStyle,int nSpeed,int DelayTime,int nAlignment,int IsVCenter);
	
	/*********************************************************************************************
	 * AddStaticTextToImageTextArea			���һ����ֹ�ı���ͼ����
	 *	
	 *	����˵��
	 *				hProgram				��Ŀ������
	 *				ProgramNo				��Ŀ��
	 *				AreaNo					�����
	 *				AddType					��ӵ�����  0.Ϊ�ַ���  1.�ļ���ֻ֧��txt��rtf�ļ���
	 *				AddStr					AddTypeΪ0��Ϊ�ַ�������,AddTypeΪ1��Ϊ�ļ�·��
	 *				FontName				������	
	 *				FontSize				�����С
	 *				FontColor				������ɫ   0xff ��ɫ  0xff00 ��ɫ  0xffff��ɫ
	 *				FontBold				�Ƿ�Ӵ� 0���Ӵ� 1�Ӵ�
	 *				FontItalic				�Ƿ���б��  0 ��б 1б
	 *				FontUnderline			�Ƿ��»��� 0�����»��� 1���»���
	 *				DelayTime				ͣ��ʱ��
	 *				nAlignment				���Ҿ��ж��뷽ʽ
	 *				IsVCenter				�Ƿ�ֱ����
	 *	����ֵ
	 *				0						�ɹ�
	 *				��0						ʧ��
	 ********************************************************************************************/
	public native static int AddStaticTextToImageTextArea(int hProgram,int ProgramNo,int AreaNo,int AddType,String AddStr,String FontName,int FontSize,int FontColor,int FontBold,int FontItalic,int FontUnderline,int DelayTime,int nAlignment,int IsVCenter);
	
	/*********************************************************************************************
	 * AddSinglelineTextToImageTextArea		���һ�������ı���ͼ����
	 *	
	 *	����˵��
	 *				hProgram				��Ŀ������
	 *				ProgramNo				��Ŀ��
	 *				AreaNo					�����
	 *				AddType					��ӵ�����  0.Ϊ�ַ���  1.�ļ���ֻ֧��txt��rtf�ļ���
	 *				AddStr					AddTypeΪ0��Ϊ�ַ�������,AddTypeΪ1��Ϊ�ļ�·��
	 *				FontName				������	
	 *				FontSize				�����С
	 *				FontColor				������ɫ   0xff ��ɫ  0xff00 ��ɫ  0xffff��ɫ
	 *				FontBold				�Ƿ�Ӵ� 0���Ӵ� 1�Ӵ�
	 *				FontItalic				�Ƿ���б��  0 ��б 1б
	 *				FontUnderline			�Ƿ��»��� 0�����»��� 1���»���
	 *				InStyle					�볡�ؼ�
	 *				nSpeed					�ؼ��ٶ�
	 *				DelayTime				ͣ��ʱ��
	 *	����ֵ
	 *				0						�ɹ�
	 *				��0						ʧ��
	 ********************************************************************************************/
	public native static int AddSinglelineTextToImageTextArea(int hProgram,int ProgramNo,int AreaNo,int AddType,String AddStr,String FontName,int FontSize,int FontColor,int FontBold,int FontItalic,int FontUnderline,int InStyle,int nSpeed,int DelayTime);
	
	/*********************************************************************************************
	 * AddSinglelineTextToImageTextArea		���һ�������ı���ͼ����
	 *	
	 *	����˵��
	 *				hProgram				��Ŀ������
	 *				ProgramNo				��Ŀ��
	 *				AreaNo					�����
	 *				l						�������ϽǺ�����
	 *				t						�������Ͻ�������
	 *				w						������
	 *				h						����߶�
	 *				FontName				������	
	 *				FontSize				�����С
	 *				FontColor				������ɫ   0xff ��ɫ  0xff00 ��ɫ  0xffff��ɫ
	 *				FontBold				�Ƿ�Ӵ� 0���Ӵ� 1�Ӵ�
	 *				FontItalic				�Ƿ���б��  0 ��б 1б
	 *				FontUnderline			�Ƿ��»��� 0�����»��� 1���»���
	 * 				IsYear					�Ƿ���ʾ�� 1Ϊ��ʾ 0����ʾ ��ͬ
	 * 				IsWeek					�Ƿ���ʾ����	
	 *				IsMonth					�Ƿ���ʾ��
	 *				IsDay					�Ƿ���ʾ��
	 *				IsHour					�Ƿ���ʾʱ
	 *				IsMinute				�Ƿ���ʾ��
	 *				IsSecond				�Ƿ���ʾ��
	 *				DateFormat				���ڸ�ʽ 0.YYYY��MM��DD��  1.YY��MM��DD��  2.MM/DD/YYYY  3.YYYY/MM/DD  4.YYYY-MM-DD  5.YYYY.MM.DD  6.MM.DD.YYYY  7.DD.MM.YYYY
	 *				DateColor				����������ɫ0xff ��ɫ  0xff00 ��ɫ  0xffff��ɫ
	 *				WeekFormat				���ڸ�ʽ 0.����X  1.Monday  2.Mon.
	 *				WeekColor				����������ɫ0xff ��ɫ  0xff00 ��ɫ  0xffff��ɫ
	 *				TimeFormat				ʱ���ʽ 0.HHʱmm��ss��  1.HH�rmm��ss��  2.HH:mm:ss  3.���� HH:mm:ss  4.AM HH:mm:ss  5.HH:mm:ss ����  6.HH:mm:ss AM
	 *				TimeColor				ʱ��������ɫ0xff ��ɫ  0xff00 ��ɫ  0xffff��ɫ
	 *	����ֵ
	 *				0						�ɹ�
	 *				��0						ʧ��
	 ********************************************************************************************/
	public native static int AddDigitalClockArea(int hProgram,int ProgramNo,int AreaNo,int l,int t,int w,int h,String FontName,int FontSize,int FontColor,int FontBold,int FontItalic,int FontUnderline,int IsYear,int IsWeek,int IsMonth,int IsDay,int IsHour,int IsMinute,int IsSecond,int DateFormat,int DateColor,int WeekFormat,int WeekColor,int TimeFormat,int TimeColor);
	
	/*********************************************************************************************
	 *	DeleteProgram						���ٽ�Ŀ����(ע�⣺����˽�Ŀ������ʹ�ã�����ô˺������٣����������ڴ�й¶)
	 *	
	 *	����˵��
	 *				hProgram				��Ŀ������
	 ********************************************************************************************/
	public native static void DeleteProgram(int hProgram);
	
	/*********************************************************************************************
	 *	NetWorkSend							���ͽ�Ŀ���˷���Ϊһ��һ����
	 *	
	 *	����˵��
	 *				IpStr					LED��IP
	 *				hProgram				��Ŀ������
	 *	����ֵ
	 *				0						�ɹ�
	 *				��0						ʧ��
	 ********************************************************************************************/
	public native static int NetWorkSend(String IpStr,int hProgram);
	
	/*********************************************************************************************
	 *	SetBasicInfo						���û�������
	 *	
	 *	����˵��
	 *				IpStr					LED����IP
	 *				ColorType				������ɫ 1.��ɫ  2.˫��ɫ  3.�߲�  4.ȫ��
	 *				LedWidth				���Ŀ�ȵ���
	 *				LedHeight				���ĸ߶ȵ���
	 *	����ֵ
	 *				0						�ɹ�
	 *				��0						ʧ��
	 ********************************************************************************************/
	public native static int SetBasicInfo(String IpStr,int ColorType,int LedWidth,int LedHeight);
	
	/*********************************************************************************************
	 *	SetOEDA								����OE DA
	 *	
	 *	����˵��
	 *				IpStr					LED����IP
	 *				Oe						OE  0.����Ч  1.����Ч
	 *				Da						DA  0.������  1.������
	 *	����ֵ
	 *				0						�ɹ�
	 *				��0						ʧ��
	 ********************************************************************************************/
	public native static int SetOEDA(String IpStr,int Oe,int Da);
	
	/*********************************************************************************************
	 *	AdjustTime							Уʱ
	 *	
	 *	����˵��
	 *				IpStr					LED����IP
	 *	����ֵ
	 *				0						�ɹ�
	 *				��0						ʧ��
	 ********************************************************************************************/
	public native static int AdjustTime(String IpStr);
	
	/*********************************************************************************************
	 *	PowerOnOff							������
	 *	
	 *	����˵��
	 *				IpStr					LED����IP
	 *				OnOff					����ֵ  0.����  1.����
	 *	����ֵ
	 *				0						�ɹ�
	 *				��0						ʧ��
	 ********************************************************************************************/
	public native static int PowerOnOff(String IpStr,int OnOff);
	
	/*********************************************************************************************
	 *	TimePowerOnOff						��ʱ������
	 *	
	 *	����˵��
	 *				IpStr					LED����IP
	 *				StartHour				��ʼСʱ
	 *				StartMinute				��ʼ����
	 *				EndHour					����Сʱ
	 *				EndMinute				��������
	 *	����ֵ
	 *				0						�ɹ�
	 *				��0						ʧ��
	 ********************************************************************************************/
	public native static int TimePowerOnOff(String IpStr,int StartHour,int StartMinute,int EndHour,int EndMinute);
	
	/*********************************************************************************************
	 *	SetBrightness						��������
	 *	
	 *	����˵��
	 *				IpStr					LED����IP
	 *				BrightnessValue			����ֵ 0~15
	 *	����ֵ
	 *				0						�ɹ�
	 *				��0						ʧ��	
	 ********************************************************************************************/
	public native static int SetBrightness(String IpStr,int BrightnessValue);
	
	/*********************************************************************************************
	 *	LedTest								LED����
	 *	
	 *	����˵��
	 *				IpStr					LED����IP
	 *				TestValue				����ֵ
	 *	����ֵ
	 *				0						�ɹ�
	 *				��0						ʧ��
	 ********************************************************************************************/
	public native static int LedTest(String IpStr,int TestValue);
	
	/*********************************************************************************************
	 *	SetLedCommunicationParameter		����LED����������Ϣ
	 *	
	 *	����˵��
	 *				IpStr					LED����IP
	 *				NewIp					LED������IP
	 *				NewNetMask				LED��������������
	 *				NewGateway				LED����������
	 *				NewMac					LED������mac��ַ
	 *	����ֵ
	 *				0						�ɹ�
	 *				��0						ʧ��
	 ********************************************************************************************/
	public native static int SetLedCommunicationParameter(String IpStr,String NewIp,String NewNetMask,String NewGateway,String NewMac);
	
	
	

}

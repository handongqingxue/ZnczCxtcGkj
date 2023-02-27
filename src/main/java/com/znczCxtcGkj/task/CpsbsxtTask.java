package com.znczCxtcGkj.task;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.znczCxtcGkj.cpsbsxt.*;
import com.znczCxtcGkj.cpsbsxt.HCNetSDK.NET_DVR_DEVICEINFO_V30;
import com.znczCxtcGkj.util.LoadProperties;

//DS-TCG205-B型号
public class CpsbsxtTask extends Thread {
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	static FMSGCallBack fMSFCallBack;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean initSuc = hCNetSDK.NET_DVR_Init();
		
		System.out.println("initSuc: " + initSuc);
		if (initSuc != true){
			System.out.println("初始化失败");
		}
		initSuc = hCNetSDK.NET_DVR_SetLogToFile(3,"d:sdklog",false);
		
		//  注册回调
	 	//FMSGCallBack fMSFCallBack = new FMSGCallBack();  //报警回调函数实现
	 	fMSFCallBack = new FMSGCallBack();  //报警回调函数实现
		Pointer pUser = null;
		hCNetSDK.NET_DVR_SetDVRMessageCallBack_V30(fMSFCallBack, pUser );
		
		//注册1
        String  jinChangDeviceIP = LoadProperties.getHikvisionJinChangIP();//设备ip地址
        System.out.println("jinChangDeviceIP==="+jinChangDeviceIP);
        //String  jinChangDeviceIP = "192.168.1.11";
        int jinChangPort = Integer.parseInt(LoadProperties.getHikvisionJinChangPort());
        //int jinChangPort = 8000;
        String jinChangUserName =LoadProperties.getHikvisionJinChangUserName();
        //String jinChangUserName ="admin";
        String jinChangPassword = LoadProperties.getHikvisionJinChangPassword();
        //String jinChangPassword = "lanfan2022";
        NET_DVR_DEVICEINFO_V30 jinChangDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
        NativeLong lUserID = hCNetSDK.NET_DVR_Login_V30(jinChangDeviceIP, (short) jinChangPort, jinChangUserName, jinChangPassword, jinChangDeviceInfo);

        long userID = lUserID.longValue();
        if (userID == -1){
           System.out.println("注册失败，ip为： " + jinChangDeviceIP);
           return;
        }
        System.out.println(jinChangDeviceIP + "  userID: " + userID);
        
        NativeLong jinChangAlarmHandle = hCNetSDK.NET_DVR_SetupAlarmChan_V30(lUserID);
        if (jinChangAlarmHandle.intValue() == -1){
        	System.out.println(jinChangDeviceInfo + "布防失败！");
            return;
        }
        
        /*
        //注册2
	    String  erJianDeviceIP = LoadProperties.getHikvisionErJianIP();//设备ip地址
	    int erJianPort = Integer.parseInt(LoadProperties.getHikvisionErJianPort());
	    String erJianUserName =LoadProperties.getHikvisionErJianUserName();
	    String erJianPassword = LoadProperties.getHikvisionErJianPassword();
	    NET_DVR_DEVICEINFO_V30 erJianDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
	    NativeLong lUserID2 = hCNetSDK.NET_DVR_Login_V30(erJianDeviceIP, (short) erJianPort, erJianUserName, erJianPassword, erJianDeviceInfo);

        long userID2 = lUserID.longValue();
        if (userID2 == -1){
        	System.out.println("注册失败，ip为： " + erJianDeviceIP);
            return;
        }
        System.out.println(erJianDeviceIP + "  userID2: " + userID2);

        NativeLong erJianAlarmHandle = hCNetSDK.NET_DVR_SetupAlarmChan_V30(lUserID2);
        if (erJianAlarmHandle.intValue() == -1){
        	System.out.println(erJianDeviceIP + "布防失败！");
            return;
        }
        */
	}
}

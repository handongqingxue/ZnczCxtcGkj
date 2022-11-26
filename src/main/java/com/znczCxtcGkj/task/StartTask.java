package com.znczCxtcGkj.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.znczCxtcGkj.socket.*;
import com.znczCxtcGkj.util.*;

public class StartTask {

	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) {
		int currentPlaceFlag = LoadProperties.getCurrentPlaceFlag();
		System.out.println("当前位置标识==="+currentPlaceFlag);
		switch (currentPlaceFlag) {
		case Constant.MEN_GANG:
			CallNumberTask ct=new CallNumberTask();
			ct.start();
			break;
		case Constant.YI_HAO_BANG_FANG:
			break;
		}
		
		ClientSocket cs = new ClientSocket();
		cs.connectServer();
		
		// 其他线程启动
		while (true) {
			// main程序一直运行
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!cs.isServerOpend()) {
				System.out.println("与服务器端通讯断开了，正在重新建立连接,时间:"+sdf.format(new Date()));
				cs.connectServer();
			}
		}
	}
}

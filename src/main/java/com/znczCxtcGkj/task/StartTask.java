package com.znczCxtcGkj.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.znczCxtcGkj.socket.*;
import com.znczCxtcGkj.util.*;

public class StartTask {

	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) {
		int currentPlaceFlag = LoadProperties.getCurrentPlaceFlag();
		System.out.println("��ǰλ�ñ�ʶ==="+currentPlaceFlag);
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
		
		// �����߳�����
		while (true) {
			// main����һֱ����
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!cs.isServerOpend()) {
				System.out.println("���������ͨѶ�Ͽ��ˣ��������½�������,ʱ��:"+sdf.format(new Date()));
				cs.connectServer();
			}
		}
	}
}

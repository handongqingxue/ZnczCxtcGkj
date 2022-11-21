package com.znczCxtcGkj.util;

import org.json.JSONObject;

import com.znczCxtcGkj.cpsbsxt.Car;
import com.znczCxtcGkj.entity.*;
import com.znczCxtcGkj.util.*;
import com.znczCxtcGkj.yz.*;
import com.znczCxtcGkj.jdq.*;

/**
 * ��������1�Ű�����������
 * */
public class BangFang1Util {
	
	/**
	 * ����һ���ά��ʶ�𶩵���Ϣ
	 * @param car
	 */
	public static void updateYJEWMSBDDXX(Car car) {

		System.out.println("��ѯ����״̬Ϊһ���ɨ��Ķ���");
		JSONObject resultJO=APIUtil.getDingDanByCphZts(car.getsLicense(),DingDanZhuangTai.YI_JIAN_DAI_SAO_MA_TEXT);
		if("ok".equals(resultJO.getString("status"))) {
        	System.out.println("���ڸö���");
        	System.out.println("�ı䶩��״̬Ϊһ����ϰ�");
        	Integer bfh = LoadProperties.getBangFangHao();
			JSONObject ddJO=resultJO.getJSONObject("dingDan");
        	DingDan dd=new DingDan();
        	dd.setId(ddJO.getLong("id"));
        	dd.setDdztMc(DingDanZhuangTai.YI_JIAN_DAI_SHANG_BANG_TEXT);
        	dd.setYjbfh(bfh);
        	APIUtil.editDingDan(dd);
        	
        	//��������:���г���ʶ��
        	System.out.println("��������:���г���ʶ��");
    		//YinZhuUtil.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
		}
        else {
        	System.out.println("message==="+resultJO.getString("message"));
        	System.out.println("����������û���ҵ�ƥ�䶩��");
        	/*
        	 * ��δ�����ʱ���ε����ȵ��ֳ��ٴ�
    		YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
			Thread.sleep(3000);
    		YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
        	 */
        }
	}
	
	/**
	 * ����һ�쳵��ʶ�𶩵���Ϣ
	 * @param car
	 */
	public static void updateYJCPSBDDXX(Car car) {
		try {
			System.out.println("��ѯ����״̬Ϊһ����ϰ��Ķ���");
			JSONObject resultJO=APIUtil.getDingDanByCphZts(car.getsLicense(),DingDanZhuangTai.YI_JIAN_DAI_SHANG_BANG_TEXT);
	        if("ok".equals(resultJO.getString("status"))) {
	        	System.out.println("���ڸö���");
	        	System.out.println("������������״̬��֤�Ƿ������������");
	        	Integer bfh = LoadProperties.getBangFangHao();
	        	JSONObject ddExistResult = APIUtil.checkDingDanIfExistByZt(bfh,0,DingDanZhuangTai.YI_JIAN_ZHONG_TEXT,DingDan.DAI_SHANG_BANG,DingDan.DAI_SHANG_BANG);
	        	if("ok".equals(ddExistResult.getString("status"))) {
	            	System.out.println("������������������״̬������������");
	        	}
	        	else {
	            	System.out.println("�����̵���");
		    		JdqZlUtil.openBangFangJdq();
		        	System.out.println("̧���ϰ���բ");
		        	JdqBf1Util.openShangBangDz();
		        	
		        	System.out.println("�ı䶩��״̬Ϊһ����");
					JSONObject ddJO=resultJO.getJSONObject("dingDan");
		        	DingDan dd=new DingDan();
		        	dd.setId(ddJO.getLong("id"));
		        	dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
		        	dd.setYjzt(DingDan.DAI_SHANG_BANG);
		        	dd.setYjbfh(bfh);
		        	APIUtil.editDingDan(dd);
		        	
		        	System.out.println("��������:���ϰ��ѳ���ͣ���ڵذ��м�");
		    		//YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
		        	
		    		//checkYJSBHWGSState();
	        	}
	        }
	        else {
	        	System.out.println("message==="+resultJO.getString("message"));
	        	System.out.println("����������û���ҵ�ƥ�䶩��");
	    		//YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
				Thread.sleep(3000);
	    		//YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
	        }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

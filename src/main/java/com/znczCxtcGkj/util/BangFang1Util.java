package com.znczCxtcGkj.util;

import org.json.JSONObject;

import com.znczCxtcGkj.cpsbsxt.Car;
import com.znczCxtcGkj.entity.*;
import com.znczCxtcGkj.util.*;
import com.znczCxtcGkj.yz.*;
import com.znczCxtcGkj.jdq.*;
import com.znczCxtcGkj.task.DiBangTask3124;

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
		        	
		    		checkYJSBHWGSState();
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
	
	/**
	 * ���һ���ϰ������դ״̬
	 */
	public static void checkYJSBHWGSState() {
		try {
			BangFangJdq bfjdq = JdqZlUtil.getBfjdq();
			System.out.println("ǰopen1==="+bfjdq.isKgl1Open());
			Integer bfh = LoadProperties.getBangFangHao();
			int waitTime=0;
			while (true) {
				bfjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				Thread.sleep(1000);
				waitTime+=1000;
					
				System.out.println("��open1==="+bfjdq.isKgl1Open());
				if(waitTime>30*1000) {
					System.out.println("�ϰ�ʧ�ܣ������³���ʶ��");
					System.out.println("���Ҷ���״̬Ϊһ���еĶ�������һ���ϰ�״̬�Ӵ��ϰ�����Ϊһ���ɨ��");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
					dd.setXddztMc(DingDanZhuangTai.YI_JIAN_DAI_SAO_MA_TEXT);
					APIUtil.editDingDanByZt(dd);
					
					waitTime+=1000;
					System.out.println("����ʧ��,����ϵ�����Ա");
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
		    		JdqBf1Util.openXiaBangDz();
		        	JdqZlUtil.closeBangFangJdq();
					Thread.sleep(2000);
					System.out.println("����ʧ��,����ϵ�����Ա");
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
					break;
				}
				else if(waitTime%(5*1000)==0) {
					waitTime+=1000;
					System.out.println("���ϰ��ѳ���ͣ���ڵذ��м�");
		    		//YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
				}
				
				
				if(bfjdq.isKgl1Open()) {
					System.out.println("���Ҷ���״̬Ϊһ���еĶ�������һ���ϰ�״̬�Ӵ��ϰ�����Ϊ�ϰ���");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
					dd.setYjzt(DingDan.DAI_SHANG_BANG);
					dd.setXyjzt(DingDan.SHANG_BANG_ZHONG);
					APIUtil.editDingDanByZt(dd);
					
					checkYJSBHXBHWGSState();
					break;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ���һ���ϰ����°������դ״̬
	 */
	public static void checkYJSBHXBHWGSState() {
		try {
			BangFangJdq bfjdq = JdqZlUtil.getBfjdq();
			System.out.println("ǰopen1==="+bfjdq.isKgl1Open());
			System.out.println("ǰopen2==="+bfjdq.isKgl2Open());
			Integer bfh = LoadProperties.getBangFangHao();
			int waitTime=0;
			while (true) {
				bfjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				Thread.sleep(1000);
				waitTime+=1000;
				System.out.println("��open1==="+bfjdq.isKgl1Open());
				System.out.println("��open2==="+bfjdq.isKgl2Open());
				System.out.println("waitTime==="+waitTime);
				if(waitTime>30*1000) {
					System.out.println("����ʧ�ܣ������³���ʶ��");
					System.out.println("���Ҷ���״̬Ϊһ���еĶ�������һ��״̬��һ���и���Ϊһ���ɨ�룬��һ���ϰ�״̬���ϰ��и���Ϊ���ϰ�");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
					dd.setXddztMc(DingDanZhuangTai.YI_JIAN_DAI_SAO_MA_TEXT);
					dd.setYjzt(DingDan.SHANG_BANG_ZHONG);
					dd.setXyjzt(DingDan.DAI_SHANG_BANG);
					APIUtil.editDingDanByZt(dd);
					
					waitTime+=1000;
					System.out.println("����ʧ��,����ϵ�����Ա");
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
		    		JdqBf1Util.openXiaBangDz();
		        	JdqZlUtil.closeBangFangJdq();
					Thread.sleep(2000);
					System.out.println("����ʧ��,����ϵ�����Ա");
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
					break;
				}
				else if(waitTime%(5*1000)==0) {
					waitTime+=1000;
					System.out.println("���ϰ��ѳ���ͣ���ڵذ��м�");
		    		//YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
				}
				
				if(!bfjdq.isKgl1Open()&&!bfjdq.isKgl2Open()) {
					System.out.println("���Ҷ���״̬Ϊһ���еĶ�������һ���ϰ�״̬���ϰ��и���Ϊ������");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
					dd.setYjzt(DingDan.SHANG_BANG_ZHONG);
					dd.setXyjzt(DingDan.DAI_CHENG_ZHONG);
					APIUtil.editDingDanByZt(dd);
					
					yiJianChengZhongZhong();
					break;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * һ�������
	 */
	public static void yiJianChengZhongZhong() {
		try {
			System.out.println("���Ҷ���״̬Ϊһ���еĶ�������һ��״̬�Ӵ����ظ���Ϊ������");
			Integer bfh = LoadProperties.getBangFangHao();
			DingDan dd=new DingDan();
			dd.setYjbfh(bfh);
			dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
			dd.setYjzt(DingDan.DAI_CHENG_ZHONG);
			dd.setXyjzt(DingDan.CHENG_ZHONG_ZHONG);
			APIUtil.editDingDanByZt(dd);
			
			System.out.println("��ѯ����״̬Ϊһ���У�һ��״̬Ϊ�����еĶ���");
			JSONObject resultJO=APIUtil.getDingDanByZt(bfh,0,DingDanZhuangTai.YI_JIAN_ZHONG_TEXT,DingDan.CHENG_ZHONG_ZHONG,DingDan.DAI_SHANG_BANG);
			String status = resultJO.getString("status");
			if("ok".equals(status)) {
				DingDan dd1=(DingDan)net.sf.json.JSONObject.toBean(net.sf.json.JSONObject.fromObject(resultJO.get("dingDan").toString()), DingDan.class);
				
				System.out.println("��ȷ�������ȶ���15���ʼһ�����");
				System.out.println("ͣ��Ϩ��ȴ����أ��������Ա��ʱ�뿪�����͵ذ�");
	    		//YinZhuTask.sendMsg(YzZlUtil.get88().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
				Thread.sleep(6000);
				System.out.println("ͣ��Ϩ��ȴ����أ��������Ա��ʱ�뿪�����͵ذ�");
	    		//YinZhuTask.sendMsg(YzZlUtil.get88().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
				Thread.sleep(15000);
				System.out.println("���ڳ���");
	    		//YinZhuTask.sendMsg(YzZlUtil.get97().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
				
				Float mz=null;
				Float pz=null;
				Float jz=null;
				float djczl=0;
				if(dd1.getLxlx()==DingDan.SONG_YUN) {
					//mz=(float)5000;
					mz=(float)DiBangTask3124.getWeight();
					djczl=mz;
				}
				else {
					//pz=(float)1000;
					pz=(float)DiBangTask3124.getWeight();
					djczl=pz;
				}

				if(djczl>0) {
					APIUtil.playWeight(djczl);
					Thread.sleep(2000);
					APIUtil.playWeight(djczl);
					Thread.sleep(2000);
					
					JSONObject ddJO=resultJO.getJSONObject("dingDan");
			    	int ddId = ddJO.getInt("id");
			    	System.out.println("ddId==="+ddId);
			    	System.out.println("���ɰ�����¼");
					System.out.println("���ݳ��س�������������Ӷ�����Ӧ�İ�����¼");
					APIUtil.newBangDanJiLu(mz, pz, jz, ddId);
	
			    	if(dd1.getLxlx()==DingDan.SONG_YUN) {
						System.out.println("���Ķ�����ʵ����������������");
				    	DingDan dd2=new DingDan();
				    	dd2.setYjbfh(bfh);
				    	dd2.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
				    	dd2.setYjzt(DingDan.CHENG_ZHONG_ZHONG);
				    	dd2.setSjzl(mz);
				    	//dd2.setZlceb(dd1.getYzxzl()/mz);
				    	APIUtil.editDingDanByZt(dd2);
			    	}
	
					//float zhongLiang=2998;
					System.out.println("����һ�������¼");
					GuoBangJiLu gbjl=new GuoBangJiLu();
					if(dd1.getLxlx()==DingDan.SONG_YUN)
						gbjl.setGbzl(mz);
					else
						gbjl.setGbzl(pz);
					//gbjl.setZp1(zp1);
					//gbjl.setZp2(zp2);
					//gbjl.setZp3(zp3);
					gbjl.setGbzt(GuoBangJiLu.ZHENG_CHANG);
					gbjl.setGblx(GuoBangJiLu.RU_CHANG_GUO_BANG);
					gbjl.setDdId(dd1.getId());
					APIUtil.newGuoBangJiLu(gbjl);
				
					System.out.println("���Ҷ���״̬Ϊһ���еĶ�������һ��״̬�ӳ����и���Ϊ���°�");
					dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
					dd.setYjzt(DingDan.CHENG_ZHONG_ZHONG);
					dd.setXyjzt(DingDan.DAI_XIA_BANG);
					APIUtil.editDingDanByZt(dd);
					
					/*
		    		//��ӡһ�������¼
					APIUtil.printGbjl(GuoBangJiLu.RU_CHANG_GUO_BANG);
					
		    		YinZhuTask.sendMsg(YzZlUtil.get99().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
					Thread.sleep(2000);
		    		YinZhuTask.sendMsg(YzZlUtil.get99().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
					Thread.sleep(2000);
		    		YinZhuTask.sendMsg(YzZlUtil.get89().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
					*/
					
		        	System.out.println("̧��һ���°���բ");
		        	JdqBf1Util.openXiaBangDz();
					
					checkYJXBHWGSState();
				}
				else if(djczl==0) {//�ذ���û�г���
					System.out.println("���Ҷ���״̬Ϊһ���еĶ�������һ��״̬�ӳ����и���Ϊ���ϰ�");
					dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
					dd.setYjzt(DingDan.CHENG_ZHONG_ZHONG);
					dd.setXyjzt(DingDan.DAI_SHANG_BANG);
					APIUtil.editDingDanByZt(dd);
					
					System.out.println("���ϰ��ѳ���ͣ���ڵذ��м�");
		    		//YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
					checkYJSBHWGSState();
				}
				else if(djczl==-1) {//����ʧ��
					System.out.println("���Ҷ���״̬Ϊһ���еĶ�����������״̬��һ���и�Ϊһ���ɨ�룬��һ��״̬�ӳ����и���Ϊ���ϰ�");
					dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
					dd.setXddztMc(DingDanZhuangTai.YI_JIAN_DAI_SAO_MA_TEXT);
					dd.setYjzt(DingDan.CHENG_ZHONG_ZHONG);
					dd.setXyjzt(DingDan.DAI_SHANG_BANG);
					APIUtil.editDingDanByZt(dd);
					
					JdqBf1Util.openXiaBangDz();
		        	JdqZlUtil.closeBangFangJdq();
					
		        	System.out.println("����ʧ�ܣ�����ϵ�����Ա");
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
					Thread.sleep(2000);
		        	System.out.println("����ʧ�ܣ�����ϵ�����Ա");
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
				}
			}
			else {
				String message = resultJO.getString("message");
				System.out.println("message==="+message+",��������");
				System.out.println("û���ҵ�ƥ�䶩��");
	    		//YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
				Thread.sleep(3000);
				System.out.println("û���ҵ�ƥ�䶩��");
	    		//YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ���һ���°������դ״̬
	 */
	public static void checkYJXBHWGSState() {
		try {
			BangFangJdq bfjdq = JdqZlUtil.getBfjdq();
			System.out.println("ǰopen2==="+bfjdq.isKgl2Open());
			Integer bfh = LoadProperties.getBangFangHao();
			while (true) {
				bfjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				Thread.sleep(1000);
				System.out.println("��open2==="+bfjdq.isKgl2Open());
				if(bfjdq.isKgl2Open()) {
		        	
					System.out.println("���Ҷ���״̬Ϊһ���еĶ�������һ��״̬�Ӵ��°�����Ϊ�°���");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
					dd.setYjzt(DingDan.DAI_XIA_BANG);
					dd.setXyjzt(DingDan.XIA_BANG_ZHONG);
					APIUtil.editDingDanByZt(dd);
					
					checkIfYJXBYwc();
					break;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ���һ���°��Ƿ����
	 */
	public static void checkIfYJXBYwc() {
		try {
			BangFangJdq bfjdq = JdqZlUtil.getBfjdq();
			System.out.println("ǰopen2==="+bfjdq.isKgl2Open());
			Integer bfh = LoadProperties.getBangFangHao();
			while (true) {
				bfjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				Thread.sleep(1000);
				System.out.println("��open2==="+bfjdq.isKgl2Open());
				if(!bfjdq.isKgl2Open()) {
					System.out.println("���Ҷ���״̬Ϊһ���еĶ��������Ķ���״̬Ϊһ�����ˡ�һ��״̬���°��и���Ϊ�����");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
			    	dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
			    	dd.setXddztMc(DingDanZhuangTai.YI_JIAN_DAI_SHEN_HE_TEXT);
			    	dd.setYjzt(DingDan.XIA_BANG_ZHONG);
			    	dd.setXyjzt(DingDan.YI_WAN_CHENG);
			    	APIUtil.editDingDanByZt(dd);
			    	
	            	System.out.println("�رռ̵���");
		    		JdqZlUtil.closeBangFangJdq();
					break;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

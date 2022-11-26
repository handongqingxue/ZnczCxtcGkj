package com.znczCxtcGkj.util;

import org.json.JSONObject;

import com.znczCxtcGkj.cpsbsxt.Car;
import com.znczCxtcGkj.entity.*;
import com.znczCxtcGkj.util.*;
import com.znczCxtcGkj.yz.*;
import com.znczCxtcGkj.jdq.*;
import com.znczCxtcGkj.task.*;

/**
 * 北磅房（3号磅房）工具类
 * */
public class BangFang3Util {

	/**
	 * 更新一检二维码识别订单信息
	 * @param car
	 */
	public static void updateYJEWMSBDDXX(String cph) {

		System.out.println("查询订单状态为一检待扫码的订单");
		JSONObject resultJO=APIUtil.getDingDanByCphZts(cph,DingDanZhuangTai.YI_JIAN_DAI_SAO_MA_TEXT);
		if("ok".equals(resultJO.getString("status"))) {
        	System.out.println("存在该订单");
        	System.out.println("改变订单状态为一检待上磅");
        	Integer bfh = LoadProperties.getPlaceFlag();//这里是磅房，地点标识充当磅房号
			JSONObject ddJO=resultJO.getJSONObject("dingDan");
        	DingDan dd=new DingDan();
        	dd.setId(ddJO.getLong("id"));
        	dd.setDdztMc(DingDanZhuangTai.YI_JIAN_DAI_SHANG_BANG_TEXT);
        	dd.setYjbfh(bfh);
        	APIUtil.editDingDan(dd);
        	
        	//音柱播报:进行车牌识别
        	System.out.println("音柱播报:进行车牌识别");
    		//YinZhuUtil.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
		}
        else {
        	System.out.println("message==="+resultJO.getString("message"));
        	System.out.println("音柱播报：没有找到匹配订单");
        	/*
        	 * 这段代码暂时屏蔽掉，等到现场再打开
    		YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
			Thread.sleep(3000);
    		YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
        	 */
        }
	}
	
	/**
	 * 更新一检车牌识别订单信息
	 * @param car
	 */
	public static void updateYJCPSBDDXX(Car car) {
		try {
			System.out.println("查询订单状态为一检待上磅的订单");
			JSONObject resultJO=APIUtil.getDingDanByCphZts(car.getsLicense(),DingDanZhuangTai.YI_JIAN_DAI_SHANG_BANG_TEXT);
	        if("ok".equals(resultJO.getString("status"))) {
	        	System.out.println("存在该订单");
	        	System.out.println("根据其他订单状态验证是否存在其他订单");
	        	Integer bfh = LoadProperties.getPlaceFlag();
	        	JSONObject ddExistResult = APIUtil.checkDingDanIfExistByZt(bfh,0,DingDanZhuangTai.YI_JIAN_ZHONG_TEXT,DingDan.DAI_SHANG_BANG,DingDan.DAI_SHANG_BANG);
	        	if("ok".equals(ddExistResult.getString("status"))) {
	            	System.out.println("音柱播报：其他订单状态存在其他订单");
	        	}
	        	else {
	            	System.out.println("开启继电器");
	            	/*
	            	 * 到现场后打开
		    		JdqZlUtil.openBangFangJdq();
		    		*/
		        	System.out.println("抬起上磅道闸");
	            	/*
	            	 * 到现场后打开
		        	JdqBf1Util.openShangBangDz();
		    		*/
		        	
		        	System.out.println("改变订单状态为一检中");
					JSONObject ddJO=resultJO.getJSONObject("dingDan");
		        	DingDan dd=new DingDan();
		        	dd.setId(ddJO.getLong("id"));
		        	dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
		        	dd.setYjzt(DingDan.DAI_SHANG_BANG);
		        	dd.setYjbfh(bfh);
		        	APIUtil.editDingDan(dd);
		        	
		        	System.out.println("语音播报:请上磅把车辆停放在地磅中间");
		    		//YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
		        	
		    		checkYJSBHWGSState();
	        	}
	        }
	        else {
	        	System.out.println("message==="+resultJO.getString("message"));
	        	System.out.println("音柱播报：没有找到匹配订单");
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
	 * 检测一检上磅红外光栅状态
	 */
	public static void checkYJSBHWGSState() {
		try {
			/*
			 * 到现场后打开
			BangFangJdq bfjdq = JdqZlUtil.getBfjdq();
			System.out.println("前open1==="+bfjdq.isKgl1Open());
			*/
			Integer bfh = LoadProperties.getPlaceFlag();
			int waitTime=0;
			while (true) {
				/*
				 * 到现场后打开
				bfjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				 */
				Thread.sleep(1000);
				waitTime+=1000;
					
				//System.out.println("后open1==="+bfjdq.isKgl1Open());
				if(waitTime>30*1000) {
					System.out.println("上磅失败，请重新车牌识别");
					System.out.println("查找订单状态为一检中的订单，将一检上磅状态从待上磅更改为一检待扫码");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
					dd.setXddztMc(DingDanZhuangTai.YI_JIAN_DAI_SAO_MA_TEXT);
					APIUtil.editDingDanByZt(dd);
					
					waitTime+=1000;
					System.out.println("称重失败,请联系相关人员");
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
		    		JdqBf1Util.openXiaBangDz();
		        	JdqZlUtil.closeBangFangJdq();
					Thread.sleep(2000);
					System.out.println("称重失败,请联系相关人员");
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
					break;
				}
				else if(waitTime%(5*1000)==0) {
					waitTime+=1000;
					System.out.println("请上磅把车辆停放在地磅中间");
		    		//YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
				}
				
				
				//if(bfjdq.isKgl1Open()) {
					System.out.println("查找订单状态为一检中的订单，将一检上磅状态从待上磅更改为上磅中");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
					dd.setYjzt(DingDan.DAI_SHANG_BANG);
					dd.setXyjzt(DingDan.SHANG_BANG_ZHONG);
					APIUtil.editDingDanByZt(dd);
					
					checkYJSBHXBHWGSState();
					break;
				//}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 检测一检上磅和下磅红外光栅状态
	 */
	public static void checkYJSBHXBHWGSState() {
		try {
			/*
			 * 到现场后打开
			BangFangJdq bfjdq = JdqZlUtil.getBfjdq();
			System.out.println("前open1==="+bfjdq.isKgl1Open());
			System.out.println("前open2==="+bfjdq.isKgl2Open());
			 */
			Integer bfh = LoadProperties.getPlaceFlag();
			int waitTime=0;
			while (true) {
				/*
				 * 到现场后打开
				bfjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				 */
				Thread.sleep(1000);
				waitTime+=1000;
				//System.out.println("后open1==="+bfjdq.isKgl1Open());
				//System.out.println("后open2==="+bfjdq.isKgl2Open());
				System.out.println("waitTime==="+waitTime);
				if(waitTime>30*1000) {
					System.out.println("称重失败，请重新车牌识别");
					System.out.println("查找订单状态为一检中的订单，将一检状态从一检中更改为一检待扫码，将一检上磅状态从上磅中更改为待上磅");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
					dd.setXddztMc(DingDanZhuangTai.YI_JIAN_DAI_SAO_MA_TEXT);
					dd.setYjzt(DingDan.SHANG_BANG_ZHONG);
					dd.setXyjzt(DingDan.DAI_SHANG_BANG);
					APIUtil.editDingDanByZt(dd);
					
					waitTime+=1000;
					System.out.println("称重失败,请联系相关人员");
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
		    		JdqBf1Util.openXiaBangDz();
		        	JdqZlUtil.closeBangFangJdq();
					Thread.sleep(2000);
					System.out.println("称重失败,请联系相关人员");
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
					break;
				}
				else if(waitTime%(5*1000)==0) {
					waitTime+=1000;
					System.out.println("请上磅把车辆停放在地磅中间");
		    		//YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
				}
				
				//if(!bfjdq.isKgl1Open()&&!bfjdq.isKgl2Open()) {
					System.out.println("查找订单状态为一检中的订单，将一检上磅状态从上磅中更改为待称重");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
					dd.setYjzt(DingDan.SHANG_BANG_ZHONG);
					dd.setXyjzt(DingDan.DAI_CHENG_ZHONG);
					APIUtil.editDingDanByZt(dd);
					
					yiJianChengZhongZhong();
					break;
				//}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 一检称重中
	 */
	public static void yiJianChengZhongZhong() {
		try {
			System.out.println("查找订单状态为一检中的订单，将一检状态从待称重更改为称重中");
			Integer bfh = LoadProperties.getPlaceFlag();
			DingDan dd=new DingDan();
			dd.setYjbfh(bfh);
			dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
			dd.setYjzt(DingDan.DAI_CHENG_ZHONG);
			dd.setXyjzt(DingDan.CHENG_ZHONG_ZHONG);
			APIUtil.editDingDanByZt(dd);
			
			System.out.println("查询订单状态为一检中，一检状态为称重中的订单");
			JSONObject resultJO=APIUtil.getDingDanByZt(bfh,0,DingDanZhuangTai.YI_JIAN_ZHONG_TEXT,DingDan.CHENG_ZHONG_ZHONG,DingDan.DAI_SHANG_BANG);
			String status = resultJO.getString("status");
			if("ok".equals(status)) {
				DingDan dd1=(DingDan)net.sf.json.JSONObject.toBean(net.sf.json.JSONObject.fromObject(resultJO.get("dingDan").toString()), DingDan.class);
				
				System.out.println("请确保车辆稳定，15秒后开始一检称重");
				System.out.println("停车熄火等待称重，请相关人员及时离开车辆和地磅");
	    		//YinZhuTask.sendMsg(YzZlUtil.get88().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
				Thread.sleep(6000);
				System.out.println("停车熄火等待称重，请相关人员及时离开车辆和地磅");
	    		//YinZhuTask.sendMsg(YzZlUtil.get88().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
				Thread.sleep(15000);
				System.out.println("正在称重");
	    		//YinZhuTask.sendMsg(YzZlUtil.get97().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
				
				Float mz=null;
				Float pz=null;
				Float jz=null;
				float djczl=0;
				if(dd1.getLxlx()==DingDan.SONG_YUN) {
					mz=(float)5000;
					//mz=(float)DiBangTask3124.getWeight();
					djczl=mz;
				}
				else {
					pz=(float)1000;
					//pz=(float)DiBangTask3124.getWeight();
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
			    	System.out.println("生成磅单记录");
					System.out.println("根据称重出来的重量，添加订单对应的磅单记录");
					APIUtil.newBangDanJiLu(mz, pz, jz, ddId);
	
			    	if(dd1.getLxlx()==DingDan.SONG_YUN) {
						System.out.println("更改订单的实际重量、重量差额比");
				    	DingDan dd2=new DingDan();
				    	dd2.setYjbfh(bfh);
				    	dd2.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
				    	dd2.setYjzt(DingDan.CHENG_ZHONG_ZHONG);
				    	dd2.setSjzl(mz);
				    	//dd2.setZlceb(dd1.getYzxzl()/mz);
				    	APIUtil.editDingDanByZt(dd2);
			    	}
	
					//float zhongLiang=2998;
					System.out.println("生成一检过磅记录");
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
				
					System.out.println("查找订单状态为一检中的订单，将一检状态从称重中更改为待下磅");
					dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
					dd.setYjzt(DingDan.CHENG_ZHONG_ZHONG);
					dd.setXyjzt(DingDan.DAI_XIA_BANG);
					APIUtil.editDingDanByZt(dd);
					
					/*
		    		//打印一检过磅记录
					APIUtil.printGbjl(GuoBangJiLu.RU_CHANG_GUO_BANG);
					
		    		YinZhuTask.sendMsg(YzZlUtil.get99().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
					Thread.sleep(2000);
		    		YinZhuTask.sendMsg(YzZlUtil.get99().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
					Thread.sleep(2000);
		    		YinZhuTask.sendMsg(YzZlUtil.get89().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
					*/
					
		        	System.out.println("抬起一检下磅道闸");
		        	/*
		        	JdqBf1Util.openXiaBangDz();
		        	*/
					
					checkYJXBHWGSState();
				}
				else if(djczl==0) {//地磅上没有车辆
					System.out.println("查找订单状态为一检中的订单，将一检状态从称重中更改为待上磅");
					dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
					dd.setYjzt(DingDan.CHENG_ZHONG_ZHONG);
					dd.setXyjzt(DingDan.DAI_SHANG_BANG);
					APIUtil.editDingDanByZt(dd);
					
					System.out.println("请上磅把车辆停放在地磅中间");
		    		//YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
					checkYJSBHWGSState();
				}
				else if(djczl==-1) {//称重失败
					System.out.println("查找订单状态为一检中的订单，将订单状态从一检中改为一检待扫码，将一检状态从称重中更改为待上磅");
					dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
					dd.setXddztMc(DingDanZhuangTai.YI_JIAN_DAI_SAO_MA_TEXT);
					dd.setYjzt(DingDan.CHENG_ZHONG_ZHONG);
					dd.setXyjzt(DingDan.DAI_SHANG_BANG);
					APIUtil.editDingDanByZt(dd);
					
					JdqBf1Util.openXiaBangDz();
		        	JdqZlUtil.closeBangFangJdq();
					
		        	System.out.println("称重失败，请联系相关人员");
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
					Thread.sleep(2000);
		        	System.out.println("称重失败，请联系相关人员");
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
				}
			}
			else {
				String message = resultJO.getString("message");
				System.out.println("message==="+message+",语音播报");
				System.out.println("没有找到匹配订单");
	    		//YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
				Thread.sleep(3000);
				System.out.println("没有找到匹配订单");
	    		//YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 检测一检下磅红外光栅状态
	 */
	public static void checkYJXBHWGSState() {
		try {
			/*
			 * 到现场后打开
			BangFangJdq bfjdq = JdqZlUtil.getBfjdq();
			System.out.println("前open2==="+bfjdq.isKgl2Open());
			 */
			Integer bfh = LoadProperties.getPlaceFlag();
			while (true) {
				//bfjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				Thread.sleep(1000);
				//System.out.println("后open2==="+bfjdq.isKgl2Open());
				//if(bfjdq.isKgl2Open()) {
		        	
					System.out.println("查找订单状态为一检中的订单，将一检状态从待下磅更改为下磅中");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
					dd.setYjzt(DingDan.DAI_XIA_BANG);
					dd.setXyjzt(DingDan.XIA_BANG_ZHONG);
					APIUtil.editDingDanByZt(dd);
					
					checkIfYJXBYwc();
					break;
				//}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 检测一检下磅是否完成
	 */
	public static void checkIfYJXBYwc() {
		try {
			/*
			 * 到现场后打开
			BangFangJdq bfjdq = JdqZlUtil.getBfjdq();
			System.out.println("前open2==="+bfjdq.isKgl2Open());
			 */
			Integer bfh = LoadProperties.getPlaceFlag();
			while (true) {
				//bfjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				Thread.sleep(1000);
				//System.out.println("后open2==="+bfjdq.isKgl2Open());
				//if(!bfjdq.isKgl2Open()) {
					System.out.println("查找订单状态为一检中的订单，更改订单状态为一检待审核、一检状态从下磅中更改为已完成");
					DingDan dd=new DingDan();
					dd.setYjbfh(bfh);
			    	dd.setDdztMc(DingDanZhuangTai.YI_JIAN_ZHONG_TEXT);
			    	dd.setXddztMc(DingDanZhuangTai.YI_JIAN_DAI_SHEN_HE_TEXT);
			    	dd.setYjzt(DingDan.XIA_BANG_ZHONG);
			    	dd.setXyjzt(DingDan.YI_WAN_CHENG);
			    	APIUtil.editDingDanByZt(dd);
			    	
	            	System.out.println("关闭继电器");
	            	/*
	            	 * 到现场后打开
		    		JdqZlUtil.closeBangFangJdq();
	            	 */
					break;
				//}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新二检二维码识别订单信息
	 * @param car
	 */
	public static void updateEJEWMSBDDXX(String cph) {

		System.out.println("查询订单状态为二检待扫码的订单");
		JSONObject resultJO=APIUtil.getDingDanByCphZts(cph,DingDanZhuangTai.ER_JIAN_DAI_SAO_MA_TEXT);
		if("ok".equals(resultJO.getString("status"))) {
        	System.out.println("存在该订单");
        	System.out.println("改变订单状态为二检待上磅");
        	Integer bfh = LoadProperties.getPlaceFlag();
			JSONObject ddJO=resultJO.getJSONObject("dingDan");
        	DingDan dd=new DingDan();
        	dd.setId(ddJO.getLong("id"));
        	dd.setDdztMc(DingDanZhuangTai.ER_JIAN_DAI_SHANG_BANG_TEXT);
        	dd.setEjbfh(bfh);
        	APIUtil.editDingDan(dd);
        	
        	//音柱播报:进行车牌识别
        	System.out.println("音柱播报:进行车牌识别");
    		//YinZhuUtil.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
		}
        else {
        	System.out.println("message==="+resultJO.getString("message"));
        	System.out.println("音柱播报：没有找到匹配订单");
        	/*
        	 * 这段代码暂时屏蔽掉，等到现场再打开
    		YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
			Thread.sleep(3000);
    		YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
        	 */
        }
	}
	
	/**
	 * 更新二检车牌识别订单信息
	 * @param car
	 */
	public static void updateEJCPSBDDXX(Car car) {
		try {
			System.out.println("查询订单状态为二检待上磅的订单");
			JSONObject resultJO=APIUtil.getDingDanByCphZts(car.getsLicense(),DingDanZhuangTai.ER_JIAN_DAI_SHANG_BANG_TEXT);
	        if("ok".equals(resultJO.getString("status"))) {
	        	System.out.println("存在该订单");
	        	System.out.println("根据其他订单状态验证是否存在其他订单");
	        	Integer bfh = LoadProperties.getPlaceFlag();
	        	JSONObject ddExistResult = APIUtil.checkDingDanIfExistByZt(0,bfh,DingDanZhuangTai.ER_JIAN_ZHONG_TEXT,DingDan.YI_WAN_CHENG,DingDan.DAI_SHANG_BANG);
	        	if("ok".equals(ddExistResult.getString("status"))) {
	            	System.out.println("音柱播报：其他订单状态存在其他订单");
	        	}
	        	else {
	            	System.out.println("开启继电器");
	            	/*
	            	 * 到现场后打开
		    		JdqZlUtil.openBangFangJdq();
		    		*/
		        	System.out.println("抬起上磅道闸");
	            	/*
	            	 * 到现场后打开
		        	JdqBf1Util.openShangBangDz();
		        	*/
		        	
		        	System.out.println("改变订单状态为二检中");
					JSONObject ddJO=resultJO.getJSONObject("dingDan");
		        	DingDan dd=new DingDan();
		        	dd.setId(ddJO.getLong("id"));
		        	dd.setDdztMc(DingDanZhuangTai.ER_JIAN_ZHONG_TEXT);
		        	dd.setEjzt(DingDan.DAI_SHANG_BANG);
		        	dd.setEjbfh(bfh);
		        	APIUtil.editDingDan(dd);
		        	
		        	System.out.println("语音播报:请上磅把车辆停放在地磅中间");
	            	/*
	            	 * 到现场后打开
		    		YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
		        	*/
		        	
		    		checkEJSBHWGSState();
	        	}
	        }
	        else {
	        	System.out.println("message==="+resultJO.getString("message"));
	        	System.out.println("音柱播报：没有找到匹配订单");
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
	 * 检测二检上磅红外光栅状态
	 */
	public static void checkEJSBHWGSState() {
		try {
			/*
			 * 到现场后打开
			BangFangJdq bfjdq = JdqZlUtil.getBfjdq();
			System.out.println("前open1==="+bfjdq.isKgl1Open());
			*/
			Integer bfh = LoadProperties.getPlaceFlag();
			int waitTime=0;
			while (true) {
				/*
				 * 到现场后打开
				bfjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				 */
				Thread.sleep(1000);
				waitTime+=1000;
					
				//System.out.println("后open1==="+bfjdq.isKgl1Open());
				if(waitTime>30*1000) {
					System.out.println("上磅失败，请重新车牌识别");
					System.out.println("查找订单状态为二检中的订单，将二检上磅状态从待上磅更改为二检待扫码");
					DingDan dd=new DingDan();
					dd.setEjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.ER_JIAN_ZHONG_TEXT);
					dd.setXddztMc(DingDanZhuangTai.ER_JIAN_DAI_SAO_MA_TEXT);
					APIUtil.editDingDanByZt(dd);
					
					waitTime+=1000;
					System.out.println("称重失败,请联系相关人员");
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
		    		JdqBf1Util.openXiaBangDz();
		        	JdqZlUtil.closeBangFangJdq();
					Thread.sleep(2000);
					System.out.println("称重失败,请联系相关人员");
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
					break;
				}
				else if(waitTime%(5*1000)==0) {
					waitTime+=1000;
					System.out.println("请上磅把车辆停放在地磅中间");
		    		//YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
				}
				
				
				//if(bfjdq.isKgl1Open()) {
					System.out.println("查找订单状态为二检中的订单，将二检上磅状态从待上磅更改为上磅中");
					DingDan dd=new DingDan();
					dd.setEjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.ER_JIAN_ZHONG_TEXT);
					dd.setEjzt(DingDan.DAI_SHANG_BANG);
					dd.setXejzt(DingDan.SHANG_BANG_ZHONG);
					APIUtil.editDingDanByZt(dd);
					
					checkEJSBHXBHWGSState();
					break;
				//}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 检测二检上磅和下磅红外光栅状态
	 */
	public static void checkEJSBHXBHWGSState() {
		try {
			/*
			 * 到现场后打开
			BangFangJdq bfjdq = JdqZlUtil.getBfjdq();
			System.out.println("前open1==="+bfjdq.isKgl1Open());
			System.out.println("前open2==="+bfjdq.isKgl2Open());
			*/
			Integer bfh = LoadProperties.getPlaceFlag();
			int waitTime=0;
			while (true) {
				/*
				 * 到现场后打开
				bfjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				*/
				Thread.sleep(1000);
				waitTime+=1000;
				//System.out.println("后open1==="+bfjdq.isKgl1Open());
				//System.out.println("后open2==="+bfjdq.isKgl2Open());
				System.out.println("waitTime==="+waitTime);
				if(waitTime>30*1000) {
					System.out.println("称重失败，请重新车牌识别");
					System.out.println("查找订单状态为二检中的订单，将二检状态从二检中更改为二检待扫码，将二检上磅状态从上磅中更改为待上磅");
					DingDan dd=new DingDan();
					dd.setEjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.ER_JIAN_ZHONG_TEXT);
					dd.setXddztMc(DingDanZhuangTai.ER_JIAN_DAI_SAO_MA_TEXT);
					dd.setEjzt(DingDan.SHANG_BANG_ZHONG);
					dd.setXejzt(DingDan.DAI_SHANG_BANG);
					APIUtil.editDingDanByZt(dd);
					
					waitTime+=1000;
					System.out.println("称重失败,请联系相关人员");
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
		    		JdqBf1Util.openXiaBangDz();
		        	JdqZlUtil.closeBangFangJdq();
					Thread.sleep(2000);
					System.out.println("称重失败,请联系相关人员");
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
					break;
				}
				else if(waitTime%(5*1000)==0) {
					waitTime+=1000;
					System.out.println("请上磅把车辆停放在地磅中间");
		    		//YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1000,YinZhuTask.YI_JIAN);
				}
				
				//if(!bfjdq.isKgl1Open()&&!bfjdq.isKgl2Open()) {
					System.out.println("查找订单状态为二检中的订单，将二检上磅状态从上磅中更改为待称重");
					DingDan dd=new DingDan();
					dd.setEjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.ER_JIAN_ZHONG_TEXT);
					dd.setEjzt(DingDan.SHANG_BANG_ZHONG);
					dd.setXejzt(DingDan.DAI_CHENG_ZHONG);
					APIUtil.editDingDanByZt(dd);
					
					erJianChengZhongZhong();
					break;
				//}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 二检称重中
	 */
	public static void erJianChengZhongZhong() {
		try {
			System.out.println("查找订单状态为二检中的订单，将二检状态从待称重更改为称重中");
			Integer bfh = LoadProperties.getPlaceFlag();
			DingDan dd=new DingDan();
			dd.setEjbfh(bfh);
			dd.setDdztMc(DingDanZhuangTai.ER_JIAN_ZHONG_TEXT);
			dd.setEjzt(DingDan.DAI_CHENG_ZHONG);
			dd.setXejzt(DingDan.CHENG_ZHONG_ZHONG);
			APIUtil.editDingDanByZt(dd);
			
			System.out.println("查询订单状态为二检中，二检状态为称重中的订单");
			JSONObject resultJO=APIUtil.getDingDanByZt(0,bfh,DingDanZhuangTai.ER_JIAN_ZHONG_TEXT,DingDan.YI_WAN_CHENG,DingDan.CHENG_ZHONG_ZHONG);
			String status = resultJO.getString("status");
			if("ok".equals(status)) {
				DingDan dd1=(DingDan)net.sf.json.JSONObject.toBean(net.sf.json.JSONObject.fromObject(resultJO.get("dingDan").toString()), DingDan.class);
	
				System.out.println("请确保车辆稳定，15秒后开始二检称重");
				/*
				 * 到现场后打开
				YinZhuTask.sendMsg(YzZlUtil.get88().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
				 */
				Thread.sleep(6000);
				/*
				 * 到现场后打开
				YinZhuTask.sendMsg(YzZlUtil.get88().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
				 */
				Thread.sleep(15000);
				/*
				 * 到现场后打开
	    		YinZhuTask.sendMsg(YzZlUtil.get97().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
				 */
				
				Float mz=null;
				Float dxgmz=null;//待修改毛重
				Float pz=null;
				Float dxgpz=null;//待修改皮重
				Float jz=null;
				float djczl=0;
				JSONObject gbjlJO=APIUtil.selectBangDanJiLuByDdId(dd1.getId());
				JSONObject bdJO=gbjlJO.getJSONObject("bdjl");
				int bdId = bdJO.getInt("id");
				if(dd1.getLxlx()==DingDan.SONG_YUN) {
					mz=(float)bdJO.getDouble("mz");
					dxgpz=(float)1000;
					//dxgpz=(float)DiBangTask3124.getWeight();
					djczl=dxgpz;
					jz=mz-dxgpz;
				}
				else {
					dxgmz=(float)5000;
					//dxgmz=(float)DiBangTask3124.getWeight();
					djczl=dxgmz;
					pz=(float)bdJO.getDouble("pz");
					jz=dxgmz-pz;
				}
	    		
				if(djczl>0) {
					APIUtil.playWeight(djczl);
					Thread.sleep(2000);
					APIUtil.playWeight(djczl);
					Thread.sleep(2000);
				
					System.out.println("根据称重出来的重量，修改订单对应的磅单记录");
					APIUtil.editBangDanJiLu(bdId,dxgmz,dxgpz,jz);
				
					if(dd1.getLxlx()==DingDan.QU_YUN) {
						System.out.println("更改订单的实际重量、重量差额比");
				    	DingDan dd2=new DingDan();
				    	dd2.setEjbfh(bfh);
				    	dd2.setDdztMc(DingDanZhuangTai.ER_JIAN_ZHONG_TEXT);
				    	dd2.setYjzt(DingDan.YI_WAN_CHENG);
				    	dd2.setEjzt(DingDan.SHANG_BANG_ZHONG);
				    	dd2.setSjzl(mz);
				    	//dd2.setZlceb(dd1.getYzxzl()/mz);
				    	APIUtil.editDingDanByZt(dd2);
			    	}
				
					System.out.println("生成二检过磅记录");
					GuoBangJiLu gbjl=new GuoBangJiLu();
					if(dd1.getLxlx()==DingDan.SONG_YUN)
						gbjl.setGbzl(dxgpz);
					else
						gbjl.setGbzl(dxgmz);
					//gbjl.setZp1(zp1);
					//gbjl.setZp2(zp2);
					//gbjl.setZp3(zp3);
					gbjl.setGbzt(GuoBangJiLu.ZHENG_CHANG);
					gbjl.setGblx(GuoBangJiLu.CHU_CHANG_GUO_BANG);
					gbjl.setDdId(dd1.getId());
					APIUtil.newGuoBangJiLu(gbjl);
				
					System.out.println("查找订单状态为二检中的订单，将二检状态从称重中更改为待下磅");
					dd=new DingDan();
					dd.setEjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.ER_JIAN_ZHONG_TEXT);
					dd.setEjzt(DingDan.CHENG_ZHONG_ZHONG);
					dd.setXejzt(DingDan.DAI_XIA_BANG);
					APIUtil.editDingDanByZt(dd);

					/*
		    		//打印二检过磅记录
					APIUtil.printGbjl(GuoBangJiLu.CHU_CHANG_GUO_BANG);
					*/
					//打印磅单记录
					//APIUtil.printBdjl();
					
		    		//YinZhuTask.sendMsg(YzZlUtil.get99().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
					Thread.sleep(2000);
		    		//YinZhuTask.sendMsg(YzZlUtil.get99().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
					Thread.sleep(2000);
		    		//YinZhuTask.sendMsg(YzZlUtil.get89().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
					
		        	System.out.println("抬起下磅道闸");
		        	/*
		        	JdqBf1Util.openXiaBangDz();
		        	*/
					
					checkEJXBHWGSState();
				}
				else if(djczl==0) {//地磅上没有车辆
					System.out.println("查找订单状态为二检中的订单，将二检上磅状态从称重中更改为待上磅");
					dd=new DingDan();
					dd.setEjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.ER_JIAN_ZHONG_TEXT);
					dd.setEjzt(DingDan.CHENG_ZHONG_ZHONG);
					dd.setXejzt(DingDan.DAI_SHANG_BANG);
					APIUtil.editDingDanByZt(dd);

		    		//YinZhuTask.sendMsg(YzZlUtil.get87().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
					checkEJSBHWGSState();
				}
				else if(djczl==-1) {//称重失败
					System.out.println("查找订单状态为二检中的订单，将二检上磅状态从称重中更改为待上磅");
					dd=new DingDan();
					dd.setEjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.ER_JIAN_ZHONG_TEXT);
					dd.setXddztMc(DingDanZhuangTai.ER_JIAN_DAI_SAO_MA_TEXT);
					dd.setEjzt(DingDan.CHENG_ZHONG_ZHONG);
					dd.setXejzt(DingDan.DAI_SHANG_BANG);
					APIUtil.editDingDanByZt(dd);
					
					JdqBf1Util.openXiaBangDz();
		        	JdqZlUtil.closeBangFangJdq();
					
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
					Thread.sleep(2000);
		    		//YinZhuTask.sendMsg(YzZlUtil.get95().replaceAll(" ", ""), 1000,YinZhuTask.ER_JIAN);
				}
			}
			else {
				String message = resultJO.getString("message");
				System.out.println("message==="+message+",语音播报");
	    		//YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
				Thread.sleep(3000);
	    		//YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.ER_JIAN);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 检测二检下磅红外光栅状态
	 */
	public static void checkEJXBHWGSState() {
		try {
			/*
			 * 到现场后打开
			BangFangJdq bfjdq = JdqZlUtil.getBfjdq();
			System.out.println("前open2==="+bfjdq.isKgl2Open());
			*/
			Integer bfh = LoadProperties.getPlaceFlag();
			while (true) {
				/*
				 * 到现场后打开
				bfjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				*/
				Thread.sleep(1000);
				//System.out.println("后open2==="+bfjdq.isKgl2Open());
				//if(bfjdq.isKgl2Open()) {
		        	
					System.out.println("查找订单状态为二检中的订单，将二检状态从待下磅更改为下磅中");
					DingDan dd=new DingDan();
					dd.setEjbfh(bfh);
					dd.setDdztMc(DingDanZhuangTai.ER_JIAN_ZHONG_TEXT);
					dd.setEjzt(DingDan.DAI_XIA_BANG);
					dd.setXejzt(DingDan.XIA_BANG_ZHONG);
					APIUtil.editDingDanByZt(dd);
					
					checkIfEJXBYwc();
					break;
				//}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 检测二检下磅是否完成
	 */
	public static void checkIfEJXBYwc() {
		try {
			/*
			 * 到现场后打开
			BangFangJdq bfjdq = JdqZlUtil.getBfjdq();
			System.out.println("前open2=="+bfjdq.isKgl2Open());
			*/
			Integer bfh = LoadProperties.getPlaceFlag();
			while (true) {
				//bfjdq.sendData(WriteZhiLingConst.DU_QU_KAI_GUAN_LIANG_ZHUANG_TAI);
				Thread.sleep(1000);
				//System.out.println("后open2==="+bfjdq.isKgl2Open());
				//if(!bfjdq.isKgl2Open()) {
			    	System.out.println("查找订单状态为二检中的订单，更改订单状态为二检待审核、二检状态从下磅中更改为已完成");
			    	DingDan dd=new DingDan();
			    	dd.setEjbfh(bfh);
			    	dd.setDdztMc(DingDanZhuangTai.ER_JIAN_ZHONG_TEXT);
			    	dd.setXddztMc(DingDanZhuangTai.ER_JIAN_DAI_SHEN_HE_TEXT);
			    	dd.setEjzt(DingDan.XIA_BANG_ZHONG);
			    	dd.setXejzt(DingDan.YI_WAN_CHENG);
			    	APIUtil.editDingDanByZt(dd);
			    	
	            	System.out.println("关闭二检继电器");
	            	/*
	            	 * 到现场后打开
		    		JdqZlUtil.closeBangFangJdq();
	            	 */
					break;
				//}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Car car=new Car();
		car.setsLicense(" 鲁B9008");
		/*
		updateYJCPSBDDXX(car);
		*/
		
		//checkYJSBHWGSState();
		
		//checkYJSBHXBHWGSState();
		
		//yiJianChengZhongZhong();
		
		//checkYJXBHWGSState();
		
		updateEJCPSBDDXX(car);
		
		//erJianChengZhongZhong();
		
		//checkEJXBHWGSState();
	}
}

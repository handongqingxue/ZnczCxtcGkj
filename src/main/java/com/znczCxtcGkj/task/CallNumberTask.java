package com.znczCxtcGkj.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.znczCxtcGkj.entity.*;
import com.znczCxtcGkj.led.LedUtil;
import com.znczCxtcGkj.util.*;
import com.znczCxtcGkj.yz.YinZhuUtil;

/**
 * 叫号线程
 * @author lenovo
 *
 */
public class CallNumberTask extends Thread {
	//static Logger logger = LoggerFactory.getLogger(CallNumberTask.class);

	@Override
	public void run() {
		// TODO Auto-generated method stub
		callNumber();
	}
	
	private void callNumber() {
		try {
			while (true) {
				org.json.JSONObject resultJO=APIUtil.getJhPdHMList();
				String status = resultJO.getString("status");
				if("no".equals(status)) {
					System.out.println("目前没有需要叫号中和排队中的车辆");
					continue;
				}
				//https://blog.csdn.net/xy631739211/article/details/111601699
				List<HaoMa> hmList=JSON.parseArray(resultJO.get("hmList").toString(), HaoMa.class);
				System.out.println("待叫号和排队中的数量为:" + hmList.size());
				
				// 存放正在叫号中的车牌号
				List<String>  cphListDaiRuChang  = new ArrayList<String>();
				// 存放排队中的车牌信息
				List<String> cphListPaiDuiZhong = new ArrayList<String>();
				for (HaoMa hm : hmList) {
					String hmztMc = hm.getHmztMc();
					if(HaoMaZhuangTai.JIAO_HAO_ZHONG_TEXT.equals(hmztMc)) {
						String ksjhsj = hm.getKsjhsj();
						Long ksjhsjTime = null;
						if (StringUtils.isBlank(ksjhsj)) {
							// 若没有开始叫号时间
							ksjhsjTime = new Date().getTime();
						} else { 
							ksjhsjTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ksjhsj).getTime();
						}
						
						long currTime = new Date().getTime();
						// 900000 为15分钟的毫秒，
						int passNumberTime = LoadProperties.getPassNumberTime();
						if ((ksjhsjTime + passNumberTime) < currTime) {
							//开始叫号时间如果和当前时间大于15分钟，那么此排号信息需要做过号处理
							hm.setHmztMc(HaoMaZhuangTai.YI_GUO_HAO_TEXT);
				        	APIUtil.editHaoMa(hm);
				        	
				        	DingDan dd=new DingDan();
							dd.setId(hm.getDdId());
							dd.setDdztMc(DingDanZhuangTai.YI_XIA_DAN_TEXT);//一旦号码过号，订单状态改为已下单，得重新刷身份证排队才行
							APIUtil.editDingDan(dd);
							
							continue;
						}
						
						String cph = hm.getClCph();
						if (StringUtils.isBlank(cph)) {
							System.out.println("此订单没有找到车牌，订单id="+hm.getDdId());
							// 发送播报信息， 此订单没有找到车牌号
							continue;
						}
						else {
							cphListDaiRuChang.add(cph);
						}
					}
					else if(HaoMaZhuangTai.PAI_DUI_ZHONG_TEXT.equals(hmztMc)) {
						String cph = hm.getClCph();
						if (StringUtils.isBlank(cph)) {
							System.out.println("此订单没有找到车牌，订单id="+hm.getDdId());
							// 发送播报信息， 此订单没有找到车牌号
							continue;
						}
						else {
							cphListPaiDuiZhong.add(cph);
						}
					}
				}

				System.out.println("发送消息到LED");
				/*
				 * 先注释掉，等到现场后再打开
				sendMsgToMenGangLed(cphListDaiRuChang,cphListPaiDuiZhong);
				*/

				// 音柱播放
				System.out.println("开始播放音频");
				for (String cphDaiRuChang : cphListDaiRuChang) {
					try {
						System.out.println("发送音频83：请车牌号为");
						/*
						 * 先注释掉，等到现场后再打开
						YinZhuUtil.sendMsg(ModBusUtil.get83(), 1500);
						*/
						char[] charCph = cphDaiRuChang.toCharArray();
						for (char c : charCph) {
							String valueOf = String.valueOf(c);
							System.out.println(valueOf);
							/*
							 * 先注释掉，等到现场后再打开
							YinZhuUtil.sendMsg(ModBusUtil.getModBusChinese(valueOf), 800);
							 */
						}
						System.out.println("发送音频81：的车辆入厂");
						/*
						 * 先注释掉，等到现场后再打开
						YinZhuUtil.sendMsg(ModBusUtil.get81(), 1500);
						 */
					} catch (Exception e) {
						System.out.println("音柱播放错误" + e);
						e.printStackTrace();
					} 
				}
				
				int callNumberTimeSpace = LoadProperties.getCallNumberTimeSpace();
				Thread.sleep(callNumberTimeSpace);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *  发送消息给门岗LED屏幕
	 * @param cphListDaiRuChang
	 * @param cphListPaiDuiZhong
	 */
	private static void sendMsgToMenGangLed(List<String> cphListDaiRuChang, List<String> cphListPaiDuiZhong) {
		Map<String, String> contextMap = LedUtil.convertListToMenGangMsg(cphListDaiRuChang, cphListPaiDuiZhong);
		String drcContext1 = contextMap.get("drcContext1").toString();
		String drcContext2 = contextMap.get("drcContext2").toString();
		String pdzContext1 = contextMap.get("pdzContext1").toString();
		String pdzContext2 = contextMap.get("pdzContext2").toString();
		
		System.out.println("开始发送车辆");
		Integer sendProgram = LedUtil.sendProgramMultiLineToMenGang(LoadProperties.getLedIp(), 
				LoadProperties.getLedFisTitleName(),
				"      待入厂车辆",
				drcContext1, 
				drcContext2,
				"      排队车辆",
				pdzContext1, 
				pdzContext2, 
				"     禁止其他 车辆入厂");
		
		System.out.println("发送车辆结束， 成功号码：  " + sendProgram);
		
		System.out.println("成功号码： " + sendProgram);
		System.out.println("待入厂： " + drcContext1+" "+drcContext2);
		System.out.println("排队中： " + pdzContext1+" "+pdzContext2);
	}
}

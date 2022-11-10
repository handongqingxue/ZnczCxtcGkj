package com.znczCxtcGkj.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.znczCxtcGkj.entity.*;
import com.znczCxtcGkj.led.LedUtil;
import com.znczCxtcGkj.util.*;
import com.znczCxtcGkj.yz.YinZhuUtil;

/**
 * �к��߳�
 * @author lenovo
 *
 */
public class CallNumberTask extends Thread {
	static Logger logger = LoggerFactory.getLogger(CallNumberTask.class);

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
					logger.info("Ŀǰû����Ҫ�к��к��Ŷ��еĳ���");
					continue;
				}
				List<HaoMa> hmList=(List<HaoMa>)resultJO.get("hmList");
				logger.info("���кź��Ŷ��е�����Ϊ:" + hmList.size());
				
				// ������ڽк��еĳ��ƺ�
				List<String>  cphListDaiRuChang  = new ArrayList<String>();
				// ����Ŷ��еĳ�����Ϣ
				List<String> cphListPaiDuiZhong = new ArrayList<String>();
				for (HaoMa hm : hmList) {
					String hmztMc = hm.getHmztMc();
					if(HaoMaZhuangTai.JIAO_HAO_ZHONG_TEXT.equals(hmztMc)) {
						String ksjhsj = hm.getKsjhsj();
						long ksjhsjTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ksjhsj).getTime();
						
						long currTime = new Date().getTime();
						// 900000 Ϊ15���ӵĺ��룬
						if ((ksjhsjTime + 900000) < currTime) {
							//��ʼ�к�ʱ������͵�ǰʱ�����15���ӣ���ô���ź���Ϣ��Ҫ�����Ŵ���
							hm.setHmztMc(HaoMaZhuangTai.YI_GUO_HAO_TEXT);
				        	APIUtil.editHaoMa(hm);
				        	
				        	DingDan dd=new DingDan();
							dd.setId(hm.getDdId());
							dd.setDdztMc(DingDanZhuangTai.YI_XIA_DAN_TEXT);//һ��������ţ�����״̬��Ϊ���µ���������ˢ���֤�ŶӲ���
							APIUtil.editDingDan(dd);
							
							continue;
						}
						
						String cph = hm.getClCph();
						if (StringUtils.isBlank(cph)) {
							logger.info("�˶���û���ҵ����ƣ�����id="+hm.getDdId());
							// ���Ͳ�����Ϣ�� �˶���û���ҵ����ƺ�
							continue;
						}
						else {
							cphListDaiRuChang.add(cph);
						}
					}
					else if(HaoMaZhuangTai.PAI_DUI_ZHONG_TEXT.equals(hmztMc)) {
						String cph = hm.getClCph();
						if (StringUtils.isBlank(cph)) {
							logger.info("�˶���û���ҵ����ƣ�����id="+hm.getDdId());
							// ���Ͳ�����Ϣ�� �˶���û���ҵ����ƺ�
							continue;
						}
						else {
							cphListPaiDuiZhong.add(cph);
						}
					}
				}

				logger.info("������Ϣ��LED");
				sendMsgToMenGangLed(cphListDaiRuChang,cphListPaiDuiZhong);

				// ��������
				logger.info("��ʼ������Ƶ");
				for (String cphDaiRuChang : cphListDaiRuChang) {
					try {
						logger.info("������Ƶ83���복�ƺ�Ϊ");
						YinZhuUtil.sendMsg(ModBusUtil.get83(), 1500);
						char[] charCph = cphDaiRuChang.toCharArray();
						for (char c : charCph) {
							String valueOf = String.valueOf(c);
							YinZhuUtil.sendMsg(ModBusUtil.getModBusChinese(valueOf), 800);
						}
						logger.info("������Ƶ81���ĳ����볧");
						YinZhuUtil.sendMsg(ModBusUtil.get81(), 1500);
					} catch (Exception e) {
						logger.info("�������Ŵ���" + e);
						e.printStackTrace();
					} 
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *  ������Ϣ���Ÿ�LED��Ļ
	 * @param cphListDaiRuChang
	 * @param cphListPaiDuiZhong
	 */
	private static void sendMsgToMenGangLed(List<String> cphListDaiRuChang, List<String> cphListPaiDuiZhong) {
		Map<String, String> contextMap = LedUtil.convertListToMenGangMsg(cphListDaiRuChang, cphListPaiDuiZhong);
		String drcContext1 = contextMap.get("drcContext1").toString();
		String drcContext2 = contextMap.get("drcContext2").toString();
		String pdzContext1 = contextMap.get("pdzContext1").toString();
		String pdzContext2 = contextMap.get("pdzContext2").toString();
		
		logger.info("��ʼ���ͳ���");
		Integer sendProgram = LedUtil.sendProgramMultiLineToMenGang(LoadProperties.getLedIp(), 
				LoadProperties.getLedFisTitleName(),
				"      ���볧����",
				drcContext1, 
				drcContext2,
				"      �Ŷӳ���",
				pdzContext1, 
				pdzContext2, 
				"     ��ֹ���� �����볧");
		
		logger.info("���ͳ��������� �ɹ����룺  " + sendProgram);
		
		logger.info("�ɹ����룺 " + sendProgram);
		logger.info("���볧�� " + drcContext1+" "+drcContext2);
		logger.info("�Ŷ��У� " + pdzContext1+" "+pdzContext2);
	}
}

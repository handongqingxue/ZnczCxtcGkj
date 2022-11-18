package com.znczCxtcGkj.util;

import org.json.JSONObject;

import com.znczCxtcGkj.cpsbsxt.Car;
import com.znczCxtcGkj.entity.*;
import com.znczCxtcGkj.jdq.*;

public class MenGangUtil {

    /**
	 * ���½���ʶ���ƶ�����Ϣ
     * @param car
     */
    public static void updateJCCPSBDDXX(Car car) {
		JSONObject drcResultJO=APIUtil.getDingDanByCphZts(car.getsLicense(),DingDanZhuangTai.DAI_RU_CHANG_TEXT);
        if("ok".equals(drcResultJO.getString("status"))) {
			JSONObject drcDdJO=drcResultJO.getJSONObject("dingDan");
			long drcDdId = drcDdJO.getLong("id");

        	DingDan dd=new DingDan();
        	dd.setId(drcDdId);
        	dd.setDdztMc(DingDanZhuangTai.DAI_JIAN_YAN_TEXT);
        	JSONObject eddResultJO=APIUtil.editDingDan(dd);
        	if("ok".equals(eddResultJO.getString("message"))) {
        		JSONObject lhmResultJO = APIUtil.getLastHaoMaByDdId(drcDdId);
        		JSONObject lhmJO = lhmResultJO.getJSONObject("haoMa");
    			long drcHmId = lhmJO.getLong("id");
    			System.out.println("drcHmId==="+drcHmId);
        		HaoMa hm=new HaoMa();
        		hm.setId(drcHmId);
        		hm.setHmztMc(HaoMaZhuangTai.SHOU_LI_ZHONG_TEXT);
        		hm.setDdId(drcDdId);
        		JSONObject ehmResultJO=APIUtil.editHaoMa(hm);
            	if("ok".equals(ehmResultJO.getString("message"))) {
	        		CheLiangTaiZhang cltz=new CheLiangTaiZhang();
	        		cltz.setDdId(drcDdId);
	        		JSONObject resultJO = APIUtil.uploadCheLiangTaiZhang(cltz, CheLiangTaiZhang.JIN_CHANG);
	        		String message = resultJO.getString("message");
	        		if("ok".equals(message)) {
	        			/*
	        			 * ��δ�����ע�͵������ֳ��ٴ�
			    		JdqZlUtil.openMenGangJdq();
			        	JdqMGUtil.openJinChangDz();
			    		JdqZlUtil.closeMenGangJdq();
	        			 */
	        		}
	        		else {
	        			System.out.println("�ϴ����ݴ���������ʶ����");
	        		}
            	}
            	else {
            		System.out.println("�޸ĺ���״̬����������ʶ����");
            	}
        	}
        	else {
        		System.out.println("�޸Ķ���״̬����������ʶ����");
        	}
        }
        else {
        	//û���ҵ����ƶ�Ӧ�Ķ�����Ϣ����������
        }
	}
    
    /**
	 * ���³���ʶ���ƶ�����Ϣ
     * @param car
     */
    public static void updateCCCPSBDDXX(Car car) {
    	//���ÿ��볧��״̬,�ʼ첻�ϸ�δ��ӡƾ֤���Ѵ�ӡƾ֤��״̬�µĶ������������볧
    	String klcztStr=DingDanZhuangTai.DAI_JIAN_YAN_TEXT+","+DingDanZhuangTai.DAI_DA_YIN_PING_ZHENG_TEXT+","+DingDanZhuangTai.DAI_LI_CHANG_TEXT;
		JSONObject klcResultJO=APIUtil.getDingDanByCphZts(car.getsLicense(),klcztStr);
        if("ok".equals(klcResultJO.getString("status"))) {
			JSONObject klcDdJO=klcResultJO.getJSONObject("dingDan");
			long klcDdId = klcDdJO.getLong("id");

        	DingDan dd=new DingDan();
        	dd.setId(klcDdId);
        	dd.setDdztMc(DingDanZhuangTai.YI_WAN_CHENG_TEXT);
        	JSONObject eddResultJO=APIUtil.editDingDan(dd);
        	if("ok".equals(eddResultJO.getString("message"))) {
    			long klcHmId = klcDdJO.getLong("hmId");
        		HaoMa hm=new HaoMa();
        		hm.setId(klcHmId);
        		hm.setHmztMc(HaoMaZhuangTai.YI_WAN_CHENG_TEXT);
        		JSONObject ehmResultJO=APIUtil.editHaoMa(hm);
            	if("ok".equals(ehmResultJO.getString("message"))) {
	        		CheLiangTaiZhang cltz=new CheLiangTaiZhang();
	        		cltz.setDdId(klcDdId);
	        		JSONObject resultJO = APIUtil.uploadCheLiangTaiZhang(cltz, CheLiangTaiZhang.CHU_CHANG);
	        		String message = resultJO.getString("message");
	        		if("ok".equals(message)) {
			    		JdqZlUtil.openMenGangJdq();
			        	JdqMGUtil.openChuChangDz();
			    		JdqZlUtil.closeMenGangJdq();
	        		}
	        		else {
	        			System.out.println("�ϴ����ݴ���������ʶ����");
	        		}
            	}
            	else {
            		System.out.println("�޸ĺ���״̬����������ʶ����");
            	}
        	}
        	else {
        		System.out.println("�޸Ķ���״̬����������ʶ����");
        	}
        }
        else {
        	//û���ҵ����ƶ�Ӧ�Ķ�����Ϣ����������
        }
	}
}

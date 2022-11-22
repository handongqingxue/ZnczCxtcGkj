package com.znczCxtcGkj.task;

import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;

import com.znczCxtcGkj.cpsbsxt.Car;
import com.znczCxtcGkj.entity.DingDanZhuangTai;
import com.znczCxtcGkj.util.*;

import gnu.io.SerialPort;

public class SaoMaQiTask extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		receiveMessage();
	}

	private void receiveMessage() {
		// TODO Auto-generated method stub
        try {
			// �򿪴��ڣ���Ҫ�ڵ���������豸������ʶ���ҵ��������ò�����
			SerialPort serialPort = RXTXUtil.openSerialPort("COM3", 4800);

			String msg=null;
			while(true) {
			    // ����1000ms���ȴ���Ƭ����Ӧ
			    Thread.sleep(1000);

			    // �ӵ�Ƭ�����յ�������
			    byte[] dat = RXTXUtil.readByteData(serialPort,0);
			    if(dat != null && dat.length > 0) {
			        String dataReceive = new String(dat, "utf-8");
			        msg="�Ӵ��ڽ��յ�����:"+dataReceive+"\n";
			        System.out.println(msg);
			        
			        dataReceive=dataReceive.substring(1);
			        /*
			        JSONObject drJO = new JSONObject(dataReceive);
			        String ddbm = drJO.getString("��������");
			        String ddh = drJO.getString("������");
			        String sjsfz = drJO.getString("˾�����֤");
			        System.out.println("��������==="+ddbm);
			        System.out.println("������==="+ddh);
			        System.out.println("˾�����֤==="+sjsfz);
			        */
		        	updateGBEWMSBDDXX(dataReceive);
			    } else {
			    	msg="���յ�������Ϊ�գ�\n";
			        System.out.println(msg);
			    }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ���¹���ʶ���ά�붩����Ϣ
	 * @param car
	 */
	private void updateGBEWMSBDDXX(String cph) {
		JSONObject resultJO=null;
		resultJO=APIUtil.getDingDanByCphZts(cph,DingDanZhuangTai.YI_JIAN_DAI_SAO_MA_TEXT);
		if(resultJO!=null) {
			int bfh = LoadProperties.getPlaceFlag();//ɨ�������ڰ��������ص��ʶ�䵱������
            if("ok".equals(resultJO.getString("status"))) {
        		//һ�쳵��ʶ��
            	switch (bfh) {
				case Constant.YI_HAO_BANG_FANG:
					BangFang1Util.updateYJEWMSBDDXX(cph);
					break;
				case Constant.ER_HAO_BANG_FANG:
					//BangFang2Util.updateYJCPSBDDXX(car);
					break;
				case Constant.SAN_HAO_BANG_FANG:
					//BangFang3Util.updateYJCPSBDDXX(car);
					break;
				}
            }
            else {
        		resultJO=APIUtil.getDingDanByCphZts(cph,DingDanZhuangTai.ER_JIAN_DAI_SAO_MA_TEXT);
        		if(resultJO!=null) {
                    if("ok".equals(resultJO.getString("status"))) {
                		//���쳵��ʶ��
                    	switch (bfh) {
						case Constant.YI_HAO_BANG_FANG:
	                    	BangFang1Util.updateEJEWMSBDDXX(cph);
							break;
						case Constant.ER_HAO_BANG_FANG:
							//BangFang2Util.updateEJCPSBDDXX(car);
							break;
						case Constant.SAN_HAO_BANG_FANG:
							//BangFang3Util.updateEJCPSBDDXX(car);
							break;
						}
                    }
                    else {
                    	System.out.println("message==="+resultJO.getString("message"));
                    	System.out.println("����������û���ҵ�ƥ�䶩��");
                		//YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
                    }
        		}
            }
		}
	}
	
	public static void main(String[] args) {
		SaoMaQiTask smqt=new SaoMaQiTask();
		smqt.updateGBEWMSBDDXX("³B9008");
	}
}

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
			// 打开串口（需要在电脑里面的设备管理器识别找到），设置波特率
			SerialPort serialPort = RXTXUtil.openSerialPort("COM3", 4800);

			String msg=null;
			while(true) {
			    // 休眠1000ms，等待单片机反应
			    Thread.sleep(1000);

			    // 从单片机接收到的数据
			    byte[] dat = RXTXUtil.readByteData(serialPort,0);
			    if(dat != null && dat.length > 0) {
			        String dataReceive = new String(dat, "utf-8");
			        msg="从串口接收的数据:"+dataReceive+"\n";
			        System.out.println(msg);
			        
			        dataReceive=dataReceive.substring(1);
			        /*
			        JSONObject drJO = new JSONObject(dataReceive);
			        String ddbm = drJO.getString("订单编码");
			        String ddh = drJO.getString("订单号");
			        String sjsfz = drJO.getString("司机身份证");
			        System.out.println("订单编码==="+ddbm);
			        System.out.println("订单号==="+ddh);
			        System.out.println("司机身份证==="+sjsfz);
			        */
		        	updateGBEWMSBDDXX(dataReceive);
			    } else {
			    	msg="接收到的数据为空！\n";
			        System.out.println(msg);
			    }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 更新过磅识别二维码订单信息
	 * @param car
	 */
	private void updateGBEWMSBDDXX(String cph) {
		JSONObject resultJO=null;
		resultJO=APIUtil.getDingDanByCphZts(cph,DingDanZhuangTai.YI_JIAN_DAI_SAO_MA_TEXT);
		if(resultJO!=null) {
			int bfh = LoadProperties.getPlaceFlag();//扫码器是在磅房处，地点标识充当磅房号
            if("ok".equals(resultJO.getString("status"))) {
        		//一检车辆识别
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
                		//二检车辆识别
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
                    	System.out.println("音柱播报：没有找到匹配订单");
                		//YinZhuTask.sendMsg(YzZlUtil.get86().replaceAll(" ", ""), 1500,YinZhuTask.YI_JIAN);
                    }
        		}
            }
		}
	}
	
	public static void main(String[] args) {
		SaoMaQiTask smqt=new SaoMaQiTask();
		smqt.updateGBEWMSBDDXX("鲁B9008");
	}
}

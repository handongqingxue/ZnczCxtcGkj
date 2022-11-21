package com.znczCxtcGkj.task;

import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;

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
}

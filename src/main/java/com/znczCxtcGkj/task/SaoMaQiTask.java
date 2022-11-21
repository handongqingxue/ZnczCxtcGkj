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
}

package com.znczCxtcGkj.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.znczCxtcGkj.cpsbsxt.Car;
import com.znczCxtcGkj.idreader.IdReaderUtil;
import com.znczCxtcGkj.util.*;

import net.sf.json.JSONObject;

public class ClientSocket implements Runnable {

	private OutputStreamWriter out;
	private BufferedReader in;
	private Socket socket;
	private boolean serverOpend;

	public boolean isServerOpend() {
		return serverOpend;
	}

	public void setServerOpend(boolean serverOpend) {
		this.serverOpend = serverOpend;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				String line = in.readLine();
				System.out.println("line==="+line);
				if(line==null) {
					//�ر���\socket
					break;
				}
				this.readMessage(line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Connection reset");
				serverOpend=false;
				break;
			}
			
		}
	}

	private void readMessage(String mesJOStr) {
		System.out.println("mesJOStr==="+mesJOStr);
		JSONObject mesJO = net.sf.json.JSONObject.fromObject(mesJOStr);
		String action = mesJO.getString("action");
		System.out.println("action==="+action);
		int placeFlag = LoadProperties.getPlaceFlag();
		System.out.println("placeFlag==="+placeFlag);
		switch (action) {//�ж���Ϣ����
		case Constant.PUSH_EWM://���յ����Ͷ�ά�����͵���Ϣ
			String cph1 = mesJO.getString("cph");
			switch (placeFlag) {
			case Constant.YI_HAO_BANG_FANG:
				int yhbfJyFlag = mesJO.getInt("jyFlag");
				System.out.println("yhbfJyFlag==="+yhbfJyFlag);
				switch (yhbfJyFlag) {
				case Constant.YI_JIAN:
					BangFang1Util.updateYJEWMSBDDXX(cph1);
					break;
				case Constant.ER_JIAN:
					BangFang1Util.updateEJEWMSBDDXX(cph1);
					break;
				}
				break;
			case Constant.ER_HAO_BANG_FANG:
				int ehbfJyFlag = mesJO.getInt("jyFlag");
				System.out.println("ehbfJyFlag==="+ehbfJyFlag);
				switch (ehbfJyFlag) {
				case Constant.YI_JIAN:
					//BangFang2Util.updateYJCPSBDDXX(ewmCar);
					break;
				case Constant.ER_JIAN:
					//BangFang2Util.updateEJCPSBDDXX(ewmCar);
					break;
				}
				break;
			case Constant.SAN_HAO_BANG_FANG:
				int shbfJyFlag = mesJO.getInt("jyFlag");
				System.out.println("shbfJyFlag==="+shbfJyFlag);
				switch (shbfJyFlag) {
				case Constant.YI_JIAN:
					//BangFang3Util.updateYJCPSBDDXX(ewmCar);
					break;
				case Constant.ER_JIAN:
					//BangFang3Util.updateEJCPSBDDXX(ewmCar);
					break;
				}
				break;
			}
			break;
		case Constant.PUSH_CPH://���յ����ͳ��ƺ����͵���Ϣ
			Car cphCar=new Car();
			String cph2 = mesJO.getString("cph");
			cphCar.setsLicense(" "+cph2);
			//�ص�������Ÿڻ�������͵���������жϡ�����������ҽ���Ǳߵ��ж��߼���̫һ���������Ǳ�ֻ�а��������˳��򣬰�һ�������ж�д������㡣����͵����жϵص㣬���ж��ĸ�����
			switch (placeFlag) {
			case Constant.MEN_GANG:
				int jccFlag = mesJO.getInt("jccFlag");
				switch (jccFlag) {
				case Constant.JIN_CHANG:
					MenGangUtil.updateJCCPSBDDXX(cphCar);
					break;
				case Constant.CHU_CHANG:
					MenGangUtil.updateCCCPSBDDXX(cphCar);
					break;
				}
				break;
			case Constant.YI_HAO_BANG_FANG:
				int yhbfJyFlag = mesJO.getInt("jyFlag");
				System.out.println("yhbfJyFlag==="+yhbfJyFlag);
				switch (yhbfJyFlag) {
				case Constant.YI_JIAN:
					BangFang1Util.updateYJCPSBDDXX(cphCar);
					break;
				case Constant.ER_JIAN:
					BangFang1Util.updateEJCPSBDDXX(cphCar);
					break;
				}
				break;
			case Constant.ER_HAO_BANG_FANG:
				int ehbfJyFlag = mesJO.getInt("jyFlag");
				System.out.println("ehbfJyFlag==="+ehbfJyFlag);
				switch (ehbfJyFlag) {
				case Constant.YI_JIAN:
					//BangFang2Util.updateYJCPSBDDXX(cphCar);
					break;
				case Constant.ER_JIAN:
					//BangFang2Util.updateEJCPSBDDXX(cphCar);
					break;
				}
				break;
			case Constant.SAN_HAO_BANG_FANG:
				int shbfJyFlag = mesJO.getInt("jyFlag");
				System.out.println("shbfJyFlag==="+shbfJyFlag);
				switch (shbfJyFlag) {
				case Constant.YI_JIAN:
					//BangFang3Util.updateYJCPSBDDXX(cphCar);
					break;
				case Constant.ER_JIAN:
					//BangFang3Util.updateEJCPSBDDXX(cphCar);
					break;
				}
				break;
			}
			break;
		case Constant.PUSH_SFZH://���յ��������֤�����͵���Ϣ
			String sfzh = mesJO.getString("sfzh");
			IdReaderUtil.paiDuiBySfzh(sfzh);
			break;
		}
	}
	
	private void sendName(){
		int placeFlag= 0;
		//name = JOptionPane.showInputDialog(f, "����������:");
		placeFlag=LoadProperties.getPlaceFlag();
		this.sendMessageToServer(placeFlag+"");
	}
	
	private void sendMessageToServer(String mes){
		try {
			out.write(mes+"\n");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connectServer() {
		try {
			String serverIp=LoadProperties.getServerIp();
			socket = new Socket(serverIp,8000);//����������
			System.out.println("���ӳɹ�!");
			out = new OutputStreamWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.sendName();//��һʱ�䷢�͵ص��־
			Thread th = new Thread(this);
			th.start();		
			serverOpend=true;
		} catch (UnknownHostException e) {
			System.out.println("������������...");
			serverOpend=false;
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("����������������쳣...");
			serverOpend=false;
			//e.printStackTrace();
		}

	}

}

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
					//关闭流\socket
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
		switch (action) {//判断消息类型
		case Constant.PUSH_CPH://接收到推送车牌号类型的消息
			Car car1=new Car();
			String cph = mesJO.getString("cph");
			car1.setsLicense(" "+cph);
			//地点可能是门岗或磅房，就得在最外层判断。这里与蓝帆医疗那边的判断逻辑不太一样，蓝帆那边只有磅房部署了程序，把一检或二检判断写在最外层。这里就得先判断地点，再判断哪个磅房
			switch (placeFlag) {
			case Constant.MEN_GANG:
				MenGangUtil.updateJCCPSBDDXX(car1);
				break;
			case Constant.YI_HAO_BANG_FANG:
				int yhbfJyFlag = mesJO.getInt("jyFlag");
				System.out.println("yhbfJyFlag==="+yhbfJyFlag);
				switch (yhbfJyFlag) {
				case Constant.YI_JIAN:
					//BangFang1Util.updateYJCPSBDDXX(car1);
					break;
				case Constant.ER_JIAN:
					//BangFang1Util.updateEJCPSBDDXX(car1);
					break;
				}
				break;
			case Constant.ER_HAO_BANG_FANG:
				int ehbfJyFlag = mesJO.getInt("jyFlag");
				System.out.println("ehbfJyFlag==="+ehbfJyFlag);
				switch (ehbfJyFlag) {
				case Constant.YI_JIAN:
					//BangFang2Util.updateYJCPSBDDXX(car1);
					break;
				case Constant.ER_JIAN:
					//BangFang2Util.updateEJCPSBDDXX(car1);
					break;
				}
				break;
			case Constant.SAN_HAO_BANG_FANG:
				int shbfJyFlag = mesJO.getInt("jyFlag");
				System.out.println("shbfJyFlag==="+shbfJyFlag);
				switch (shbfJyFlag) {
				case Constant.YI_JIAN:
					//BangFang3Util.updateYJCPSBDDXX(car1);
					break;
				case Constant.ER_JIAN:
					//BangFang3Util.updateEJCPSBDDXX(car1);
					break;
				}
				break;
			}
			break;
		case Constant.PUSH_SFZH://接收到推送身份证号类型的消息
			String sfzh = mesJO.getString("sfzh");
			IdReaderUtil.paiDuiBySfzh(sfzh);
			break;
		}
	}
	
	private void sendName(){
		int placeFlag= 0;
		//name = JOptionPane.showInputDialog(f, "请输入姓名:");
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
			socket = new Socket(serverIp,8000);//能输入配置
			System.out.println("连接成功!");
			out = new OutputStreamWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.sendName();//第一时间发送地点标志
			Thread th = new Thread(this);
			th.start();		
			serverOpend=true;
		} catch (UnknownHostException e) {
			System.out.println("服务器不存在...");
			serverOpend=false;
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("与服务器建立连接异常...");
			serverOpend=false;
			//e.printStackTrace();
		}

	}

}

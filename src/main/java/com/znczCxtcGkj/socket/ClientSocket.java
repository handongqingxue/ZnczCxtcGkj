package com.znczCxtcGkj.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.znczCxtcGkj.cpsbsxt.Car;
import com.znczCxtcGkj.util.APIUtil;
//import com.znczCxtcGkj.util.BangFang1Util;
//import com.znczCxtcGkj.util.BangFang2Util;
import com.znczCxtcGkj.util.LoadProperties;

import net.sf.json.JSONObject;

public class ClientSocket implements Runnable {

	public static final int YI_JIAN=1;
	public static final int ER_JIAN=2;
	public static final String PUSH_CPH="pushCph";
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

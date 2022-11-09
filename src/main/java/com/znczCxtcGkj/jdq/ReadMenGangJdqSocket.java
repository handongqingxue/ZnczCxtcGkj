package com.znczCxtcGkj.jdq;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

import com.znczCxtcGkj.util.StringUtil;

public class ReadMenGangJdqSocket implements Runnable {

	private Socket client;
	MenGangJdq menGangJdq;
	private int BUFFER_SIZE = 50;
	
	public ReadMenGangJdqSocket(Socket socket, MenGangJdq menGangJdq) {
		// TODO Auto-generated constructor stub
		client = socket;
		this.menGangJdq=menGangJdq;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//声明一个输入流监听客户端数据
		try {
			InputStream in = client.getInputStream();
			OutputStream out =client.getOutputStream();
			byte[] recData = null;
			int munber =0;
			while(true) {
				if(client.isClosed()) {
					break;
				}
				else {
					try {
						recData = new byte[1024];
						//从输入流中读取客户端数据
						int size = in.read(recData);
						System.out.println("size="+size);
						if(size>-1) {
							byte[] new_data=new byte[size]	;
							//截取有用数据放到新数组
							new_data=Arrays.copyOf(recData,size);					
							String data = StringUtil.bytesToHex(new_data);	
							System.out.println("data==="+data);
							if(data.substring(0,4).equals("EEFF")) {
								/***************截取状态十六进制字符串*****************/
						    	String state=data.substring(8,12);
								System.out.println("state==="+state);
						    	/***************将状态十六进制字符串转换为十六进制*****************/
						    	BigInteger h = new BigInteger(state, 16);// 16进制转10进制
						    	/***************将状态十进制数转换为二进制字符串*****************/
						    	String tb = h.toString(2);    // 10进制转2进制
								System.out.println("tb1==="+tb);
						    	int length=16-tb.length();
						    	/***************将二进制状态高位补0*****************/
						    	for(int i=0;i<length;i++){
						    		tb="0"+tb;
						    	}
						    	tb=new StringBuffer(tb).reverse().toString();
								System.out.println("tb2==="+tb);
						    	
						    	int kglNum1=1;
						    	char state1 = tb.charAt(kglNum1-1);
								System.out.println("state1==="+state1);
					            if(state1=='1'){
					                System.out.println("开关量1:已导通"); 
					                menGangJdq.setKgl1Open(true);
					            }
					            else { 
					            	System.out.println("开关量1:已断开"); 
					            	menGangJdq.setKgl1Open(false);
					            }
					            
						    	int kglNum2=2;
						    	char state2 = tb.charAt(kglNum2-1);
					            if(state2=='1'){
					                System.out.println("开关量2:已导通");
					                menGangJdq.setKgl2Open(true);
					            }
					            else { 
					                System.out.println("开关量2:已断开");
					                menGangJdq.setKgl2Open(false);
					            }
					            
						    	int kglNum3=3;
						    	char state3 = tb.charAt(kglNum3-1);
					            if(state3=='1'){
					                System.out.println("开关量3:已导通");
					                menGangJdq.setKgl3Open(true);
					            }
					            else { 
					                System.out.println("开关量3:已断开");
					                menGangJdq.setKgl3Open(false);
					            }
					            
						    	int kglNum4=4;
						    	char state4 = tb.charAt(kglNum4-1);
					            if(state4=='1'){
					                System.out.println("开关量4:已导通");
					                menGangJdq.setKgl4Open(true);
					            }
					            else { 
					                System.out.println("开关量4:已断开");
					                menGangJdq.setKgl4Open(false);
					            }
							}
						}
						else {
							System.out.println("数据读取完毕！");
							client.close();
							break;
						}
					} catch (SocketException err) {
						// TODO Auto-generated catch block
						//err.printStackTrace();
						System.out.println("已断开连接");
						client.close();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

}

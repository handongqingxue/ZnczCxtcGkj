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
		//����һ�������������ͻ�������
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
						//���������ж�ȡ�ͻ�������
						int size = in.read(recData);
						System.out.println("size="+size);
						if(size>-1) {
							byte[] new_data=new byte[size]	;
							//��ȡ�������ݷŵ�������
							new_data=Arrays.copyOf(recData,size);					
							String data = StringUtil.bytesToHex(new_data);	
							System.out.println("data==="+data);
							if(data.substring(0,4).equals("EEFF")) {
								/***************��ȡ״̬ʮ�������ַ���*****************/
						    	String state=data.substring(8,12);
								System.out.println("state==="+state);
						    	/***************��״̬ʮ�������ַ���ת��Ϊʮ������*****************/
						    	BigInteger h = new BigInteger(state, 16);// 16����ת10����
						    	/***************��״̬ʮ������ת��Ϊ�������ַ���*****************/
						    	String tb = h.toString(2);    // 10����ת2����
								System.out.println("tb1==="+tb);
						    	int length=16-tb.length();
						    	/***************��������״̬��λ��0*****************/
						    	for(int i=0;i<length;i++){
						    		tb="0"+tb;
						    	}
						    	tb=new StringBuffer(tb).reverse().toString();
								System.out.println("tb2==="+tb);
						    	
						    	int kglNum1=1;
						    	char state1 = tb.charAt(kglNum1-1);
								System.out.println("state1==="+state1);
					            if(state1=='1'){
					                System.out.println("������1:�ѵ�ͨ"); 
					                menGangJdq.setKgl1Open(true);
					            }
					            else { 
					            	System.out.println("������1:�ѶϿ�"); 
					            	menGangJdq.setKgl1Open(false);
					            }
					            
						    	int kglNum2=2;
						    	char state2 = tb.charAt(kglNum2-1);
					            if(state2=='1'){
					                System.out.println("������2:�ѵ�ͨ");
					                menGangJdq.setKgl2Open(true);
					            }
					            else { 
					                System.out.println("������2:�ѶϿ�");
					                menGangJdq.setKgl2Open(false);
					            }
					            
						    	int kglNum3=3;
						    	char state3 = tb.charAt(kglNum3-1);
					            if(state3=='1'){
					                System.out.println("������3:�ѵ�ͨ");
					                menGangJdq.setKgl3Open(true);
					            }
					            else { 
					                System.out.println("������3:�ѶϿ�");
					                menGangJdq.setKgl3Open(false);
					            }
					            
						    	int kglNum4=4;
						    	char state4 = tb.charAt(kglNum4-1);
					            if(state4=='1'){
					                System.out.println("������4:�ѵ�ͨ");
					                menGangJdq.setKgl4Open(true);
					            }
					            else { 
					                System.out.println("������4:�ѶϿ�");
					                menGangJdq.setKgl4Open(false);
					            }
							}
						}
						else {
							System.out.println("���ݶ�ȡ��ϣ�");
							client.close();
							break;
						}
					} catch (SocketException err) {
						// TODO Auto-generated catch block
						//err.printStackTrace();
						System.out.println("�ѶϿ�����");
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

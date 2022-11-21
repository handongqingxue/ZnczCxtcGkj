package com.znczCxtcGkj.util;

import gnu.io.*;

import javax.sound.midi.SoundbankResource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

/**
 * 使用rxtx连接串口工具类
 */
public class RXTXUtil {
    /**
     * 返回结果
     */
    private static String res=null;
    
    
    /**
     * 	获得系统可用的串口名称列表
     * @return 可用串口名称列表
     */
    @SuppressWarnings("unchecked")
    public static List<String> getSystemUseAblePort(){
        List<String> systemPorts = new ArrayList<>();
        //获得系统可用的串口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        while(portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();//获得端口的名称
            systemPorts.add(portName);
        }
        return systemPorts;
    }
    
    /**
     * 验证串口是否可用
     * @param serialPortName
     * @return
     */
    public static boolean checkPortUseAble(String serialPortName) {
    	boolean useAble=false;
    	List<String> systemPortNameList = getSystemUseAblePort();
    	for (String systemPortName : systemPortNameList) {
    		if(systemPortName.equals(serialPortName)) {
            	useAble=true;
            	break;
            }
		}
    	return useAble;
    }

    /**
     * 	�?启串�?
     * @param serialPortName 串口名称
     * @param baudRate 波特�?
     * @return 串口对象
     */
    public static SerialPort openSerialPort(String serialPortName,int baudRate) {
    	SerialPort serialPort=null;
        try {
            //通过端口名称得到端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(serialPortName);
            //打开端口，（自定义名字，打开超时时间�?
            CommPort commPort = portIdentifier.open(serialPortName, 50);
            //判断是不是串�?
            if (commPort instanceof SerialPort) {
                serialPort = (SerialPort) commPort;
                //设置串口参数（波特率，数据位8，停止位1，校验位无）
                serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                //    System.out.println("�?启串口成功，串口名称�?"+serialPortName);
            }else {
                //是其他类型的端口
                throw new NoSuchPortException();
            }
        } catch (NoSuchPortException e) {
            e.printStackTrace();
        } catch (PortInUseException e) {
            e.printStackTrace();
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        }

        return serialPort;
    }

    /**
     * 	向串口发送数据
     * @param serialPort
     * @param order
     */
    public static void sendData(SerialPort serialPort,String order) {
    	System.out.println("order==="+order);
        //16进制表示的字符串转换为字节数据
        byte[] data =HexadecimalUtil.hexStringToByteArray(order);
        OutputStream os = null;
        try {
            os = serialPort.getOutputStream();//获得串口的输出流
            os.write(data);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流操作
            try {
                if (os != null) {
                    os.close();
                    os = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 上位机往单板机通过串口发送数据
     * 串口对象 seriesPort
     * 数据帧:dataPackage
     * 发送的标志:数据未发送成功抛出一个异常
     */
    public static void sendData(SerialPort serialPort,byte[] dataPackage) {
        OutputStream out=null;
        try {
            out=serialPort.getOutputStream();
            out.write(dataPackage);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭输出流
            if(out!=null) {
                try {
                    out.close();
                    out=null;
                    //System.out.println("数据已发送完毕!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 	从串口读取数据
     * @return 读取的数据
     */
    public static String readData(SerialPort serialPort) {
        //保存串口返回信息
        StringBuffer res=new StringBuffer(40);
        InputStream is = null;
        byte[] bytes = null;
        try {
            is = serialPort.getInputStream();//获得串口的输入流
            int bufflenth = is.available();//获得数据长度
            while (bufflenth != 0) {
                bytes = new byte[bufflenth];//初始化byte数组
                is.read(bytes);
                bufflenth = is.available();
            }
            if(bytes!=null) {
                for (int i = 0; i < bytes.length; i++) {
                    //转换�?16进制数（FF�?
                    res.append(HexadecimalUtil.get16Num((bytes[i]&0xff)));
                }
            }
            System.out.println("res: "+res.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                    is = null;
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        return res.toString();
    }

    /**
     * 上位机接收数据
     * 串口对象seriesPort
     * 接收数据buffer
     * 返回一个byte数组
     */
    public static byte[] readByteData(SerialPort serialPort,long timeSpace) {
        byte[] receiveDataPackage=null;
        InputStream in=null;
        try {
            //Thread.sleep(500);
        	if(timeSpace>0)//从地磅里读取数据需要休眠
        		Thread.sleep(timeSpace);
            in=serialPort.getInputStream();
            // 获取data buffer数据长度
            int bufferLength=in.available();
            while(bufferLength!=0) {
                receiveDataPackage=new byte[bufferLength];
                in.read(receiveDataPackage);
                bufferLength=in.available();

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                	in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return receiveDataPackage;
    }

    /**
     * 关闭串口
     *
     */
    public static void closeSerialPort(SerialPort serialPort) {
        if(serialPort != null) {
            serialPort.close();
            //System.out.println("关闭了串口："+serialPort.getName());
            serialPort = null;
        }
    }
    
    public static void main(String[] args) {
    	try {
    		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM4");
    		System.out.println("portIdentifier==="+portIdentifier);
		} catch (NoSuchPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
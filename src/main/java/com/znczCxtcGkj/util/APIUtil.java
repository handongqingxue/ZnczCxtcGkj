package com.znczCxtcGkj.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import net.sf.jmimemagic.Magic;

import net.sf.jmimemagic.MagicMatch;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.znczCxtcGkj.entity.*;

public class APIUtil {
	
	public static InputStream getPngFile(String spec) {
		InputStream is = null;
		try {
			URL url = new URL(spec);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");//请求GET方式
			connection.setDoInput(true); 
			connection.setDoOutput(true); 
			//header内的的参数在这里set    
			//connection.setRequestProperty("key", "value");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.connect(); 
			
			is = connection.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return is;
	}

	//https://www.cnblogs.com/aeolian/p/7746158.html
	//https://www.cnblogs.com/bobc/p/8809761.html
	public static JSONObject doHttp(String method, Map<String, Object> paramMap) throws IOException {
		// 构建请求参数  
        StringBuffer paramsSB = new StringBuffer();
		if (paramMap != null) {  
            for (Entry<String, Object> e : paramMap.entrySet()) {
            	paramsSB.append(e.getKey());  
            	paramsSB.append("=");  
            	paramsSB.append(e.getValue());  
            	paramsSB.append("&");  
            }  
            paramsSB.substring(0, paramsSB.length() - 1);  
        }  
		
		StringBuffer sbf = new StringBuffer(); 
		String strRead = null; 
		URL url = new URL(Constant.SERVICE_URL+method);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("POST");//请求post方式
		connection.setDoInput(true); 
		connection.setDoOutput(true); 
		//header内的的参数在这里set    
		//connection.setRequestProperty("key", "value");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.connect(); 
		
		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(),"UTF-8"); 
		//OutputStream writer = connection.getOutputStream(); 
		String paramsStr = paramsSB.toString();
		//System.out.println("paramsStr==="+paramsStr);
		writer.write(paramsStr);
		writer.flush();
		InputStream is = connection.getInputStream(); 
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		while ((strRead = reader.readLine()) != null) {
			sbf.append(strRead); 
			sbf.append("\r\n"); 
		}
		reader.close();
		
		connection.disconnect();
		String result = sbf.toString();
		System.out.println("result==="+result);
		JSONObject resultJO = new JSONObject(result);
		return resultJO;
	}
	
	/**
	* 上传图片
	* @param urlStr
	* @param textMap
	* @param fileMap
	* @return
	*/
	public static JSONObject formUpload(String method, Map textMap, Map fileMap) {
		//https://blog.csdn.net/weixin_35674742/article/details/114192180
		String result = null;
		String urlStr = null;
		HttpURLConnection conn = null;
		String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符
		try {
			urlStr=Constant.SERVICE_URL+method;
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			
			OutputStream out = new DataOutputStream(conn.getOutputStream());
		
			//text
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					//System.out.println("inputName="+inputName);
					//System.out.println("inputValue="+inputValue);
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}
	
			//file
			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();
					MagicMatch match = Magic.getMagicMatch(file, false, true);
					String contentType = match.getMimeType();
					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename + "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
		
					out.write(strBuf.toString().getBytes());
		
					DataInputStream in = new DataInputStream(new FileInputStream(file));
		
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}
	
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();
	
			// 读取返回数据
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			result = strBuf.toString();
			System.out.println("result==="+result);
		
			reader.close();
			reader = null;
		} 
		catch (Exception e) {
			System.out.println("发送POST请求出错。" + urlStr);
			e.printStackTrace();
		} 
		finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		JSONObject resultJO = null;
		if(!StringUtils.isBlank(result))
			resultJO = new JSONObject(result);
		return resultJO;
	}


	public static JSONObject getDingDanBySfzhZt(String sfzh, String ddztMc) {
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("sfzh", sfzh);  
	        parames.put("ddztMc", ddztMc);
	        resultJO = doHttp("getDingDanBySfzhZt",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	public static JSONObject getDingDanByCphZts(String cph, String ddztMcs) {
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("cph", cph);  
	        parames.put("ddztMcs", ddztMcs);
	        resultJO = doHttp("getDingDanByCphZts",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}

	/*
	public static JSONObject getDingDan(String cph, String ddztMc) {
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("cph", cph);  
	        parames.put("ddztMc", ddztMc);
	        resultJO = doHttp("getDingDan",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}
	*/
	
	public static JSONObject editDingDan(DingDan dd) {
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("id", dd.getId());
	        parames.put("ddztMc", dd.getDdztMc());
	        parames.put("ckcs", dd.getCkcs());
	        parames.put("yjbfh", dd.getYjbfh());
	        parames.put("ejbfh", dd.getEjbfh());
	        resultJO = doHttp("editDingDan",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}
	
	public static JSONObject getJhPdHMList() {
		JSONObject resultJO = null;
		try {
	        resultJO = doHttp("getJhPdHMList",null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}
	
	public static JSONObject newHaoMa(Long ddId) {
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("ddId", ddId);  
	        resultJO = doHttp("newHaoMa",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}
	
	public static JSONObject editHaoMa(HaoMa hm) {
		JSONObject resultJO = null;
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("id", hm.getId());
	        parames.put("hmztMc", hm.getHmztMc());
	        parames.put("ddId", hm.getDdId());
	        resultJO = doHttp("editHaoMa",parames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}
	
	public static JSONObject uploadCheLiangTaiZhang(CheLiangTaiZhang cltz,int actionFlag) {
		JSONObject resultJO = null;
		try {
			Map textMap = new HashMap<String, String>();
			textMap.put("ddId", cltz.getDdId());
			textMap.put("actionFlag", actionFlag);

			Map fileMap = new HashMap<String, Object>();
			String zpFilePath = "D:/wear/202211/IMG_20221105_120734.jpg";
			fileMap.put("zp_file", zpFilePath);
			
	        resultJO = formUpload("uploadCheLiangTaiZhang",textMap,fileMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}
	
	public static JSONObject testFile() {
		String file1Path = "D:/wear/202211/IMG_20221105_120734.jpg";
		File file1 = new File(file1Path);
		String file2Path = "D:/wear/202211/IMG_20221104_200132.jpg";
		File file2 = new File(file2Path);
		JSONObject resultJO = null;
		try {
			Map textMap = new HashMap<String, Object>();
			textMap.put("jcsj", "1997-07-01");
			
			Map fileMap = new HashMap<String, Object>();
			System.out.println("size1==="+file1.length());
			System.out.println("size2==="+file2.length());
			fileMap.put("file1", file1Path);
			fileMap.put("file2", file2Path);
			resultJO = formUpload("testFile",textMap,fileMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return resultJO;
		}
	}
	
	public static void main(String[] args) {
		testFile();
	}
}

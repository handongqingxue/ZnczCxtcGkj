package com.znczCxtcGkj.idreader;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.znczCxtcGkj.netty.HttpClient;
import com.znczCxtcGkj.netty.HttpClientHandler;
import com.znczCxtcGkj.util.*;

import io.netty.handler.codec.http.HttpRequest;

/**
 * ���֤������������
 * @author lenovo
 *
 */
public class IdReaderUtil {

	static Logger logger = LoggerFactory.getLogger(IdReaderUtil.class);

	/**
	 *  �������֤������
	 */
	public static boolean openDevice() {
		boolean flag = false;
		long startTime = System.currentTimeMillis();
		
		String idReaderIp = LoadProperties.getIdReaderIp();
		String idReaderPort = LoadProperties.getIdReaderPort();
		String url = "http://"+idReaderIp+":"+idReaderPort+"/OpenDevice";
		Map<String, String> getData = new HashMap<String, String>();
//	        getData.put("tags", "806:938356;");
//	        getData.put("sort", "_p");

		Map<String, String> header = new HashMap<String, String>();
		header.put("Sec-Fetch-Dest", "empty");
		header.put("Sec-Fetch-Mode", "cors");
		header.put("Sec-Fetch-Site", "same-origin");
		HttpClient client = new HttpClient();
		HttpRequest get;
		try {
			get = client.getRequestMethod(header, getData, url, "get");
			HttpClientHandler handler = new HttpClientHandler();
			client.run(url, get, handler);

			// Thread.sleep(10000);
			logger.debug("the result:{}", handler.getResult());
			
			String result = handler.getResult();
			JSONObject parseObject = JSON.parseObject(result);
			// ����
			Integer resultFlag = (Integer) parseObject.get("resultFlag");
			if (resultFlag == 0) {
				logger.info("�������֤�������ɹ� ");
				flag = true;
			} else {
				String errorMsg = (String) parseObject.get("errorMsg");
				logger.info("�������֤������ʧ�ܣ� ", errorMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 *  ���ض�ȡ�����֤��Ϣ
	 */
	public static String readData() {

		long startTime = System.currentTimeMillis();
		
		String idReaderIp = LoadProperties.getIdReaderIp();
		String idReaderPort = LoadProperties.getIdReaderPort();
		String url = "http://"+idReaderIp+":"+idReaderPort+"/readcard";
		Map<String, String> getData = new HashMap<String, String>();
//	        getData.put("tags", "806:938356;");
//	        getData.put("sort", "_p");

		Map<String, String> header = new HashMap<String, String>();
		header.put("Sec-Fetch-Dest", "empty");
		header.put("Sec-Fetch-Mode", "cors");
		header.put("Sec-Fetch-Site", "same-origin");
		HttpClient client = new HttpClient();
		HttpRequest get;
		try {
			get = client.getRequestMethod(header, getData, url, "get");
			HttpClientHandler handler = new HttpClientHandler();
			client.run(url, get, handler);

			// Thread.sleep(10000);
			logger.debug("the result:{}", handler.getResult());
			return handler.getResult();
			//Thread.sleep(10000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();// ��¼����ʱ��
		logger.debug(((endTime - startTime) / 1000) + "");
		return null;
	}
}

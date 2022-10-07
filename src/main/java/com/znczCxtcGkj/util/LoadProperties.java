package com.znczCxtcGkj.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ���������ļ���
 * 
 * @author lhb
 *
 */
public class LoadProperties {
	static Logger logger = LoggerFactory.getLogger(LoadProperties.class);
	private static Properties prop = null;
	private static final int CURRENT_BF_NO=1;//1.������(�°���) 2.ҫ��(�ɰ���)
	private static final int YI_HAO_BANG_FANG=1;
	private static final int ER_HAO_BANG_FANG=2;
	//private static final boolean IS_TEST=true;//�Ƿ��ǲ���
	private static final boolean IS_TEST=false;//�Ƿ��ǲ���

	static {
		prop = Method2();
	}

	private static synchronized Properties Method2() {
		Properties prop = null;
		try {
			InputStream inputStream = null;
			switch (CURRENT_BF_NO) {
			case YI_HAO_BANG_FANG:
				if(IS_TEST)
					inputStream = LoadProperties.class.getResourceAsStream("/config/configBf1.properties");
				else
					inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("configBf1.properties");
				break;
			case ER_HAO_BANG_FANG:
				if(IS_TEST)
					inputStream = LoadProperties.class.getResourceAsStream("/config/configBf2.properties");
				else
					inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("configBf2.properties");
				break;
			}
			System.out.println("inputStream==="+inputStream);

			prop = new Properties();

			prop.load(inputStream);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * ���������ļ�
	 * 
	 * @return
	 */
	public static Properties getProperties() {
		if (prop == null) {
			prop = Method2();
		}
		return prop;
	}

	/**
	 * ��ȡip��ַ
	 * 
	 * @return
	 */
	public static String getIp() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("ip").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������ip��ַ");
			return null;
		}

		return trim;
	}

	/**
	 * ��ȡ�˿ں�
	 * 
	 * @return
	 */
	public static String getPort() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("port").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ������ö˿ں�port");
			return null;
		}

		return trim;
	}

	public static String getServiceName() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("serviceName").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������serviceName");
			return null;
		}

		return trim;
	}

	/**
	 * ��ȡ����http�����ַ
	 * 
	 * @return
	 */
	public static String getBaseUrl() {
		String url = "http://" + getIp() + ":" + getPort() + "/" + getServiceName();
		return url;
	}

	/**
	 * ��ȡ����������ַ
	 * 
	 * @return
	 */
	public static String getServiceBaseUrl() {
		String url = getBaseUrl() + "/api2/ks/c";
		return url;
	}

	/**
	 * ��ѯ�к��е��ŶӺ�
	 * 
	 * @return
	 */
	public static String getCallNumberUrl() {
		String url = getBaseUrl() + "/api2/ks/clist/callingnumber";
		return url;
	}

	/**
	 * ��ȡ����λ��������ѯ����url
	 * 
	 * @return
	 */
	public static String getUpperUrl() {
		String url = getBaseUrl() + "/api2/ks/c/upper_computer_order";
		return url;
	}

	/**
	 * ��ȡ�����¶�������url
	 * 
	 * @return
	 */
	public static String getupdateorderUrl() {
		String url = getBaseUrl() + "/api2/ks/c/updateorder";
		return url;
	}

	/**
	 * ���³���̨�� update_vehicleaccount ��һʵ����� ��Ҫ�����ϴ���Ƭ
	 * 
	 * @return
	 */
	public static String getupdateVehicleaccountUrl() {
		String url = getBaseUrl() + "/api2/ks/c/update_vehicleaccount";
		return url;
	}

	/**
	 * ��ȡ#����֤��������ip��ַ
	 * 
	 * @return
	 */
	public static String getIdReaderIp() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("idReaderIp").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������idReaderIp");
			return null;
		}

		return trim;
	}

	/**
	 * ��ȡ#����֤��������ip�˿ں�
	 * 
	 * @return
	 */
	public static String getIdReaderPort() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("idReaderPort").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������idReaderPort");
			return null;
		}

		return trim;
	}

	/**
	 * ��ȡ##OPCServer ��������ַ
	 * 
	 * @return
	 */
	public static String getOpcHost() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("opcHost").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������opcHost");
			return null;
		}

		return trim;
	}

	/**
	 * ��ȡ##OPCServer opcServerProgID
	 * 
	 * @return
	 */
	public static String getOpcServerProgID() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("opcServerProgID").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������opcServerProgID");
			return null;
		}

		return trim;
	}

	/**
	 * ��ȡ##OPCServer opcServerClientHandle
	 * 
	 * @return
	 */
	public static String getOpcServerClientHandle() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("opcServerClientHandle").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������opcServerClientHandle");
			return null;
		}

		return trim;
	}

	/**
	 * ��ȡ##OPCServer ���� opcGroupJin
	 * 
	 * @return
	 */
	public static String getOpcGroupJin() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("opcGroupJin").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������opcGroupJin");
			return null;
		}

		return trim;
	}

	/**
	 * ��ȡ##OPCServer ���� opcItemJin
	 * 
	 * @return
	 */
	public static String getOpcItemJin() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("opcItemJin").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������opcItemJin");
			return null;
		}

		return trim;
	}

	/**
	 * ��ȡ##OPCServer ���� opcGroupChu
	 * 
	 * @return
	 */
	public static String getOpcGroupChu() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("opcGroupChu").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������opcGroupChu");
			return null;
		}

		return trim;
	}

	/**
	 * ��ȡ##OPCServer ���� opcItemChu
	 * 
	 * @return
	 */
	public static String getOpcItemChu() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("opcItemChu").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������opcItemChu");
			return null;
		}

		return trim;
	}

	/**
	 * ������������
	 * 
	 * @return
	 */
	public static String getYinZhuCom() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("yinZhuCom").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������yinZhuCom");
			return null;
		}

		return trim;
	}

	/////////////////////
	/**
	 * #��������ͷ��ip hikvisionIP
	 * 
	 * @return
	 */
	public static String getHikvisionIP() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("hikvisionIP").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������hikvisionIP");
			return null;
		}
		return trim;
	}

	/**
	 * #��������ͷ�Ķ˿� hikvisionPort
	 * 
	 * @return
	 */
	public static String getHikvisionPort() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("hikvisionPort").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������hikvisionPort");
			return null;
		}
		return trim;
	}

	/**
	 * #����ͷ�û��� hikvisionUserName
	 * 
	 * @return
	 */
	public static String getHikvisionUserName() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("hikvisionUserName").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������hikvisionUserName");
			return null;
		}
		return trim;
	}

	/**
	 * #����ͷ������ hikvisionPassword
	 * 
	 * @return
	 */
	public static String getHikvisionPassword() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("hikvisionPassword").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������hikvisionPassword");
			return null;
		}
		return trim;
	}

	/**
	 * ��ü̵���ip
	 * @return
	 */
	public static String getJdqIp() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("jdqIp").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������jdqIp");
			return null;
		}
		return trim;
	}
	
	/**
	 * ��ü̵����˿ں�
	 * @return
	 */
	public static Integer getJdqPort() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("jdqPort").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������jdqPort");
			return null;
		}
		
		int parseInt = Integer.parseInt(trim);
		
		return parseInt;
	}

	/**
	 * ��ü̵�������
	 * @return
	 */
	public static Integer getJdqMaiChong() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("jdqMaiChong").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������jdqMaiChong");
			return null;
		}
		
		int parseInt = Integer.parseInt(trim);
		
		return parseInt;
	}
	
	/**
	 * ��÷�����ip
	 * @return
	 */
	public static String getServerIp() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("serverIp").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������serverIp");
			return null;
		}
		
		return trim;
	}

	/**
	 * ��ð�����
	 * @return
	 */
	public static Integer getBangFangHao() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("bangFangHao").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������bangFangHao");
			return null;
		}
		
		int parseInt = Integer.parseInt(trim);
		
		return parseInt;
	}

	/**
	 * ��õذ�Com�ں�
	 * 
	 * @return
	 */
	public static String getDiBangCom() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("diBangCom").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������diBangCom");
			return null;
		}

		return trim;
	}
	
	
	
	///////////////////////���´����Ǵ�����������Ŀ���������ģ���ʱ�ò���
	/**
	 * 
	 * #���ö�ά�뻹������IF��, value: QR or  IF
	 * @return 
	 */
	public static String getOpenIForQR() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("openIForQR").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������openIForQR");
			return null;
		}
		
		return trim;
	}
	
	/**
	 * 
	 * #��������������
	 * @return 
	 */
	public static String getIfWriterPort() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ifWriterPort").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������ifWriterPort");
			return null;
		}
		
		return trim;
	}
	
	/**
	 * 
	 * #���������ڲ�����
	 * @return 
	 */
	public static Integer getIfWriterBaudrate() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ifWriterBaudrate").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������ifWriterBaudrate");
			return null;
		}
		
		int parseInt = Integer.parseInt(trim);
		
		return parseInt;
	}
	
	
	
	/**
	 * 
	 * #����LEDip
	 * @return 
	 */
	public static String getLedIp() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ledIp").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������ledIp");
			return null;
		}
		
		return trim;
	}
	
	
	/**
	 * #������������
	 * @return 
	 */
	public static String getLedTitleFontType() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ledTitleFontType").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������ledTitleFontType");
			return null;
		}
		
		return trim;
	}
	
	/**
	 * #���������С
	 * @return 
	 */
	public static Integer getLedTitleFontSize() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ledTitleFontSize").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������ledTitleFontSize");
			return null;
		}
		
		int parseInt = Integer.parseInt(trim);
		return parseInt;
	}
	
	
	/**
	 * #������������
	 * @return 
	 */
	public static String getLedContentFontType() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ledContentFontType").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������ledContentFontType");
			return null;
		}
		
		return trim;
	}
	
	/**
	 *#���������С
	 * @return 
	 */
	public static Integer getLedContentFontSize() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ledContentFontSize").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������ledContentFontSize");
			return null;
		}
		
		int parseInt = Integer.parseInt(trim);
		return parseInt;
	}
	
	
	
	/**
	 * #��˾����
	 * @return 
	 */
	public static String getLedFisTitleName() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ledFisTitleName");
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������ledFisTitleName");
			return null;
		}
		
		return trim;
	}
	
	/**
	 *#��˾���ƴ�С
	 * @return 
	 */
	public static Integer getLedFisTitleSize() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ledFisTitleSize").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������ledFisTitleSize");
			return null;
		}
		
		int parseInt = Integer.parseInt(trim);
		return parseInt;
	}

	/**
	 * #����ʶ��洢·��
	 * @return 
	 */
	public static String getPhotoPath() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("photoPath").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("���������ļ�������photoPath");
			return null;
		}
		
		return trim;
	}
	
	

}
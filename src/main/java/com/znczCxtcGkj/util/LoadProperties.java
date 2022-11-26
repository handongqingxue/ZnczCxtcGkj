package com.znczCxtcGkj.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加载配置文件类
 * 
 * @author lhb
 *
 */
public class LoadProperties {
	static Logger logger = LoggerFactory.getLogger(LoadProperties.class);
	private static Properties prop = null;
	private static final int CURRENT_PLACE_FLAG=Constant.MEN_GANG;
	public static int getCurrentPlaceFlag() {
		return CURRENT_PLACE_FLAG;
	}

	private static final boolean IS_TEST=true;//是否是测试
	//private static final boolean IS_TEST=false;//是否是测试

	static {
		prop = Method2();
	}

	private static synchronized Properties Method2() {
		Properties prop = null;
		try {
			InputStream inputStream = null;
			switch (CURRENT_PLACE_FLAG) {
			case Constant.YI_HAO_BANG_FANG:
				if(IS_TEST)
					inputStream = LoadProperties.class.getResourceAsStream("/config/configBf1.properties");
				else
					inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("configBf1.properties");
				break;
			case Constant.ER_HAO_BANG_FANG:
				if(IS_TEST)
					inputStream = LoadProperties.class.getResourceAsStream("/config/configBf2.properties");
				else
					inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("configBf2.properties");
				break;
			case Constant.SAN_HAO_BANG_FANG:
				if(IS_TEST)
					inputStream = LoadProperties.class.getResourceAsStream("/config/configBf3.properties");
				else
					inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("configBf3.properties");
				break;
			case Constant.MEN_GANG:
				if(IS_TEST)
					inputStream = LoadProperties.class.getResourceAsStream("/config/configMg.properties");
				else
					inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("configMg.properties");
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
	 * 返回配置文件
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
	 * 获取ip地址
	 * 
	 * @return
	 */
	public static String getIp() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("ip").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置ip地址");
			return null;
		}

		return trim;
	}

	/**
	 * 获取端口号
	 * 
	 * @return
	 */
	public static String getPort() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("port").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置端口号port");
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
			logger.debug("请在配置文件中配置serviceName");
			return null;
		}

		return trim;
	}

	/**
	 * 获取基本http请求地址
	 * 
	 * @return
	 */
	public static String getBaseUrl() {
		String url = "http://" + getIp() + ":" + getPort() + "/" + getServiceName();
		return url;
	}

	/**
	 * 获取轻服务基本地址
	 * 
	 * @return
	 */
	public static String getServiceBaseUrl() {
		String url = getBaseUrl() + "/api2/ks/c";
		return url;
	}

	/**
	 * 查询叫号中的排队号
	 * 
	 * @return
	 */
	public static String getCallNumberUrl() {
		String url = getBaseUrl() + "/api2/ks/clist/callingnumber";
		return url;
	}

	/**
	 * 获取【上位机订单查询】的url
	 * 
	 * @return
	 */
	public static String getUpperUrl() {
		String url = getBaseUrl() + "/api2/ks/c/upper_computer_order";
		return url;
	}

	/**
	 * 获取【更新订单】的url
	 * 
	 * @return
	 */
	public static String getupdateorderUrl() {
		String url = getBaseUrl() + "/api2/ks/c/updateorder";
		return url;
	}

	/**
	 * 更新车辆台账 update_vehicleaccount 单一实体更新 主要用于上传照片
	 * 
	 * @return
	 */
	public static String getupdateVehicleaccountUrl() {
		String url = getBaseUrl() + "/api2/ks/c/update_vehicleaccount";
		return url;
	}

	/**
	 * 获取#身份证读卡器的ip地址
	 * 
	 * @return
	 */
	public static String getIdReaderIp() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("idReaderIp").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置idReaderIp");
			return null;
		}

		return trim;
	}

	/**
	 * 获取#身份证读卡器的ip端口号
	 * 
	 * @return
	 */
	public static String getIdReaderPort() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("idReaderPort").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置idReaderPort");
			return null;
		}

		return trim;
	}

	/**
	 * 获取##OPCServer 的主机地址
	 * 
	 * @return
	 */
	public static String getOpcHost() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("opcHost").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置opcHost");
			return null;
		}

		return trim;
	}

	/**
	 * 获取##OPCServer opcServerProgID
	 * 
	 * @return
	 */
	public static String getOpcServerProgID() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("opcServerProgID").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置opcServerProgID");
			return null;
		}

		return trim;
	}

	/**
	 * 获取##OPCServer opcServerClientHandle
	 * 
	 * @return
	 */
	public static String getOpcServerClientHandle() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("opcServerClientHandle").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置opcServerClientHandle");
			return null;
		}

		return trim;
	}

	/**
	 * 获取##OPCServer 进门 opcGroupJin
	 * 
	 * @return
	 */
	public static String getOpcGroupJin() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("opcGroupJin").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置opcGroupJin");
			return null;
		}

		return trim;
	}

	/**
	 * 获取##OPCServer 进门 opcItemJin
	 * 
	 * @return
	 */
	public static String getOpcItemJin() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("opcItemJin").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置opcItemJin");
			return null;
		}

		return trim;
	}

	/**
	 * 获取##OPCServer 出门 opcGroupChu
	 * 
	 * @return
	 */
	public static String getOpcGroupChu() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("opcGroupChu").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置opcGroupChu");
			return null;
		}

		return trim;
	}

	/**
	 * 获取##OPCServer 出门 opcItemChu
	 * 
	 * @return
	 */
	public static String getOpcItemChu() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("opcItemChu").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置opcItemChu");
			return null;
		}

		return trim;
	}

	/**
	 * 音柱串口配置
	 * 
	 * @return
	 */
	public static String getYinZhuCom() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("yinZhuCom").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置yinZhuCom");
			return null;
		}

		return trim;
	}
	/**
	 * #进厂车辆摄像头ip hikvisionJinChangIP
	 * 
	 * @return
	 */
	public static String gethikvisionJinChangIP() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("hikvisionJinChangIP").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置hikvisionJinChangIP");
			return null;
		}
		return trim;
	}

	/**
	 * 进厂车辆摄像头端口 hikvisionJinChangPort
	 * 
	 * @return
	 */
	public static String gethikvisionJinChangPort() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("hikvisionJinChangPort").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置hikvisionJinChangPort");
			return null;
		}
		return trim;
	}

	/**
	 * 进厂摄像头用户名 hikvisionJinChangUserName
	 * 
	 * @return
	 */
	public static String gethikvisionJinChangUserName() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("hikvisionJinChangUserName").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置hikvisionJinChangUserName");
			return null;
		}
		return trim;
	}

	/**
	 * 进厂摄像头密码 hikvisionJinChangPassword
	 * 
	 * @return
	 */
	public static String gethikvisionJinChangPassword() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("hikvisionJinChangPassword").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置hikvisionJinChangPassword");
			return null;
		}
		return trim;
	}

	//东车牌识别摄像头start
	/**
	 * #车辆摄像头的ip hikvisionEastIP
	 * 
	 * @return
	 */
	public static String getHikvisionEastIP() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("hikvisionEastIP").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置hikvisionEastIP");
			return null;
		}
		return trim;
	}

	/**
	 * #车辆摄像头的端口 hikvisionEastPort
	 * 
	 * @return
	 */
	public static String getHikvisionEastPort() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("hikvisionEastPort").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置hikvisionEastPort");
			return null;
		}
		return trim;
	}

	/**
	 * #摄像头用户名 hikvisionEastUserName
	 * 
	 * @return
	 */
	public static String getHikvisionEastUserName() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("hikvisionEastUserName").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置hikvisionEastUserName");
			return null;
		}
		return trim;
	}

	/**
	 * #摄像头的密码 hikvisionEastPassword
	 * 
	 * @return
	 */
	public static String getHikvisionEastPassword() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("hikvisionEastPassword").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置hikvisionEastPassword");
			return null;
		}
		return trim;
	}
	//东车牌识别摄像头end


	//西车牌识别摄像头start
	/**
	 * #车辆摄像头的ip hikvisionWestIP
	 * 
	 * @return
	 */
	public static String getHikvisionWestIP() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("hikvisionWestIP").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置hikvisionWestIP");
			return null;
		}
		return trim;
	}

	/**
	 * #车辆摄像头的端口 hikvisionWestPort
	 * 
	 * @return
	 */
	public static String getHikvisionWestPort() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("hikvisionWestPort").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置hikvisionWestPort");
			return null;
		}
		return trim;
	}

	/**
	 * #摄像头用户名 hikvisionWestUserName
	 * 
	 * @return
	 */
	public static String getHikvisionWestUserName() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("hikvisionWestUserName").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置hikvisionWestUserName");
			return null;
		}
		return trim;
	}

	/**
	 * #摄像头的密码 hikvisionWestPassword
	 * 
	 * @return
	 */
	public static String getHikvisionWestPassword() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("hikvisionWestPassword").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置hikvisionWestPassword");
			return null;
		}
		return trim;
	}
	//西车牌识别摄像头end
	

	/**
	 * 获得继电器ip
	 * @return
	 */
	public static String getJdqIp() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("jdqIp").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置jdqIp");
			return null;
		}
		return trim;
	}
	
	/**
	 * 获得继电器端口号
	 * @return
	 */
	public static Integer getJdqPort() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("jdqPort").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置jdqPort");
			return null;
		}
		
		int parseInt = Integer.parseInt(trim);
		
		return parseInt;
	}

	/**
	 * 获得继电器脉冲
	 * @return
	 */
	public static Integer getJdqMaiChong() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("jdqMaiChong").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置jdqMaiChong");
			return null;
		}
		
		int parseInt = Integer.parseInt(trim);
		
		return parseInt;
	}
	
	/**
	 * 叫号时间间隔(毫秒)
	 * @return
	 */
	public static Integer getCallNumberTimeSpace() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("callNumberTimeSpace").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置callNumberTimeSpace");
			return null;
		}
		
		int parseInt = Integer.parseInt(trim);
		
		return parseInt;
	}
	
	/**
	 * 过号时间(毫秒)
	 * @return
	 */
	public static Integer getPassNumberTime() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("passNumberTime").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置passNumberTime");
			return null;
		}
		
		int parseInt = Integer.parseInt(trim);
		
		return parseInt;
	}
	
	/**
	 * 获得服务器ip
	 * @return
	 */
	public static String getServerIp() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("serverIp").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置serverIp");
			return null;
		}
		
		return trim;
	}
	
	/**
	 * 获得tomcat端口号
	 * @return
	 */
	public static String getTomcatPort() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("tomcatPort").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置tomcatPort");
			return null;
		}
		
		return trim;
	}
	
	/**
	 * 获得socket端口号
	 * @return
	 */
	public static String getSocketPort() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("socketPort").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置socketPort");
			return null;
		}
		
		return trim;
	}

	/**
	 * 获得地点标识(门岗、磅房)
	 * @return
	 */
	public static Integer getPlaceFlag() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("placeFlag").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置placeFlag");
			return null;
		}
		
		int parseInt = Integer.parseInt(trim);
		
		return parseInt;
	}

	/**
	 * 获得地磅Com口号
	 * 
	 * @return
	 */
	public static String getDiBangCom() {
		if (prop == null) {
			prop = Method2();
		}

		String trim = prop.getProperty("diBangCom").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置diBangCom");
			return null;
		}

		return trim;
	}
	
	
	
	///////////////////////以下代码是从王工做的项目里拷贝过来的，暂时用不到
	/**
	 * 
	 * #启用二维码还是启用IF卡, value: QR or  IF
	 * @return 
	 */
	public static String getOpenIForQR() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("openIForQR").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置openIForQR");
			return null;
		}
		
		return trim;
	}
	
	/**
	 * 
	 * #发卡机串口配置
	 * @return 
	 */
	public static String getIfWriterPort() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ifWriterPort").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置ifWriterPort");
			return null;
		}
		
		return trim;
	}
	
	/**
	 * 
	 * #发卡机串口波特率
	 * @return 
	 */
	public static Integer getIfWriterBaudrate() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ifWriterBaudrate").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置ifWriterBaudrate");
			return null;
		}
		
		int parseInt = Integer.parseInt(trim);
		
		return parseInt;
	}
	
	
	
	/**
	 * 
	 * #配置LEDip
	 * @return 
	 */
	public static String getLedIp() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ledIp").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置ledIp");
			return null;
		}
		
		return trim;
	}
	
	
	/**
	 * #标题字体类型
	 * @return 
	 */
	public static String getLedTitleFontType() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ledTitleFontType").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置ledTitleFontType");
			return null;
		}
		
		return trim;
	}
	
	/**
	 * #标题字体大小
	 * @return 
	 */
	public static Integer getLedTitleFontSize() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ledTitleFontSize").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置ledTitleFontSize");
			return null;
		}
		
		int parseInt = Integer.parseInt(trim);
		return parseInt;
	}
	
	
	/**
	 * #内容字体类型
	 * @return 
	 */
	public static String getLedContentFontType() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ledContentFontType").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置ledContentFontType");
			return null;
		}
		
		return trim;
	}
	
	/**
	 *#内容字体大小
	 * @return 
	 */
	public static Integer getLedContentFontSize() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ledContentFontSize").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置ledContentFontSize");
			return null;
		}
		
		int parseInt = Integer.parseInt(trim);
		return parseInt;
	}
	
	
	
	/**
	 * #公司名称
	 * @return 
	 */
	public static String getLedFisTitleName() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ledFisTitleName");
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置ledFisTitleName");
			return null;
		}
		
		return trim;
	}
	
	/**
	 *#公司名称大小
	 * @return 
	 */
	public static Integer getLedFisTitleSize() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("ledFisTitleSize").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置ledFisTitleSize");
			return null;
		}
		
		int parseInt = Integer.parseInt(trim);
		return parseInt;
	}

	/**
	 * #车辆识别存储路径
	 * @return 
	 */
	public static String getPhotoPath() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("photoPath").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置photoPath");
			return null;
		}
		
		return trim;
	}
	
	/**
	 * #配置【计划运输日期】的可入厂时间， 例如：从零点算起， 未来32小时可进入，即第二天八点之前可以进入
	 * @return 
	 */
	public static Integer getIntoTheFactoryDate() {
		if (prop == null) {
			prop = Method2();
		}
		String trim = prop.getProperty("intoTheFactoryDate").trim();
		if (StringUtils.isBlank(trim)) {
			logger.debug("请在配置文件中配置intoTheFactoryDate");
			return null;
		}
		int parseInt = Integer.parseInt(trim);
		return parseInt;
	}
	
	

}
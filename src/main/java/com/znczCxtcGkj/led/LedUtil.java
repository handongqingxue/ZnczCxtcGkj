package com.znczCxtcGkj.led;

import org.apache.commons.lang.StringUtils;

import com.listenvision.led;
import com.znczCxtcGkj.util.LoadProperties;

//灵信led屏工具类
public class LedUtil {
	
	/**
	 * 发送内容到门岗屏幕, 192*192
	 * @param IpStr
	 * @param content1
	 * @param content2
	 * @param content3
	 * @param content4
	 * @param content5
	 * @param content6
	 * @param content7
	 * @param content8
	 * @return
	 */
	public static Integer sendProgramMultiLineToMenGang(String IpStr,String content1, 
			String content2, 
			String content3, 
			String content4, 
			String content5, 
			String content6, 
			String content7, 
			String content8) {
		int hProgram;
		// 新建一个节目
		hProgram=led.CreateProgram(192, 192, 1);
		
		Integer fisTitleSize = LoadProperties.getLedFisTitleSize();
		
		String titleFontType = LoadProperties.getLedTitleFontType();
		Integer titleFontSize = LoadProperties.getLedTitleFontSize();
		
		String contentFontType = LoadProperties.getLedContentFontType();
		Integer contentFontSize = LoadProperties.getLedContentFontSize();
		
		if (!StringUtils.isBlank(content1)) {
//			绑定节目到LED屏幕
			int addProgram = led.AddProgram(hProgram, 1, 500, 10);
			// 新建字幕1
			led.AddImageTextArea(hProgram, 1, 1, 0, 8, 192, 24, 1);
//			// 为区域1 赋值
			led.AddMultiLineTextToImageTextArea(hProgram, 1, 1, 0, content1, titleFontType, fisTitleSize, 0xff, 0, 0, 0, 0, 4, 2, 0, 0);
			
		}
		
		   if (!StringUtils.isBlank(content2)) {
			// 新建字幕2
				led.AddImageTextArea(hProgram, 1, 2, 0, 38, 192, 16, 1);
//				// 为区域2赋值
				led.AddMultiLineTextToImageTextArea(hProgram, 1, 2, 0, content2, titleFontType, titleFontSize, 0xff, 0, 0, 0, 0, 4, 2, 0, 0);
				
				
			}
		
		   if (!StringUtils.isBlank(content3)) {
			// 新建字幕3
				led.AddImageTextArea(hProgram, 1, 3, 0, 62, 192, 16, 1);
				// 为区域3赋值
				led.AddMultiLineTextToImageTextArea(hProgram, 1, 3, 0, content3, contentFontType, contentFontSize, 0xff, 0, 0, 0, 0, 4, 2, 0, 0);
			}
		   
		   if (!StringUtils.isBlank(content4)) {
			   // 新建字幕4
				led.AddImageTextArea(hProgram, 1, 4, 0, 82, 192, 16, 1);
				// 为区域4赋值
				led.AddMultiLineTextToImageTextArea(hProgram, 1, 4, 0, content4, contentFontType, contentFontSize, 0xff, 0, 0, 0, 0, 4, 2, 0, 0);
				
				
			}
		
		   if (!StringUtils.isBlank(content5)){
			   // 新建字幕5
				led.AddImageTextArea(hProgram, 1, 5, 0, 104, 192, 26, 1);
				// 为区域5赋值
				led.AddMultiLineTextToImageTextArea(hProgram, 1, 5, 0, content5, titleFontType, titleFontSize, 0xff, 0, 0, 0, 0, 4, 2, 0, 0);
			}
		   if (!StringUtils.isBlank(content6)) {
			   // 新建字幕6
				led.AddImageTextArea(hProgram, 1, 6, 0, 130, 192, 16, 1);
				// 为区域6赋值
				led.AddMultiLineTextToImageTextArea(hProgram, 1, 6, 0, content6, contentFontType, contentFontSize, 0xff, 0, 0, 0, 0, 4, 2, 0, 0);
			}
		
		   if (!StringUtils.isBlank(content7)) {

			   // 新建字幕7
				led.AddImageTextArea(hProgram, 1, 7, 0, 150, 192, 16, 1);
				// 为区域7赋值
				led.AddMultiLineTextToImageTextArea(hProgram, 1, 7, 0, content7, contentFontType, contentFontSize, 0xff, 0, 0, 0, 0, 4, 2, 0, 0);
				
			}
		
		   if (!StringUtils.isBlank(content8)) {
				  // 新建字幕8， // 为区域8赋值
				led.AddImageTextArea(hProgram, 1, 8, 0, 170, 192, 16, 1);
				led.AddMultiLineTextToImageTextArea(hProgram, 1, 8, 0, content8, titleFontType, titleFontSize, 0xff, 0, 0, 0, 0, 4, 2, 0, 0);
			}
		
		int netWorkSend = led.NetWorkSend(IpStr, hProgram);
		System.out.println("netWorkSend==="+netWorkSend);
		led.DeleteProgram(hProgram);
		return netWorkSend;
	}
	
	/**
	 * 发送一层内容到磅房led屏幕   192*32 
	 * @param IpStr
	 * @param content1
	 * @return
	 */
	public static Integer sendProgramMultiLineToBangFang(String IpStr,String content1) {
		int hProgram;
		//新建一个节目
		hProgram=led.CreateProgram(192, 32, 1);
		//绑定节目到LED屏幕
		led.AddProgram(hProgram, 1, 500, 10);
		//新建区域1
		led.AddImageTextArea(hProgram, 1, 1, 96, 0, 96, 32, 1);
		//为区域1 赋值
		led.AddMultiLineTextToImageTextArea(hProgram, 1, 1, 0, content1, "宋体", 9, 0xff, 0, 0, 0, 2, 4, 2, 1, 1);
		
		int netWorkSend = led.NetWorkSend(IpStr, hProgram);
		System.out.println("netWorkSend==="+netWorkSend);
		led.DeleteProgram(hProgram);
		return netWorkSend;
	}

	public static void test() {
		//加载dll报错解决方案：https://www.yisu.com/zixun/546321.html
		int hProgram;
		hProgram=led.CreateProgram(96, 32, 1);
		System.out.println("hProgram==="+hProgram);
		
		led.AddProgram(hProgram, 1, 0, 1);
		led.AddImageTextArea(hProgram, 1, 1, 0, 0, 96, 8, 1);
		led.AddMultiLineTextToImageTextArea(hProgram, 1, 1, 0, "待入厂车辆", "宋体", 6, 0xff, 0, 0, 0, 0, 4, 2, 0, 0);
		//led.AddFileToImageTextArea(hProgram, 1, 1, "your file full path", 1, 4, 2);
		
		led.AddImageTextArea(hProgram, 1, 2, 0, 8, 96, 8, 1);
		led.AddMultiLineTextToImageTextArea(hProgram, 1, 2, 0, "9001 9002 9003", "宋体", 6, 0xff, 0, 0, 0, 0, 4, 2, 0, 0);
		
		led.AddImageTextArea(hProgram, 1, 3, 0, 16, 96, 8, 1);
		led.AddMultiLineTextToImageTextArea(hProgram, 1, 3, 0, "9004 9005 9006", "宋体", 6, 0xff, 0, 0, 0, 0, 4, 2, 0, 0);

		/*
		led.AddProgram(hProgram, 2, 0, 1);
		led.AddImageTextArea(hProgram, 2, 1, 0, 0, 64, 16, 1);
		led.AddSinglelineTextToImageTextArea(hProgram, 2, 1, 0, "welcome to listen vision", "Tahoma", 12, 0xff, 0, 0, 0, 6, 4, 1);
		
		led.AddDigitalClockArea(hProgram, 2, 2, 0, 16, 64, 16, "Tahoma", 9, 0xff, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0xff, 0, 0xff, 2, 0xff);
		*/
		
		int netWorkSend = led.NetWorkSend("192.168.1.98", hProgram);
		System.out.println("netWorkSend==="+netWorkSend);
		led.DeleteProgram(hProgram);
	}
	
	public static void main(String[] args) {
		//LedUtil.test();
		System.out.println("LED\u914d\u7f6e\u5f00\u59cb");
	}
}

package com.znczCxtcGkj.led;

import com.listenvision.led;

public class LedBf1Util {

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
		LedBf1Util.test();
	}
}

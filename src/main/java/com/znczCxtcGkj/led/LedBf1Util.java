package com.znczCxtcGkj.led;

import com.listenvision.Led;

public class LedBf1Util {

	public static void test() {
		int hProgram;
		hProgram=Led.CreateProgram(64, 32, 1);
		Led.AddProgram(hProgram, 1, 0, 1);
		Led.AddImageTextArea(hProgram, 1, 1, 0, 0, 64, 32, 1);
		Led.AddMultiLineTextToImageTextArea(hProgram, 1, 1, 0, "hello world", "Tahoma", 9, 0xff, 0, 0, 0, 1, 4, 2, 1, 1);
		//Led.AddFileToImageTextArea(hProgram, 1, 1, "your file full path", 1, 4, 2);
		
		Led.AddProgram(hProgram, 2, 0, 1);
		Led.AddImageTextArea(hProgram, 2, 1, 0, 0, 64, 16, 1);
		Led.AddSinglelineTextToImageTextArea(hProgram, 2, 1, 0, "welcome to listen vision", "Tahoma", 12, 0xff, 0, 0, 0, 6, 4, 1);
		
		Led.AddDigitalClockArea(hProgram, 2, 2, 0, 16, 64, 16, "Tahoma", 9, 0xff, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0xff, 0, 0xff, 2, 0xff);
		
		System.out.println(Led.NetWorkSend("192.168.1.188", hProgram));
		Led.DeleteProgram(hProgram);
	}
	
	public static void main(String[] args) {
		LedBf1Util.test();
	}
}

package com.znczCxtcGkj.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	/**
	 * 十六进制字符串转化为字节数组
	 * @param hexString
	 * @return
	 */
	public static byte[] hexStringToByteArray(String hexString) {
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
        	
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character
                    .digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }
	
	/**
	 * 字节数组转化为十六进制
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
		String r = "";
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            r += hex.toUpperCase();
        }
        return r;
	}
	
	/**
	 * 把list 中的数据每三个组成一个字符串
	 * @param oldStrList
	 * @return
	 */
	public static List<String> getListTo3(List<String> oldStrList) {
		int count = 0;
		int countSize = 0;
		List<String> newStrList = new ArrayList<String>();
		String newStr = "";
		for (String oldStr : oldStrList) {
			count++;
			countSize++;
			if (count<=3) {
				newStr += oldStr + " ";
			}
			if (count == 3) {
				newStr = newStr.substring(0, newStr.length()-1);
				newStrList.add(newStr);
				newStr = "";
				count = 0;
			}
			
			int size = oldStrList.size();
			if (countSize == size) {
				newStrList.add(newStr);
			}
		}
			
		return newStrList;
	}
}

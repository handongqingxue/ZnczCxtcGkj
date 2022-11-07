package com.znczCxtcGkj.util;

/**
 * modbus 指令集
 * @author lenovo
 *
 */
public class ModBusUtil {
	/**
	 * 
	 * @return  的车辆入厂
	 */
	public static String get81() {
		return getModBusNum(81);
	}
	/**
	 * 
	 * @return  读取成功请
	 */
	public static String get82() {
		return getModBusNum(82);
	}
	/**
	 * 
	 * @return  请车牌号为
	 */
	public static String get83() {
		return getModBusNum(83);
	}
	
	/**
	 * 
	 * @return  身份识别已成功请拿走身份证
	 */
	public static String get84() {
		return getModBusNum(84);
	}
	
	/**
	 * @return  请等待
	 */
	public static String get85() {
		return getModBusNum(85);
	}
	
	/**
	 * @return  没有找到匹配订单
	 */
	public static String get86() {
		return getModBusNum(86);
	}
	
	/**
	 * 
	 * @return  请上磅把车辆停放在地磅中间
	 */
	public static String get87() {
		return getModBusNum(87);
	}
	
	/**
	 * 
	 * @return  停车熄火等待称重
	 */
	public static String get88() {
		return getModBusNum(88);
	}
	
	/**
	 * 
	 * @return  称重完成请下磅
	 */
	public static String get89() {
		return getModBusNum(89);
	}
	
	/**
	 * 
	 * @return  请确保车辆停在磅秤中间
	 */
	public static String get90() {
		return getModBusNum(90);
	}
	
	/**
	 * 
	 * @return  车牌识别失败，请联系相关人员
	 */
	public static String get91() {
		return getModBusNum(91);
	}
	
	/**
	 * 
	 * @return  车牌未识别，请移动车辆位置
	 */
	public static String get92() {
		return getModBusNum(92);
	}
	
	/**
	 * 
	 * @return  红外设备故障，请联系相关人员
	 */
	public static String get93() {
		return getModBusNum(93);
	}
	
	/**
	 * 
	 * @return  红外识别有误， 请联系管理人员
	 */
	public static String get94() {
		return getModBusNum(94);
	}
	
	/**
	 * 
	 * @return  称重失败， 请联系相关人员
	 */
	public static String get95() {
		return getModBusNum(95);
	}
	
	/**
	 * 
	 * @return  车牌号和订单不一致， 请联系相关人员
	 */
	public static String get96() {
		return getModBusNum(96);
	}
	
	public static void main(String[] args) {
		String modBusNum = getModBusNum(0);
		System.out.println(modBusNum);
	}
	
	
	/**
	 *  F0 带不带都可以， 0x 16进制前缀带不带都可以
	 * @param num 1-100 
	 * @return  1-100 所对应的modBus的指令
	 */
	public static String getModBusNum(int num) {
		
		switch (num) {
		case 0:
			return getModBusNum(36);
		case 1:
			return "0x01060004000109CB"; 
		case 2:
			return "0x01060004000249CA"; 
		case 3:
			return "0x010600040003880A"; 
		case 4:
			return "0x010600040004C9C8"; 
		case 5:
			return "0x0106000400050808"; 
		case 6:
			return "0x0106000400064809"; 
		case 7:
			return "0x01060004000789C9"; 
		case 8:
			return "0x010600040008C9CD"; 
		case 9:
			return "0x010600040009080D"; 
		case 10:
			return "0x01060004000A480C"; 
		case 11:
			return "0x01060004000B89CC"; 
		case 12:
			return "0x01060004000CC80E"; 
		case 13:
			return "0x01060004000D09CE"; 
		case 14:
			return "0x01060004000E49CF"; 
		case 15:
			return "0x01060004000F880F"; 
		case 16:
			return "0x010600040010C9C7"; 
		case 17:
			return "0x0106000400110807"; 
		case 18:
			return "0x0106000400124806"; 
		case 19:
			return "0x01060004001389C6"; 
		case 20:
			return "0x010600040014C804"; 
		case 21:
			return "0x01060004001509C4"; 
		case 22:
			return "0x01060004001649C5"; 
		case 23:
			return "0x0106000400178805";
		case 24:
			return "0x010600040018C801"; 
		case 25:
			return "0x01060004001909C1"; 
		case 26:
			return "0x01060004001A49C0"; 
		case 27:
			return "0x01060004001B8800"; 
		case 28:
			return "0x01060004001CC9C2"; 
		case 29:
			return "0x01060004001D0802"; 
		case 30:
			return "0x01060004001E4803"; 
		case 31:
			return "0x01060004001F89C3"; 
		case 32:
			return "0x010600040020C9D3"; 
		case 33:
			return "0x0106000400210813"; 
		case 34:
			return "0x0106000400224812"; 
		case 35:
			return "0x01060004002389D2"; 
		case 36: return "0x010600040024C810"; 
		case 37: return "0x01060004002509D0"; 
		case 38: return "0x01060004002649D1"; 
		case 39: return "0x0106000400278811"; 
		case 40: return "0x010600040028C815"; 
		case 41: return "0x01060004002909D5"; 
		case 42: return "0x01060004002A49D4"; 
		case 43: return "0x01060004002B8814"; 
		case 44: return "0x01060004002CC9D6"; 
		case 45: return "0x01060004002D0816"; 
		case 46: return "0x01060004002E4817"; 
		case 47: return "0x01060004002F89D7"; 
		case 48: return "0x010600040030C81F"; 
		case 49: return "0x01060004003109DF"; 
		case 50: return "0x01060004003249DE"; 
		case 51: return "0x010600040033881E"; 
		case 52: return "0x010600040034C9DC"; 
		case 53: return "0x010600040035081C"; 
		case 54: return "0x010600040036481D"; 
		case 55: return "0x01060004003789DD"; 
		case 56: return "0x010600040038C9D9"; 
		case 57: return "0x0106000400390819"; 
		case 58: return "0x01060004003A4818"; 
		case 59: return "0x01060004003B89D8"; 
		case 60: return "0x01060004003CC81A"; 
		case 61: return "0x01060004003D09DA"; 
		case 62: return "0x01060004003E49DB"; 
		case 63: return "0x01060004003F881B"; 
		case 64: return "0x010600040040C9FB"; 
		case 65: return "0x010600040041083B"; 
		case 66: return "0x010600040042483A"; 
		case 67: return "0x01060004004389FA"; 
		case 68: return "0x010600040044C838"; 
		case 69: return "0x01060004004509F8"; 
		case 70: return "0x01060004004649F9"; 
		case 71: return "0x0106000400478839"; 
		case 72: return "0x010600040048C83D"; 
		case 73: return "0x01060004004909FD"; 
		case 74: return "0x01060004004A49FC"; 
		case 75: return "0x01060004004B883C"; 
		case 76: return "0x01060004004CC9FE"; 
		case 77: return "0x01060004004D083E"; 
		case 78: return "0x01060004004E483F"; 
		case 79: return "0x01060004004F89FF"; 
		case 80: return "0x010600040050C837"; 
		case 81: return "0x01060004005109F7"; 
		case 82: return "0x01060004005249F6"; 
		case 83: return "0x0106000400538836"; 
		case 84: return "0x010600040054C9F4"; 
		case 85: return "0x0106000400550834"; 
		case 86: return "0x0106000400564835"; 
		case 87: return "0x01060004005789F5"; 
		case 88: return "0x010600040058C9F1"; 
		case 89: return "0x0106000400590831"; 
		case 90: return "0x01060004005A4830"; 
		case 91: return "0x01060004005B89F0"; 
		case 92: return "0x01060004005CC832"; 
		case 93: return "0x01060004005D09F2"; 
		case 94: return "0x01060004005E49F3"; 
		case 95: return "0x01060004005F8833"; 
		case 96: return "0x010600040060C823"; 
		case 97: return "0x01060004006109E3"; 
		case 98: return "0x01060004006249E2"; 
		case 99: return "0x0106000400638822"; 
		case 100: return "0x010600040064C9E0"; 
		}
		return null;
	}
	
	/**
	 * 根据汉字获取modbus 指令
	 * @param chinese
	 * @return
	 */
	public static String getModBusChinese(String chinese) {
		
		switch (chinese) {
		case "0": return  getModBusNum(36);
		case "1": return  getModBusNum(1);		
		case "2": return  getModBusNum(2);    
		case "3":
			return getModBusNum(3);
			
		case "4":
			return getModBusNum(4);
			
		case "5":
			return getModBusNum(5);
			
		case "6":
			return getModBusNum(6);
			
		case "7":
			return getModBusNum(7);
			
		case "8":
			return getModBusNum(8);
			
		case "9":
			return getModBusNum(9);
			
		case "A":
			return getModBusNum(10);
			
		case "B":
			return getModBusNum(11);
			
		case "C":
			return getModBusNum(12);
			
		case "D":
			return getModBusNum(13);
			
		case "E":
			return getModBusNum(14);
			
		case "F":
			return getModBusNum(15);
			
		case "G":
			return getModBusNum(16);
			
		case "H":
			return getModBusNum(17);
			
		case "I":
			return getModBusNum(18);
			
		case "J":
			return getModBusNum(19);
			
		case "K":
			return getModBusNum(20);
			
		case "L":
			return getModBusNum(21);
			
		case "M":
			return getModBusNum(22);
			
		case "N":
			return getModBusNum(23);
			
		case "O":
			return getModBusNum(24);
			
		case "P":
			return getModBusNum(25);
			
		case "Q":
			return getModBusNum(26);
			
		case "R":
			return getModBusNum(27);
			
		case "S":
			return getModBusNum(28);
			
		case "T":
			return getModBusNum(29);
			
		case "U":
			return getModBusNum(30);
			
		case "V":
			return getModBusNum(31);
			
		case "W":
			return getModBusNum(32);
			
		case "X":
			return getModBusNum(33);
			
		case "Y":
			return getModBusNum(34);
			
		case "Z":
			return getModBusNum(35);
			
		case "藏":	return getModBusNum(41); 
		case "甘":	return getModBusNum(42); 
		case "赣":	return getModBusNum(43); 
		case "贵":	return getModBusNum(44); 
		case "桂":	return getModBusNum(45); 
		case "黑":	return getModBusNum(46); 
		case "沪":	return getModBusNum(47); 
		case "吉":	return getModBusNum(48); 
		case "冀":	return getModBusNum(49); 
		case "津":	return getModBusNum(50); 
		case "晋":	return getModBusNum(51); 
		case "京":	return getModBusNum(52); 
		case "辽":	return getModBusNum(53); 
		case "鲁":	return getModBusNum(54); 
		case "蒙":	return getModBusNum(55); 
		case "闽":	return getModBusNum(56); 
		case "青":	return getModBusNum(57); 
		case "琼":	return getModBusNum(58); 
		case "陕":	return getModBusNum(59); 
		case "苏":	return getModBusNum(60); 
		case "皖":	return getModBusNum(61); 
		case "湘":	return getModBusNum(62); 
		case "新":	return getModBusNum(63); 
		case "渝":	return getModBusNum(64); 
		case "豫":	return getModBusNum(65); 
		case "粤":	return getModBusNum(66); 
		case "云":	return getModBusNum(67); 
		case "浙":	return getModBusNum(68); 
		
	}
		return getModBusNum(1); 
	}
		
}

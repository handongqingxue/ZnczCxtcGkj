package com.znczCxtcGkj.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.znczCxtcGkj.entity.*;
import com.znczCxtcGkj.util.*;

/**
 * 叫号线程
 * @author lenovo
 *
 */
public class CallNumberTask extends Thread {
	static Logger logger = LoggerFactory.getLogger(CallNumberTask.class);

	@Override
	public void run() {
		// TODO Auto-generated method stub
		callNumber();
	}
	
	private void callNumber() {
		try {
			while (true) {
				org.json.JSONObject resultJO=APIUtil.getJhPdHMList();
				String status = resultJO.getString("status");
				if("no".equals(status)) {
					logger.info("目前没有需要叫号中和排队中的车辆");
					continue;
				}
				List<HaoMa> hmList=(List<HaoMa>)resultJO.get("hmList");
				logger.info("待叫号和排队中的数量为:" + hmList.size());
				
				// 存放正在叫号中的车牌号
				List<String>  cphListDaiRuChang  = new ArrayList<String>();
				// 存放排队中的车牌信息
				List<String> cphListPaiDuiZhong = new ArrayList<String>();
				for (HaoMa hm : hmList) {
					String hmztMc = hm.getHmztMc();
					if(HaoMaZhuangTai.JIAO_HAO_ZHONG_TEXT.equals(hmztMc)) {
						String ksjhsj = hm.getKsjhsj();
						long ksjhsjTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ksjhsj).getTime();
						
						long currTime = new Date().getTime();
						// 900000 为15分钟的毫秒，
						if ((ksjhsjTime + 900000) < currTime) {

							continue;
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

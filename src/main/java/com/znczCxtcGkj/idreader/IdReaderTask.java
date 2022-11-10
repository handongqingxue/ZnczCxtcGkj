package com.znczCxtcGkj.idreader;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.znczCxtcGkj.entity.*;
import com.znczCxtcGkj.util.*;
import com.znczCxtcGkj.yz.*;

/**
 * ���֤�������߳�����
 * @author Administrator
 *
 */
public class IdReaderTask extends Thread {
	Logger logger = LoggerFactory.getLogger(IdReaderTask.class);

	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info("���֤����������");
		try {
			idReader();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logger.info("���ǿ������ң�������������");
		}
	}

	private void idReader() {

		while (true) {
			try {
				// �� readData �л�ȡ�����֤����
				logger.debug("======��ȡ�����֤��Ϣ-start =======");
				// �õ����֤��Ϣ��json
				String readData = IdReaderUtil.readData();
				logger.debug(readData);
				logger.debug("======��ȡ�����֤��Ϣ-end=======");
				JSONObject parseObject = JSON.parseObject(readData);
				
				Integer resultFlag = (Integer) parseObject.get("resultFlag");
				if (-3 == resultFlag) {
					boolean flag = true;
					while (flag) {
						boolean openDevice = IdReaderUtil.openDevice();
						if (openDevice) {
							flag = false;
						}
					}
				}
				// ����
				String name = (String) parseObject.get("partyName");
				// ���֤����
				String certNumber = (String) parseObject.get("certNumber");
				
				if (certNumber.length() > 14) {
					// �õ����֤�ţ� ���������ﴥ����������
					logger.info("���ʶ���ѳɹ����������֤" + certNumber);
					
					// �������֤�Ų�ѯ���µ�����
					org.json.JSONObject resultJO=APIUtil.getDingDanBySfzhZt(certNumber,DingDanZhuangTai.YI_XIA_DAN_TEXT);
					String status = resultJO.getString("status");
					if("ok".equals(status)) {
						// ���ݻ�ȡ��������Ϣ���ı䶩��״̬�������з���(��ӡ��ά��)
						logger.debug("===========����˾�����֤�Ų�ѯ�Ķ�����Ϣ============");
						logger.debug(resultJO.toString());
						org.json.JSONObject ddJO=resultJO.getJSONObject("dingDan");
						String ckcsStr = ddJO.getString("ckcs");
						Integer ckcs = null;
						if (StringUtils.isBlank(ckcsStr)) {
							ckcs = 0;
						} else {
							ckcs = Integer.parseInt(ckcsStr);
						}
						
						String jhysrq = ddJO.getString("jhysrq");
						DateUtil dateUtil = new DateUtil();
						Date jhysrqDate = dateUtil.parse(jhysrq);
						// �ƻ�����ʱ��
						long jhysrqTime = jhysrqDate.getTime();
						
						DateUtil timeUtil = new DateUtil("yyyy-MM-dd HH:mm:ss");
						String currentDateStr = timeUtil.format(new Date());
						Date currentDate = timeUtil.parse(currentDateStr);
						// ��ǰʱ��
						long currentTime = currentDate.getTime();

						// ���Խ�������ʱ��
						long endTime = jhysrqTime + DateUtil.getTime(LoadProperties.getIntoTheFactoryDate());
						if (jhysrqTime <= currentTime && currentTime <= endTime) {
							// ���Խ��볧��
							if(ckcs==0) {
								try {
									ckcs = ckcs + 1;//��ӡ��ά���൱�ڳ����ˣ�����������1
									String ewm = ddJO.getString("ewm");
									logger.info("��ά���ַ�� " + ewm);
									QrcodePrint.drawImage(ewm);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									logger.info("��ӡ����ӡ��ά�����");
									e.printStackTrace();
								}
							}
							
							//���¶���״̬Ϊ�Ŷ���
				        	DingDan dd=new DingDan();
				        	long ddId = ddJO.getLong("id");
				        	dd.setId(ddId);
				        	dd.setDdztMc(DingDanZhuangTai.PAI_DUI_ZHONG_TEXT);
				        	dd.setCkcs(ckcs);
				        	org.json.JSONObject eddResultJO = APIUtil.editDingDan(dd);
				        	if("ok".equals(eddResultJO.getString("message"))) {
				        		//�༭�����ɹ�
				        		logger.info(eddResultJO.getString("info")+"״̬Ϊ"+DingDanZhuangTai.PAI_DUI_ZHONG_TEXT);
				        		//�����ź���Ϣ
				        		org.json.JSONObject nhmResultJO = APIUtil.newHaoMa(ddId);
					        	if("ok".equals(nhmResultJO.getString("message"))) {
					        		logger.info(nhmResultJO.getString("info"));
					        	}
					        	else {
					        		logger.info(nhmResultJO.getString("info"));
					        	}
				        	}
				        	else {
				        		logger.info(eddResultJO.getString("info"));
				        	}
						}
						else {
							logger.info("�ƻ��������ڲ�׼ȷ");
						}
					}
					else {
						// ��������
						logger.info("û�л�ȡ����ǰ�û��Ķ�����Ϣ");
					}
				} else {
					// û���õ����֤�ţ� ���������ﴥ����������
					logger.info("ip��ַΪ�� " + LoadProperties.getIp());
					logger.info("�����֤��");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				YinZhuUtil.sendMsg(ModBusUtil.get86(), 1500);
			} finally {
				sleepTime(5000);
				logger.info("˯��5��");
			}
		} // while ѭ������
	}

	/**
	 * �߳�˯��
	 */
	private void sleepTime(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

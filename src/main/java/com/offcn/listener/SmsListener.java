package com.offcn.listener;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.offcn.util.SmsUtil;

@Component
public class SmsListener {
	
	@Autowired
	private SmsUtil smsUtil;
	
	@JmsListener(destination="sms_offcn")
	public void sendSms(Map<String, String> map) {
		try {
			String phone = map.get("phone");
			String param = map.get("param");
			
			SendSmsResponse resp = smsUtil.sendSms(phone, param);
			
			System.out.println("���ͽ��״̬�룺" + resp.getCode());
			System.out.println("���ͽ����Ϣ��" + resp.getMessage());
			
		} catch (ClientException e) {
			e.printStackTrace();
		}
		
	}
	
}

package com.cbwleft.sms.mgr.modular.message.warpper;

import java.util.Map;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.common.warpper.BaseControllerWarpper;

public class MessageWarpper extends BaseControllerWarpper {

	public MessageWarpper(Object obj) {
		super(obj);
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		int sendStatus = ((Byte) map.get("sendStatus")).intValue();
		int validateStatus = ((Byte) map.get("validateStatus")).intValue();
		String mobile = (String) map.get("mobile");
		String mobileSensitive = mobile.substring(0, 3)+"****"+mobile.substring(7);
		map.put("sendStatusName", ConstantFactory.me().getDictsByName("发送状态", sendStatus));
		map.put("validateStatusName", ConstantFactory.me().getDictsByName("验证状态", validateStatus));
		map.put("mobile", mobileSensitive);
	}

}

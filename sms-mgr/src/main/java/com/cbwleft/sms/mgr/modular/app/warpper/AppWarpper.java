package com.cbwleft.sms.mgr.modular.app.warpper;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.common.warpper.BaseControllerWarpper;

public class AppWarpper extends BaseControllerWarpper {

	public AppWarpper(Object obj) {
		super(obj);
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		String channelParams = (String) map.get("channelParams");
		String channelParamsSensitive = channelParams;
		try {
			JSONObject jsonObject = JSONObject.parseObject(channelParams);
			jsonObject.entrySet().forEach(entry -> entry.setValue("****"));
			channelParamsSensitive = jsonObject.toJSONString();
		} catch (Exception e) {
		}
		map.put("channelParams", channelParamsSensitive);

	}

}

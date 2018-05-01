package com.cbwleft.sms.mgr.modular.template.warpper;

import java.util.Map;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.common.warpper.BaseControllerWarpper;

public class TemplateWarpper extends BaseControllerWarpper {

	public TemplateWarpper(Object obj) {
		super(obj);
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		map.put("typeName", ConstantFactory.me().getDictsByName("短信类型", ((Byte) map.get("type")).intValue()));
	}

}

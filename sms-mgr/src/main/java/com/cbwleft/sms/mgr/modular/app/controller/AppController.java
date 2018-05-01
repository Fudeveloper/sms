package com.cbwleft.sms.mgr.modular.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbwleft.sms.dao.model.App;
import com.cbwleft.sms.mgr.modular.app.service.IAppService;
import com.cbwleft.sms.mgr.modular.app.warpper.AppWarpper;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.support.BeanKit;

/**
 * 应用管理控制器
 *
 * @author cbwleft
 * @Date 2018-04-28 14:33:00
 */
@Controller
@RequestMapping("/app")
public class AppController extends BaseController {

	private String PREFIX = "/app/app/";

	@Autowired
	private IAppService appService;

	/**
	 * 跳转到应用管理首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "app.html";
	}

	/**
	 * 跳转到添加应用管理
	 */
	@RequestMapping("/app_add")
	public String appAdd() {
		return PREFIX + "app_add.html";
	}

	/**
	 * 跳转到修改应用管理
	 */
	@RequestMapping("/app_update/{appId}")
	public String appUpdate(@PathVariable Byte appId, Model model) {
		App app = appService.selectById(appId);
		model.addAttribute("item", new AppWarpper(BeanKit.beanToMap(app)).warp());
		LogObjectHolder.me().set(app);
		return PREFIX + "app_edit.html";
	}

	/**
	 * 获取应用管理列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(String condition) {
		List<App> result = appService.selectList(null);
		List<Map<String, Object>> list = new ArrayList<>();
		result.forEach(app -> list.add(BeanKit.beanToMap(app)));
		return new AppWarpper(list).warp();
	}

	/**
	 * 新增应用管理
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(App app) {
		appService.insert(app);
		return SUCCESS_TIP;
	}

	/**
	 * 删除应用管理
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Byte appId) {
		appService.deleteById(appId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改应用管理
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(App app) {
		if (app.getChannelParams() != null) {
			app.setChannelParams(app.getChannelParams().trim());
		}
		appService.updateById(app);
		return SUCCESS_TIP;
	}

	/**
	 * 应用管理详情
	 */
	@RequestMapping(value = "/detail/{appId}")
	@ResponseBody
	public Object detail(@PathVariable("appId") Byte appId) {
		App app = appService.selectById(appId);
		return new AppWarpper(BeanKit.beanToMap(app)).warp();
	}
}

package com.cbwleft.sms.mgr.modular.message.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbwleft.sms.dao.model.Message;
import com.cbwleft.sms.mgr.modular.message.service.IMessageService;
import com.cbwleft.sms.mgr.modular.message.warpper.MessageWarpper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.page.PageReq;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.support.BeanKit;

import tk.mybatis.mapper.entity.Example;

/**
 * 短信管理控制器
 *
 * @author cbwleft
 * @Date 2018-04-28 16:31:28
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

    private String PREFIX = "/message/message/";

    @Autowired
    private IMessageService messageService;

    /**
     * 跳转到短信管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "message.html";
    }

    /**
     * 跳转到添加短信管理
     */
    @RequestMapping("/message_add")
    public String messageAdd() {
        return PREFIX + "message_add.html";
    }

    /**
     * 跳转到修改短信管理
     */
    @RequestMapping("/message_update/{messageId}")
    public String messageUpdate(@PathVariable Integer messageId, Model model) {
        Message message = messageService.selectById(messageId);
        model.addAttribute("item",message);
        LogObjectHolder.me().set(message);
        return PREFIX + "message_edit.html";
    }

    /**
     * 获取短信管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Message query) {
    	PageReq params = defaultPage();
        PageHelper.offsetPage(params.getOffset(), params.getOffset());
        Example example = new Example(Message.class);
        if (!StringUtils.isEmpty(query.getMobile())) {
        	example.createCriteria().andEqualTo("mobile", query.getMobile());
        }
        example.orderBy("id").desc();
        /*if (!params.isOpenSort()) {
        	example.orderBy("id").desc();
        } else {
        	if(params.isAsc()) {
        		example.orderBy(params.getSort()).asc();
        	} else {
        		example.orderBy(params.getSort()).desc();
        	}
        }*/
        List<Message> result = messageService.selectByExample(example);
        Page<Map<String, Object>> page = new Page<>();
        if(result instanceof Page) {
        	page.setTotal(((Page<?>) result).getTotal());
        }
        result.forEach(message -> page.add(BeanKit.beanToMap(message)));
        new MessageWarpper(page).warp();
        return packForBT(page);
    }

    /**
     * 新增短信管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Message message) {
        messageService.insert(message);
        return SUCCESS_TIP;
    }

    /**
     * 删除短信管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer messageId) {
        messageService.deleteById(messageId);
        return SUCCESS_TIP;
    }

    /**
     * 修改短信管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Message message) {
        messageService.updateById(message);
        return SUCCESS_TIP;
    }

    /**
     * 短信管理详情
     */
    @RequestMapping(value = "/detail/{messageId}")
    @ResponseBody
    public Object detail(@PathVariable("messageId") Integer messageId) {
        return messageService.selectById(messageId);
    }
}

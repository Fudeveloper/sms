package com.cbwleft.sms.mgr.modular.message.service;

import java.util.List;

import com.cbwleft.sms.dao.model.Message;
import com.cbwleft.sms.mgr.core.service.IService;

import tk.mybatis.mapper.entity.Example;
	
/**
 * <p>
 * 短信表 服务类
 * </p>
 *
 * @author cbwleft
 * @since 2018-04-28
 */
public interface IMessageService extends IService<Message>{

	List<Message> selectByExample(Example example);

}

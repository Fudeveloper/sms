package com.cbwleft.sms.mgr.modular.message.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cbwleft.sms.dao.mapper.MessageMapper;
import com.cbwleft.sms.dao.model.Message;
import com.cbwleft.sms.mgr.core.service.impl.ServiceImpl;
import com.cbwleft.sms.mgr.modular.message.service.IMessageService;

import tk.mybatis.mapper.entity.Example;

/**
 * <p>
 * 短信表 服务实现类
 * </p>
 *
 * @author cbwleft
 * @since 2018-04-28
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {
	
	@Override
	public List<Message> selectByExample(Example example){
		return baseMapper.selectByExample(example);
	}

}

package com.cbwleft.sms.mgr.modular.app.service.impl;

import org.springframework.stereotype.Service;

import com.cbwleft.sms.dao.mapper.AppMapper;
import com.cbwleft.sms.dao.model.App;
import com.cbwleft.sms.mgr.core.service.impl.ServiceImpl;
import com.cbwleft.sms.mgr.modular.app.service.IAppService;

/**
 * <p>
 * 应用表 服务实现类
 * </p>
 *
 * @author cbwleft
 * @since 2018-04-28
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App> implements IAppService {
	
}

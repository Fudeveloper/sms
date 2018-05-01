package com.cbwleft.sms.mgr.modular.template.service.impl;

import org.springframework.stereotype.Service;

import com.cbwleft.sms.dao.mapper.TemplateMapper;
import com.cbwleft.sms.dao.model.Template;
import com.cbwleft.sms.mgr.core.service.impl.ServiceImpl;
import com.cbwleft.sms.mgr.modular.template.service.ITemplateService;

/**
 * <p>
 * 短信模板表 服务实现类
 * </p>
 *
 * @author cbwleft
 * @since 2018-04-28
 */
@Service
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, Template> implements ITemplateService {

}

package com.cbwleft.sms.mgr.core.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cbwleft.sms.mgr.core.service.IService;

import tk.mybatis.mapper.common.BaseMapper;

public class ServiceImpl <M extends BaseMapper<T>, T> implements IService<T>{
	
	@Autowired
	protected M baseMapper;

	@Override
	public List<T> selectList(T query) {
		return baseMapper.select(query);
	}

	@Override
	public boolean insert(T entity) {
		int result = baseMapper.insertSelective(entity);
		return result > 0;
	}

	@Override
	public boolean deleteById(Serializable id) {
		int result = baseMapper.deleteByPrimaryKey(id);
		return result > 0;
	}

	@Override
	public boolean updateById(T entity) {
		int result = baseMapper.updateByPrimaryKeySelective(entity);
		return result > 0;
	}

	@Override
	public T selectById(Serializable id) {
		return baseMapper.selectByPrimaryKey(id);
	}

}

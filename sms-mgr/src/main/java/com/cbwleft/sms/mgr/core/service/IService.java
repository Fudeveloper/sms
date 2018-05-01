package com.cbwleft.sms.mgr.core.service;

import java.io.Serializable;
import java.util.List;


/**
 * <p>
 * 顶级 Service
 * </p>
 *
 * @author cbwleft
 * @Date 2018-04-28
 */
public interface IService<T> {
	
	
	List<T> selectList(T query);

    /**
     * <p>
     * 插入一条记录（选择字段，策略插入）
     * </p>
     *
     * @param entity 实体对象
     * @return boolean
     */
    boolean insert(T entity);

    /**
     * <p>
     * 根据 ID 删除
     * </p>
     *
     * @param id 主键ID
     * @return boolean
     */
    boolean deleteById(Serializable id);

    /**
     * <p>
     * 根据 ID 选择修改
     * </p>
     *
     * @param entity 实体对象
     * @return boolean
     */
    boolean updateById(T entity);

    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param id 主键ID
     * @return T
     */
    T selectById(Serializable id);


}

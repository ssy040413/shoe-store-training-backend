package com.xys.service;

import com.xys.entity.Types;
import java.util.List;

/**
 * 类型业务逻辑接口
 */
public interface TypesService {

    /**
     * 查询所有类型
     */
    List<Types> getAllTypes();

    /**
     * 根据ID查询类型
     */
    Types getTypeById(Integer typeId);

    /**
     * 添加类型
     */
    boolean addType(Types types);

    /**
     * 更新类型
     */
    boolean updateType(Types types);

    /**
     * 删除类型
     */
    boolean deleteType(Integer typeId);

    /**
     * 查询启用状态的类型
     */
    List<Types> getActiveTypes();

    /**
     * 添加类型并返回保存的对象
     */
    Types addTypeAndReturn(Types types);

    /**
     * 更新类型并返回更新的对象
     */
    Types updateTypeAndReturn(Types types);
}
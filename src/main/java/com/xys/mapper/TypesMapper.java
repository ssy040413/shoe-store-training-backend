package com.xys.mapper;

import com.xys.entity.Types;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 类型数据访问接口
 */
@Mapper
public interface TypesMapper {

    /**
     * 查询所有类型
     */
    List<Types> findAll();

    /**
     * 根据ID查询类型
     */
    Types findById(@Param("typeId") Integer typeId);

    /**
     * 添加类型
     */
    int insert(Types types);

    /**
     * 更新类型
     */
    int update(Types types);

    /**
     * 删除类型
     */
    int deleteById(@Param("typeId") Integer typeId);

    /**
     * 查询启用状态的类型
     */
    List<Types> findActiveTypes();
}
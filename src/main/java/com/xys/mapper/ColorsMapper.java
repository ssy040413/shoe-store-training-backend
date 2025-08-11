package com.xys.mapper;

import com.xys.entity.Colors;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 颜色数据访问接口
 */
@Mapper
public interface ColorsMapper {

    /**
     * 查询所有颜色
     */
    List<Colors> findAll();

    /**
     * 根据ID查询颜色
     */
    Colors findById(@Param("colorId") Integer colorId);

    /**
     * 添加颜色
     */
    int insert(Colors colors);

    /**
     * 更新颜色
     */
    int update(Colors colors);

    /**
     * 删除颜色
     */
    int deleteById(@Param("colorId") Integer colorId);

    /**
     * 查询启用状态的颜色
     */
    List<Colors> findActiveColors();

    /**
     * 统计使用指定颜色的鞋子数量
     */
    int countShoesUsingColor(@Param("colorId") Integer colorId);
}
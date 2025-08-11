package com.xys.mapper;

import com.xys.entity.Sizes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 尺寸数据访问接口
 */
@Mapper
public interface SizesMapper {

    /**
     * 查询所有尺寸
     */
    List<Sizes> findAll();

    /**
     * 根据ID查询尺寸
     */
    Sizes findById(@Param("sizeId") Integer sizeId);

    /**
     * 添加尺寸
     */
    int insert(Sizes sizes);

    /**
     * 更新尺寸
     */
    int update(Sizes sizes);

    /**
     * 删除尺寸
     */
    int deleteById(@Param("sizeId") Integer sizeId);

    /**
     * 查询启用状态的尺寸
     */
    List<Sizes> findActiveSizes();

    /**
     * 检查尺寸是否被商品使用
     */
    int countShoesUsingSizeId(@Param("sizeId") Integer sizeId);
}
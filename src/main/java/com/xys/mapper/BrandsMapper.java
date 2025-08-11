package com.xys.mapper;

import com.xys.entity.Brands;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 品牌数据访问接口
 */
@Mapper
public interface BrandsMapper {

    /**
     * 查询所有品牌
     */
    List<Brands> findAll();

    /**
     * 根据ID查询品牌
     */
    Brands findById(@Param("brand_id") Integer brand_id);

    /**
     * 添加品牌
     */
    int insert(Brands brands);

    /**
     * 更新品牌
     */
    int update(Brands brands);

    /**
     * 删除品牌
     */
    int delete(@Param("brand_id") Integer brand_id);

    /**
     * 更新品牌状态
     */
    int updateStatus(@Param("brand_id") Integer brand_id, @Param("status") String status);

    /**
     * 查询启用状态的品牌
     */
    List<Brands> findActiveBrands();

    /**
     * 检查品牌是否被鞋子表引用
     */
    int countShoesUsingBrand(@Param("brand_id") Integer brand_id);
}
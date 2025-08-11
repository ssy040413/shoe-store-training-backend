package com.xys.mapper;

import com.xys.entity.Shoes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 鞋类产品数据访问接口
 */
@Mapper
public interface ShoesMapper {

    /**
     * 查询所有产品（包含关联信息）
     */
    List<Shoes> findAll();

    /**
     * 根据ID查询产品
     */
    Shoes findById(@Param("shoeId") Integer shoeId);

    /**
     * 添加产品
     */
    int insert(Shoes shoes);

    /**
     * 更新产品
     */
    int update(Shoes shoes);

    /**
     * 动态更新产品（只更新非空字段）
     */
    int updateSelective(Shoes shoes);

    /**
     * 删除产品
     */
    int deleteById(@Param("shoeId") Integer shoeId);

    /**
     * 批量删除产品
     */
    int deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 根据关键词搜索产品
     */
    List<Shoes> findByKeyword(@Param("keyword") String keyword);

    /**
     * 根据品牌ID查询产品
     */
    List<Shoes> findByBrandId(@Param("brandId") Integer brandId);

    /**
     * 根据类型ID查询产品
     */
    List<Shoes> findByTypeId(@Param("typeId") Integer typeId);

    /**
     * 根据颜色ID查询产品
     */
    List<Shoes> findByColorId(@Param("colorId") Integer colorId);

    /**
     * 多条件查询产品
     */
    List<Shoes> findByConditions(@Param("brandId") Integer brandId,
                                @Param("typeId") Integer typeId,
                                @Param("colorId") Integer colorId,
                                @Param("status") String status,
                                @Param("keyword") String keyword,
                                @Param("minPrice") BigDecimal minPrice,
                                @Param("maxPrice") BigDecimal maxPrice);

    /**
     * 统计产品数量
     */
    int count();

    /**
     * 根据条件统计产品数量
     */
    int countByConditions(@Param("brandId") Integer brandId,
                         @Param("typeId") Integer typeId,
                         @Param("colorId") Integer colorId,
                         @Param("status") String status,
                         @Param("keyword") String keyword);
}
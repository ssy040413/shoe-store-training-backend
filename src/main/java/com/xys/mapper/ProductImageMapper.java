package com.xys.mapper;

import com.xys.entity.ProductImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品图片Mapper接口
 */
@Mapper
public interface ProductImageMapper {
    
    /**
     * 根据商品ID查询图片列表
     */
    List<ProductImage> findByShoeId(@Param("shoeId") Long shoeId);
    
    /**
     * 根据商品ID查询主图
     */
    ProductImage findMainImageByShoeId(@Param("shoeId") Long shoeId);
    
    /**
     * 根据ID查询图片
     */
    ProductImage findById(@Param("imageId") Long imageId);
    
    /**
     * 插入商品图片
     */
    int insert(ProductImage productImage);
    
    /**
     * 批量插入商品图片
     */
    int insertBatch(@Param("images") List<ProductImage> images);
    
    /**
     * 更新商品图片
     */
    int update(ProductImage productImage);
    
    /**
     * 根据ID删除图片
     */
    int deleteById(@Param("imageId") Long imageId);
    
    /**
     * 根据商品ID删除所有图片
     */
    int deleteByShoeId(@Param("shoeId") Long shoeId);
    
    /**
     * 批量删除图片
     */
    int deleteByIds(@Param("imageIds") List<Long> imageIds);
    
    /**
     * 设置主图（先清除原主图，再设置新主图）
     */
    int clearMainImage(@Param("shoeId") Long shoeId);
    
    /**
     * 设置为主图
     */
    int setAsMainImage(@Param("imageId") Long imageId);
    
    /**
     * 更新图片排序
     */
    int updateSortOrder(@Param("imageId") Long imageId, @Param("sortOrder") Integer sortOrder);
}
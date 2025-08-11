package com.xys.service;

import com.xys.entity.ProductImage;

import java.util.List;

/**
 * 商品图片服务接口
 */
public interface ProductImageService {
    
    /**
     * 根据商品ID获取图片列表
     */
    List<ProductImage> getImagesByShoeId(Long shoeId);
    
    /**
     * 根据商品ID获取主图
     */
    ProductImage getMainImageByShoeId(Long shoeId);
    
    /**
     * 根据图片ID获取图片信息
     */
    ProductImage getImageById(Long imageId);
    
    /**
     * 添加商品图片
     */
    ProductImage addProductImage(ProductImage productImage);
    
    /**
     * 批量添加商品图片
     */
    List<ProductImage> addProductImages(List<ProductImage> images);
    
    /**
     * 更新商品图片
     */
    boolean updateProductImage(ProductImage productImage);
    
    /**
     * 删除商品图片
     */
    boolean deleteProductImage(Long imageId);
    
    /**
     * 根据商品ID删除所有图片
     */
    boolean deleteByShoeId(Long shoeId);
    
    /**
     * 批量删除商品图片
     */
    boolean deleteProductImages(List<Long> imageIds);
    
    /**
     * 设置主图
     */
    boolean setMainImage(Long imageId);
    
    /**
     * 更新图片排序
     */
    boolean updateImageSort(List<ProductImage> images);
    
    /**
     * 保存商品图片（新增或更新商品时调用）
     */
    boolean saveProductImages(Long shoeId, List<ProductImage> images);
}
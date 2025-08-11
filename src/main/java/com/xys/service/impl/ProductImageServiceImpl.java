package com.xys.service.impl;

import com.xys.entity.ProductImage;
import com.xys.mapper.ProductImageMapper;
import com.xys.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品图片服务实现类
 */
@Service
public class ProductImageServiceImpl implements ProductImageService {
    
    @Autowired
    private ProductImageMapper productImageMapper;
    
    @Override
    public List<ProductImage> getImagesByShoeId(Long shoeId) {
        return productImageMapper.findByShoeId(shoeId);
    }
    
    @Override
    public ProductImage getMainImageByShoeId(Long shoeId) {
        return productImageMapper.findMainImageByShoeId(shoeId);
    }
    
    @Override
    public ProductImage getImageById(Long imageId) {
        return productImageMapper.findById(imageId);
    }
    
    @Override
    public ProductImage addProductImage(ProductImage productImage) {
        // 如果设置为主图，先清除其他主图
        if (productImage.getIsMain() != null && productImage.getIsMain() == 1) {
            productImageMapper.clearMainImage(productImage.getShoeId());
        }
        int result = productImageMapper.insert(productImage);
        return result > 0 ? productImage : null;
    }
    
    @Override
    @Transactional
    public List<ProductImage> addProductImages(List<ProductImage> images) {
        if (images == null || images.isEmpty()) {
            return images;
        }
        
        // 检查是否有主图设置
        boolean hasMainImage = images.stream().anyMatch(img -> img.getIsMain() != null && img.getIsMain() == 1);
        if (hasMainImage && !images.isEmpty()) {
            // 清除原有主图
            productImageMapper.clearMainImage(images.get(0).getShoeId());
        }
        
        int result = productImageMapper.insertBatch(images);
        return result > 0 ? images : null;
    }
    
    @Override
    public boolean updateProductImage(ProductImage productImage) {
        // 如果设置为主图，先清除其他主图
        if (productImage.getIsMain() != null && productImage.getIsMain() == 1) {
            productImageMapper.clearMainImage(productImage.getShoeId());
        }
        return productImageMapper.update(productImage) > 0;
    }
    
    @Override
    public boolean deleteProductImage(Long imageId) {
        return productImageMapper.deleteById(imageId) > 0;
    }
    
    @Override
    public boolean deleteByShoeId(Long shoeId) {
        return productImageMapper.deleteByShoeId(shoeId) >= 0;
    }
    
    @Override
    public boolean deleteProductImages(List<Long> imageIds) {
        if (imageIds == null || imageIds.isEmpty()) {
            return true;
        }
        return productImageMapper.deleteByIds(imageIds) > 0;
    }
    
    @Override
    @Transactional
    public boolean setMainImage(Long imageId) {
        // 先获取图片信息
        ProductImage image = productImageMapper.findById(imageId);
        if (image == null) {
            return false;
        }
        
        // 先清除原有主图
        productImageMapper.clearMainImage(image.getShoeId());
        // 设置新主图
        return productImageMapper.setAsMainImage(imageId) > 0;
    }
    
    @Override
    @Transactional
    public boolean updateImageSort(List<ProductImage> images) {
        if (images == null || images.isEmpty()) {
            return true;
        }
        
        for (ProductImage image : images) {
            if (image.getImageId() != null && image.getSortOrder() != null) {
                productImageMapper.updateSortOrder(image.getImageId(), image.getSortOrder());
            }
        }
        return true;
    }
    
    @Override
    @Transactional
    public boolean saveProductImages(Long shoeId, List<ProductImage> images) {
        if (images == null || images.isEmpty()) {
            return true;
        }
        
        // 删除原有图片
        deleteByShoeId(shoeId);
        
        // 设置商品ID并添加新图片
        for (int i = 0; i < images.size(); i++) {
            ProductImage image = images.get(i);
            image.setShoeId(shoeId);
            if (image.getSortOrder() == null) {
                image.setSortOrder(i);
            }
            if (image.getStatus() == null) {
                image.setStatus(1);
            }
        }
        
        List<ProductImage> result = addProductImages(images);
        return result != null && !result.isEmpty();
    }
}
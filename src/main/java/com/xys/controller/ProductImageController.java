package com.xys.controller;

import com.xys.entity.ProductImage;
import com.xys.service.ProductImageService;
import com.xys.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品图片控制器
 */
@RestController
@RequestMapping("/api/v1/product-images")
public class ProductImageController {
    
    @Autowired
    private ProductImageService productImageService;
    
    /**
     * 根据商品ID获取图片列表
     */
    @GetMapping("/shoe/{shoeId}")
    public ResponseResult<List<ProductImage>> getImagesByShoeId(@PathVariable Long shoeId) {
        try {
            List<ProductImage> images = productImageService.getImagesByShoeId(shoeId);
            return ResponseResult.success(images);
        } catch (Exception e) {
            return ResponseResult.failure("获取商品图片失败: " + e.getMessage());
        }
    }
    
    /**
     * 根据商品ID获取主图
     */
    @GetMapping("/main/{shoeId}")
    public ResponseResult<ProductImage> getMainImageByShoeId(@PathVariable Long shoeId) {
        try {
            ProductImage mainImage = productImageService.getMainImageByShoeId(shoeId);
            return ResponseResult.success(mainImage);
        } catch (Exception e) {
            return ResponseResult.failure("获取商品主图失败: " + e.getMessage());
        }
    }
    
    /**
     * 添加商品图片
     */
    @PostMapping("/add")
    public ResponseResult<ProductImage> addProductImage(@RequestBody ProductImage productImage) {
        try {
            ProductImage result = productImageService.addProductImage(productImage);
            if (result != null) {
                return ResponseResult.success("添加商品图片成功", result);
            } else {
                return ResponseResult.failure("添加商品图片失败");
            }
        } catch (Exception e) {
            return ResponseResult.failure("添加商品图片失败: " + e.getMessage());
        }
    }
    
    /**
     * 批量添加商品图片
     */
    @PostMapping("/batch-add")
    public ResponseResult<List<ProductImage>> addProductImages(@RequestBody List<ProductImage> images) {
        try {
            List<ProductImage> result = productImageService.addProductImages(images);
            if (result != null && !result.isEmpty()) {
                return ResponseResult.success("批量添加商品图片成功", result);
            } else {
                return ResponseResult.failure("批量添加商品图片失败");
            }
        } catch (Exception e) {
            return ResponseResult.failure("批量添加商品图片失败: " + e.getMessage());
        }
    }
    
    /**
     * 更新商品图片
     */
    @PutMapping("/update")
    public ResponseResult<Boolean> updateProductImage(@RequestBody ProductImage productImage) {
        try {
            boolean success = productImageService.updateProductImage(productImage);
            if (success) {
                return ResponseResult.success("更新商品图片成功", true);
            } else {
                return ResponseResult.failure("更新商品图片失败");
            }
        } catch (Exception e) {
            return ResponseResult.failure("更新商品图片失败: " + e.getMessage());
        }
    }
    
    /**
     * 删除商品图片
     */
    @DeleteMapping("/delete/{imageId}")
    public ResponseResult<Boolean> deleteProductImage(@PathVariable Long imageId) {
        try {
            boolean success = productImageService.deleteProductImage(imageId);
            if (success) {
                return ResponseResult.success("删除商品图片成功", true);
            } else {
                return ResponseResult.failure("删除商品图片失败");
            }
        } catch (Exception e) {
            return ResponseResult.failure("删除商品图片失败: " + e.getMessage());
        }
    }
    
    /**
     * 批量删除商品图片
     */
    @DeleteMapping("/batch-delete")
    public ResponseResult<Boolean> deleteProductImages(@RequestBody List<Long> imageIds) {
        try {
            boolean success = productImageService.deleteProductImages(imageIds);
            if (success) {
                return ResponseResult.success("批量删除商品图片成功", true);
            } else {
                return ResponseResult.failure("批量删除商品图片失败");
            }
        } catch (Exception e) {
            return ResponseResult.failure("批量删除商品图片失败: " + e.getMessage());
        }
    }
    
    /**
     * 设置主图
     */
    @PutMapping("/set-main/{imageId}")
    public ResponseResult<Boolean> setMainImage(@PathVariable Long imageId) {
        try {
            boolean success = productImageService.setMainImage(imageId);
            if (success) {
                return ResponseResult.success("设置主图成功", true);
            } else {
                return ResponseResult.failure("设置主图失败");
            }
        } catch (Exception e) {
            return ResponseResult.failure("设置主图失败: " + e.getMessage());
        }
    }
    
    /**
     * 更新图片排序
     */
    @PutMapping("/update-sort")
    public ResponseResult<Boolean> updateImageSort(@RequestBody List<ProductImage> images) {
        try {
            boolean success = productImageService.updateImageSort(images);
            if (success) {
                return ResponseResult.success("更新图片排序成功", true);
            } else {
                return ResponseResult.failure("更新图片排序失败");
            }
        } catch (Exception e) {
            return ResponseResult.failure("更新图片排序失败: " + e.getMessage());
        }
    }
    
    /**
     * 保存商品图片（用于商品新增/编辑时）
     */
    @PostMapping("/save/{shoeId}")
    public ResponseResult<Boolean> saveProductImages(@PathVariable Long shoeId, @RequestBody List<ProductImage> images) {
        try {
            boolean success = productImageService.saveProductImages(shoeId, images);
            if (success) {
                return ResponseResult.success("保存商品图片成功", true);
            } else {
                return ResponseResult.failure("保存商品图片失败");
            }
        } catch (Exception e) {
            return ResponseResult.failure("保存商品图片失败: " + e.getMessage());
        }
    }
}
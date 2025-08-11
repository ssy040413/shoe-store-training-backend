package com.xys.controller;

import com.github.pagehelper.PageInfo;
import com.xys.entity.Shoes;
import com.xys.entity.ProductImage;
import com.xys.service.ShoesService;
import com.xys.service.ProductImageService;
import com.xys.util.ResponseResult;
import com.xys.util.FileUploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.ArrayList;

/**
 * 鞋类产品管理控制器
 */
@RestController
@RequestMapping("/api/v1/shoes")
@Api(tags = "产品管理模块")
public class ShoesController {

    @Autowired
    private ShoesService shoesService;
    
    @Autowired
    private ProductImageService productImageService;

    /**
     * 获取所有产品列表
     */
    @GetMapping("/list")
    @ApiOperation("获取所有产品列表")
    public ResponseResult<List<Shoes>> getAllShoes() {
        List<Shoes> shoesList = shoesService.getAllShoes();
        if (shoesList != null) {
            return ResponseResult.success(shoesList);
        } else {
            return ResponseResult.failure("获取产品列表失败");
        }
    }

    /**
     * 分页查询产品列表
     */
    @GetMapping("/list/page")
    @ApiOperation("分页查询产品列表")
    public ResponseResult<PageInfo<Shoes>> listByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        try {
            PageInfo<Shoes> pageInfo = shoesService.listByPage(pageNum, pageSize, keyword);
            return ResponseResult.success(pageInfo);
        } catch (Exception e) {
            return ResponseResult.failure("系统错误：" + e.getMessage());
        }
    }

    /**
     * 修改产品状态
     */
    @PostMapping("/status/{id}")
    @ApiOperation("修改产品状态")
    public ResponseResult<Boolean> updateShoesStatus(
            @ApiParam(value = "产品ID", required = true) @PathVariable Integer id,
            @ApiParam(value = "状态值", required = true) @RequestParam String status) {
        try {
            boolean success = shoesService.updateShoesStatus(id, status);
            if (success) {
                return ResponseResult.success(true);
            } else {
                return ResponseResult.failure("修改产品状态失败");
            }
        } catch (Exception e) {
            return ResponseResult.failure("系统错误：" + e.getMessage());
        }
    }

    /**
     * 根据ID获取产品详情
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID获取产品详情")
    public ResponseResult<Shoes> getShoesById(
            @ApiParam(value = "产品ID", required = true) @PathVariable Integer id) {
        Shoes shoes = shoesService.getShoesById(id);
        if (shoes != null) {
            return ResponseResult.success(shoes);
        } else {
            return ResponseResult.failure("产品不存在");
        }
    }

    /**
     * 添加新产品
     */
    @PostMapping("/add")
    @ApiOperation("添加新产品")
    public ResponseResult<Shoes> addShoes(
            @ApiParam(value = "产品信息", required = true) @RequestBody Shoes shoes) {
        Shoes newShoes = shoesService.addShoes(shoes);
        if (newShoes != null) {
            return ResponseResult.success(newShoes);
        } else {
            return ResponseResult.failure("添加产品失败");
        }
    }

    /**
     * 更新产品信息
     */
    @PostMapping("/update")
    @ApiOperation("更新产品信息")
    public ResponseResult<Boolean> updateShoes(
            @ApiParam(value = "产品信息", required = true) @RequestBody Shoes shoes) {
        if (shoes.getShoeId() == null) {
            return ResponseResult.failure("产品ID不能为空");
        }
        boolean success = shoesService.updateShoes(shoes);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("更新产品失败");
        }
    }

    /**
     * 删除产品
     */
    @PostMapping("/delete/{id}")
    @ApiOperation("删除产品")
    public ResponseResult<Boolean> deleteShoes(
            @ApiParam(value = "产品ID", required = true) @PathVariable Integer id) {
        boolean success = shoesService.deleteShoes(id);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("删除产品失败");
        }
    }

    /**
     * 搜索产品
     */
    @GetMapping("/search")
    @ApiOperation("搜索产品")
    public ResponseResult<List<Shoes>> searchShoes(
            @ApiParam(value = "搜索关键词") @RequestParam(required = false) String keyword) {
        List<Shoes> shoesList = shoesService.searchShoes(keyword);
        if (shoesList != null) {
            return ResponseResult.success(shoesList);
        } else {
            return ResponseResult.failure("搜索产品失败");
        }
    }

    /**
     * 上传商品图片
     */
    @PostMapping("/upload-image/{shoeId}")
    @ApiOperation("上传商品图片")
    public ResponseResult<ProductImage> uploadImage(
            @ApiParam(value = "商品ID", required = true) @PathVariable Long shoeId,
            @ApiParam(value = "图片文件", required = true) @RequestParam("file") MultipartFile file,
            @ApiParam(value = "图片名称") @RequestParam(required = false) String imageName,
            @ApiParam(value = "图片类型") @RequestParam(defaultValue = "product") String imageType,
            @ApiParam(value = "是否为主图") @RequestParam(defaultValue = "false") Boolean isMain) {
        try {
            // 上传文件
            String imageUrl = FileUploadUtil.uploadImage(file);
            
            // 创建图片记录
            ProductImage productImage = new ProductImage();
            productImage.setShoeId(shoeId);
            productImage.setImageUrl(imageUrl);
            productImage.setImageName(imageName != null ? imageName : file.getOriginalFilename());
            productImage.setImageType(imageType);
            productImage.setIsMain(isMain ? 1 : 0);
            productImage.setStatus(1);
            
            // 保存到数据库
            ProductImage savedImage = productImageService.addProductImage(productImage);
            if (savedImage != null) {
                return ResponseResult.success(savedImage);
            } else {
                return ResponseResult.failure("保存图片信息失败");
            }
        } catch (Exception e) {
            return ResponseResult.failure("上传图片失败：" + e.getMessage());
        }
    }

    /**
     * 批量上传商品图片
     */
    @PostMapping("/upload-images/{shoeId}")
    @ApiOperation("批量上传商品图片")
    public ResponseResult<List<ProductImage>> uploadImages(
            @ApiParam(value = "商品ID", required = true) @PathVariable Long shoeId,
            @ApiParam(value = "图片文件列表", required = true) @RequestParam("files") MultipartFile[] files,
            @ApiParam(value = "图片类型") @RequestParam(defaultValue = "product") String imageType) {
        try {
            List<ProductImage> productImages = new ArrayList<>();
            
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                
                // 上传文件
                String imageUrl = FileUploadUtil.uploadImage(file);
                
                // 创建图片记录
                ProductImage productImage = new ProductImage();
                productImage.setShoeId(shoeId);
                productImage.setImageUrl(imageUrl);
                productImage.setImageName(file.getOriginalFilename());
                productImage.setImageType(imageType);
                productImage.setSortOrder(i + 1);
                productImage.setIsMain(i == 0 ? 1 : 0); // 第一张图片设为主图
                productImage.setStatus(1);
                
                productImages.add(productImage);
            }
            
            // 批量保存到数据库
            List<ProductImage> savedImages = productImageService.addProductImages(productImages);
            if (savedImages != null && !savedImages.isEmpty()) {
                return ResponseResult.success(savedImages);
            } else {
                return ResponseResult.failure("保存图片信息失败");
            }
        } catch (Exception e) {
            return ResponseResult.failure("批量上传图片失败：" + e.getMessage());
        }
    }

    /**
     * 获取商品图片列表
     */
    @GetMapping("/{shoeId}/images")
    @ApiOperation("获取商品图片列表")
    public ResponseResult<List<ProductImage>> getProductImages(
            @ApiParam(value = "商品ID", required = true) @PathVariable Long shoeId) {
        try {
            List<ProductImage> images = productImageService.getImagesByShoeId(shoeId);
            return ResponseResult.success(images);
        } catch (Exception e) {
            return ResponseResult.failure("获取图片列表失败：" + e.getMessage());
        }
    }

    /**
     * 删除商品图片
     */
    @PostMapping("/delete-image/{imageId}")
    @ApiOperation("删除商品图片")
    public ResponseResult<Boolean> deleteProductImage(
            @ApiParam(value = "图片ID", required = true) @PathVariable Long imageId) {
        try {
            // 先获取图片信息
            ProductImage image = productImageService.getImageById(imageId);
            if (image != null) {
                // 删除文件
                FileUploadUtil.deleteFile(image.getImageUrl());
                // 删除数据库记录
                boolean success = productImageService.deleteProductImage(imageId);
                return ResponseResult.success(success);
            } else {
                return ResponseResult.failure("图片不存在");
            }
        } catch (Exception e) {
            return ResponseResult.failure("删除图片失败：" + e.getMessage());
        }
    }

    /**
     * 设置主图
     */
    @PostMapping("/set-main-image/{imageId}")
    @ApiOperation("设置主图")
    public ResponseResult<Boolean> setMainImage(
            @ApiParam(value = "图片ID", required = true) @PathVariable Long imageId) {
        try {
            boolean success = productImageService.setMainImage(imageId);
            if (success) {
                return ResponseResult.success(true);
            } else {
                return ResponseResult.failure("设置主图失败");
            }
        } catch (Exception e) {
            return ResponseResult.failure("设置主图失败：" + e.getMessage());
        }
    }
}
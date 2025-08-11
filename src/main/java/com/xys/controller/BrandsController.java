package com.xys.controller;

import com.xys.entity.Brands;
import com.xys.service.BrandsService;
import com.xys.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 品牌管理控制器
 */
@RestController
@RequestMapping("/api/brands")
@Api(tags = "品牌管理模块")
public class BrandsController {

    @Autowired
    private BrandsService brandsService;

    /**
     * 获取所有品牌列表
     */
    @GetMapping("")
    @ApiOperation("获取所有品牌列表")
    public ResponseResult<List<Brands>> getAllBrands() {
        List<Brands> brandsList = brandsService.getAllBrands();
        if (brandsList != null) {
            return ResponseResult.success(brandsList);
        } else {
            return ResponseResult.failure("获取品牌列表失败");
        }
    }

    /**
     * 获取启用状态的品牌列表
     */
    @GetMapping("/active")
    @ApiOperation("获取启用状态的品牌列表")
    public ResponseResult<List<Brands>> getActiveBrands() {
        List<Brands> brandsList = brandsService.getActiveBrands();
        if (brandsList != null) {
            return ResponseResult.success(brandsList);
        } else {
            return ResponseResult.failure("获取品牌列表失败");
        }
    }

    /**
     * 根据ID获取品牌详情
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID获取品牌详情")
    public ResponseResult<Brands> getBrandById(
            @ApiParam(value = "品牌ID", required = true) @PathVariable Integer id) {
        Brands brands = brandsService.getBrandById(id);
        if (brands != null) {
            return ResponseResult.success(brands);
        } else {
            return ResponseResult.failure("品牌不存在");
        }
    }

    /**
     * 添加新品牌
     */
    @PostMapping("")
    @ApiOperation("添加新品牌")
    public ResponseResult<Boolean> addBrand(
            @ApiParam(value = "品牌信息", required = true) @RequestBody Brands brands) {
        boolean success = brandsService.addBrand(brands);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("添加品牌失败");
        }
    }

    /**
     * 更新品牌信息
     */
    @PostMapping("/update/{id}")
    @ApiOperation("更新品牌信息")
    public ResponseResult<Boolean> updateBrand(
            @ApiParam(value = "品牌ID", required = true) @PathVariable Integer id,
            @ApiParam(value = "品牌信息", required = true) @RequestBody Brands brands) {
        brands.setBrand_id(id);
        boolean success = brandsService.updateBrand(brands);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("更新品牌失败");
        }
    }

    /**
     * 删除品牌
     */
    @PostMapping("/delete/{id}")
    @ApiOperation("删除品牌")
    public ResponseResult<Boolean> deleteBrand(
            @ApiParam(value = "品牌ID", required = true) @PathVariable Integer id) {
        try {
            boolean success = brandsService.deleteBrand(id);
            if (success) {
                return ResponseResult.success(true);
            } else {
                return ResponseResult.failure("删除品牌失败");
            }
        } catch (RuntimeException e) {
            // 捕获业务逻辑异常，返回具体错误信息
            return ResponseResult.failure(e.getMessage());
        } catch (Exception e) {
            // 捕获其他异常
            e.printStackTrace();
            return ResponseResult.failure("删除品牌失败：系统错误");
        }
    }
}
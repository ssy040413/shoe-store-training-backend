package com.xys.controller;

import com.xys.entity.Sizes;
import com.xys.service.SizesService;
import com.xys.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 尺寸管理控制器
 */
@RestController
@RequestMapping("/api/v1/sizes")
@Api(tags = "尺寸管理模块")
public class SizesController {

    @Autowired
    private SizesService sizesService;

    /**
     * 获取所有尺寸列表
     */
    @GetMapping("/list")
    @ApiOperation("获取所有尺寸列表")
    public ResponseResult<List<Sizes>> getAllSizes() {
        List<Sizes> sizesList = sizesService.getAllSizes();
        if (sizesList != null) {
            return ResponseResult.success(sizesList);
        } else {
            return ResponseResult.failure("获取尺寸列表失败");
        }
    }

    /**
     * 获取启用状态的尺寸列表
     */
    @GetMapping("/active")
    @ApiOperation("获取启用状态的尺寸列表")
    public ResponseResult<List<Sizes>> getActiveSizes() {
        List<Sizes> sizesList = sizesService.getActiveSizes();
        if (sizesList != null) {
            return ResponseResult.success(sizesList);
        } else {
            return ResponseResult.failure("获取尺寸列表失败");
        }
    }

    /**
     * 根据ID获取尺寸详情
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID获取尺寸详情")
    public ResponseResult<Sizes> getSizeById(
            @ApiParam(value = "尺寸ID", required = true) @PathVariable Integer id) {
        Sizes sizes = sizesService.getSizeById(id);
        if (sizes != null) {
            return ResponseResult.success(sizes);
        } else {
            return ResponseResult.failure("尺寸不存在");
        }
    }

    /**
     * 添加新尺寸
     */
    @PostMapping("/add")
    @ApiOperation("添加新尺寸")
    public ResponseResult<Boolean> addSize(
            @ApiParam(value = "尺寸信息", required = true) @RequestBody Sizes sizes) {
        boolean success = sizesService.addSize(sizes);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("添加尺寸失败");
        }
    }

    /**
     * 更新尺寸信息
     */
    @PostMapping("/update")
    @ApiOperation("更新尺寸信息")
    public ResponseResult<Boolean> updateSize(
            @ApiParam(value = "尺寸信息", required = true) @RequestBody Sizes sizes) {
        if (sizes.getSizeId() == null) {
            return ResponseResult.failure("尺寸ID不能为空");
        }
        boolean success = sizesService.updateSize(sizes);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("更新尺寸失败");
        }
    }

    /**
     * 删除尺寸
     */
    @PostMapping("/delete/{id}")
    @ApiOperation("删除尺寸")
    public ResponseResult<Boolean> deleteSize(
            @ApiParam(value = "尺寸ID", required = true) @PathVariable Integer id) {
        // 检查尺寸是否被商品使用
        if (sizesService.isSizeInUse(id)) {
            return ResponseResult.failure("该尺寸正在被商品使用，无法删除");
        }
        
        boolean success = sizesService.deleteSize(id);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("删除尺寸失败");
        }
    }

    /**
     * 检查尺寸是否被使用
     */
    @GetMapping("/check-usage/{id}")
    @ApiOperation("检查尺寸是否被商品使用")
    public ResponseResult<Boolean> checkSizeUsage(
            @ApiParam(value = "尺寸ID", required = true) @PathVariable Integer id) {
        boolean inUse = sizesService.isSizeInUse(id);
        return ResponseResult.success(inUse);
    }
}
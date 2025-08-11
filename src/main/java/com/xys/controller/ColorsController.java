package com.xys.controller;

import com.xys.entity.Colors;
import com.xys.service.ColorsService;
import com.xys.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 颜色管理控制器
 */
@RestController
@RequestMapping("/api/colors")
@Api(tags = "颜色管理模块")
public class ColorsController {

    @Autowired
    private ColorsService colorsService;

    /**
     * 获取所有颜色列表
     */
    @GetMapping("")
    @ApiOperation("获取所有颜色列表")
    public ResponseResult<List<Colors>> getAllColors() {
        List<Colors> colorsList = colorsService.getAllColors();
        if (colorsList != null) {
            return ResponseResult.success(colorsList);
        } else {
            return ResponseResult.failure("获取颜色列表失败");
        }
    }

    /**
     * 获取启用状态的颜色列表
     */
    @GetMapping("/active")
    @ApiOperation("获取启用状态的颜色列表")
    public ResponseResult<List<Colors>> getActiveColors() {
        List<Colors> colorsList = colorsService.getActiveColors();
        if (colorsList != null) {
            return ResponseResult.success(colorsList);
        } else {
            return ResponseResult.failure("获取颜色列表失败");
        }
    }

    /**
     * 根据ID获取颜色详情
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID获取颜色详情")
    public ResponseResult<Colors> getColorById(
            @ApiParam(value = "颜色ID", required = true) @PathVariable Integer id) {
        Colors colors = colorsService.getColorById(id);
        if (colors != null) {
            return ResponseResult.success(colors);
        } else {
            return ResponseResult.failure("颜色不存在");
        }
    }

    /**
     * 添加新颜色
     */
    @PostMapping("")
    @ApiOperation("添加新颜色")
    public ResponseResult<Boolean> addColor(
            @ApiParam(value = "颜色信息", required = true) @RequestBody Colors colors) {
        boolean success = colorsService.addColor(colors);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("添加颜色失败");
        }
    }

    /**
     * 更新颜色信息
     */
    @PostMapping("/update/{id}")
    @ApiOperation("更新颜色信息")
    public ResponseResult<Boolean> updateColor(
            @ApiParam(value = "颜色ID", required = true) @PathVariable Integer id,
            @ApiParam(value = "颜色信息", required = true) @RequestBody Colors colors) {
        colors.setColorId(id);
        boolean success = colorsService.updateColor(colors);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("更新颜色失败");
        }
    }

    /**
     * 删除颜色
     */
    @PostMapping("/delete/{id}")
    @ApiOperation("删除颜色")
    public ResponseResult<Boolean> deleteColor(
            @ApiParam(value = "颜色ID", required = true) @PathVariable Integer id) {
        boolean success = colorsService.deleteColor(id);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("删除颜色失败");
        }
    }
}
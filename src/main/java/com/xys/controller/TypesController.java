package com.xys.controller;

import com.xys.entity.Types;
import com.xys.service.TypesService;
import com.xys.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 类型管理控制器
 */
@RestController
@RequestMapping("/api/types")
@Api(tags = "类型管理模块")
public class TypesController {

    @Autowired
    private TypesService typesService;

    /**
     * 获取所有类型列表
     */
    @GetMapping("")
    @ApiOperation("获取所有类型列表")
    public ResponseResult<List<Types>> getAllTypes() {
        List<Types> typesList = typesService.getAllTypes();
        if (typesList != null) {
            return ResponseResult.success(typesList);
        } else {
            return ResponseResult.failure("获取类型列表失败");
        }
    }

    /**
     * 获取启用状态的类型列表
     */
    @GetMapping("/active")
    @ApiOperation("获取启用状态的类型列表")
    public ResponseResult<List<Types>> getActiveTypes() {
        List<Types> typesList = typesService.getActiveTypes();
        if (typesList != null) {
            return ResponseResult.success(typesList);
        } else {
            return ResponseResult.failure("获取类型列表失败");
        }
    }

    /**
     * 根据ID获取类型详情
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID获取类型详情")
    public ResponseResult<Types> getTypeById(
            @ApiParam(value = "类型ID", required = true) @PathVariable Integer id) {
        Types types = typesService.getTypeById(id);
        if (types != null) {
            return ResponseResult.success(types);
        } else {
            return ResponseResult.failure("类型不存在");
        }
    }

    /**
     * 添加新类型
     */
    @PostMapping("")
    @ApiOperation("添加新类型")
    public ResponseResult<Types> addType(
            @ApiParam(value = "类型信息", required = true) @RequestBody Types types) {
        Types savedType = typesService.addTypeAndReturn(types);
        if (savedType != null) {
            return ResponseResult.success(savedType);
        } else {
            return ResponseResult.failure("添加类型失败");
        }
    }

    /**
     * 更新类型信息
     */
    @PostMapping("/update/{id}")
    @ApiOperation("更新类型信息")
    public ResponseResult<Types> updateType(
            @ApiParam(value = "类型ID", required = true) @PathVariable Integer id,
            @ApiParam(value = "类型信息", required = true) @RequestBody Types types) {
        types.setTypeId(id);
        Types updatedType = typesService.updateTypeAndReturn(types);
        if (updatedType != null) {
            return ResponseResult.success(updatedType);
        } else {
            return ResponseResult.failure("更新类型失败");
        }
    }

    /**
     * 删除类型
     */
    @PostMapping("/delete/{id}")
    @ApiOperation("删除类型")
    public ResponseResult<Boolean> deleteType(
            @ApiParam(value = "类型ID", required = true) @PathVariable Integer id) {
        boolean success = typesService.deleteType(id);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("删除类型失败");
        }
    }
}
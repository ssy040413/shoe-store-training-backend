package com.xys.controller;

import com.xys.entity.Stock;
import com.xys.service.StockService;
import com.xys.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 库存管理控制器
 */
@RestController
@RequestMapping("/api/v1/stock")
@Api(tags = "库存管理模块")
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * 获取所有库存列表
     */
    @GetMapping("/list")
    @ApiOperation("获取所有库存列表")
    public ResponseResult<List<Stock>> getAllStock() {
        List<Stock> stockList = stockService.getAllStock();
        if (stockList != null) {
            return ResponseResult.success(stockList);
        } else {
            return ResponseResult.failure("获取库存列表失败");
        }
    }

    /**
     * 根据产品ID获取库存列表
     */
    @GetMapping("/product/{productId}")
    @ApiOperation("根据产品ID获取库存列表")
    public ResponseResult<List<Stock>> getStockByProductId(
            @ApiParam(value = "产品ID", required = true) @PathVariable Integer productId) {
        List<Stock> stockList = stockService.getStockByProductId(productId);
        if (stockList != null) {
            return ResponseResult.success(stockList);
        } else {
            return ResponseResult.failure("获取库存列表失败");
        }
    }

    /**
     * 根据产品ID和尺寸ID查询库存
     */
    @GetMapping("/product/{productId}/size/{sizeId}")
    @ApiOperation("根据产品ID和尺寸ID查询库存")
    public ResponseResult<Stock> getStockByProductAndSize(
            @ApiParam(value = "产品ID", required = true) @PathVariable Integer productId,
            @ApiParam(value = "尺寸ID", required = true) @PathVariable Integer sizeId) {
        Stock stock = stockService.getStockByProductAndSize(productId, sizeId);
        if (stock != null) {
            return ResponseResult.success(stock);
        } else {
            return ResponseResult.failure("库存不存在");
        }
    }

    /**
     * 添加新库存
     */
    @PostMapping("/add")
    @ApiOperation("添加新库存")
    public ResponseResult<Boolean> addStock(
            @ApiParam(value = "库存信息", required = true) @RequestBody Stock stock) {
        boolean success = stockService.addStock(stock);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("添加库存失败");
        }
    }

    /**
     * 更新库存信息
     */
    @PostMapping("/update")
    @ApiOperation("更新库存信息")
    public ResponseResult<Boolean> updateStock(
            @ApiParam(value = "库存信息", required = true) @RequestBody Stock stock) {
        if (stock.getShoeId() == null || stock.getSizeId() == null) {
            return ResponseResult.failure("产品ID和尺寸ID不能为空");
        }
        boolean success = stockService.updateStock(stock);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("更新库存失败");
        }
    }

    /**
     * 删除库存
     */
    @PostMapping("/delete/{shoeId}/{sizeId}")
    @ApiOperation("删除库存")
    public ResponseResult<Boolean> deleteStock(
            @ApiParam(value = "产品ID", required = true) @PathVariable Integer shoeId,
            @ApiParam(value = "尺寸ID", required = true) @PathVariable Integer sizeId) {
        boolean success = stockService.deleteStock(shoeId, sizeId);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("删除库存失败");
        }
    }
}
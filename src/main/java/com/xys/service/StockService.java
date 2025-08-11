package com.xys.service;

import com.xys.entity.Stock;
import java.util.List;

/**
 * 库存业务逻辑接口
 */
public interface StockService {

    /**
     * 查询所有库存
     */
    List<Stock> getAllStock();

    /**
     * 根据产品ID和尺寸ID查询库存
     */
    Stock getStockByProductAndSize(Integer shoeId, Integer sizeId);

    /**
     * 添加库存
     */
    boolean addStock(Stock stock);

    /**
     * 更新库存
     */
    boolean updateStock(Stock stock);

    /**
     * 删除库存
     */
    boolean deleteStock(Integer shoeId, Integer sizeId);

    /**
     * 根据产品ID查询库存列表
     */
    List<Stock> getStockByProductId(Integer shoeId);
}
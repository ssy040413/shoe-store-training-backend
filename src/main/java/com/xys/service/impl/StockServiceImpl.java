package com.xys.service.impl;

import com.xys.entity.Stock;
import com.xys.mapper.StockMapper;
import com.xys.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 库存业务逻辑实现类
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public List<Stock> getAllStock() {
        try {
            return stockMapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Stock getStockByProductAndSize(Integer shoeId, Integer sizeId) {
        try {
            return stockMapper.findByProductAndSize(shoeId, sizeId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addStock(Stock stock) {
        try {
            int result = stockMapper.insert(stock);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateStock(Stock stock) {
        try {
            int result = stockMapper.update(stock);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStock(Integer shoeId, Integer sizeId) {
        try {
            int result = stockMapper.deleteByProductAndSize(shoeId, sizeId);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Stock> getStockByProductId(Integer shoeId) {
        try {
            return stockMapper.findByProductId(shoeId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
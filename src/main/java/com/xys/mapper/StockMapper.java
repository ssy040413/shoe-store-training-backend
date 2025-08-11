package com.xys.mapper;

import com.xys.entity.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 库存数据访问接口
 */
@Mapper
public interface StockMapper {

    /**
     * 查询所有库存
     */
    List<Stock> findAll();

    /**
     * 根据产品ID和尺寸ID查询库存
     */
    Stock findByProductAndSize(@Param("shoeId") Integer shoeId, @Param("sizeId") Integer sizeId);

    /**
     * 添加库存
     */
    int insert(Stock stock);

    /**
     * 更新库存
     */
    int update(Stock stock);

    /**
     * 根据产品ID和尺寸ID删除库存
     */
    int deleteByProductAndSize(@Param("shoeId") Integer shoeId, @Param("sizeId") Integer sizeId);

    /**
     * 根据产品ID查询库存
     */
    List<Stock> findByProductId(@Param("shoeId") Integer shoeId);
}
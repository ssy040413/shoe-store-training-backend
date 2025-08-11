package com.xys.mapper;

import com.xys.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单详情数据访问层接口
 */
@Mapper
public interface OrderItemMapper {
    
    /**
     * 插入订单详情
     */
    int insertOrderItem(OrderItem orderItem);
    
    /**
     * 批量插入订单详情
     */
    int insertOrderItems(@Param("orderItems") List<OrderItem> orderItems);
    
    /**
     * 根据详情ID查询订单详情
     */
    OrderItem selectOrderItemById(@Param("itemId") Long itemId);
    
    /**
     * 根据订单ID查询订单详情列表
     */
    List<OrderItem> selectOrderItemsByOrderId(@Param("orderId") Long orderId);
    
    /**
     * 根据商品ID查询订单详情列表
     */
    List<OrderItem> selectOrderItemsByShoeId(@Param("shoeId") Long shoeId);
    
    /**
     * 更新订单详情
     */
    int updateOrderItem(OrderItem orderItem);
    
    /**
     * 删除订单详情
     */
    int deleteOrderItem(@Param("itemId") Long itemId);
    
    /**
     * 根据订单ID删除所有订单详情
     */
    int deleteOrderItemsByOrderId(@Param("orderId") Long orderId);
    
    /**
     * 统计订单详情数量
     */
    int countOrderItemsByOrderId(@Param("orderId") Long orderId);
}
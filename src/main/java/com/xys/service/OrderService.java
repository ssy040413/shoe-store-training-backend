package com.xys.service;

import com.xys.entity.Order;
import com.xys.entity.OrderItem;

import java.util.List;
import java.util.Map;

/**
 * 订单服务层接口
 */
public interface OrderService {
    
    /**
     * 创建订单
     */
    Order createOrder(Order order, List<OrderItem> orderItems);
    
    /**
     * 根据订单ID查询订单详情
     */
    Order getOrderById(Long orderId);
    
    /**
     * 根据订单编号查询订单详情
     */
    Order getOrderByOrderSn(String orderSn);
    
    /**
     * 根据用户ID查询订单列表
     */
    List<Order> getOrdersByUserId(Long userId);
    
    /**
     * 根据状态查询订单列表
     */
    List<Order> getOrdersByStatus(Integer status);
    
    /**
     * 分页查询所有订单
     */
    Map<String, Object> getAllOrders(int page, int size);
    
    /**
     * 更新订单状态
     */
    boolean updateOrderStatus(Long orderId, Integer status);
    
    /**
     * 更新订单信息
     */
    boolean updateOrder(Order order);
    
    /**
     * 删除订单
     */
    boolean deleteOrder(Long orderId);
    
    /**
     * 搜索订单
     */
    Map<String, Object> searchOrders(String keyword, Integer status, int page, int size);
    
    /**
     * 获取订单统计信息
     */
    Map<String, Object> getOrderStatistics();
    
    /**
     * 取消订单
     */
    boolean cancelOrder(Long orderId);
    
    /**
     * 确认收货
     */
    boolean confirmOrder(Long orderId);
    
    /**
     * 申请退货
     */
    boolean applyRefund(Long orderId);
    
    /**
     * 处理退货
     */
    boolean processRefund(Long orderId);
}
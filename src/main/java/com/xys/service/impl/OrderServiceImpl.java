package com.xys.service.impl;

import com.xys.entity.Order;
import com.xys.entity.OrderItem;
import com.xys.mapper.OrderMapper;
import com.xys.mapper.OrderItemMapper;
import com.xys.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单服务层实现类
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public Order createOrder(Order order, List<OrderItem> orderItems) {
        try {
            // 生成订单编号
            if (order.getOrderSn() == null || order.getOrderSn().isEmpty()) {
                order.setOrderSn(generateOrderSn());
            }
            
            // 插入订单
            int result = orderMapper.insertOrder(order);
            if (result > 0 && order.getOrderId() != null) {
                // 设置订单详情的订单ID
                for (OrderItem item : orderItems) {
                    item.setOrderId(order.getOrderId());
                }
                
                // 批量插入订单详情
                orderItemMapper.insertOrderItems(orderItems);
                
                // 查询完整的订单信息
                return orderMapper.selectOrderById(order.getOrderId());
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("创建订单失败: " + e.getMessage(), e);
        }
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderMapper.selectOrderById(orderId);
    }

    @Override
    public Order getOrderByOrderSn(String orderSn) {
        return orderMapper.selectOrderByOrderSn(orderSn);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderMapper.selectOrdersByUserId(userId);
    }

    @Override
    public List<Order> getOrdersByStatus(Integer status) {
        return orderMapper.selectOrdersByStatus(status);
    }

    @Override
    public Map<String, Object> getAllOrders(int page, int size) {
        int offset = (page - 1) * size;
        List<Order> orders = orderMapper.selectAllOrders(offset, size);
        int total = orderMapper.countOrders();
        
        Map<String, Object> result = new HashMap<>();
        result.put("orders", orders);
        result.put("total", total);
        result.put("page", page);
        result.put("size", size);
        result.put("totalPages", (int) Math.ceil((double) total / size));
        
        return result;
    }

    @Override
    public boolean updateOrderStatus(Long orderId, Integer status) {
        return orderMapper.updateOrderStatus(orderId, status) > 0;
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderMapper.updateOrder(order) > 0;
    }

    @Override
    @Transactional
    public boolean deleteOrder(Long orderId) {
        try {
            // 先删除订单详情
            orderItemMapper.deleteOrderItemsByOrderId(orderId);
            // 再删除订单
            return orderMapper.deleteOrder(orderId) > 0;
        } catch (Exception e) {
            throw new RuntimeException("删除订单失败: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> searchOrders(String keyword, Integer status, int page, int size) {
        int offset = (page - 1) * size;
        List<Order> orders = orderMapper.searchOrders(keyword, status, offset, size);
        int total = orderMapper.countSearchOrders(keyword, status);
        
        Map<String, Object> result = new HashMap<>();
        result.put("orders", orders);
        result.put("total", total);
        result.put("page", page);
        result.put("size", size);
        result.put("totalPages", (int) Math.ceil((double) total / size));
        
        return result;
    }

    @Override
    public Map<String, Object> getOrderStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        
        // 总订单数
        statistics.put("totalOrders", orderMapper.countOrders());
        
        // 各状态订单数
        statistics.put("pendingPayment", orderMapper.countOrdersByStatus(0)); // 待付款
        statistics.put("pendingShipment", orderMapper.countOrdersByStatus(1)); // 待发货
        statistics.put("shipping", orderMapper.countOrdersByStatus(2)); // 运输中
        statistics.put("completed", orderMapper.countOrdersByStatus(3)); // 已完成
        statistics.put("cancelled", orderMapper.countOrdersByStatus(4)); // 已取消
        statistics.put("refundApplied", orderMapper.countOrdersByStatus(5)); // 申请退货
        statistics.put("refunded", orderMapper.countOrdersByStatus(6)); // 已退货
        
        return statistics;
    }

    @Override
    public boolean cancelOrder(Long orderId) {
        // 只有待付款和待发货的订单可以取消
        Order order = orderMapper.selectOrderById(orderId);
        if (order != null && (order.getStatus() == 0 || order.getStatus() == 1)) {
            return updateOrderStatus(orderId, 4); // 设置为已取消
        }
        return false;
    }

    @Override
    public boolean confirmOrder(Long orderId) {
        // 只有运输中的订单可以确认收货
        Order order = orderMapper.selectOrderById(orderId);
        if (order != null && order.getStatus() == 2) {
            return updateOrderStatus(orderId, 3); // 设置为已完成
        }
        return false;
    }

    @Override
    public boolean applyRefund(Long orderId) {
        // 只有已完成的订单可以申请退货
        Order order = orderMapper.selectOrderById(orderId);
        if (order != null && order.getStatus() == 3) {
            return updateOrderStatus(orderId, 5); // 设置为申请退货
        }
        return false;
    }

    @Override
    public boolean processRefund(Long orderId) {
        // 只有申请退货的订单可以处理退货
        Order order = orderMapper.selectOrderById(orderId);
        if (order != null && order.getStatus() == 5) {
            return updateOrderStatus(orderId, 6); // 设置为已退货
        }
        return false;
    }

    /**
     * 生成订单编号
     */
    private String generateOrderSn() {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        // 添加3位随机数
        int random = (int) (Math.random() * 1000);
        return "ORD" + timestamp + String.format("%03d", random);
    }
}
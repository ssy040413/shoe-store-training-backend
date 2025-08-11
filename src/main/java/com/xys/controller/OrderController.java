package com.xys.controller;

import com.xys.entity.Order;
import com.xys.entity.OrderItem;
import com.xys.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单控制器
 */
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Map<String, Object> requestData) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 解析订单信息
            Map<String, Object> orderData = (Map<String, Object>) requestData.get("order");
            List<Map<String, Object>> itemsData = (List<Map<String, Object>>) requestData.get("items");

            // 构建订单对象
            Order order = new Order();
            order.setUserId(Long.valueOf(orderData.get("userId").toString()));
            order.setTotalAmount(new java.math.BigDecimal(orderData.get("totalAmount").toString()));
            order.setPayAmount(new java.math.BigDecimal(orderData.get("payAmount").toString()));
            order.setStatus(0); // 默认待付款
            order.setReceiverName(orderData.get("receiverName").toString());
            order.setReceiverPhone(orderData.get("receiverPhone").toString());
            order.setReceiverAddress(orderData.get("receiverAddress").toString());

            // 构建订单详情列表
            List<OrderItem> orderItems = new java.util.ArrayList<>();
            for (Map<String, Object> itemData : itemsData) {
                OrderItem item = new OrderItem();
                item.setShoeId(Long.valueOf(itemData.get("shoeId").toString()));
                item.setProductSku(itemData.get("productSku").toString());
                item.setProductName(itemData.get("productName").toString());
                item.setProductImage(itemData.get("productImage").toString());
                item.setProductPrice(new java.math.BigDecimal(itemData.get("productPrice").toString()));
                item.setQuantity(Integer.valueOf(itemData.get("quantity").toString()));
                item.setTotalPrice(new java.math.BigDecimal(itemData.get("totalPrice").toString()));
                orderItems.add(item);
            }

            Order createdOrder = orderService.createOrder(order, orderItems);
            if (createdOrder != null) {
                response.put("success", true);
                response.put("message", "订单创建成功");
                response.put("data", createdOrder);
            } else {
                response.put("success", false);
                response.put("message", "订单创建失败");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "订单创建失败: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 根据订单ID查询订单详情
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<Map<String, Object>> getOrderById(@PathVariable Long orderId) {
        Map<String, Object> response = new HashMap<>();
        try {
            Order order = orderService.getOrderById(orderId);
            if (order != null) {
                response.put("success", true);
                response.put("data", order);
            } else {
                response.put("success", false);
                response.put("message", "订单不存在");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "查询失败: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 根据订单编号查询订单详情
     */
    @GetMapping("/sn/{orderSn}")
    public ResponseEntity<Map<String, Object>> getOrderByOrderSn(@PathVariable String orderSn) {
        Map<String, Object> response = new HashMap<>();
        try {
            Order order = orderService.getOrderByOrderSn(orderSn);
            if (order != null) {
                response.put("success", true);
                response.put("data", order);
            } else {
                response.put("success", false);
                response.put("message", "订单不存在");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "查询失败: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 根据用户ID查询订单列表
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getOrdersByUserId(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Order> orders = orderService.getOrdersByUserId(userId);
            response.put("success", true);
            response.put("data", orders);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "查询失败: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 根据状态查询订单列表
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<Map<String, Object>> getOrdersByStatus(@PathVariable Integer status) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Order> orders = orderService.getOrdersByStatus(status);
            response.put("success", true);
            response.put("data", orders);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "查询失败: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 分页查询所有订单（支持状态过滤）
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllOrders(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> result;
            if (status != null || startDate != null || endDate != null) {
                // 如果有过滤条件，使用搜索方法
                result = orderService.searchOrders(null, status, page, size);
            } else {
                // 否则使用原来的方法
                result = orderService.getAllOrders(page, size);
            }
            response.put("success", true);
            response.put("data", result);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "查询失败: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 搜索订单
     */
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchOrders(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> result = orderService.searchOrders(keyword, status, page, size);
            response.put("success", true);
            response.put("data", result);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "搜索失败: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 更新订单状态
     */
    @PutMapping("/{orderId}/status")
    public ResponseEntity<Map<String, Object>> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody Map<String, Object> requestData) {
        Map<String, Object> response = new HashMap<>();
        try {
            Integer status = Integer.valueOf(requestData.get("status").toString());
            boolean success = orderService.updateOrderStatus(orderId, status);
            if (success) {
                response.put("success", true);
                response.put("message", "状态更新成功");
            } else {
                response.put("success", false);
                response.put("message", "状态更新失败");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "状态更新失败: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 取消订单
     */
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Map<String, Object>> cancelOrder(@PathVariable Long orderId) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean success = orderService.cancelOrder(orderId);
            if (success) {
                response.put("success", true);
                response.put("message", "订单取消成功");
            } else {
                response.put("success", false);
                response.put("message", "订单取消失败，只有待付款和待发货的订单可以取消");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "订单取消失败: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 确认收货
     */
    @PutMapping("/{orderId}/confirm")
    public ResponseEntity<Map<String, Object>> confirmOrder(@PathVariable Long orderId) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean success = orderService.confirmOrder(orderId);
            if (success) {
                response.put("success", true);
                response.put("message", "确认收货成功");
            } else {
                response.put("success", false);
                response.put("message", "确认收货失败，只有运输中的订单可以确认收货");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "确认收货失败: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 申请退货
     */
    @PutMapping("/{orderId}/refund/apply")
    public ResponseEntity<Map<String, Object>> applyRefund(@PathVariable Long orderId) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean success = orderService.applyRefund(orderId);
            if (success) {
                response.put("success", true);
                response.put("message", "退货申请成功");
            } else {
                response.put("success", false);
                response.put("message", "退货申请失败，只有已完成的订单可以申请退货");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "退货申请失败: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 处理退货
     */
    @PutMapping("/{orderId}/refund/process")
    public ResponseEntity<Map<String, Object>> processRefund(@PathVariable Long orderId) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean success = orderService.processRefund(orderId);
            if (success) {
                response.put("success", true);
                response.put("message", "退货处理成功");
            } else {
                response.put("success", false);
                response.put("message", "退货处理失败，只有申请退货的订单可以处理");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "退货处理失败: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 删除订单
     */
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Map<String, Object>> deleteOrder(@PathVariable Long orderId) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean success = orderService.deleteOrder(orderId);
            if (success) {
                response.put("success", true);
                response.put("message", "订单删除成功");
            } else {
                response.put("success", false);
                response.put("message", "订单删除失败");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "订单删除失败: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 获取订单统计信息
     */
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getOrderStatistics() {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> statistics = orderService.getOrderStatistics();
            response.put("success", true);
            response.put("data", statistics);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "获取统计信息失败: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 获取订单统计信息 (简化路径)
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getOrderStats() {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> statistics = orderService.getOrderStatistics();
            response.put("success", true);
            response.put("data", statistics);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "获取统计信息失败: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
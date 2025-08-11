package com.xys.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单实体类
 */
public class Order {
    private Long orderId;           // 订单ID (主键)
    private String orderSn;         // 订单编号
    private Long userId;            // 下单用户ID
    private BigDecimal totalAmount; // 订单总金额
    private BigDecimal payAmount;   // 实际支付金额
    private Integer status;         // 订单状态 (0:待付款, 1:待发货, 2:运输中, 3:已完成, 4:已取消, 5:申请退货, 6:已退货)
    private String receiverName;    // 收货人姓名
    private String receiverPhone;   // 收货人电话
    private String receiverAddress; // 收货人详细地址
    private LocalDateTime createdAt; // 订单创建时间
    private LocalDateTime updatedAt; // 订单更新时间
    
    // 关联的订单详情列表
    private List<OrderItem> orderItems;

    // 构造函数
    public Order() {}

    public Order(Long orderId, String orderSn, Long userId, BigDecimal totalAmount, 
                 BigDecimal payAmount, Integer status, String receiverName, 
                 String receiverPhone, String receiverAddress) {
        this.orderId = orderId;
        this.orderSn = orderSn;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.payAmount = payAmount;
        this.status = status;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverAddress = receiverAddress;
    }

    // Getter 和 Setter 方法
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    /**
     * 获取订单状态描述
     */
    public String getStatusDesc() {
        switch (this.status) {
            case 0: return "待付款";
            case 1: return "待发货";
            case 2: return "运输中";
            case 3: return "已完成";
            case 4: return "已取消";
            case 5: return "申请退货";
            case 6: return "已退货";
            default: return "未知状态";
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderSn='" + orderSn + '\'' +
                ", userId=" + userId +
                ", totalAmount=" + totalAmount +
                ", payAmount=" + payAmount +
                ", status=" + status +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
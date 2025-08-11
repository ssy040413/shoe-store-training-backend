package com.xys.entity;

import java.math.BigDecimal;

/**
 * 订单详情实体类
 */
public class OrderItem {
    private Long itemId;        // 订单详情项ID (主键)
    private Long orderId;       // 所属订单ID
    private Long shoeId;        // 购买的鞋子ID
    private String productSku;  // 商品SKU (冗余)
    private String productName; // 商品名称 (冗余)
    private String productImage; // 商品主图 (冗余)
    private BigDecimal productPrice; // 购买时单价
    private Integer quantity;   // 购买数量
    private BigDecimal totalPrice; // 该项总金额

    // 构造函数
    public OrderItem() {}

    public OrderItem(Long itemId, Long orderId, Long shoeId, String productSku, 
                     String productName, String productImage, BigDecimal productPrice, 
                     Integer quantity, BigDecimal totalPrice) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.shoeId = shoeId;
        this.productSku = productSku;
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getter 和 Setter 方法
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getShoeId() {
        return shoeId;
    }

    public void setShoeId(Long shoeId) {
        this.shoeId = shoeId;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemId=" + itemId +
                ", orderId=" + orderId +
                ", shoeId=" + shoeId +
                ", productSku='" + productSku + '\'' +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productPrice=" + productPrice +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
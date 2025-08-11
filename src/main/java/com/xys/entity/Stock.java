package com.xys.entity;

/**
 * 库存实体类
 */
public class Stock {
    private Integer stockId;
    private Integer shoeId;
    private Integer sizeId;
    private Integer stockQuantity;

    // 关联对象
    private Shoes shoe;
    private Sizes size;

    // 构造函数
    public Stock() {}

    public Stock(Integer shoeId, Integer sizeId, Integer stockQuantity) {
        this.shoeId = shoeId;
        this.sizeId = sizeId;
        this.stockQuantity = stockQuantity;
    }

    // Getter和Setter方法
    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getShoeId() {
        return shoeId;
    }

    public void setShoeId(Integer shoeId) {
        this.shoeId = shoeId;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Shoes getShoe() {
        return shoe;
    }

    public void setShoe(Shoes shoe) {
        this.shoe = shoe;
    }

    public Sizes getSize() {
        return size;
    }

    public void setSize(Sizes size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", shoeId=" + shoeId +
                ", sizeId=" + sizeId +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
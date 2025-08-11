package com.xys.entity;

/**
 * 尺寸实体类
 */
public class Sizes {
    private Integer sizeId;
    private String shoeSize;
    private Integer status;  // 改为Integer类型，1-启用，0-禁用
    private String remarks;  // 备注

    // 构造函数
    public Sizes() {}

    public Sizes(Integer sizeId, String shoeSize, Integer status, String remarks) {
        this.sizeId = sizeId;
        this.shoeSize = shoeSize;
        this.status = status;
        this.remarks = remarks;
    }

    // Getter和Setter方法
    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public String getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(String shoeSize) {
        this.shoeSize = shoeSize;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Sizes{" +
                "sizeId=" + sizeId +
                ", shoeSize='" + shoeSize + '\'' +
                ", status=" + status +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
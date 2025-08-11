package com.xys.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 品牌实体类
 */
public class Brands {
    @JsonProperty("brand_id")
    private Integer brand_id;
    
    @JsonProperty("brand_name")
    private String brand_name;
    
    private Integer status;
    private String remarks;

    // 构造函数
    public Brands() {}

    public Brands(Integer brand_id, String brand_name, Integer status) {
        this.brand_id = brand_id;
        this.brand_name = brand_name;
        this.status = status;
    }

    public Brands(Integer brand_id, String brand_name, Integer status, String remarks) {
        this.brand_id = brand_id;
        this.brand_name = brand_name;
        this.status = status;
        this.remarks = remarks;
    }

    // Getter和Setter方法
    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
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
        return "Brands{" +
                "brand_id=" + brand_id +
                ", brand_name='" + brand_name + '\'' +
                ", status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
package com.xys.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 颜色实体类
 */
public class Colors {
    @JsonProperty("colorId")
    private Integer colorId;
    
    @JsonProperty("colorName")
    private String colorName;
    
    private String status;
    private String remarks;

    // 构造函数
    public Colors() {}

    public Colors(Integer colorId, String colorName, String status) {
        this.colorId = colorId;
        this.colorName = colorName;
        this.status = status;
    }

    public Colors(Integer colorId, String colorName, String status, String remarks) {
        this.colorId = colorId;
        this.colorName = colorName;
        this.status = status;
        this.remarks = remarks;
    }

    // Getter和Setter方法
    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
        return "Colors{" +
                "colorId=" + colorId +
                ", colorName='" + colorName + '\'' +
                ", status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
package com.xys.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 类型实体类
 */
public class Types {
    @JsonProperty("typeId")
    private Integer typeId;
    
    @JsonProperty("typeName")
    private String typeName;
    
    private String status;

    // 构造函数
    public Types() {}

    public Types(Integer typeId, String typeName, String status) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.status = status;
    }

    // Getter和Setter方法
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Types{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
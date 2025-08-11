package com.xys.entity;

import java.time.LocalDateTime;

public class PartnerLink {

    private Integer id;
    private String linkName;
    private String linkUrl;
    private String logoUrl;
    private Integer sortOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // --- 标准的 Getters 和 Setters ---
    // (可在IDE中自动生成)
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getLinkName() { return linkName; }
    public void setLinkName(String linkName) { this.linkName = linkName; }
    public String getLinkUrl() { return linkUrl; }
    public void setLinkUrl(String linkUrl) { this.linkUrl = linkUrl; }
    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
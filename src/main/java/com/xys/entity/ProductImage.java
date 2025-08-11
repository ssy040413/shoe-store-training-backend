package com.xys.entity;

import java.time.LocalDateTime;

/**
 * 商品图片实体类
 */
public class ProductImage {
    
    private Long imageId;
    private Long shoeId;
    private String imageUrl;
    private String imageName;
    private String imageType;
    private Integer sortOrder;
    private Integer isMain;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public ProductImage() {}
    
    public ProductImage(Long shoeId, String imageUrl, String imageName, String imageType, Integer sortOrder, Integer isMain) {
        this.shoeId = shoeId;
        this.imageUrl = imageUrl;
        this.imageName = imageName;
        this.imageType = imageType;
        this.sortOrder = sortOrder;
        this.isMain = isMain;
        this.status = 1; // 默认启用
    }
    
    // Getters and Setters
    public Long getImageId() {
        return imageId;
    }
    
    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
    
    public Long getShoeId() {
        return shoeId;
    }
    
    public void setShoeId(Long shoeId) {
        this.shoeId = shoeId;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getImageName() {
        return imageName;
    }
    
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    
    public String getImageType() {
        return imageType;
    }
    
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
    
    public Integer getSortOrder() {
        return sortOrder;
    }
    
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    
    public Integer getIsMain() {
        return isMain;
    }
    
    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
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
    
    @Override
    public String toString() {
        return "ProductImage{" +
                "imageId=" + imageId +
                ", shoeId=" + shoeId +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageName='" + imageName + '\'' +
                ", imageType='" + imageType + '\'' +
                ", sortOrder=" + sortOrder +
                ", isMain=" + isMain +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
package com.xys.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 鞋类产品实体类
 */
public class Shoes {
    private Integer shoeId;
    private String serialNumber;
    private String name;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private LocalDate launchDate;
    private String originPlace;
    private String gender;
    private String description;
    private String detailedInfo;
    private Integer itemPoints;
    private String status;
    private String productImageUrl;
    private Integer salesVolume;
    private Integer colorId;
    private Integer brandId;
    private Integer typeId;

    // 关联对象
    private Colors color;
    private Brands brand;
    private Types type;
    
    // 商品图片列表
    private List<ProductImage> productImages;

    // 构造函数
    public Shoes() {}

    public Shoes(Integer shoeId, String serialNumber, String name, BigDecimal price, 
                 BigDecimal discountPrice, LocalDate launchDate, String originPlace, 
                 String gender, String description, String detailedInfo, Integer itemPoints, 
                 String status, String productImageUrl, Integer salesVolume, 
                 Integer colorId, Integer brandId, Integer typeId) {
        this.shoeId = shoeId;
        this.serialNumber = serialNumber;
        this.name = name;
        this.price = price;
        this.discountPrice = discountPrice;
        this.launchDate = launchDate;
        this.originPlace = originPlace;
        this.gender = gender;
        this.description = description;
        this.detailedInfo = detailedInfo;
        this.itemPoints = itemPoints;
        this.status = status;
        this.productImageUrl = productImageUrl;
        this.salesVolume = salesVolume;
        this.colorId = colorId;
        this.brandId = brandId;
        this.typeId = typeId;
    }

    // Getter和Setter方法
    public Integer getShoeId() {
        return shoeId;
    }

    public void setShoeId(Integer shoeId) {
        this.shoeId = shoeId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailedInfo() {
        return detailedInfo;
    }

    public void setDetailedInfo(String detailedInfo) {
        this.detailedInfo = detailedInfo;
    }

    public Integer getItemPoints() {
        return itemPoints;
    }

    public void setItemPoints(Integer itemPoints) {
        this.itemPoints = itemPoints;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public Brands getBrand() {
        return brand;
    }

    public void setBrand(Brands brand) {
        this.brand = brand;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    @Override
    public String toString() {
        return "Shoes{" +
                "shoeId=" + shoeId +
                ", serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", launchDate=" + launchDate +
                ", originPlace='" + originPlace + '\'' +
                ", gender='" + gender + '\'' +
                ", description='" + description + '\'' +
                ", detailedInfo='" + detailedInfo + '\'' +
                ", itemPoints=" + itemPoints +
                ", status='" + status + '\'' +
                ", productImageUrl='" + productImageUrl + '\'' +
                ", salesVolume=" + salesVolume +
                ", colorId=" + colorId +
                ", brandId=" + brandId +
                ", typeId=" + typeId +
                '}';
    }
}
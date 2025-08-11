package com.xys.service;

import com.xys.entity.Brands;
import java.util.List;

/**
 * 品牌业务逻辑接口
 */
public interface BrandsService {

    /**
     * 查询所有品牌
     */
    List<Brands> getAllBrands();

    /**
     * 根据ID查询品牌
     */
    Brands getBrandById(Integer brand_id);

    /**
     * 添加品牌
     */
    boolean addBrand(Brands brands);

    /**
     * 更新品牌
     */
    boolean updateBrand(Brands brands);

    /**
     * 删除品牌
     */
    boolean deleteBrand(Integer brand_id);

    /**
     * 查询启用状态的品牌
     */
    List<Brands> getActiveBrands();
}
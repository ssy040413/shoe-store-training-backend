package com.xys.service.impl;

import com.xys.entity.Brands;
import com.xys.mapper.BrandsMapper;
import com.xys.service.BrandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 品牌业务逻辑实现类
 */
@Service
public class BrandsServiceImpl implements BrandsService {

    @Autowired
    private BrandsMapper brandsMapper;

    @Override
    public List<Brands> getAllBrands() {
        try {
            return brandsMapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Brands getBrandById(Integer brand_id) {
        try {
            return brandsMapper.findById(brand_id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addBrand(Brands brands) {
        try {
            if (brands.getStatus() == null) {
                brands.setStatus(1);
            }
            int result = brandsMapper.insert(brands);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateBrand(Brands brands) {
        try {
            int result = brandsMapper.update(brands);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBrand(Integer brand_id) {
        try {
            // 检查是否有鞋子引用此品牌
            int shoesCount = brandsMapper.countShoesUsingBrand(brand_id);
            if (shoesCount > 0) {
                // 如果有引用，抛出自定义异常或返回特定错误
                throw new RuntimeException("无法删除品牌：该品牌下还有 " + shoesCount + " 个商品，请先删除相关商品");
            }
            
            int result = brandsMapper.delete(brand_id);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Brands> getActiveBrands() {
        try {
            return brandsMapper.findActiveBrands();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
package com.xys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xys.entity.Shoes;
import com.xys.mapper.ShoesMapper;
import com.xys.service.ShoesService;
import com.xys.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 鞋类产品业务逻辑实现类
 */
@Service
public class ShoesServiceImpl implements ShoesService {

    @Autowired
    private ShoesMapper shoesMapper;
    
    @Autowired
    private ProductImageService productImageService;

    @Override
    public List<Shoes> getAllShoes() {
        try {
            return shoesMapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PageInfo<Shoes> listByPage(int pageNum, int pageSize, String keyword) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<Shoes> list;
            if (keyword != null && !keyword.trim().isEmpty()) {
                list = shoesMapper.findByKeyword(keyword.trim());
            } else {
                list = shoesMapper.findAll();
            }
            return new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageInfo<>();
        }
    }

    @Override
    public Shoes getShoesById(Integer shoeId) {
        try {
            return shoesMapper.findById(shoeId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public Shoes addShoes(Shoes shoes) {
        try {
            // 设置默认值
            if (shoes.getStatus() == null) {
                shoes.setStatus("1"); // 使用数字字符串，1表示ACTIVE
            }
            if (shoes.getSalesVolume() == null) {
                shoes.setSalesVolume(0);
            }
            if (shoes.getItemPoints() == null) {
                shoes.setItemPoints(0);
            }
            
            int result = shoesMapper.insert(shoes);
            if (result > 0) {
                // 处理商品图片
                if (shoes.getProductImages() != null && !shoes.getProductImages().isEmpty()) {
                    productImageService.saveProductImages(shoes.getShoeId().longValue(), shoes.getProductImages());
                }
                
                // 插入成功后，返回包含生成ID的商品对象
                return shoes;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public boolean updateShoes(Shoes shoes) {
        try {
            // 使用 updateSelective 方法，只更新非空字段，避免 null 约束冲突
            int result = shoesMapper.updateSelective(shoes);
            
            if (result > 0) {
                // 处理商品图片
                if (shoes.getProductImages() != null) {
                    productImageService.saveProductImages(shoes.getShoeId().longValue(), shoes.getProductImages());
                }
            }
            
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateShoesStatus(Integer shoeId, String status) {
        try {
            Shoes shoes = new Shoes();
            shoes.setShoeId(shoeId);
            
            // 将字符串状态转换为数字字符串，以匹配数据库的整数类型
            String numericStatus;
            if ("ACTIVE".equalsIgnoreCase(status)) {
                numericStatus = "1";
            } else if ("INACTIVE".equalsIgnoreCase(status)) {
                numericStatus = "0";
            } else {
                // 如果已经是数字字符串，直接使用
                numericStatus = status;
            }
            
            shoes.setStatus(numericStatus);
            int result = shoesMapper.updateSelective(shoes);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteShoes(Integer shoeId) {
        try {
            // 先删除商品图片
            productImageService.deleteByShoeId(shoeId.longValue());
            
            // 再删除商品
            int result = shoesMapper.deleteById(shoeId);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Shoes> searchShoes(String keyword) {
        try {
            if (keyword == null || keyword.trim().isEmpty()) {
                return getAllShoes();
            }
            return shoesMapper.findByKeyword(keyword.trim());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
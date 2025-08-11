package com.xys.service.impl;

import com.xys.entity.Colors;
import com.xys.mapper.ColorsMapper;
import com.xys.service.ColorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 颜色业务逻辑实现类
 */
@Service
public class ColorsServiceImpl implements ColorsService {

    @Autowired
    private ColorsMapper colorsMapper;

    @Override
    public List<Colors> getAllColors() {
        try {
            return colorsMapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Colors getColorById(Integer colorId) {
        try {
            return colorsMapper.findById(colorId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addColor(Colors colors) {
        try {
            if (colors.getStatus() == null) {
                colors.setStatus("1");
            }
            int result = colorsMapper.insert(colors);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateColor(Colors colors) {
        try {
            int result = colorsMapper.update(colors);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteColor(Integer colorId) {
        try {
            // 检查是否有鞋子引用此颜色
            int shoesCount = colorsMapper.countShoesUsingColor(colorId);
            if (shoesCount > 0) {
                // 如果有引用，抛出自定义异常或返回特定错误
                throw new RuntimeException("无法删除颜色：该颜色下还有 " + shoesCount + " 个商品，请先删除相关商品");
            }
            
            int result = colorsMapper.deleteById(colorId);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Colors> getActiveColors() {
        try {
            return colorsMapper.findActiveColors();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
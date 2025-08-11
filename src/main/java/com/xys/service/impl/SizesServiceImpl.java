package com.xys.service.impl;

import com.xys.entity.Sizes;
import com.xys.mapper.SizesMapper;
import com.xys.service.SizesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 尺寸业务逻辑实现类
 */
@Service
public class SizesServiceImpl implements SizesService {

    @Autowired
    private SizesMapper sizesMapper;

    @Override
    public List<Sizes> getAllSizes() {
        try {
            return sizesMapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Sizes getSizeById(Integer sizeId) {
        try {
            return sizesMapper.findById(sizeId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addSize(Sizes sizes) {
        try {
            if (sizes.getStatus() == null) {
                sizes.setStatus(1); // 默认启用状态
            }
            int result = sizesMapper.insert(sizes);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSize(Sizes sizes) {
        try {
            int result = sizesMapper.update(sizes);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSize(Integer sizeId) {
        try {
            int result = sizesMapper.deleteById(sizeId);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Sizes> getActiveSizes() {
        try {
            return sizesMapper.findActiveSizes();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isSizeInUse(Integer sizeId) {
        try {
            int count = sizesMapper.countShoesUsingSizeId(sizeId);
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
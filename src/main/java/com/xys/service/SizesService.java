package com.xys.service;

import com.xys.entity.Sizes;
import java.util.List;

/**
 * 尺寸业务逻辑接口
 */
public interface SizesService {

    /**
     * 查询所有尺寸
     */
    List<Sizes> getAllSizes();

    /**
     * 根据ID查询尺寸
     */
    Sizes getSizeById(Integer sizeId);

    /**
     * 添加尺寸
     */
    boolean addSize(Sizes sizes);

    /**
     * 更新尺寸
     */
    boolean updateSize(Sizes sizes);

    /**
     * 删除尺寸
     */
    boolean deleteSize(Integer sizeId);

    /**
     * 查询启用状态的尺寸
     */
    List<Sizes> getActiveSizes();

    /**
     * 检查尺寸是否被商品使用
     */
    boolean isSizeInUse(Integer sizeId);
}
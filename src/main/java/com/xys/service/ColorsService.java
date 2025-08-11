package com.xys.service;

import com.xys.entity.Colors;
import java.util.List;

/**
 * 颜色业务逻辑接口
 */
public interface ColorsService {

    /**
     * 查询所有颜色
     */
    List<Colors> getAllColors();

    /**
     * 根据ID查询颜色
     */
    Colors getColorById(Integer colorId);

    /**
     * 添加颜色
     */
    boolean addColor(Colors colors);

    /**
     * 更新颜色
     */
    boolean updateColor(Colors colors);

    /**
     * 删除颜色
     */
    boolean deleteColor(Integer colorId);

    /**
     * 查询启用状态的颜色
     */
    List<Colors> getActiveColors();
}
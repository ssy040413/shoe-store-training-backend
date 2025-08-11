package com.xys.service;

import com.github.pagehelper.PageInfo;
import com.xys.entity.Shoes;
import java.util.List;

/**
 * 鞋类产品业务逻辑接口
 */
public interface ShoesService {

    /**
     * 查询所有产品
     */
    List<Shoes> getAllShoes();

    /**
     * 分页查询产品列表
     */
    PageInfo<Shoes> listByPage(int pageNum, int pageSize, String keyword);

    /**
     * 根据ID查询产品
     */
    Shoes getShoesById(Integer shoeId);

    /**
     * 添加产品
     */
    Shoes addShoes(Shoes shoes);

    /**
     * 更新产品
     */
    boolean updateShoes(Shoes shoes);

    /**
     * 修改产品状态
     */
    boolean updateShoesStatus(Integer shoeId, String status);

    /**
     * 删除产品
     */
    boolean deleteShoes(Integer shoeId);

    /**
     * 根据关键词搜索产品
     */
    List<Shoes> searchShoes(String keyword);
}
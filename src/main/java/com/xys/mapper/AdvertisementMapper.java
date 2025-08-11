package com.xys.mapper;

import com.xys.entity.Advertisement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface AdvertisementMapper {

    /**
     * 根据关键词进行动态分页查询
     * @param keyword 搜索关键词 (对应 title)
     * @return 广告列表
     */
    List<Advertisement> findByKeyword(@Param("keyword") String keyword);

    /**
     * 新增一个广告
     */
    int insert(Advertisement advertisement);

    /**
     * 根据ID删除一个广告
     */
    int deleteById(Integer id);

    /**
     * 更新广告
     */
    int updateById(Advertisement advertisement);

    /**
     * 批量删除
     */
    int deleteBatchByIds(@Param("ids") List<Integer> ids);
}
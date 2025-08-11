package com.xys.mapper;

import com.xys.entity.PartnerLink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface PartnerLinkMapper {

    /**
     * 查询所有合作链接 (无任何条件)
     * 这是最基础、最通用的查询，所有条件判断都将移到 Service 层处理。
     * @return 所有的合作链接列表
     */
    List<PartnerLink> findAll();

    /**
     * 新增一个合作链接
     */
    int insert(PartnerLink partnerLink);

    /**
     * 更新合作链接
     */
    int updateById(PartnerLink partnerLink);

    /**
     * 根据ID删除一个合作链接
     */
    int deleteById(Integer id);

    /**
     * 批量删除
     */
    int deleteBatchByIds(@Param("ids") List<Integer> ids);
}
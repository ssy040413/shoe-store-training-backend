package com.xys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xys.entity.PartnerLink;
import com.xys.mapper.PartnerLinkMapper;
import com.xys.service.PartnerLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartnerLinkServiceImpl implements PartnerLinkService {

    @Autowired
    private PartnerLinkMapper partnerLinkMapper;

    @Override
    public PageInfo<PartnerLink> listByPage(int pageNum, int pageSize, String keyword) {
        // 1. 【核心变化】在这里不再直接进行分页查询，而是先查询出【所有】数据
        List<PartnerLink> allLinks = partnerLinkMapper.findAll();

        // 2. 在内存中进行数据处理 (筛选和搜索)
        List<PartnerLink> filteredLinks;

        // 判断关键词是否存在且不为空
        if (StringUtils.hasText(keyword)) {
            // 使用 Java 8 Stream API 进行内存过滤
            filteredLinks = allLinks.stream()
                    .filter(link ->
                            // 检查 linkName 是否包含关键词 (忽略大小写)
                            (link.getLinkName() != null && link.getLinkName().toLowerCase().contains(keyword.toLowerCase())) ||
                                    // 检查 linkUrl 是否包含关键词 (忽略大小写)
                                    (link.getLinkUrl() != null && link.getLinkUrl().toLowerCase().contains(keyword.toLowerCase()))
                    )
                    .collect(Collectors.toList()); // 将过滤后的结果收集成一个新的 List
        } else {
            // 如果没有关键词，则直接使用全部数据
            filteredLinks = allLinks;
        }

        // 3. 【手动分页】对【筛选后】的结果进行手动分页
        int total = filteredLinks.size();
        int startIndex = (pageNum - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, total);

        List<PartnerLink> pagedList;
        if (startIndex >= total) {
            // 如果起始索引超出总数，返回空列表
            pagedList = new java.util.ArrayList<>();
        } else {
            pagedList = filteredLinks.subList(startIndex, endIndex);
        }

        // 4. 将手动分页的结果封装到 PageInfo 对象中
        // PageInfo 的构造函数可以接收一个 List，并可以手动设置其他分页信息
        PageInfo<PartnerLink> pageInfo = new PageInfo<>(pagedList);
        pageInfo.setTotal(total); // 设置总记录数
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPages((int) Math.ceil((double) total / pageSize)); // 计算总页数
        pageInfo.setIsFirstPage(pageNum == 1);
        pageInfo.setIsLastPage(pageNum == pageInfo.getPages() || pageInfo.getPages() == 0);

        return pageInfo;
    }
    @Override
    public boolean addLink(PartnerLink partnerLink) {
        return partnerLinkMapper.insert(partnerLink) > 0;
    }

    @Override
    public boolean updateLink(PartnerLink partnerLink) {
        return partnerLinkMapper.updateById(partnerLink) > 0;
    }

    @Override
    public boolean deleteLinkById(Integer id) {
        return partnerLinkMapper.deleteById(id) > 0;
    }

    @Override
    public boolean deleteBatchLinks(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        return partnerLinkMapper.deleteBatchByIds(ids) > 0;
    }
}
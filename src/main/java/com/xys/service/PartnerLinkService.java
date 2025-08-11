package com.xys.service;

import com.github.pagehelper.PageInfo;
import com.xys.entity.PartnerLink;
import java.util.List;

public interface PartnerLinkService {
    PageInfo<PartnerLink> listByPage(int pageNum, int pageSize, String keyword);
    boolean addLink(PartnerLink partnerLink);
    boolean updateLink(PartnerLink partnerLink);
    boolean deleteLinkById(Integer id);
    boolean deleteBatchLinks(List<Integer> ids);
}
package com.xys.service;

import com.github.pagehelper.PageInfo;
import com.xys.entity.Advertisement;
import java.util.List;

public interface AdvertisementService {
    PageInfo<Advertisement> listByPage(int pageNum, int pageSize, String keyword);
    boolean addAd(Advertisement advertisement);
    boolean updateAd(Advertisement advertisement);
    boolean deleteAdById(Integer id);
    boolean deleteBatchAds(List<Integer> ids);
}
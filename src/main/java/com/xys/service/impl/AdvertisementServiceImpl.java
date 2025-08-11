package com.xys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xys.entity.Advertisement;
import com.xys.mapper.AdvertisementMapper;
import com.xys.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Override
    public PageInfo<Advertisement> listByPage(int pageNum, int pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<Advertisement> ads = advertisementMapper.findByKeyword(keyword);
        return new PageInfo<>(ads);
    }

    @Override
    public boolean addAd(Advertisement advertisement) {
        return advertisementMapper.insert(advertisement) > 0;
    }

    @Override
    public boolean updateAd(Advertisement advertisement) {
        return advertisementMapper.updateById(advertisement) > 0;
    }

    @Override
    public boolean deleteAdById(Integer id) {
        return advertisementMapper.deleteById(id) > 0;
    }

    @Override
    public boolean deleteBatchAds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        return advertisementMapper.deleteBatchByIds(ids) > 0;
    }
}
package com.xys.controller;

import com.github.pagehelper.PageInfo;
import com.xys.entity.Advertisement;
import com.xys.service.AdvertisementService;
import com.xys.util.ResponseResult; // 确保导入路径正确
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/advertisements")
@Api(tags = "广告管理模块")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping("/list")
    @ApiOperation("分页并搜索广告列表")
    public ResponseResult<PageInfo<Advertisement>> list(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        PageInfo<Advertisement> pageInfo = advertisementService.listByPage(pageNum, pageSize, keyword);
        return ResponseResult.success(pageInfo);
    }

    @PostMapping("/add")
    @ApiOperation("新增广告")
    public ResponseResult<Boolean> add(@RequestBody Advertisement advertisement) {
        boolean success = advertisementService.addAd(advertisement);
        return success ? ResponseResult.success(true) : ResponseResult.failure("新增广告失败");
    }

    @PostMapping("/update/{id}")
    @ApiOperation("更新广告")
    public ResponseResult<Boolean> update(@PathVariable Integer id, @RequestBody Advertisement advertisement) {
        advertisement.setId(id); // 确保ID正确设置
        boolean success = advertisementService.updateAd(advertisement);
        return success ? ResponseResult.success(true) : ResponseResult.failure("更新广告失败");
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除单个广告")
    public ResponseResult<Boolean> delete(@PathVariable Integer id) {
        boolean success = advertisementService.deleteAdById(id);
        return success ? ResponseResult.success(true) : ResponseResult.failure("删除广告失败");
    }

    @PostMapping("/delete/batch")
    @ApiOperation("批量删除广告")
    public ResponseResult<Boolean> deleteBatch(@RequestBody List<Integer> ids) {
        boolean success = advertisementService.deleteBatchAds(ids);
        return success ? ResponseResult.success(true) : ResponseResult.failure("批量删除广告失败");
    }
}

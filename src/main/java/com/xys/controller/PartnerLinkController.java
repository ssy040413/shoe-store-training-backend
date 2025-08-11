package com.xys.controller;

import com.github.pagehelper.PageInfo;
import com.xys.entity.PartnerLink;
import com.xys.service.PartnerLinkService;
import com.xys.util.ResponseResult; // 确保导入路径正确
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/partner-links")
@Api(tags = "合作链接管理模块")
public class PartnerLinkController {

    @Autowired
    private PartnerLinkService partnerLinkService;

    @GetMapping("/list")
    @ApiOperation("分页并搜索合作链接列表")
    public ResponseResult<PageInfo<PartnerLink>> list(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize, // 默认每页5条，和截图一致
            @RequestParam(required = false) String keyword) {
        PageInfo<PartnerLink> pageInfo = partnerLinkService.listByPage(pageNum, pageSize, keyword);
        return ResponseResult.success(pageInfo);
    }

    @PostMapping("/add")
    @ApiOperation("新增合作链接")
    public ResponseResult<Boolean> add(@RequestBody PartnerLink partnerLink) {
        boolean success = partnerLinkService.addLink(partnerLink);
        return success ? ResponseResult.success(true) : ResponseResult.failure("新增链接失败");
    }

    @PostMapping("/update/{id}")
    @ApiOperation("更新合作链接")
    public ResponseResult<Boolean> update(@PathVariable Integer id, @RequestBody PartnerLink partnerLink) {
        partnerLink.setId(id); // 确保ID正确设置
        boolean success = partnerLinkService.updateLink(partnerLink);
        return success ? ResponseResult.success(true) : ResponseResult.failure("更新链接失败");
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除单个合作链接")
    public ResponseResult<Boolean> delete(@PathVariable Integer id) {
        boolean success = partnerLinkService.deleteLinkById(id);
        return success ? ResponseResult.success(true) : ResponseResult.failure("删除链接失败");
    }

    @PostMapping("/delete/batch")
    @ApiOperation("批量删除合作链接")
    public ResponseResult<Boolean> deleteBatch(@RequestBody List<Integer> ids) {
        boolean success = partnerLinkService.deleteBatchLinks(ids);
        return success ? ResponseResult.success(true) : ResponseResult.failure("批量删除链接失败");
    }
}
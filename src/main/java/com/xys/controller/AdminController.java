package com.xys.controller;

import com.xys.util.ResponseResult;
import com.xys.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Api(tags = "后台管理员模块")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public ResponseResult<Boolean> login(String username, String password) {

        boolean loginSuccess = adminService.login(username, password);

        if (loginSuccess) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("用户名或密码错误");
        }
    }

    @PostMapping("/logout")
    @ApiOperation("管理员退出登录")
    public ResponseResult<Boolean> logout() {
        try {
            // 这里可以添加会话清理逻辑，比如：
            // 1. 清除服务器端的会话信息
            // 2. 记录退出日志
            // 3. 清理缓存等
            
            return ResponseResult.success("退出登录成功", true);
        } catch (Exception e) {
            return ResponseResult.failure("退出登录失败");
        }
    }
}
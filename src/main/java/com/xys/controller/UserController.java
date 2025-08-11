package com.xys.controller;

import com.github.pagehelper.PageInfo;
import com.xys.entity.User;
import com.xys.service.UserService;
// 确保导入您项目中 ResponseResult 的正确路径
import com.xys.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")

@Api(tags = "用户管理模块")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @ApiOperation("获取所有用户列表")
    public ResponseResult<List<User>> listAll() {
        List<User> userList = userService.listAllUsers();
        return ResponseResult.success(userList);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID获取单个用户信息")
    public ResponseResult<User> getById(
            @ApiParam(value = "用户ID", required = true) @PathVariable Long id) { // ID类型改为Long
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseResult.success(user);
        } else {
            return ResponseResult.failure("用户不存在");
        }
    }

    @PostMapping("/add")
    @ApiOperation("新增一个用户")
    public ResponseResult<Boolean> add(
            @ApiParam(value = "用户信息", required = true) @RequestBody User user) {
        // @RequestBody 告诉Spring将请求体中的JSON直接转换为User对象
        boolean success = userService.addUser(user);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("新增用户失败");
        }
    }

    @PostMapping("/update") // 使用POST请求进行更新操作
    @ApiOperation("更新一个用户信息")
    public ResponseResult<Boolean> update(
            @ApiParam(value = "用户信息", required = true) @RequestBody User user) {
        if (user.getId() == null) {
            return ResponseResult.failure("更新失败：用户ID不能为空");
        }
        boolean success = userService.updateUser(user);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("更新用户失败或用户不存在");
        }
    }

    @PostMapping("/delete/{id}") // 使用POST请求进行删除操作
    @ApiOperation("根据ID删除一个用户")
    public ResponseResult<Boolean> delete(
            @ApiParam(value = "用户ID", required = true) @PathVariable Long id) { // ID类型改为Long
        boolean success = userService.deleteUserById(id);
        if (success) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.failure("删除用户失败或用户不存在");
        }
    }

    @PostMapping("/toggle-status") // 定义这是一个POST请求
    @ApiOperation("切换用户启用/禁用状态")
    public ResponseResult<Boolean> toggleStatus(
            @ApiParam(value = "用户ID", required = true) @RequestParam Long id,
            @ApiParam(value = "目标状态 (1:启用, 0:禁用)", required = true) @RequestParam Integer status) {

        try {
            // 1. 从数据库中获取该用户的当前完整信息
            User userToUpdate = userService.getUserById(id);

            // 2. 检查用户是否存在
            if (userToUpdate == null) {
                return ResponseResult.failure("操作失败：用户不存在");
            }

            // 3. 设置新的状态
            userToUpdate.setStatus(status);

            // 4. 调用通用的更新服务来保存更改
            //    因为我们的 updateUser 方法是“先查再改”，所以这里传递
            //    一个只更新了 status 的完整对象是完全安全的。
            boolean success = userService.updateUser(userToUpdate);

            if (success) {
                return ResponseResult.success(true);
            } else {
                return ResponseResult.failure("切换状态失败");
            }
        } catch (Exception e) {
            e.printStackTrace(); // 在后台打印详细错误日志
            return ResponseResult.failure("服务器内部错误，操作失败");
        }
    }

    @GetMapping("/list/page")
    @ApiOperation("分页并按条件获取用户列表")
    public ResponseResult<PageInfo<User>> listByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer gender,
            @RequestParam(required = false) Integer status
    ) {
        try {
            // 调用 Service 层时，不再传递时间参数
            PageInfo<User> pageInfo = userService.listUsersByPage(
                    pageNum, pageSize, keyword, gender, status
            );
            return ResponseResult.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.failure("查询用户列表失败");
        }
    }
}
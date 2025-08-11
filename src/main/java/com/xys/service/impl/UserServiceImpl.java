package com.xys.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xys.entity.User;
import com.xys.mapper.UserMapper;
import com.xys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listAllUsers() {
        return userMapper.findAll();
    }

    @Override
    public User getUserById(Long id) { // ID类型改为Long
        return userMapper.findById(id);
    }

    @Override
    public boolean addUser(User user) {
        // 在实际项目中，密码加密应在这里完成
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean updateUser(User userFromRequest) {
        // 1. 根据请求传来的ID，从数据库中查询出完整的、原始的用户信息
        User userFromDB = userMapper.findById(userFromRequest.getId());

        // 2. 检查用户是否存在
        if (userFromDB == null) {
            // 如果用户不存在，直接返回更新失败
            return false;
        }

        // 3. 将请求中需要更新的字段，覆盖到从数据库查出的对象上
        //    这样可以保证其他字段（如 username, password等）保持不变
        if (userFromRequest.getNickname() != null) {
            userFromDB.setNickname(userFromRequest.getNickname());
        }
        if (userFromRequest.getEmail() != null) {
            userFromDB.setEmail(userFromRequest.getEmail());
        }
        if (userFromRequest.getPhone() != null) {
            userFromDB.setPhone(userFromRequest.getPhone());
        }
        if (userFromRequest.getAvatarUrl() != null) {
            userFromDB.setAvatarUrl(userFromRequest.getAvatarUrl());
        }
        if (userFromRequest.getGender() != null) {
            userFromDB.setGender(userFromRequest.getGender());
        }
        if (userFromRequest.getStatus() != null) {
            // 这正是我们“切换用户状态”功能所需要的！
            userFromDB.setStatus(userFromRequest.getStatus());
        }
        // 注意：username 和 password 通常不在这里更新

        // 4. 将这个完整的、已更新部分字段的对象，传给 Mapper 进行更新
        return userMapper.update(userFromDB) > 0;
    }

    @Override
    public PageInfo<User> listUsersByPage(int pageNum, int pageSize, String keyword,
                                          Integer gender, Integer status) {
        PageHelper.startPage(pageNum, pageSize);
        // 调用 Mapper 方法时，不再传递时间参数
        List<User> userList = userMapper.findByConditions(keyword, gender, status);
        return new PageInfo<>(userList);
    }

    @Override
    public boolean deleteUserById(Long id) { // ID类型改为Long
        return userMapper.deleteById(id) > 0;
    }
}
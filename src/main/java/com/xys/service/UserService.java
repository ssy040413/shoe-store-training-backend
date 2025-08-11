package com.xys.service;

import com.github.pagehelper.PageInfo;
import com.xys.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    List<User> listAllUsers();
    User getUserById(Long id); // ID类型改为Long
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUserById(Long id); // ID类型改为Long
    PageInfo<User> listUsersByPage(int pageNum, int pageSize, String keyword,
                                   Integer gender, Integer status);
}
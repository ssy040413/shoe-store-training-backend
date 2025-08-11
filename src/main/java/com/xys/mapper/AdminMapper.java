package com.xys.mapper;

import com.xys.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    /**
     * 根据用户名和密码查询管理员
     * @param username 用户名
     * @param password 密码
     * @return 如果找到，返回Admin对象；否则返回null
     */
    Admin findByUsernameAndPassword(String username, String password);
}
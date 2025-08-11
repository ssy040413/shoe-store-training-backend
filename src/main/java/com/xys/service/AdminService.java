package com.xys.service;

public interface AdminService {
    /**
     * 管理员登录验证
     * @param username 账号
     * @param password 密码
     *      * @return 登录成功返回 true, 失败返回 false
     */
    boolean login(String username, String password);
}
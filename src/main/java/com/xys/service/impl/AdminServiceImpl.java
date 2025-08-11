package com.xys.service.impl;

import com.xys.entity.Admin;
import com.xys.mapper.AdminMapper;
import com.xys.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean login(String username, String password) {

        Admin admin = adminMapper.findByUsernameAndPassword(username, password);

        return admin != null;
    }
}
package com.project.tableMaid.service;

import com.project.tableMaid.entity.account.Admin;
import com.project.tableMaid.repository.AdminMapper;
import com.project.tableMaid.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Admin searchCompanyName(int adminId) {
        return userMapper.findCompanyNameByAdminId(adminId);
    }
}

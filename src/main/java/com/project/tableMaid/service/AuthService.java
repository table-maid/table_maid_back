package com.project.tableMaid.service;

import com.project.tableMaid.dto.account.request.AdminSignupReqDto;
import com.project.tableMaid.entity.account.Admin;
import org.springframework.transaction.annotation.Transactional;

public class AuthService {

//    @Transactional(rollbackFor = Exception.class)
//    public void signup(AdminSignupReqDto signupReqDto) {
//        int successCount = 0;
//        Admin admin = signupReqDto.toEntity(passwordEncoder);
//
//        successCount += userMapper.saveUser(user);
//        successCount += userMapper.saveRole(user.getUserId(), 1);
//
//        if(successCount < 2) {
//            throw new SaveException();
//        }
//    }
}

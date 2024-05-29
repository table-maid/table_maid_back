package com.project.tableMaid.service;

import com.project.tableMaid.dto.account.request.AdminSignupReqDto;
import com.project.tableMaid.entity.account.Admin;
import com.project.tableMaid.exception.SaveException;
import com.project.tableMaid.repository.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminAuthService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void adminSignup(AdminSignupReqDto adminSignupReqDto) {
        System.out.println("서비스 진입");
        int successCount = 0;
        Admin admin = adminSignupReqDto.toEntity(passwordEncoder);

        successCount += adminMapper.saveAdmin(admin);

        if(successCount < 1) {
            throw new SaveException();
        }
    }
}

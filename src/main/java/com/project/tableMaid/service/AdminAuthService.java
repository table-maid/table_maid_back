package com.project.tableMaid.service;

import com.project.tableMaid.dto.account.request.AdminSignupReqDto;
import com.project.tableMaid.entity.account.Admin;
import com.project.tableMaid.exception.SaveException;
import com.project.tableMaid.jwt.JwtProvider;
import com.project.tableMaid.repository.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminAuthService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    JwtProvider jwtProvider;

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

    public String adminSignin(AdminSignupReqDto adminSignupReqDto) {
        Admin admin = adminMapper.findAdminByUsername(adminSignupReqDto.getUsername());
        if( admin == null) {
            throw new UsernameNotFoundException("사용자 정보를 확인하세요");
        }
        if(!passwordEncoder.matches(adminSignupReqDto.getPassword(), admin.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 확인하세요");
        }
        return jwtProvider.generateToken(admin);
    }
}

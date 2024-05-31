package com.project.tableMaid.service;

import com.project.tableMaid.dto.account.request.EditPasswordReqDto;
import com.project.tableMaid.dto.account.response.SearchAdminInfoRespDto;
import com.project.tableMaid.entity.account.Admin;
import com.project.tableMaid.exception.ValidException;
import com.project.tableMaid.repository.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminAccountService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void editPassword(EditPasswordReqDto editPasswordReqDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminMapper.findAdminByUsername(authentication.getName());
        if(!passwordEncoder.matches(editPasswordReqDto.getOldPassword(),admin.getPassword())) {
            throw new ValidException(Map.of("oldPassword", "비밀번호 인증에 실패하였습니다. \n다시입력해주세요."));
        }
        if(!editPasswordReqDto.getNewPassword().equals(editPasswordReqDto.getNewPasswordCheck())) {
            throw new ValidException(Map.of("newPasswordCheck", "새로운 비밀번호가 서로 일치하지 않습니다.\n다시입력해주세요."));
        }
        if(passwordEncoder.matches(editPasswordReqDto.getNewPassword(),admin.getPassword())) {
            throw new ValidException(Map.of("newPassword","이전 비밀번호와 동일한 비밀번호는 사용하실 수 없습니다.\n다시입력해주세요."));
        }

        admin.setPassword(passwordEncoder.encode(editPasswordReqDto.getNewPassword()));
        adminMapper.modifyPassword(admin);
    }

    public SearchAdminInfoRespDto searchAdminInfoByAdminId(int adminId) {
        Admin admin = adminMapper.findAdminByAdminId(adminId);
        if (admin == null) {
            System.out.println("사용자가 null입니다");
            return null;
        }
        return SearchAdminInfoRespDto.builder()
                .adminId(admin.getAdminId())
                .adminName(admin.getAdminName())
                .username(admin.getUsername())
                .email(admin.getEmail())
                .build();
    }

}

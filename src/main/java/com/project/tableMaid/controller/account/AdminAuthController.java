package com.project.tableMaid.controller.account;

import com.project.tableMaid.aop.annotation.ParamsPrintAspect;
import com.project.tableMaid.aop.annotation.ValidAspect;
import com.project.tableMaid.dto.account.request.AdminSignupReqDto;
import com.project.tableMaid.service.AdminAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
    @RequestMapping("/admin/auth")
public class AdminAuthController {

    @Autowired
    AdminAuthService adminAuthService;

    @ParamsPrintAspect
    @ValidAspect
    @PostMapping("/signup")
    public ResponseEntity<?> adminSignup(@Valid @RequestBody AdminSignupReqDto adminSignupReqDto, BindingResult bindingResult) {
        adminAuthService.adminSignup(adminSignupReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> adminSignin(@RequestBody AdminSignupReqDto adminSignupReqDto) {
        return ResponseEntity.ok(adminAuthService.adminSignin(adminSignupReqDto));
    }

}

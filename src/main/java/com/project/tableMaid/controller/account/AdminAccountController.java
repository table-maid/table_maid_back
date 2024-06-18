package com.project.tableMaid.controller.account;

import com.project.tableMaid.aop.annotation.ParamsPrintAspect;
import com.project.tableMaid.aop.annotation.ValidAspect;
import com.project.tableMaid.dto.account.request.EditPasswordReqDto;
import com.project.tableMaid.security.PrincipalUser;
import com.project.tableMaid.service.AdminAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AdminAccountController {

    @Autowired
    private AdminAccountService adminAccountService;

    @GetMapping("/principal")
    public ResponseEntity<?> getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        return ResponseEntity.ok(principalUser);
    }

    @ParamsPrintAspect
    @ValidAspect
    @PutMapping("/password")
    public ResponseEntity<?> editPassword(@Valid @RequestBody EditPasswordReqDto editPasswordReqDto, BindingResult bindingResult) {
        adminAccountService.editPassword(editPasswordReqDto);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/id")
    public ResponseEntity<?> getUsername(@RequestParam(value = "adminName") String adminName, @RequestParam(value = "email")String email) {
        return ResponseEntity.ok(adminAccountService.findAccountByNameAndEmail(adminName, email));
    }
}
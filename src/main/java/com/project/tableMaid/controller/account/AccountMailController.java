package com.project.tableMaid.controller.account;

import com.project.tableMaid.aop.annotation.ParamsPrintAspect;
import com.project.tableMaid.dto.account.request.AdminFindPasswordReqDto;
import com.project.tableMaid.dto.account.request.AdminSearchAdminNameReqDto;
import com.project.tableMaid.dto.account.request.verifyAuthCodeReqDto;
import com.project.tableMaid.entity.account.Admin;
import com.project.tableMaid.service.AccountMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/mail")
public class AccountMailController {

    @Autowired
    AccountMailService accountMailService;

    @PostMapping("/{email}/send/authenticate")
    @ResponseBody
    public ResponseEntity<?> sendAuthenticate(HttpServletRequest request, @PathVariable String email) {
        request.getSession().setAttribute("timer", new Date());
        return ResponseEntity.ok(accountMailService.sendAuthMail(email));
    }

    @ParamsPrintAspect
    @PostMapping("/verify/authenticate")
    public ResponseEntity<?> verifyAuthCode(@RequestBody verifyAuthCodeReqDto verifyAuthCodeReqDto) {
        Map<String, String> resultMap = accountMailService.verifyEmailCode(verifyAuthCodeReqDto.getEmail(), verifyAuthCodeReqDto.getAuthCode());
        String status = resultMap.get("status");
        String message = resultMap.get("message");

        if("success".equals(status)) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }


    @PostMapping("/send/id")
    public ResponseEntity<?> send(HttpServletRequest request, @RequestBody AdminSearchAdminNameReqDto adminSearchAdminNameReqDto) {
        request.getSession().setAttribute("timer", new Date());
        Admin admin = accountMailService.searchAccountByNameAndEmail(adminSearchAdminNameReqDto.getAdminName(), adminSearchAdminNameReqDto.getEmail());
        accountMailService.searchAccountByMail(admin);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/send/temporary/password")
    public ResponseEntity<?> sendTemporaryPassword(HttpServletRequest request, @RequestBody AdminFindPasswordReqDto adminFindPasswordReqDto) {
        request.getSession().setAttribute("timer", new Date());
        Admin admin = accountMailService.searchAccountByUsernameAndEmail(adminFindPasswordReqDto.getUsername(), adminFindPasswordReqDto.getEmail());
        accountMailService.sendTemporaryPassword(admin);

        if (admin != null && accountMailService.sendTemporaryPassword(admin)) {
            return ResponseEntity.ok("임시 비밀번호가 성공적으로 전송되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("임시 비밀번호 전송에 실패했습니다.");
        }
    }
}
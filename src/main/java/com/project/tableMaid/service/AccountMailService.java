package com.project.tableMaid.service;

import com.project.tableMaid.entity.account.Admin;
import com.project.tableMaid.repository.AdminMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

@Service
public class AccountMailService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.address}")
    private String fromMailAddress;
    @Value("${server.port}")
    private String serverPort;
    @Value("${server.deploy-address}")
    private String serverAddress;

    public Admin searchAccountByNameAndEmail(String name, String email) {
        return adminMapper.findAccountByNameAndEmail(name, email);
    }
    public boolean searchAccountByMail(Admin admin) {
        boolean result = false;
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        if(admin != null) {
            try {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
                helper.setSubject("[Table Maid] 계정 찾기");
                helper.setFrom(fromMailAddress);
                helper.setTo(admin.getEmail());

                StringBuilder mailContent = new StringBuilder();
                mailContent.append("<div>");
                mailContent.append("<h1>Table Maid</h1>");
                mailContent.append("<div>");
                mailContent.append("<h3>귀하의 아이디는 " + admin.getUsername() + "입니다</h3>");
                mailContent.append("</div>");
                mailContent.append("</div>");

                mimeMessage.setText(mailContent.toString(), "UTF-8", "html");
                javaMailSender.send(mimeMessage);
                result = true;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return result;
    }

    public Admin searchAccountByUsernameAndEmail(String username, String email) {
        return adminMapper.findAccountByNameAndEmail(username, email);
    }

    public boolean sendTemporaryPassword(Admin admin) {

        boolean result = false;
        String temporaryPassword = generateTemporaryPassword();
        String encodedPassword = passwordEncoder.encode(temporaryPassword);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        if(admin != null) {
            try {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
                helper.setSubject("임시 비밀번호 발급");
                helper.setFrom(fromMailAddress);
                helper.setTo(admin.getEmail());

                StringBuilder mailContent = new StringBuilder();
                mailContent.append("<div>");
                mailContent.append("<h1>Table Maid</h1>");
                mailContent.append("<div>");
                mailContent.append("<p>안녕하세요, " + admin.getUsername() + "님!</p>");
                mailContent.append("<p>임시 비밀번호는 다음과 같습니다: <strong>" + temporaryPassword + "</strong></p>");
                mailContent.append("<p>로그인 후에 비밀번호를 변경해주세요.</p>");
                mailContent.append("</div>");
                mailContent.append("</div>");

                mimeMessage.setText(mailContent.toString(), "UTF-8", "html");
                javaMailSender.send(mimeMessage);

                admin.setPassword(encodedPassword);
                adminMapper.updateAccountTemporaryPw(admin.getAdminId(), admin.getPassword());

                result = true;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return result;
    }

    private String generateTemporaryPassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}

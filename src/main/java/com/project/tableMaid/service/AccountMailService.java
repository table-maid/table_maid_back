package com.project.tableMaid.service;

import com.project.tableMaid.entity.account.Admin;
import com.project.tableMaid.repository.AdminMapper;
import com.project.tableMaid.util.RedisUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Service
public class AccountMailService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${spring.mail.address}")
    private String fromMailAddress;
    @Value("${server.port}")
    private String serverPort;
    @Value("${server.deploy-address}")
    private String serverAddress;
    // 0~9 a~z 까지 숫자와 문자를 섞는 6자리 난수
    private String createdCode() {
        int leftLimit = 48; // number '0'
        int rightLimit = 122; // alphabet 'z'
        int targetStringLength = 6;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <=57 || i >=65) && (i <= 90 || i>= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }


    // 본인계정 이메일 인증하기
    public boolean sendAuthMail(String Email) {
        boolean result = false;
        String toMailAddress = Email;
        String authCode = createdCode();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setSubject("[Table Maid] 계정 메일 인증");
            helper.setFrom(fromMailAddress);
            helper.setTo(toMailAddress);

            StringBuilder mailContent = new StringBuilder();
            mailContent.append("<div>");
            mailContent.append("<h1>Table Maid</h1>");
            mailContent.append("<div>");
            mailContent.append("<h3>인증번호는 " + authCode + "입니다</h3>");
            mailContent.append("</div>");
            mailContent.append("</div>");

            mimeMessage.setText(mailContent.toString(), "utf-8", "html");

            javaMailSender.send(mimeMessage);   // 메일 전송

            redisUtil.setDataExpire(toMailAddress, authCode, 60*3L);
            result = true;

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 메일 인증 코드 확인
    public Map<String, String> verifyEmailCode(String email, String code) {
        String authCode = redisUtil.getData(email);
        if (authCode != null && Objects.equals(authCode, code)) {
            return Map.of("status", "success", "message", "이메일 인증에 성공하였습니다.");
        }
        if (authCode == null) {
            return Map.of("status", "fail", "message", "인증 시간을 초과하였습니다.");
        }
        return Map.of("status", "fail", "message", "인증번호가 일치하지 않습니다.");
    }


    // 아이디 찾기
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

    // 비밀번호 찾기
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

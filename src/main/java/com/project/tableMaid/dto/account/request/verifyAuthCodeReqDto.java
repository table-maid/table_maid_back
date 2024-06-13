package com.project.tableMaid.dto.account.request;

import lombok.Data;

@Data
public class verifyAuthCodeReqDto {
    private String email;
    private String authCode;
}

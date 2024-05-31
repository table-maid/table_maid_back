package com.project.tableMaid.dto.account.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchAdminInfoRespDto {
    private int adminId;
    private String adminName;
    private String username;
    private String email;
}

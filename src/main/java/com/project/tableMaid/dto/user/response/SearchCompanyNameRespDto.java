package com.project.tableMaid.dto.user.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchCompanyNameRespDto {
    private String companyName;
    private int adminId;
}

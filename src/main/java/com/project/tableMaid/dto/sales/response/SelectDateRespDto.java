package com.project.tableMaid.dto.sales.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SelectDateRespDto {
    private int year;
    private int month;
    private int day;
    private int count;
    private int dayTotalSales;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

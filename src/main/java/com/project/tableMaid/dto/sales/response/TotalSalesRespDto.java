package com.project.tableMaid.dto.sales.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TotalSalesRespDto {
    private int adminId;
    private int year;
    private int month;
    private int count;
    private int totalSales;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

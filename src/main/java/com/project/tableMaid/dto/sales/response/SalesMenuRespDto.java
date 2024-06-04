package com.project.tableMaid.dto.sales.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SalesMenuRespDto {
    private int year;
    private int month;
    private int day;
    private int totalSales;
}

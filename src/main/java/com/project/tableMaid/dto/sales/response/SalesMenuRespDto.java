package com.project.tableMaid.dto.sales.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SalesMenuRespDto {
    private int adminId;
    private int orderNumber;
    private String menuName;
    private int year;
    private int month;
    private int day;
    private int count;
    private int menuTotalPrice;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

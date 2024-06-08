package com.project.tableMaid.dto.sales.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MenuTotalSalesRespDto {
    private int menuId;
    private String menuName;
    private int year;
    private int month;
    private int count;
    private int menuTotalSales;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

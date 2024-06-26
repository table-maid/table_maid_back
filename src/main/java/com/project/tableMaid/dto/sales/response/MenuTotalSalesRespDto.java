package com.project.tableMaid.dto.sales.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MenuTotalSalesRespDto {
    private int adminId;
    private int menuId;
    private String menuName;
    private int year;
    private int month;
    private int day;
    private int count;
    private int menuTotalSales;
    private String menuImgUrl;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

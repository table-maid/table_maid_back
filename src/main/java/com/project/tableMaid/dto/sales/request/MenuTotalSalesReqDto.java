package com.project.tableMaid.dto.sales.request;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MenuTotalSalesReqDto {
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

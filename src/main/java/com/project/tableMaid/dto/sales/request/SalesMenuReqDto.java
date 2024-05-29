package com.project.tableMaid.dto.sales.request;

import lombok.Data;

@Data
public class SalesMenuReqDto {
    private int adminId;
    private String menuName;
    private int year;
    private int month;
    private int day;
    private int count;
    private int price;
}

package com.project.tableMaid.dto.sales.request;

import lombok.Data;

@Data
public class OrderMenuReqDto {
    private int adminId;
    private int orderNumber;
    private int tableId;
    private int peopleCount;
    private String menuName;
    private int menuPrice;
    private int menuCount;
}

package com.project.tableMaid.dto.sales.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderMenuRespDto {
    private int adminId;
    private int orderNumber;
    private int tableNumber;
    private int peopleCount;
    private String menuName;
    private int menuCount;
    private int menuTotalPrice;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

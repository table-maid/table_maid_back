package com.project.tableMaid.dto.sales.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderListRespDto {
    private int orderNumber;
    private int tableNumber;
    private int peopleCount;
    private String menuName;
    private int menuCount;
    private int menuTotalPrice;
    private int orderTotalPrice;
    private LocalDateTime paymentDate;


}

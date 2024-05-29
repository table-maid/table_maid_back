package com.project.tableMaid.entity.sales;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private int orderId;
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

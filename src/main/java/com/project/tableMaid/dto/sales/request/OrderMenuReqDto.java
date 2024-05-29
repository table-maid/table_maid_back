package com.project.tableMaid.dto.sales.request;

import com.project.tableMaid.entity.sales.Order;
import lombok.Data;

@Data
public class OrderMenuReqDto {
    private int adminId;
    private int orderNumber;
    private int tableNumber;
    private int peopleCount;
    private String menuName;
    private int menuCount;
    private int menuTotalPrice;

    public Order toOrder() {
        return Order.builder()
                .adminId(adminId)
                .orderNumber(orderNumber)
                .tableNumber(tableNumber)
                .peopleCount(peopleCount)
                .menuName(menuName)
                .menuCount(menuCount)
                .menuTotalPrice(menuTotalPrice)
                .build();
    }
}

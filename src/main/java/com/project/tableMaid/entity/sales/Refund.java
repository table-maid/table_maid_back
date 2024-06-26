package com.project.tableMaid.entity.sales;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Refund {
    private int refundId;
    private int adminId;
    private String  refundCode;
    private int orderNumber;
    private int tableNumber;
    private String menuName;
    private int menuCount;
    private String optionName;
    private int optionPrice;
    private int menuTotalPrice;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

package com.project.tableMaid.dto.sales.request;

import com.project.tableMaid.entity.sales.Sales;
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

    public Sales toSales() {
        return Sales.builder()
                .adminId(adminId)
                .menuName(menuName)
                .year(year)
                .month(month)
                .day(day)
                .count(count)
                .menuTotalPrice(count*price)
                .build();
    }

}

package com.project.tableMaid.entity.sales;

import com.project.tableMaid.dto.sales.response.SalesMenuRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sales {
    private int salesId;
    private int adminId;
    private int orderNumber;
    private String menuName;
    private int year;
    private int month;
    private int day;
    private int count;
    private int menuTotalPrice;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public SalesMenuRespDto toSalesMenuRespDto () {
        return SalesMenuRespDto.builder()
                .adminId(adminId)
                .orderNumber(orderNumber)
                .menuName(menuName)
                .year(year)
                .month(month)
                .day(day)
                .count(count)
                .menuTotalPrice(menuTotalPrice)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }
}

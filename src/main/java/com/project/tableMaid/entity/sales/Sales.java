package com.project.tableMaid.entity.sales;

import com.project.tableMaid.dto.sales.request.MenuTotalSalesReqDto;
import com.project.tableMaid.dto.sales.response.MenuTotalSalesRespDto;
import com.project.tableMaid.dto.sales.response.SelectDateRespDto;
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
    private int menuId;
    private int orderNumber;
    private String menuName;
    private int year;
    private int month;
    private int day;
    private int count;
    private int menuTotalPrice;
    private int dayTotalSales;
    private int menuTotalSales;
    private String menuImgUrl;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;


    public SelectDateRespDto toSalesMenuRespDto () {
        return SelectDateRespDto.builder()
                .year(year)
                .month(month)
                .day(day)
                .count(count)
                .dayTotalSales(dayTotalSales)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }

    public MenuTotalSalesRespDto toMenuTotalSalesRespDto () {
        return MenuTotalSalesRespDto.builder()
                .adminId(adminId)
                .menuId(menuId)
                .menuName(menuName)
                .year(year)
                .month(month)
                .day(day)
                .count(count)
                .menuTotalSales(menuTotalSales)
                .menuImgUrl(menuImgUrl)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }
}

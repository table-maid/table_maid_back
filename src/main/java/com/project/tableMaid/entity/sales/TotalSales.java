package com.project.tableMaid.entity.sales;

import com.project.tableMaid.dto.sales.response.TotalSalesRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalSales {
    private int year;
    private int month;
    private int day;
    private int totalSales;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public TotalSalesRespDto toTotalSalesMenuRespDto () {
        return TotalSalesRespDto.builder()
                .year(year)
                .month(month)
                .day(day)
                .totalSales(totalSales)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }
}

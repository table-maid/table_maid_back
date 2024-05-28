package com.project.tableMaid.entity.sales;

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
    private String menuName;
    private int year;
    private int month;
    private int day;
    private int count;
    private int menuTotalPrice;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

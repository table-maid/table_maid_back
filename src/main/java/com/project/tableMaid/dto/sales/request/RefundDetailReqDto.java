package com.project.tableMaid.dto.sales.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RefundDetailReqDto {
    private int adminId;
    private int orderNumber;
    private List<Menu> menu;
    private String paymentDate;
    private int tableNumber;

    @Data
    public static class Menu {
        private int menuCount;
        private List<Option> options;
        private int menuTotalPrice;
        private String menuName;
    }

    @Data
    public static class Option {
        private int optionPrice;
        private String optionName;
    }
}

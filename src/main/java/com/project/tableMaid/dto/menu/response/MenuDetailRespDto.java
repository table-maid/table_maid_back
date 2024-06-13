package com.project.tableMaid.dto.menu.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDetailRespDto {
    private int adminId;
    private int menuId;
    private int menuCategoryId;
    private String menuCategoryName;
    private String menuCode;
    private String menuName;
    private int menuPrice;
    private String menuImgUrl;
    private int recommendMenu;
    private int menuState;
    private List<OptionDetail> optionList;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OptionDetail {
        private int optionTitleId;
        private String optionTitleName;
        private List<Integer> optionNameIds;
        private List<String> optionNames;
        private List<Integer> optionPrices;
    }
}

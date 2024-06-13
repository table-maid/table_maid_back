package com.project.tableMaid.dto.menu.request;

import lombok.Data;

@Data
public class SearchMenuListReqDto {
    private int adminId;
    private String menuName;
    private String menuCode;
    private int menuState;
    private int recommendMenu;
    private int menuCategoryId;
}

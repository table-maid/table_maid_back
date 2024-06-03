package com.project.tableMaid.dto.menu.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MenusRespDto {
    private int adminId;
    private int menuCategoryId;
    private String menuCode;
    private String menuName;
    private int menuPrice;
    private int recommendMenu;
    private int menuState;
    private String menuImgUrl;

}

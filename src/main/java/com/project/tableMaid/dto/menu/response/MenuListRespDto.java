package com.project.tableMaid.dto.menu.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuListRespDto {
    private int adminId;
    private String companyName;
    private int menuId;
    private String menuCode;
    private String menuCategoryName;
    private String menuName;
    private int menuPrice;
    private int menuState;
    private int recommendMenu;

}

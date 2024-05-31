package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.Menu;
import lombok.Data;

@Data
public class UpdateMenuReqDto {
    private int menuId;
    private int adminId;
    private String menuName;
    private int menuPrice;
    private int recommendMenu;
    private int menuState;
    private String menuImgUrl;
    private int menuCategoryId;

    public Menu toEntity() {
        return Menu.builder()
                .menuId(menuId)
                .adminId(adminId)
                .menuName(menuName)
                .menuPrice(menuPrice)
                .recommendMenu(recommendMenu)
                .menuState(menuState)
                .menuImgUrl(menuImgUrl)
                .menuCategoryId(menuCategoryId)
                .build();
    }
}

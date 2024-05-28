package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.Menu;
import lombok.Data;

@Data
public class RegisterMenuReqDto {
    private String menuCode;
    private String menuName;
    private int menuPrice;
    private int recommendMenu;
    private int menuState;
    private String menuImgUrl;

    public Menu toEntity() {
        return Menu.builder()
                .menuCode(menuCode)
                .menuName(menuName)
                .menuPrice(menuPrice)
                .menuState(menuState)
                .recommendMenu(recommendMenu)
                .menuImgUrl(menuImgUrl)
                .build();
    }
}

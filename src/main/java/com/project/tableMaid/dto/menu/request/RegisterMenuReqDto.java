package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.Menu;
import lombok.Data;

import java.util.Random;

@Data
public class RegisterMenuReqDto {
    private int adminId;
    private String menuCode;
    private String menuName;
    private int menuPrice;
    private int recommendMenu;
    private int menuState;
    private String menuImgUrl;
    private int menuCategoryId;

    public Menu toEntity() {
        if (menuCode == null || menuCode.isEmpty()) {
            this.menuCode = generateRandomString(8);
        }
        return Menu.builder()
                .adminId(adminId)
                .menuCode(menuCode)
                .menuName(menuName)
                .menuPrice(menuPrice)
                .menuState(menuState)
                .recommendMenu(recommendMenu)
                .menuImgUrl(menuImgUrl)
                .menuCategoryId(menuCategoryId)
                .build();
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}

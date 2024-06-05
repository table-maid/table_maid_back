package com.project.tableMaid.entity.menu;

import com.project.tableMaid.dto.menu.response.MenusRespDto;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private int menuId;
    private int adminId;
    private int menuCategoryId;
    private String menuCode;
    private String menuName;
    private int menuPrice;
    private int recommendMenu;
    private int menuState;
    private String menuImgUrl;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private MenuCategory menuCategory;

    public MenusRespDto toMenuRespDto() {
        return MenusRespDto.builder()
                .adminId(adminId)
                .menuId(menuId)
                .menuCategoryId(menuCategoryId)
                .menuCategoryName(menuCategory.getMenuCategoryName())
                .menuName(menuName)
                .menuCode(menuCode)
                .menuPrice(menuPrice)
                .menuState(menuState)
                .menuImgUrl(menuImgUrl)
                .recommendMenu(recommendMenu)
                .build();
    }
}

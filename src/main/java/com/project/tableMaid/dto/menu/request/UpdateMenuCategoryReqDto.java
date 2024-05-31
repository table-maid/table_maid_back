package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.MenuCategory;
import lombok.Data;

@Data
public class UpdateMenuCategoryReqDto {
    private int menuCategoryId;
    private int adminId;
    private String menuCategoryName;

    public MenuCategory toEntity() {
        return MenuCategory.builder()
                .menuCategoryId(menuCategoryId)
                .adminId(adminId)
                .menuCategoryName(menuCategoryName)
                .build();
    }
}

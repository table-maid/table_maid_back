package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.MenuCategory;
import lombok.Data;

@Data
public class AddMenuCategoryReqDto {
    private int adminId;
    private String menuCategoryName;

    public MenuCategory toEntity() {
        return MenuCategory.builder()
                .adminId(adminId)
                .menuCategoryName(menuCategoryName)
                .build();
    }
}
package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.MenuCategory;
import lombok.Data;

@Data
public class AddMenuCategoryReqDto {
    private String menuCategoryName;

    public MenuCategory toEntity() {
        return MenuCategory.builder()
                .menuCategoryName(menuCategoryName)
                .build();
    }
}
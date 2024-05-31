package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.MenuCategory;
import lombok.Data;

@Data
public class DeleteMenuCategoryReqDto {
    private int adminId;
    private int menuCategoryId;

    public MenuCategory toEntity() {
        return MenuCategory.builder()
                .adminId(adminId)
                .menuCategoryId(menuCategoryId)
                .build();
    }
}

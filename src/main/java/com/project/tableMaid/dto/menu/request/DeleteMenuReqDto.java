package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.Menu;
import com.project.tableMaid.entity.menu.MenuCategory;
import lombok.Data;

@Data
public class DeleteMenuReqDto {
    private int adminId;
    private int menuId;

    public Menu toEntity() {
        return Menu.builder()
                .adminId(adminId)
                .menuId(menuId)
                .build();
    }
}

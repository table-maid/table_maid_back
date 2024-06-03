package com.project.tableMaid.entity.menu;

import com.project.tableMaid.dto.menu.response.CategoriesRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuCategory {
    private int menuCategoryId;
    private int adminId;
    private String menuCategoryName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public CategoriesRespDto toCategoriesRespDto() {
        return CategoriesRespDto.builder()
                .adminId(adminId)
                .menuCategoryId(menuCategoryId)
                .menuCategoryName(menuCategoryName)
                .build();
    }
}

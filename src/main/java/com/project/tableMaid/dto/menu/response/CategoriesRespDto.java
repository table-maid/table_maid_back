package com.project.tableMaid.dto.menu.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesRespDto {
    private int adminId;
    private int menuCategoryId;
    private String menuCategoryName;

}

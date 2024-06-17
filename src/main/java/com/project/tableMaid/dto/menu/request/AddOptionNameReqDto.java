package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.MenuCategory;
import com.project.tableMaid.entity.menu.OptionName;
import lombok.Data;

@Data
public class AddOptionNameReqDto {
    private int adminId;
    private int menuId;
    private int optionTitleId;
    private String optionName;
    private int optionPrice;

    public OptionName toEntity() {
        return OptionName.builder()
                .adminId(adminId)
                .menuId(menuId)
                .optionTitleId(optionTitleId)
                .optionName(optionName)
                .optionPrice(optionPrice)
                .build();
    }
}

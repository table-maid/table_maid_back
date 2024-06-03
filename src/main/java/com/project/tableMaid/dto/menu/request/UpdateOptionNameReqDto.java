package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.OptionName;
import com.project.tableMaid.entity.menu.OptionTitle;
import lombok.Data;

@Data
public class UpdateOptionNameReqDto {
    private int optionNameId;
    private int adminId;
    private int menuId;
    private int titleId;
    private String optionName;

    public OptionName toEntity() {
        return OptionName.builder()
                .optionNameId(optionNameId)
                .adminId(adminId)
                .menuId(menuId)
                .titleId(titleId)
                .optionName(optionName)
                .build();
    }
}

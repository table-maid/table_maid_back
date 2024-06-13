package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.OptionTitle;
import lombok.Data;

@Data
public class UpdateOptionTitleReqDto {
    private int optionTitleId;
    private int adminId;
    private int menuId;
    private String titleName;

    public OptionTitle toEntity() {
        return OptionTitle.builder()
                .optionTitleId(optionTitleId)
                .adminId(adminId)
                .menuId(menuId)
                .titleName(titleName)
                .build();
    }
}
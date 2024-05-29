package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.OptionName;
import com.project.tableMaid.entity.menu.OptionTitle;
import lombok.Data;

@Data
public class AddOptionTitleReqDto {
    private int adminId;
    private int menuId;
    private String titleName;

    public OptionTitle toEntity() {
        return OptionTitle.builder()
                .adminId(adminId)
                .menuId(menuId)
                .titleName(titleName)
                .build();
    }
}

package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.OptionTitle;
import lombok.Data;

@Data
public class DeleteOptionTitleReqDto {
    private int adminId;
    private int optionTitleId;

    public OptionTitle toEntity() {
        return OptionTitle.builder()
                .optionTitleId(optionTitleId)
                .adminId(adminId)
                .build();
    }
}

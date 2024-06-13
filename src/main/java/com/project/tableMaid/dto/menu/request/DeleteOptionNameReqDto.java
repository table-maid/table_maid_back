package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.OptionName;
import lombok.Data;

import java.util.List;

@Data
public class DeleteOptionNameReqDto {
    private int optionNameId;
    private int adminId;

    public OptionName toEntity() {
        return OptionName.builder()
                .optionNameId(optionNameId)
                .adminId(adminId)
                .build();
    }
}

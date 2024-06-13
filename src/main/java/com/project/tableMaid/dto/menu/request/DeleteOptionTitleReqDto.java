package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.OptionTitle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOptionTitleReqDto {
    private int adminId;
    private int optionTitleId;

    public OptionTitle toEntity() {
        return OptionTitle.builder()
                .adminId(adminId)
                .optionTitleId(optionTitleId)
                .build();
    }
}

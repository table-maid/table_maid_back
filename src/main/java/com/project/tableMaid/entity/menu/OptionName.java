package com.project.tableMaid.entity.menu;

import com.project.tableMaid.dto.menu.response.OptionsRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionName {
    private int optionNameId;
    private int adminId;
    private int menuId;
    private int optionTitleId;
    private String optionName;
    private int optionPrice;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private OptionTitle optionTitle;
}

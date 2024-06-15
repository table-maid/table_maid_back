package com.project.tableMaid.entity.menu;

import com.project.tableMaid.dto.menu.response.OptionsRespDto;
import com.project.tableMaid.dto.user.response.OptionRespDto;
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
    private int titleId;
    private String optionName;
    private int optionPrice;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private OptionTitle optionTitle;
    private Menu menu;

    public OptionRespDto toOptionRespDto() {
        return OptionRespDto.builder()
                .adminId(adminId)
                .menuId(menuId)
                .optionNameId(optionNameId)
                .optionName(optionName)
                .optionTitleId(optionTitle.getOptionTitleId())
                .optionTitleName(optionTitle.getTitleName())
                .optionPrice(optionPrice)
                .menuImgUrl(menu.getMenuImgUrl())
                .menuName(menu.getMenuName())
                .menuPrice(menu.getMenuPrice())
                .build();
    }
}

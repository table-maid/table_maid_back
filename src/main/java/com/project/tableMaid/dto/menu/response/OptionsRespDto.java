package com.project.tableMaid.dto.menu.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionsRespDto {
    private int adminId;
    private int menuId;
    private int optionTitleId;
    private String titleName;

    private List<Integer> optionNameIds;
    private List<String> optionNames;
    private List<Integer> optionPrices;
}

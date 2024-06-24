package com.project.tableMaid.dto.SSE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SSEOptionReqDto {
    private int adminId;
    private int menuId;
    private int optionTitleId;
    private String titleName;

    private List<Integer> optionNameIds;
    private List<String> optionNames;
    private List<Integer> optionPrices;
}

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
public class OptionTitlesRespDto {

    private List<Integer> optionTitlesId;
    private List<String> optionTitleNames;

}

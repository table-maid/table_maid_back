package com.project.tableMaid.entity.menu;

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
    private String optionName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

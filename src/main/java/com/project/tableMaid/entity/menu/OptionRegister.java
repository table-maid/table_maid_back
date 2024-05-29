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
public class OptionRegister {
    private int optionRegisterId;
    private int adminId;
    private int menuId;
    private int optionTitleId;
    private int optionNameId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

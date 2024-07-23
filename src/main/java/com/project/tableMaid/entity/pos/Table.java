package com.project.tableMaid.entity.pos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Table {
    private int tablesId;
    private int adminId;
    private int floorNum;
    private int tablesNum;
    private String tablesName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

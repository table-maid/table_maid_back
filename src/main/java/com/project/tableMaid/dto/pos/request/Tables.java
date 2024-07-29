package com.project.tableMaid.dto.pos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tables {
    private int adminId;
    private int floorNum;
    private int tablesNum;
    private String tablesName;
}

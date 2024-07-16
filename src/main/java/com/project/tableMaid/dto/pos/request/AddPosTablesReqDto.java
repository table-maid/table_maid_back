package com.project.tableMaid.dto.pos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPosTablesReqDto {
    private int tableNum;
    private String tableName;
}

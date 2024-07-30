package com.project.tableMaid.dto.pos.request;

import com.project.tableMaid.entity.pos.Table;
import lombok.Data;

@Data
public class UpdateTableReqDto {
    private int adminId;
    private int floorNum;
    private int tablesNum;
    private String tablesName;

    public Table toEntity() {
        return Table.builder()
                .adminId(adminId)
                .floorNum(floorNum)
                .tablesNum(tablesNum)
                .tablesName(tablesName)
                .build();
    }
}

package com.project.tableMaid.dto.pos.request;

import com.project.tableMaid.entity.pos.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddPosTablesReqDto {
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

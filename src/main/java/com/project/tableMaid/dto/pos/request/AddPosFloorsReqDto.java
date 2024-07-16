package com.project.tableMaid.dto.pos.request;

import com.project.tableMaid.entity.pos.Floors;
import com.project.tableMaid.entity.pos.Tables;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPosFloorsReqDto {
    private int adminId;
    private int floorNum;
    private String floorName;
    private List<AddPosTablesReqDto> addPosTablesReqDtos;

    public Floors toFloorEntity() {
        return Floors.builder()
                .adminId(adminId)
                .floorNum(floorNum)
                .floorName(floorName)
                .build();
    }

    public List<Tables> toTableEntity() {
        List<Tables> tables = new ArrayList<>();

        for(AddPosTablesReqDto addPosTablesReqDto : addPosTablesReqDtos) {
            tables.add(
            Tables.builder()
                    .adminId(adminId)
                    .floorNum(floorNum)
                    .tablesNum(addPosTablesReqDto.getTableNum())
                    .tablesName(addPosTablesReqDto.getTableName())
                    .build()
            );
        }
        return tables;
    }
}

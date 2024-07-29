package com.project.tableMaid.dto.pos.request;

import com.project.tableMaid.entity.pos.Floors;
import com.project.tableMaid.entity.pos.Table;
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
    private List<Tables> tables;

    public Floors toFloorEntity() {
        return Floors.builder()
                .adminId(adminId)
                .floorNum(floorNum)
                .floorName(floorName)
                .build();
    }

    public List<Table> toTableEntity() {
        List<Table> tableList = new ArrayList<>();

        for(Tables addPosTablesReqDto : tables) {
            tableList.add(
                    Table.builder()
                            .adminId(adminId)
                            .floorNum(floorNum)
                            .tablesNum(addPosTablesReqDto.getTablesNum())
                            .tablesName(addPosTablesReqDto.getTablesName())
                            .build()
            );
        }
        return tableList;
    }
}

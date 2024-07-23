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
    private List<Tables> Tables;

    public Floors toFloorEntity() {
        return Floors.builder()
                .adminId(adminId)
                .floorNum(floorNum)
                .floorName(floorName)
                .build();
    }

    public List<Table> toTableEntity() {
        List<Table> table = new ArrayList<>();

        for(Tables addPosTablesReqDto : Tables) {
            table.add(
            Table.builder()
                    .adminId(adminId)
                    .floorNum(floorNum)
                    .tablesNum(addPosTablesReqDto.getTableNum())
                    .tablesName(addPosTablesReqDto.getTableName())
                    .build()
            );
        }
        return table;
    }
}

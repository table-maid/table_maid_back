package com.project.tableMaid.entity.pos;

import com.project.tableMaid.dto.pos.response.PosFloorsTablesListRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Floors {
    private int floorId;
    private int adminId;
    private int floorNum;
    private String floorName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private List<Table> tableList;

    public PosFloorsTablesListRespDto toRespDto() {
        return PosFloorsTablesListRespDto.builder()
                .floorId(floorId)
                .adminId(adminId)
                .floorNum(floorNum)
                .floorName(floorName)
                .createDate(createDate)
                .updateDate(updateDate)
                .tables(tableList)
                .build();
    }
}

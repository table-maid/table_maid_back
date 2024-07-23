package com.project.tableMaid.dto.pos.response;

import com.project.tableMaid.entity.pos.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PosFloorsTablesListRespDto {
    private int floorId;
    private int adminId;
    private int floorNum;
    private String floorName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private List<Table> tables;
}

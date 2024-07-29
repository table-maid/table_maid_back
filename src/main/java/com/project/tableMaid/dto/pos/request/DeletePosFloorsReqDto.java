package com.project.tableMaid.dto.pos.request;

import com.project.tableMaid.entity.pos.Table;
import lombok.Data;

import java.util.List;

@Data
public class DeletePosFloorsReqDto {
    private int adminId;
    private int floorNum;
}

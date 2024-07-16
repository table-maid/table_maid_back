package com.project.tableMaid.repository;

import com.project.tableMaid.entity.pos.Floors;
import com.project.tableMaid.entity.pos.Tables;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PosMapper {
    public int savePosFloor(List<Floors> floors);
    public int savePosTable(List<Tables> tables);
}

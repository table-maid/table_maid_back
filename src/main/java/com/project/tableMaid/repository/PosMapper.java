package com.project.tableMaid.repository;

import com.project.tableMaid.entity.pos.Floors;
import com.project.tableMaid.entity.pos.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PosMapper {
    public int savePosFloor(List<Floors> floors);
    public int savePosTable(List<Table> tables);
    public List<Floors> findPosFloorsTables(@Param("adminId") int adminId);
    public int deletePosFloor(@Param("adminId") int adminId, @Param("floorNum") int floorNum);
}
package com.project.tableMaid.repository;

import com.project.tableMaid.entity.pos.Floors;
import com.project.tableMaid.entity.pos.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PosMapper {
    int savePosFloor(List<Floors> floors);
    int savePosTable(List<Table> tables);
    List<Floors> findPosFloorsTables(@Param("adminId") int adminId);
    int deletePosFloor(@Param("adminId") int adminId, @Param("floorNum") int floorNum);
    int updatePosTable(Table table);
}
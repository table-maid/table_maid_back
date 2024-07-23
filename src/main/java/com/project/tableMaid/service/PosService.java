package com.project.tableMaid.service;

import com.project.tableMaid.dto.pos.request.AddPosFloorsReqDto;
import com.project.tableMaid.dto.pos.response.PosFloorsTablesListRespDto;
import com.project.tableMaid.entity.pos.Floors;
import com.project.tableMaid.exception.SaveException;
import com.project.tableMaid.repository.PosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PosService {

    @Autowired
    PosMapper posMapper;

    // 층, 테이블 추가
    @Transactional(rollbackFor = Exception.class)
    public void insertFloorsTables(List<AddPosFloorsReqDto> addPosFloorsReqDtods) {
        int tableSuccessCount = 0;
        int floorSuccessCount = 0;

        List<Floors> floors= new ArrayList<>();
        for (AddPosFloorsReqDto addPosFloorsReqDto : addPosFloorsReqDtods) {
            floors.add(addPosFloorsReqDto.toFloorEntity());
            tableSuccessCount += posMapper.savePosTable(addPosFloorsReqDto.toTableEntity());
        }

        floorSuccessCount += posMapper.savePosFloor(floors);

        if(tableSuccessCount < floors.size()) {
            throw new SaveException(Map.of("table 갯수 저장 오류", "정상적으로 테이블 갯수가 저장되지 않았습니다."));
        }
        if(floorSuccessCount < 1) {
            throw new SaveException(Map.of("floor 갯수 저장 오류", "정상적으로 층 갯수가 저장되지 않았습니다."));
        }

    }

    // 층, 테이블 조회
    public List<PosFloorsTablesListRespDto> getPosFloorsTables(int adminId) {
        List<Floors> floors = posMapper.findPosFloorsTables(adminId);
        System.out.println(floors);
        return floors.stream().map(Floors::toRespDto).collect(Collectors.toList());
    }

}

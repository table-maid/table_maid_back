package com.project.tableMaid.controller;

import com.project.tableMaid.dto.pos.request.AddPosFloorsReqDto;
import com.project.tableMaid.service.PosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pos")
public class PosController {

    @Autowired
    private PosService posService;

    @PostMapping("/floor/talbe")
    public ResponseEntity<?> addPos (@RequestBody List<AddPosFloorsReqDto> addPosFloorsReqDto) {
        posService.insertFloorsTables(addPosFloorsReqDto);
        return ResponseEntity.created(null).body(true);
    }

}

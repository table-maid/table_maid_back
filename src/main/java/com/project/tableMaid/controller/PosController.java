package com.project.tableMaid.controller;

import com.project.tableMaid.aop.annotation.ParamsPrintAspect;
import com.project.tableMaid.dto.pos.request.AddPosFloorsReqDto;
import com.project.tableMaid.security.PrincipalUser;
import com.project.tableMaid.service.PosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pos")
public class PosController {

    @Autowired
    private PosService posService;

    @PostMapping("/floor/table")
    public ResponseEntity<?> addPos (@RequestBody List<AddPosFloorsReqDto> addPosFloorsReqDto) {

        posService.insertFloorsTables(addPosFloorsReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @GetMapping("/floor/table")
    public ResponseEntity<?> getPos(@RequestParam(value = "adminId") int adminId) {
        return ResponseEntity.ok(posService.getPosFloorsTables(adminId));
    }

}

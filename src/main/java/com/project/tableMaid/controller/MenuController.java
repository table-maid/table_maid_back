package com.project.tableMaid.controller;

import com.project.tableMaid.dto.menu.request.RegisterMenuReqDto;
import com.project.tableMaid.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/register")
    public ResponseEntity<?> addMenu(@RequestBody RegisterMenuReqDto registerMenuReqDto) {
        menuService.insertMenu(registerMenuReqDto);
        return ResponseEntity.created(null).body(true);
    }
}

package com.project.tableMaid.controller;

import com.project.tableMaid.dto.menu.request.*;
import com.project.tableMaid.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/menus")
    public ResponseEntity<?> addMenu(@RequestBody RegisterMenuReqDto registerMenuReqDto) {
        menuService.insertMenu(registerMenuReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @PostMapping("/category")
    public ResponseEntity<?> addMenuCategory(@RequestBody AddMenuCategoryReqDto addMenuCategoryReqDto) {
        menuService.insertMenuCategory(addMenuCategoryReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @PostMapping("/title")
    public ResponseEntity<?> addOptionTitle(@RequestBody AddOptionTitleReqDto addOptionTitleReqDto) {
        menuService.insertOptionTitle(addOptionTitleReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @PostMapping("/name")
    public ResponseEntity<?> addOptionName(@RequestBody AddOptionNameReqDto addOptionNameReqDto) {
        menuService.insertOptionName(addOptionNameReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @PutMapping("/category")
    public ResponseEntity<?> updateMenuCategory(@RequestBody UpdateMenuCategoryReqDto updateMenuCategoryReqDto) {
        menuService.editMenuCategory(updateMenuCategoryReqDto);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/category")
    public ResponseEntity<?> deleteMenuCategory(@RequestBody DeleteMenuCategoryReqDto deleteMenuCategoryReqDto) {
        menuService.deleteMenuCategory(deleteMenuCategoryReqDto);
        return ResponseEntity.ok(true);
    }
}

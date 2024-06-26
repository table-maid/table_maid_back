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

    @GetMapping("/list")
    public ResponseEntity<?> getMenuListByAdminId(SearchMenuListReqDto searchMenuListReqDto) {
        return ResponseEntity.ok(menuService.getMenuList(searchMenuListReqDto));
    }
    @GetMapping("/detail")
    public ResponseEntity<?> getMenuDetailByAdminId(@RequestParam int adminId, int menuId) {
        return ResponseEntity.ok(menuService.getMenuDetail(adminId, menuId));
    }
    @GetMapping("/categories")
    public ResponseEntity<?> getCategoriesByAdminId(@RequestParam int adminId, int categoryPageNum) {

        return ResponseEntity.ok(menuService.getCategories(adminId, categoryPageNum));
    }
    @GetMapping("/menus")
    public ResponseEntity<?> getMenusByAdminId(@RequestParam int adminId, int menuCategoryId, int menuPageNum) {
        return ResponseEntity.ok(menuService.getMenusByCategoryId(adminId, menuCategoryId, menuPageNum));
    }
    @GetMapping("/option")
    public ResponseEntity<?> getOptionsByAdminId(@RequestParam int adminId, int menuId) {
        return ResponseEntity.ok(menuService.getOptionsByMenuId(adminId, menuId));
    }

    @PostMapping("/menus")
    public ResponseEntity<?> addMenu(@RequestBody RegisterMenuReqDto registerMenuReqDto) {
        menuService.insertMenu(registerMenuReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @PutMapping("/menus")
    public ResponseEntity<?> updateMenu(@RequestBody UpdateMenuReqDto updateMenuReqDto) {
        menuService.editMenu(updateMenuReqDto);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/menus")
    public ResponseEntity<?> deleteMenu(@RequestBody DeleteMenuReqDto deleteMenuReqDto) {
        menuService.deleteMenu(deleteMenuReqDto);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/category")
    public ResponseEntity<?> addMenuCategory(@RequestBody AddMenuCategoryReqDto addMenuCategoryReqDto) {
        menuService.insertMenuCategory(addMenuCategoryReqDto);
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

    @PostMapping("/option/title")
    public ResponseEntity<?> addOptionTitle(@RequestBody AddOptionTitleReqDto addOptionTitleReqDto) {
        menuService.insertOptionTitle(addOptionTitleReqDto);
        return ResponseEntity.created(null).body(true);
    }
    @GetMapping("/option/title")
    public ResponseEntity<?> getOptionTitles(@RequestParam int adminId, int menuId) {
        menuService.getOptionTitles(adminId, menuId);
        return ResponseEntity.ok(menuService.getOptionTitles(adminId, menuId));
    }

    @PutMapping("/option/title")
    public ResponseEntity<?> updateOptionTitle(@RequestBody UpdateOptionTitleReqDto updateOptionTitleReqDto) {
        menuService.editOptionTitle(updateOptionTitleReqDto);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/option/title")
    public ResponseEntity<?> deleteOptionTitle(@RequestBody DeleteOptionTitleReqDto deleteOptionTitleReqDto) {
        menuService.deleteOptionTitle(deleteOptionTitleReqDto);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/option/name")
    public ResponseEntity<?> addOptionName(@RequestBody AddOptionNameReqDto addOptionNameReqDto) {
        menuService.insertOptionName(addOptionNameReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @PutMapping("/option")
    public ResponseEntity<?> updateOptionName(@RequestBody UpdateOptionNameReqDto updateOptionNameReqDto) {
        menuService.editOptionName(updateOptionNameReqDto);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/option/name")
    public ResponseEntity<?> deleteOptionName(@RequestBody DeleteOptionNameReqDto deleteOptionNameReqDto) {
        menuService.deleteOptionName(deleteOptionNameReqDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/img")
    public ResponseEntity<?> menuImgUrlUpload(@RequestBody MenuImgUploadReqDto menuImgUploadReqDto) {
        menuService.menuImgUrlUpload(menuImgUploadReqDto);
        return ResponseEntity.ok(true);
    }

}

package com.project.tableMaid.controller;

import com.project.tableMaid.dto.user.request.DeleteSingleMenuReqDto;
import com.project.tableMaid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/company/name")
    public ResponseEntity<?> getCompanyName(@RequestParam(value = "adminId") int adminId) {
        return ResponseEntity.ok(userService.searchCompanyNameByAdminId(adminId));
    }

    @GetMapping("/category")
    public ResponseEntity<?> getCategories(@RequestParam(value = "adminId") int adminId) {
        return ResponseEntity.ok(userService.searchCategoriesByAdminId(adminId));
    }

    @GetMapping("/menu")
    public ResponseEntity<?> getMenus(@RequestParam(value = "adminId") int adminId, @RequestParam(value = "menuCategoryId") int menuCategoryId) {
        return ResponseEntity.ok(userService.searchMenusByAdminIdAndCategoryId(adminId, menuCategoryId));
    }

    @DeleteMapping("/menu")
    public ResponseEntity<?> deleteMenus(@RequestBody DeleteSingleMenuReqDto deleteSingleMenuReqDto) {
        userService.deleteSingleMenu(deleteSingleMenuReqDto);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/option")
    public ResponseEntity<?> getOptions(@RequestParam int adminId, int menuId) {
        return ResponseEntity.ok(userService.searchOptionAndMenuByMenuId(adminId, menuId));
    }

    @GetMapping("/menu/solo")
    public ResponseEntity<?> getSoloMenu(@RequestParam int menuId, int adminId, int menuCategoryId) {
        return ResponseEntity.ok(userService.searchMenuById(menuId, adminId, menuCategoryId));
    }
}

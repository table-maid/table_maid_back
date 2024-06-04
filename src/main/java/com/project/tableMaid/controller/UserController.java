package com.project.tableMaid.controller;

import com.project.tableMaid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

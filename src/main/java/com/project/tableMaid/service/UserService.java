package com.project.tableMaid.service;

import com.project.tableMaid.dto.menu.response.CategoriesRespDto;
import com.project.tableMaid.dto.menu.response.MenusRespDto;
import com.project.tableMaid.entity.account.Admin;
import com.project.tableMaid.entity.menu.Menu;
import com.project.tableMaid.entity.menu.MenuCategory;
import com.project.tableMaid.repository.AdminMapper;
import com.project.tableMaid.repository.MenuMapper;
import com.project.tableMaid.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    public Admin searchCompanyNameByAdminId(int adminId) {
        return userMapper.findCompanyNameByAdminId(adminId);
    }

    public List<CategoriesRespDto> searchCategoriesByAdminId(int adminId) {
        List<MenuCategory> menuCategories = menuMapper.getMenuCategoryByAdminId(adminId);
        return menuCategories.stream().map(MenuCategory::toCategoriesRespDto).collect(Collectors.toList());
    }

    public List<MenusRespDto> searchMenusByAdminIdAndCategoryId(int adminId, int menuCategoryId) {
        List<Menu> menus = menuMapper.getMenuByAdminIdAndCategoryId(adminId, menuCategoryId);
        return menus.stream().map(Menu::toMenuRespDto).collect(Collectors.toList());
    }
}

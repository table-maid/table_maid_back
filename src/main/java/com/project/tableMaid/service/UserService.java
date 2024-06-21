package com.project.tableMaid.service;

import com.project.tableMaid.dto.menu.response.CategoriesRespDto;
import com.project.tableMaid.dto.menu.response.MenusRespDto;
import com.project.tableMaid.dto.user.request.DeleteSingleMenuReqDto;
import com.project.tableMaid.dto.user.response.OptionRespDto;
import com.project.tableMaid.dto.user.response.SearchMenuRespDto;
import com.project.tableMaid.entity.account.Admin;
import com.project.tableMaid.entity.menu.Menu;
import com.project.tableMaid.entity.menu.MenuCategory;
import com.project.tableMaid.entity.menu.OptionName;
import com.project.tableMaid.repository.MenuMapper;
import com.project.tableMaid.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        int categoryLimitNum = 0;
        List<MenuCategory> menuCategories = menuMapper.getMenuCategoryByAdminId(adminId, categoryLimitNum, categoryLimitNum);
        return menuCategories.stream().map(MenuCategory::toCategoriesRespDto).collect(Collectors.toList());
    }

    public List<MenusRespDto> searchMenusByAdminIdAndCategoryId(int adminId, int menuCategoryId) {
        int limitMenuNum = 0;
        List<Menu> menus = menuMapper.getMenuByAdminIdAndCategoryId(adminId, menuCategoryId, limitMenuNum);
        return menus.stream().map(Menu::toMenuRespDto).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteSingleMenu(DeleteSingleMenuReqDto deleteSingleMenuReqDto) {
        userMapper.deleteSingleMenu(deleteSingleMenuReqDto.getAdminId(), deleteSingleMenuReqDto.getMenuId());
    }

    public List<OptionRespDto> searchOptionAndMenuByMenuId(int adminId, int menuId) {
        List<OptionName> options = userMapper.getOptionsAndMenuByMenuId(adminId, menuId);
        return options.stream().map(OptionName::toOptionRespDto).collect(Collectors.toList());
    }

    public SearchMenuRespDto searchMenuById(int menuId, int adminId, int menuCategoryId) {
        Menu menu = userMapper.getMenuByAdminIdAndCategoryId(menuId, adminId, menuCategoryId);
        return menu.toSearchMenuRespDto();
    }
}
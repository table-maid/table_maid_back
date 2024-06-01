package com.project.tableMaid.service;

import com.project.tableMaid.dto.menu.request.*;
import com.project.tableMaid.repository.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public void insertMenu(RegisterMenuReqDto registerMenuReqDto) {
        menuMapper.saveMenu(registerMenuReqDto.toEntity());
    }

    public void insertMenuCategory(AddMenuCategoryReqDto addMenuCategoryReqDto) {
        menuMapper.saveMenuCategory(addMenuCategoryReqDto.toEntity());
    }

    public void insertOptionTitle(AddOptionTitleReqDto addOptionTitleReqDto) {
        menuMapper.saveOptionTitle(addOptionTitleReqDto.toEntity());
    }

    public void insertOptionName(AddOptionNameReqDto addOptionNameReqDto) {
        menuMapper.saveOptionName(addOptionNameReqDto.toEntity());
    }

    public void editMenuCategory(UpdateMenuCategoryReqDto updateMenuCategoryReqDto) {
        menuMapper.updateMenuCategory(updateMenuCategoryReqDto.toEntity());
    }

    public void deleteMenuCategory(DeleteMenuCategoryReqDto deleteMenuCategoryReqDto) {
        menuMapper.deleteMenuCategory(deleteMenuCategoryReqDto.toEntity());
    }

    public void editMenu(UpdateMenuReqDto updateMenuReqDto) {
        menuMapper.updateMenu(updateMenuReqDto.toEntity());
    }

    public void deleteMenu(DeleteMenuReqDto deleteMenuReqDto) {
        menuMapper.deleteMenu(deleteMenuReqDto.toEntity());
    }
}

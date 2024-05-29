package com.project.tableMaid.service;

import com.project.tableMaid.dto.menu.request.AddMenuCategoryReqDto;
import com.project.tableMaid.dto.menu.request.AddOptionNameReqDto;
import com.project.tableMaid.dto.menu.request.AddOptionTitleReqDto;
import com.project.tableMaid.dto.menu.request.RegisterMenuReqDto;
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
}

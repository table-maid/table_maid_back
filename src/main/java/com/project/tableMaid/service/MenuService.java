package com.project.tableMaid.service;

import com.project.tableMaid.aop.annotation.ParamsPrintAspect;
import com.project.tableMaid.dto.menu.request.*;
import com.project.tableMaid.dto.menu.response.CategoriesRespDto;
import com.project.tableMaid.dto.menu.response.MenusRespDto;
import com.project.tableMaid.dto.menu.response.OptionTitlesRespDto;
import com.project.tableMaid.dto.menu.response.OptionsRespDto;
import com.project.tableMaid.entity.menu.Menu;
import com.project.tableMaid.entity.menu.MenuCategory;
import com.project.tableMaid.entity.menu.OptionName;
import com.project.tableMaid.entity.menu.OptionTitle;
import com.project.tableMaid.repository.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;
    //카테고리 조회
    @Transactional(rollbackFor = Exception.class)
    public List<CategoriesRespDto> getCategories(int adminId) {
        List<MenuCategory> menuCategories = menuMapper.getMenuCategoryByAdminId(adminId);
        List<CategoriesRespDto> categoriesRespDtoList = new ArrayList<>();
        for (MenuCategory menuCategory : menuCategories) {
            categoriesRespDtoList.add(menuCategory.toCategoriesRespDto());
        }
        return categoriesRespDtoList;
    }
    // 메뉴 조회
    @Transactional(rollbackFor = Exception.class)
    public List<MenusRespDto> getMenusByCategoryId(int adminId, int menuCategoryId) {
        List<Menu> menus = menuMapper.getMenuByAdminIdAndCategoryId(adminId, menuCategoryId);
        List<MenusRespDto> menuRespDtoList = new ArrayList<>();
        for (Menu menu : menus) {
            menuRespDtoList.add(menu.toMenuRespDto());
        }
        System.out.println(menuRespDtoList);
        return menuRespDtoList;
    }
    // 메뉴 별 옵션 조회
    @Transactional(rollbackFor = Exception.class)
    public List<OptionsRespDto> getOptionsByMenuId(int adminId, int menuId) {
        List<OptionName> options = menuMapper.getOptionsByMenuId(adminId, menuId);
        Map<Integer, OptionsRespDto> optionsMap = new HashMap<>();

        for (OptionName optionName : options) {
            int optionTitleId = optionName.getOptionTitle().getOptionTitleId();
            OptionsRespDto optionsRespDto = optionsMap.get(optionTitleId);
            if (optionsRespDto == null) {
                optionsRespDto = OptionsRespDto.builder()
                        .adminId(optionName.getAdminId())
                        .menuId(optionName.getMenuId())
                        .optionTitleId(optionTitleId)
                        .titleName(optionName.getOptionTitle().getTitleName())
                        .optionNameIds(new ArrayList<>())
                        .optionNames(new ArrayList<>())
                        .optionPrices(new ArrayList<>())
                        .build();
                optionsMap.put(optionTitleId, optionsRespDto);
            }
            optionsRespDto.getOptionNameIds().add(optionName.getOptionNameId());
            optionsRespDto.getOptionNames().add(optionName.getOptionName());
            optionsRespDto.getOptionPrices().add(optionName.getOptionPrice());
        }
        System.out.println(options);
        return new ArrayList<>(optionsMap.values());
    }
    // 옵션 타이틀 조회
    @Transactional(rollbackFor = Exception.class)
    public OptionTitlesRespDto getOptionTitle(int adminId, int menuId) {
        List<OptionTitle> optionTitles = menuMapper.getOptionTitleByMenuId(adminId, menuId);
        List<Integer> optionTitlesId = optionTitles.stream()
                .map(OptionTitle::getOptionTitleId)
                .collect(Collectors.toList());

        List<String> optionTitleNames = optionTitles.stream()
                .map(OptionTitle::getTitleName)
                .collect(Collectors.toList());

        return OptionTitlesRespDto.builder()
                .optionTitlesId(optionTitlesId)
                .optionTitleNames(optionTitleNames)
                .build();
    }

    public void insertMenu(RegisterMenuReqDto registerMenuReqDto) {
        menuMapper.saveMenu(registerMenuReqDto.toEntity());
    }

    public void editMenu(UpdateMenuReqDto updateMenuReqDto) {
        menuMapper.updateMenu(updateMenuReqDto.toEntity());
    }

    public void deleteMenu(DeleteMenuReqDto deleteMenuReqDto) {
        menuMapper.deleteMenu(deleteMenuReqDto.toEntity());
    }

    public void insertMenuCategory(AddMenuCategoryReqDto addMenuCategoryReqDto) {
        menuMapper.saveMenuCategory(addMenuCategoryReqDto.toEntity());
    }

    public void editMenuCategory(UpdateMenuCategoryReqDto updateMenuCategoryReqDto) {
        menuMapper.updateMenuCategory(updateMenuCategoryReqDto.toEntity());
    }

    public void deleteMenuCategory(DeleteMenuCategoryReqDto deleteMenuCategoryReqDto) {
        menuMapper.deleteMenuCategory(deleteMenuCategoryReqDto.toEntity());
    }

    public void insertOptionTitle(AddOptionTitleReqDto addOptionTitleReqDto) {
        menuMapper.saveOptionTitle(addOptionTitleReqDto.toEntity());
    }

    public void editOptionTitle(UpdateOptionTitleReqDto updateOptionTitleReqDto) {
        menuMapper.updateOptionTitle(updateOptionTitleReqDto.toEntity());
    }

    public void deleteOptionTitle(DeleteOptionTitleReqDto deleteOptionTitleReqDto) {
        menuMapper.deleteOptionTitle(deleteOptionTitleReqDto.toEntity());
    }

    public void insertOptionName(AddOptionNameReqDto addOptionNameReqDto) {
        menuMapper.saveOptionName(addOptionNameReqDto.toEntity());
    }

    public void editOptionName(UpdateOptionNameReqDto updateOptionNameReqDto) {
        menuMapper.updateOptionName(updateOptionNameReqDto.toEntity());
    }

    public void deleteOptionName(DeleteOptionNameReqDto deleteOptionNameReqDto) {
        menuMapper.deleteOptionName(deleteOptionNameReqDto.toEntity());
    }

}

package com.project.tableMaid.service;

import com.project.tableMaid.aop.annotation.ParamsPrintAspect;
import com.project.tableMaid.dto.menu.request.*;
import com.project.tableMaid.dto.menu.response.*;
import com.project.tableMaid.entity.menu.Menu;
import com.project.tableMaid.entity.menu.MenuCategory;
import com.project.tableMaid.entity.menu.OptionName;
import com.project.tableMaid.repository.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    // 메뉴 리스트 조회
    @Transactional(rollbackFor = Exception.class)
    public List<MenuListRespDto> getMenuList(SearchMenuListReqDto searchMenuListReqDto) {
        List<Menu> menuList = menuMapper.getMenuList(
                searchMenuListReqDto.getAdminId(),
                searchMenuListReqDto.getMenuName(),
                searchMenuListReqDto.getMenuCode(),
                searchMenuListReqDto.getMenuState(),
                searchMenuListReqDto.getRecommendMenu(),
                searchMenuListReqDto.getCategoryId()
        );
        List<MenuListRespDto> menuListRespDto = new ArrayList<>();
        for (Menu menu : menuList) {
            menuListRespDto.add(menu.toMenuListRespDto());
        }
        return menuListRespDto;
    }
    //메뉴 단건 상세 조회
    @Transactional(rollbackFor = Exception.class)
    public List<MenuDetailRespDto> getMenuDetail(int adminId, int menuId) {
        List<Menu> menuDetail = menuMapper.getMenuDetail(adminId, menuId);
        System.out.println(menuDetail);
        List<MenuDetailRespDto> menuDetailRespDtoList = new ArrayList<>();

        Set<String> uniqueMenuCodes = new HashSet<>();

        for (Menu menu : menuDetail) {
            if (!uniqueMenuCodes.contains(menu.getMenuCode())) {
                uniqueMenuCodes.add(menu.getMenuCode());

                Map<String, Map<String, Integer>> titleOptionMap = new HashMap<>();

                menuDetail.stream()
                        .filter(m -> m.getMenuCode().equals(menu.getMenuCode()))
                        .forEach(m -> {
                            String titleName = m.getOptionTitle().getTitleName();
                            String optionName = m.getOptionName().getOptionName();
                            Integer optionPrice = m.getOptionName().getOptionPrice();
                            titleOptionMap.computeIfAbsent(titleName, k -> new HashMap<>()).put(optionName, optionPrice);
                        });

                MenuDetailRespDto dto = MenuDetailRespDto.builder()
                        .adminId(menu.getAdminId())
                        .menuCode(menu.getMenuCode())
                        .menuName(menu.getMenuName())
                        .menuPrice(menu.getMenuPrice())
                        .menuImgUrl(menu.getMenuImgUrl())
                        .recommendMenu(menu.getRecommendMenu())
                        .menuState(menu.getMenuState())
                        .menuCategoryId(menu.getMenuCategory().getMenuCategoryId())
                        .menuCategoryName(menu.getMenuCategory().getMenuCategoryName())
                        .titleOptionMap(titleOptionMap)
                        .build();

                menuDetailRespDtoList.add(dto);
            }
        }

        return menuDetailRespDtoList;
    }
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
        return new ArrayList<>(optionsMap.values());
    }

    public void insertMenu(RegisterMenuReqDto registerMenuReqDto) {
        menuMapper.saveMenu(registerMenuReqDto.toEntity());
    }

    public void editMenu(UpdateMenuReqDto updateMenuReqDto) {
        menuMapper.updateMenu(updateMenuReqDto.toEntity());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteMenu(DeleteMenuReqDto deleteMenuReqDto) {
        menuMapper.deleteMenu(deleteMenuReqDto.getAdminId(), deleteMenuReqDto.getMenuIds());
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

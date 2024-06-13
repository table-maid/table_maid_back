package com.project.tableMaid.service;

import com.project.tableMaid.dto.menu.request.*;
import com.project.tableMaid.dto.menu.response.*;
import com.project.tableMaid.entity.menu.Menu;
import com.project.tableMaid.entity.menu.MenuCategory;
import com.project.tableMaid.entity.menu.OptionName;
import com.project.tableMaid.entity.menu.OptionTitle;
import com.project.tableMaid.repository.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

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
                searchMenuListReqDto.getMenuCategoryId()
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
        List<MenuDetailRespDto> menuDetailRespDtoList = new ArrayList<>();
        Set<String> uniqueMenuCodes = new HashSet<>();

        for (Menu menu : menuDetail) {
            if (!uniqueMenuCodes.contains(menu.getMenuCode())) {
                uniqueMenuCodes.add(menu.getMenuCode());

                Map<Integer, MenuDetailRespDto.OptionDetail> optionMap = new HashMap<>();

                menuDetail.stream()
                        .filter(m -> m.getMenuCode().equals(menu.getMenuCode()))
                        .forEach(m -> {
                            if (m.getOptionTitle() != null) {
                                int optionTitleId = m.getOptionTitle().getOptionTitleId();
                                MenuDetailRespDto.OptionDetail optionDetail = optionMap.getOrDefault(optionTitleId, MenuDetailRespDto.OptionDetail.builder()
                                        .optionTitleId(optionTitleId)
                                        .optionTitleName(m.getOptionTitle().getTitleName())
                                        .optionNameIds(new ArrayList<>())
                                        .optionNames(new ArrayList<>())
                                        .optionPrices(new ArrayList<>())
                                        .build());

                                if (m.getOptionName() != null) {
                                    optionDetail.getOptionNameIds().add(m.getOptionName().getOptionNameId());
                                    optionDetail.getOptionNames().add(m.getOptionName().getOptionName());
                                    optionDetail.getOptionPrices().add(m.getOptionName().getOptionPrice());
                                }
                                optionMap.put(optionTitleId, optionDetail);
                            }
                        });

                MenuDetailRespDto dto = MenuDetailRespDto.builder()
                        .adminId(menu.getAdminId())
                        .menuId(menuId)
                        .menuCode(menu.getMenuCode())
                        .menuName(menu.getMenuName())
                        .menuPrice(menu.getMenuPrice())
                        .menuImgUrl(menu.getMenuImgUrl())
                        .recommendMenu(menu.getRecommendMenu())
                        .menuState(menu.getMenuState())
                        .menuCategoryId(menu.getMenuCategory().getMenuCategoryId())
                        .menuCategoryName(menu.getMenuCategory().getMenuCategoryName())
                        .optionList(new ArrayList<>(optionMap.values()))
                        .build();

                menuDetailRespDtoList.add(dto);
            }
        }
        System.out.println(menuDetailRespDtoList);
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

    // 메뉴 옵션 타이틀 조회
    @Transactional(rollbackFor = Exception.class)
    public OptionTitlesRespDto getOptionTitles(int adminId, int menuId) {
        List<OptionTitle> optionTitles = menuMapper.getOptionTitleByMenuId(adminId, menuId);
        OptionTitlesRespDto optionTitlesRespDto = new OptionTitlesRespDto();

        List<Integer> optionTitleIds = new ArrayList<>();
        List<String> optionTitleNames = new ArrayList<>();

        for (OptionTitle optionTitle : optionTitles) {
            optionTitleIds.add(optionTitle.getOptionTitleId());
            optionTitleNames.add(optionTitle.getTitleName());
        }

        optionTitlesRespDto.setOptionTitlesId(optionTitleIds);
        optionTitlesRespDto.setOptionTitleNames(optionTitleNames);

        return optionTitlesRespDto;
    }


    public void insertMenu(RegisterMenuReqDto registerMenuReqDto) {
        menuMapper.saveMenu(registerMenuReqDto.toEntity());
    }

    public void menuImgUrlUpload(MenuImgUploadReqDto menuImgUploadReqDto) {
        menuMapper.menuImgUrlUpload(menuImgUploadReqDto.toEntity());
    }

    // 옵션 업데이트
    @Transactional
    public void editMenu(UpdateMenuReqDto updateMenuReqDto) {
        Menu menu = updateMenuReqDto.toEntity();
        menuMapper.updateMenu(menu);
    }

    // 메뉴 삭제
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

    public void insertOptionName(AddOptionNameReqDto addOptionNameReqDto) {
        menuMapper.saveOptionName(addOptionNameReqDto.toEntity());
    }

    public void editOptionName(UpdateOptionNameReqDto updateOptionNameReqDto) {
        menuMapper.updateOptionName(updateOptionNameReqDto.toEntity());
    }

    // 옵션 타이틀 삭제
    @Transactional(rollbackFor = Exception.class)
    public void deleteOptionTitle(DeleteOptionTitleReqDto deleteOptionTitleReqDto) {
        menuMapper.deleteOptionTitle(deleteOptionTitleReqDto.toEntity());
    }

    // 옵션 삭제
    @Transactional(rollbackFor = Exception.class)
    public void deleteOptionName(DeleteOptionNameReqDto deleteOptionNameReqDto) {
        menuMapper.deleteOptionName(deleteOptionNameReqDto.toEntity());
    }
}

package com.project.tableMaid.repository;

import com.project.tableMaid.entity.menu.Menu;
import com.project.tableMaid.entity.menu.MenuCategory;
import com.project.tableMaid.entity.menu.OptionName;
import com.project.tableMaid.entity.menu.OptionTitle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<MenuCategory> getMenuCategoryByAdminId(@Param("adminId") int adminId);
    List<Menu> getMenuByAdminIdAndCategoryId(@Param("adminId") int adminId, @Param("menuCategoryId") int menuCategoryId);
    List<OptionName> getOptionsByMenuId(@Param("adminId") int adminId, @Param("menuId") int menuId);
    int saveMenu(Menu menu);
    int saveMenuCategory(MenuCategory menuCategory);
    int saveOptionTitle(OptionTitle optionTitle);
    int saveOptionName(OptionName optionName);
    int updateMenuCategory(MenuCategory menuCategory);
    int deleteMenuCategory(MenuCategory menuCategory);
    int updateMenu(Menu menu);
    int deleteMenu(Menu menu);
    int updateOptionTitle(OptionTitle optionTitle);
    int updateOptionName(OptionName optionName);
    int deleteOptionTitle(OptionTitle optionTitle);
    int deleteOptionName(OptionName optionName);
}

package com.project.tableMaid.repository;

import com.project.tableMaid.entity.menu.Menu;
import com.project.tableMaid.entity.menu.MenuCategory;
import com.project.tableMaid.entity.menu.OptionName;
import com.project.tableMaid.entity.menu.OptionTitle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MenuMapper {
    int saveMenu(Menu menu);
    int saveMenuCategory(MenuCategory menuCategory);
    int saveOptionTitle(OptionTitle optionTitle);
    int saveOptionName(OptionName optionName);
    int saveOptionRegister(@Param("optionTitleId") int optionTitleId, @Param("optionNameId") int optionNameId);
    int updateMenuCategory(MenuCategory menuCategory);
    int deleteMenuCategory(MenuCategory menuCategory);
    int updateMenu(Menu menu);
    int deleteMenu(Menu menu);
    int updateOptionTitle(OptionTitle optionTitle);
    int updateOptionName(OptionName optionName);
    int deleteOptionTitle(OptionTitle optionTitle);
    int deleteOptionName(OptionName optionName);
}

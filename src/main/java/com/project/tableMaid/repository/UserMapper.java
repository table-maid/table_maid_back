package com.project.tableMaid.repository;

import com.project.tableMaid.entity.account.Admin;
import com.project.tableMaid.entity.menu.Menu;
import com.project.tableMaid.entity.menu.MenuCategory;
import com.project.tableMaid.entity.menu.OptionName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    Admin findCompanyNameByAdminId(int adminId);
    int deleteSingleMenu(@Param("adminId") int adminId, int menuId);
    List<OptionName> findOptionsAndMenuByMenuId(@Param("adminId") int adminId, @Param("menuId") int menuId);
    Menu findMenuByAdminIdAndCategoryId(
            @Param("menuId") int menuId,
            @Param("adminId") int adminId,
            @Param("menuCategoryId") int menuCategoryId
    );
    Admin findAdminByCompanyNumber(int companyNumber);
}

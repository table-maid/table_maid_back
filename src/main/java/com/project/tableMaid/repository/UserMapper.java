package com.project.tableMaid.repository;

import com.project.tableMaid.entity.account.Admin;
import com.project.tableMaid.entity.menu.MenuCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    Admin findCompanyNameByAdminId(int adminId);
}

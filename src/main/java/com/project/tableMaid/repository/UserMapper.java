package com.project.tableMaid.repository;

import com.project.tableMaid.entity.account.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    Admin findCompanyNameByAdminId(@Param("adminId") int adminId);
}

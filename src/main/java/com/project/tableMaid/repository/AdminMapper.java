package com.project.tableMaid.repository;

import com.project.tableMaid.entity.account.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {
    public int saveAdmin(Admin admin);
    public Admin findAdminByUsername(String username);
    public int modifyPassword(Admin admin);
    public Admin findAdminByAdminId(int adminId);

    public String findAccountByNameAndEmail(@Param("adminName") String adminName, @Param("email") String email);
}

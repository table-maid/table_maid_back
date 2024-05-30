package com.project.tableMaid.repository;

import com.project.tableMaid.entity.account.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    public int saveAdmin(Admin admin);
    public Admin findAdminByUsername(String username);
    public int modifyPassword(Admin admin);
}

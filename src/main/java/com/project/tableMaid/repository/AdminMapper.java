package com.project.tableMaid.repository;

import com.project.tableMaid.entity.account.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {
    public int saveAdmin(Admin admin);

    public Admin findAdminByUsername(String username);

    public Admin findCompanyNumber(int companyNumber);

    public Admin findCompanyAddress(String companyAddress);

    public int modifyPassword(Admin admin);

    public Admin findAdminByAdminId(int adminId);

    public Admin findAccountByNameAndEmail(@Param("adminName") String adminName, @Param("email") String email);

    public int updateAccountTemporaryPw(@Param("adminId") int adminId, @Param("tempPassword") String tempPassword);
}

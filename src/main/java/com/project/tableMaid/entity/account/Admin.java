package com.project.tableMaid.entity.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    private int adminId;
    private String adminName;
    private String username;
    private String password;
    private String email;
    private String companyNumber;
    private String companyName;
    private String ownerName;
    private String companyAddress;
    private LocalDate createDate;
    private LocalDate updateDate;
}

package com.project.tableMaid.dto.user.request;

import lombok.Data;

import java.util.List;

@Data
public class DeleteSingleMenuReqDto {
    private int adminId;
    private int menuId;
}

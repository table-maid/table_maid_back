package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.Menu;
import com.project.tableMaid.entity.menu.MenuCategory;
import lombok.Data;

import java.util.List;

@Data
public class DeleteMenuReqDto {
    private int adminId;
    private List<Integer> menuIds;


}

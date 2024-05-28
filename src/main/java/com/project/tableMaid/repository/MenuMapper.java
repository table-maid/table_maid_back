package com.project.tableMaid.repository;

import com.project.tableMaid.entity.menu.Menu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper {
    int saveMenu(Menu menu);
}

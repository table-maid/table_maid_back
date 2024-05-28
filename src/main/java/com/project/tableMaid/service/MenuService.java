package com.project.tableMaid.service;

import com.project.tableMaid.dto.menu.request.RegisterMenuReqDto;
import com.project.tableMaid.repository.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public void insertMenu(RegisterMenuReqDto registerMenuReqDto) {
        menuMapper.saveMenu(registerMenuReqDto.toEntity());
    }
}

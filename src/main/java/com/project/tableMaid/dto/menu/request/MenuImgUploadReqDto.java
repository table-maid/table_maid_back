package com.project.tableMaid.dto.menu.request;

import com.project.tableMaid.entity.menu.Menu;
import lombok.Data;

@Data
public class MenuImgUploadReqDto {
    private int adminId;
    private int menuId;
    private String menuImgUrl;

    public Menu toEntity() {
        return Menu.builder()
                .adminId(adminId)
                .menuId(menuId)
                .menuImgUrl(menuImgUrl)
                .build();
    }
}

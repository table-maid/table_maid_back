package com.project.tableMaid.entity.menu;

import com.project.tableMaid.dto.menu.response.MenuListRespDto;
import com.project.tableMaid.dto.menu.response.MenusRespDto;
import com.project.tableMaid.dto.user.response.SearchMenuRespDto;
import com.project.tableMaid.entity.account.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private int menuId;
    private int adminId;
    private int menuCategoryId;
    private String menuCode;
    private String menuName;
    private int menuPrice;
    private int recommendMenu;
    private int menuState;
    private String menuImgUrl;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private List<OptionName> options;  // 옵션 리스트 추가

    private MenuCategory menuCategory;
    private Admin admin;
    private OptionTitle optionTitle;
    private OptionName optionName;

    public MenusRespDto toMenuRespDto() {
        return MenusRespDto.builder()
                .adminId(adminId)
                .menuId(menuId)
                .menuCategoryId(menuCategoryId)
                .menuName(menuName)
                .menuCode(menuCode)
                .menuPrice(menuPrice)
                .menuState(menuState)
                .menuImgUrl(menuImgUrl)
                .recommendMenu(recommendMenu)
                .build();
    }

    public MenuListRespDto toMenuListRespDto() {
        return MenuListRespDto.builder()
                .adminId(admin.getAdminId())
                .companyName(admin.getCompanyName())
                .menuId(menuId)
                .menuCode(menuCode)
                .menuCategoryName(menuCategory.getMenuCategoryName())
                .menuName(menuName)
                .menuPrice(menuPrice)
                .menuState(menuState)
                .menuImgUrl(menuImgUrl)
                .recommendMenu(recommendMenu)
                .build();
    }

    public SearchMenuRespDto toSearchMenuRespDto() {
        return SearchMenuRespDto.builder()
                .adminId(admin.getAdminId())
                .menuId(menuId)
                .menuCategoryId(menuCategory.getMenuCategoryId())
                .menuCode(menuCode)
                .menuName(menuName)
                .menuPrice(menuPrice)
                .menuState(menuState)
                .recommendMenu(recommendMenu)
                .menuImgUrl(menuImgUrl)
                .build();
    }
}

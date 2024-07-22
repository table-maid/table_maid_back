package com.project.tableMaid.dto.SSE;

import com.project.tableMaid.entity.menu.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SSESendMenusReqDto {
    private int tableNumber;
    private SSEMenuReqDto menu;
    private List<SSEOptionReqDto> options;
}

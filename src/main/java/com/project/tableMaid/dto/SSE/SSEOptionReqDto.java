package com.project.tableMaid.dto.SSE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SSEOptionReqDto {
    private int optionId;
    private String optionName;
    private int optionPrice;
}

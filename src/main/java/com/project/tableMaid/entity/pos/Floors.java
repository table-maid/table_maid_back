package com.project.tableMaid.entity.pos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Floors {
    private int floorId;
    private int adminId;
    private int floorNum;
    private String floorName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

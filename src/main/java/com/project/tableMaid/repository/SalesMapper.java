package com.project.tableMaid.repository;

import com.project.tableMaid.entity.sales.Sales;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalesMapper {
    public int saveSales(List<Sales> sales);

}

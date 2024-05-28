package com.project.tableMaid.repository;

import com.project.tableMaid.entity.sales.Sales;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SalesMapper {
    public void SaveSales(Sales sales);

}

package com.project.tableMaid.repository;

import com.project.tableMaid.entity.sales.Order;
import com.project.tableMaid.entity.sales.Sales;
import com.project.tableMaid.entity.sales.TotalSales;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SalesMapper {
    public int saveSales(List<Sales> sales);
    public int saveOrders(List<Order> orders);
    public List<TotalSales> findTotalSales();
    public List<Sales> findSelectDaySales();
    public List<Order> findOrders();
    public int deleteSales(@Param("orderNumber") int orderNumber, @Param("adminId") int adminId);
    public int deleteOrder(@Param("orderNumber") int orderNumber, @Param("adminId") int adminId);
}

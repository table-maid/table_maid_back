package com.project.tableMaid.repository;

import com.project.tableMaid.entity.sales.Order;
import com.project.tableMaid.entity.sales.Refund;
import com.project.tableMaid.entity.sales.Sales;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SalesMapper {
    public int saveSales(List<Sales> sales);
    public int saveOrders(List<Order> orders);
    public List<Sales> findTotalSales(@Param("adminId") int adminId);
    public List<Sales> findSelectDaySales(@Param("adminId") int adminId);
    public List<Sales> findMenuTotalSales(
            @Param("adminId") int adminId,
            @Param("menuId") int menuId
    );
    public List<Order> findOrders();
    public int deleteSales(@Param("orderNumber") int orderNumber, @Param("adminId") int adminId);
    public int deleteOrder(@Param("orderNumber") int orderNumber, @Param("adminId") int adminId);
    public List<Order> findOrderListByAdminId(@Param("adminId") int adminId);
    public List<Order> findOrderDetailByOrderId(@Param("adminId") int adminId, @Param("orderNumber") int orderNumber);
    public void saveRefundDetail (Refund refund);
}

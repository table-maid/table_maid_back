package com.project.tableMaid.service;

import com.project.tableMaid.dto.sales.request.OrderMenuReqDto;
import com.project.tableMaid.dto.sales.request.SalesMenuReqDto;
import com.project.tableMaid.dto.sales.response.OrderMenuRespDto;
import com.project.tableMaid.entity.sales.Order;
import com.project.tableMaid.entity.sales.Sales;
import com.project.tableMaid.repository.SalesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesService {

    @Autowired
    SalesMapper salesMapper;

    //판매 POST
    public void insertSales(List<SalesMenuReqDto> salesMenuReqDtos) {
        List<Sales> sales = new ArrayList<>();
        for(SalesMenuReqDto salesMenuReqDto : salesMenuReqDtos) {
            sales.add(salesMenuReqDto.toSales());
        }

        salesMapper.saveSales(sales);
    }

    // 오더 POST
    public void insertOrders(List<OrderMenuReqDto> orderMenuReqDtos) {
        List<Order> orders = new ArrayList<>();
        for (OrderMenuReqDto orderMenuReqDto : orderMenuReqDtos) {
            orders.add(orderMenuReqDto.toOrder());
        }

        salesMapper.saveOrders(orders);
    }

    // 오더 GET
    public List<OrderMenuRespDto> searchOrders () {
        List<Order> orders = salesMapper.findOrders();
        List<OrderMenuRespDto> orderMenuRespDtos = new ArrayList<>();
        for (Order order : orders) {
            orderMenuRespDtos.add(order.toOrderMenuRespDto());
        }

        return orderMenuRespDtos;
    }

    // 오더 DELETE
    public void deleteOrders(int orderNumber, int adminId) {
        salesMapper.deleteOrder(orderNumber, adminId);
    }





}

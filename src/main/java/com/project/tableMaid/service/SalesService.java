package com.project.tableMaid.service;

import com.project.tableMaid.dto.sales.request.OrderMenuReqDto;
import com.project.tableMaid.dto.sales.request.SalesMenuReqDto;
import com.project.tableMaid.dto.sales.response.OrderMenuRespDto;
import com.project.tableMaid.dto.sales.response.SalesMenuRespDto;
import com.project.tableMaid.entity.sales.Order;
import com.project.tableMaid.entity.sales.Sales;
import com.project.tableMaid.exception.DeleteException;
import com.project.tableMaid.exception.SaveException;
import com.project.tableMaid.repository.SalesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SalesService {

    @Autowired
    SalesMapper salesMapper;

    //판매 POST
    @Transactional(rollbackFor = Exception.class)
    public void insertSales(List<SalesMenuReqDto> salesMenuReqDtos) {
        int successCount = 0;
        List<Sales> sales = new ArrayList<>();
        for(SalesMenuReqDto salesMenuReqDto : salesMenuReqDtos) {
            sales.add(salesMenuReqDto.toEntity());
        }

        successCount += salesMapper.saveSales(sales);

        if(successCount < 1) {
            throw new SaveException(Map.of("insertSales 오류", "정상적으로 판매되지 않았습니다."));
        }
    }

    //판매 GET
    public List<SalesMenuRespDto> searchSales () {
        List<Sales> sales = salesMapper.findSales();

        return sales.stream().map(Sales::toSalesMenuRespDto).collect(Collectors.toList());
    }

    // 오더 POST
    @Transactional(rollbackFor = Exception.class)
    public void insertOrders(List<OrderMenuReqDto> orderMenuReqDtos) {
        int successCount = 0;
        List<Order> orders = new ArrayList<>();
        for (OrderMenuReqDto orderMenuReqDto : orderMenuReqDtos) {
            orders.add(orderMenuReqDto.toEntity());
        }

        successCount += salesMapper.saveOrders(orders);

        if(successCount < 1) {
            throw new SaveException(Map.of("insertOrders 오류", "정상적으로 오더에 저장되지 않았습니다."));
        }

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

    // 판매 DELETE
    @Transactional(rollbackFor = Exception.class)
    public void deleteSales(int orderNumber, int adminId) {
        int successCount = 0;
        successCount += salesMapper.deleteSales(orderNumber, adminId);

        if(successCount < 1) {
            throw new DeleteException(Map.of("deleteSales 오류", "정상적으로 환불(sales)이 되지 않았습니다."));
        }
    }

    // 오더 DELETE
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrders(int orderNumber, int adminId) {
        int successCount= 0;
        successCount += salesMapper.deleteOrder(orderNumber, adminId);

        if(successCount < 1) {
            throw new DeleteException(Map.of("deleteSales 오류", "정상적으로 환불(order)이 되지 않았습니다."));
        }
    }





}

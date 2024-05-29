package com.project.tableMaid.service;

import com.project.tableMaid.dto.sales.request.SalesMenuReqDto;
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
        for( SalesMenuReqDto salesMenuReqDto : salesMenuReqDtos ) {
            sales.add(salesMenuReqDto.toSales());
        }

        salesMapper.saveSales(sales);


    }

}

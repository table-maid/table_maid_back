package com.project.tableMaid.controller;

import com.project.tableMaid.dto.sales.request.SalesMenuReqDto;
import com.project.tableMaid.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    SalesService salesService;

    @PostMapping("/menu")
    public ResponseEntity<?> addSales(@RequestBody List<SalesMenuReqDto> salesMenuReqDto) {
        salesService.insertSales(salesMenuReqDto);
        return ResponseEntity.created(null).body(true);
    }

//    @PostMapping("/order")
//    public ResponseEntity<?> addOrder(@RequestBody List<Order> )


}

package com.project.tableMaid.controller;

import com.project.tableMaid.aop.annotation.ParamsPrintAspect;
import com.project.tableMaid.dto.sales.request.OrderMenuReqDto;
import com.project.tableMaid.dto.sales.request.SalesMenuReqDto;
import com.project.tableMaid.entity.sales.Order;
import com.project.tableMaid.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/menu")
    public ResponseEntity<?> getSales() {
        return ResponseEntity.ok().body(salesService.searchTotalSales());
    }

    @GetMapping("/menu/select")
    public ResponseEntity<?> getSelectSales() {
        return ResponseEntity.ok().body(salesService.selectSales());
    }

    @DeleteMapping("/menu")
    public ResponseEntity<?> deleteSales(@RequestBody int orderNumber) {
        salesService.deleteSales(orderNumber, 2);
        return ResponseEntity.created(null).body(true);
    }

    @PostMapping("/order")
    public ResponseEntity<?> addOrder(@RequestBody List<OrderMenuReqDto> orderReqDto) {
        salesService.insertOrders(orderReqDto);
        return ResponseEntity.created(null).body(true);
    }
    @ParamsPrintAspect
    @GetMapping("/order")
    public ResponseEntity<?> getOrder() {
        return ResponseEntity.ok(salesService.searchOrders());
    }

    @DeleteMapping("/order")
    public ResponseEntity<?> deleteOrder(@RequestParam int orderNumber) {
        salesService.deleteOrders(orderNumber, 2);
        return ResponseEntity.created(null).body(true);
    }


}

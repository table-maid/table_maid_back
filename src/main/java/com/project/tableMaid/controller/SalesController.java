package com.project.tableMaid.controller;

import com.project.tableMaid.aop.annotation.ParamsPrintAspect;
import com.project.tableMaid.dto.sales.request.MenuTotalSalesReqDto;
import com.project.tableMaid.dto.sales.request.OrderMenuReqDto;
import com.project.tableMaid.dto.sales.request.RefundDetailReqDto;
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

    // 디비 수정으로 코드 전체 수정 필요
//    @PostMapping("/menu")
//    public ResponseEntity<?> addSales(@RequestBody List<SalesMenuReqDto> salesMenuReqDto) {
//        salesService.insertSales(salesMenuReqDto);
//        return ResponseEntity.created(null).body(true);
//    }

    @GetMapping("/total")
    public ResponseEntity<?> getSales(@RequestParam int adminId) {
        return ResponseEntity.ok().body(salesService.searchTotalSales(adminId));
    }

    @GetMapping("/total/select")
    public ResponseEntity<?> getSelectSales(@RequestParam int adminId) {
        return ResponseEntity.ok().body(salesService.selectSales(adminId));
    }

    @GetMapping("/menu/total")
    public ResponseEntity<?> getMenuTotalSales(@RequestParam int adminId, int menuId) {
        return ResponseEntity.ok(salesService.searchMenuTotalSales(adminId, menuId));
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
    @GetMapping("/payment")
    public ResponseEntity<?> getPaymentList(@RequestParam int adminId, String startDate, String endDate) {
        return ResponseEntity.ok(salesService.getOrderList(adminId, startDate, endDate));
    }
    @GetMapping("/payment/detail")
    public ResponseEntity<?> getPaymentDetail(@RequestParam int adminId, int orderNumber) {
        return ResponseEntity.ok(salesService.getOrderDetail(adminId, orderNumber));
    }
    @PostMapping("/refund/detail")
    public ResponseEntity<?> addRefundDetail(@RequestBody RefundDetailReqDto refundDetailReqDto) {
        salesService.insertRefund(refundDetailReqDto);
        return ResponseEntity.created(null).body(true);
    }

}

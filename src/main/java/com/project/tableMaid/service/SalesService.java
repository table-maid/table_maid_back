package com.project.tableMaid.service;

import com.project.tableMaid.dto.sales.request.OrderMenuReqDto;
import com.project.tableMaid.dto.sales.request.RefundDetailReqDto;
import com.project.tableMaid.dto.sales.request.SalesMenuReqDto;
import com.project.tableMaid.dto.sales.response.*;
import com.project.tableMaid.entity.sales.Order;
import com.project.tableMaid.entity.sales.Refund;
import com.project.tableMaid.entity.sales.Sales;
import com.project.tableMaid.exception.DeleteException;
import com.project.tableMaid.exception.SaveException;
import com.project.tableMaid.repository.SalesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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

    //총매출 GET
    public List<TotalSalesRespDto> searchTotalSales (int adminId) {
        List<Sales> totalSales = salesMapper.findTotalSales(adminId);

        return totalSales.stream().map(Sales::toTotalSalesMenuRespDto).collect(Collectors.toList());
    }

    //일매출 GET
    public List<SelectDateRespDto> selectSales (int adminId) {
        List<Sales> sales = salesMapper.findSelectDaySales(adminId);

        return sales.stream().map(Sales::toSalesDateRespDto).collect(Collectors.toList());
    }

    // 메뉴 총매출 GET
    @Transactional(rollbackFor = Exception.class)
    public List<MenuTotalSalesRespDto> searchMenuTotalSales (int adminId, int menuId) {
        List<Sales> sales = salesMapper.findMenuTotalSales(adminId,menuId);

        return sales.stream().map(Sales::toMenuTotalSalesRespDto).collect(Collectors.toList());
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

    // 주문 내역 리스트 조회
    @Transactional(rollbackFor = Exception.class)
    public List<Map<String, Object>> getOrderList(int adminId, String startDate, String endDate) {
        List<Order> orderList = salesMapper.findOrderListByAdminId(adminId, startDate, endDate);
        Map<Integer, Map<String, Object>> groupedOrders = new HashMap<>();

        for (Order order : orderList) {
            groupedOrders.computeIfAbsent(order.getOrderNumber(), k -> new HashMap<>());
            Map<String, Object> orderInfo = groupedOrders.get(order.getOrderNumber());

            // 주문 정보 설정
            orderInfo.putIfAbsent("orderNumber", order.getOrderNumber());
            orderInfo.putIfAbsent("tableNumber", order.getTableNumber());
            orderInfo.putIfAbsent("paymentDate", order.getCreateDate().toString());
            orderInfo.compute("orderTotalPrice", (k, v) -> (v == null) ? order.getMenuTotalPrice() : (int)v + order.getMenuTotalPrice());

            // 메뉴 정보 설정
            List<Map<String, Object>> menus = (List<Map<String, Object>>) orderInfo.computeIfAbsent("menu", k -> new ArrayList<>());
                Map<String, Object> menuDetails = new HashMap<>();
                menuDetails.put("menuName", order.getMenuName());
                menuDetails.put("menuCount", order.getMenuCount());
                menuDetails.put("menuTotalPrice", order.getMenuTotalPrice());
                menus.add(menuDetails);
        }
        return new ArrayList<>(groupedOrders.values());
    }
    // 주문 내역 상세 조회
    @Transactional(rollbackFor = Exception.class)
    public List<Map<String, Object>> getOrderDetail(int adminId, int orderNumber) {
        List<Order> orderList = salesMapper.findOrderDetailByOrderId(adminId, orderNumber);
        System.out.println(orderList);
        Map<Integer, Map<String, Object>> groupedOrders = new HashMap<>();

        for (Order order : orderList) {
            groupedOrders.computeIfAbsent(order.getOrderNumber(), k -> new HashMap<>());
            Map<String, Object> orderInfo = groupedOrders.get(order.getOrderNumber());

            // 주문 정보 설정
            orderInfo.putIfAbsent("adminId", order.getAdminId());
            orderInfo.putIfAbsent("orderNumber", order.getOrderNumber());
            orderInfo.putIfAbsent("tableNumber", order.getTableNumber());
            orderInfo.putIfAbsent("paymentDate", order.getCreateDate().toString());

            // 메뉴 정보 설정
            Map<String, Map<String, Object>> menuMap = (Map<String, Map<String, Object>>) orderInfo.computeIfAbsent("menu", k -> new HashMap<>());
            Map<String, Object> menuDetails = menuMap.computeIfAbsent(order.getMenuName(), k -> new HashMap<>());

            // 메뉴 상세 정보 설정
            menuDetails.putIfAbsent("menuName", order.getMenuName());
            menuDetails.putIfAbsent("menuCount", order.getMenuCount());
            menuDetails.putIfAbsent("menuTotalPrice", order.getMenuTotalPrice());

            // 옵션 리스트 가져오기 및 옵션 추가
            List<Map<String, Object>> options = (List<Map<String, Object>>) menuDetails.computeIfAbsent("options", k -> new ArrayList<>());
            if (order.getOptionName() != null) {
                Map<String, Object> optionDetails = new HashMap<>();
                optionDetails.put("optionName", order.getOptionName());
                optionDetails.put("optionPrice", order.getOptionPrice());
                options.add(optionDetails);
            }
        }

        // 결과 포맷 변경
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> orderInfo : groupedOrders.values()) {
            Map<String, Map<String, Object>> menuMap = (Map<String, Map<String, Object>>) orderInfo.get("menu");
            orderInfo.put("menu", new ArrayList<>(menuMap.values()));
            result.add(orderInfo);
        }

        return result;
    }

    //환불 내역 오더 db 삭제 후 환불 db에 추가
    @Transactional
    public void insertRefund(RefundDetailReqDto refundDetailReqDto) {
        int successCount= 0;
        successCount += salesMapper.deleteOrder(refundDetailReqDto.getOrderNumber(), refundDetailReqDto.getAdminId());

        if(successCount < 1) {
            throw new DeleteException(Map.of("deleteSales 오류", "정상적으로 환불(order)이 되지 않았습니다."));
        }

        // 환불 코드 랜덤 생성
        String randomCode = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            sb.append(randomCode.charAt(random.nextInt(randomCode.length())));
        }
        String refundCode = sb.toString();

        List<Refund> refunds = new ArrayList<>();
        for (RefundDetailReqDto.Menu menu : refundDetailReqDto.getMenu()) {
            if (menu.getOptions().isEmpty()) {
                Refund refund = Refund.builder()
                        .adminId(refundDetailReqDto.getAdminId())
                        .refundCode(refundCode)
                        .orderNumber(refundDetailReqDto.getOrderNumber())
                        .tableNumber(refundDetailReqDto.getTableNumber())
                        .menuName(menu.getMenuName())
                        .menuCount(menu.getMenuCount())
                        .optionName(null)
                        .optionPrice(0)
                        .menuTotalPrice(menu.getMenuTotalPrice())
                        .build();
                refunds.add(refund);
            } else {
                for (RefundDetailReqDto.Option option : menu.getOptions()) {
                    Refund refund = Refund.builder()
                            .adminId(refundDetailReqDto.getAdminId())
                            .refundCode(refundCode)
                            .orderNumber(refundDetailReqDto.getOrderNumber())
                            .tableNumber(refundDetailReqDto.getTableNumber())
                            .menuName(menu.getMenuName())
                            .menuCount(menu.getMenuCount())
                            .optionName(option.getOptionName())
                            .optionPrice(option.getOptionPrice())
                            .menuTotalPrice(menu.getMenuTotalPrice())
                            .build();
                    refunds.add(refund);
                }
            }
        }

        for (Refund refund : refunds) {
            System.out.println(refund);

            salesMapper.saveRefundDetail(refund);
        }
    }
}

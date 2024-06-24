package com.project.tableMaid.controller.sse;

import com.project.tableMaid.aop.annotation.ParamsPrintAspect;
import com.project.tableMaid.dto.SSE.SSESendMenusReqDto;
import com.project.tableMaid.service.SseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SseOrderController {

    private final SseOrderService seeOrderService;

    @GetMapping(value = "/send/menus/{table_number}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable(value = "table_number") int tableNum) {
        return seeOrderService.subscribe(tableNum);
    }

    @ParamsPrintAspect
    @PostMapping("/send/menus")
    public ResponseEntity<?> sendMenus (@RequestBody List<SSESendMenusReqDto> sseSendMenusReqDtoList) {
        seeOrderService.customNotify(1, sseSendMenusReqDtoList, "주문이 들어왔습니다", "SSEOrder");
        return ResponseEntity.created(null).body(true);
    }
















}

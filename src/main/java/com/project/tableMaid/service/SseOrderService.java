package com.project.tableMaid.service;

import com.project.tableMaid.repository.EmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SseOrderService {

    private final EmitterRepository emitterRepository;

    private static final Long DEFAULT_TIMEOUT = 600L * 1000 * 60;

    public SseEmitter subscribe(int tableNum) {
        SseEmitter emitter = createEmitter(tableNum);

        sendToClient(tableNum, "EventStream Created. [tableNum="+ tableNum + "]", "sse 접속 성공");

        return emitter;
    }

    public <T> void customNotify(int tableNum, T data, String comment, String type) {
        sendToClient(tableNum, data, comment, type);
    }
    public void notify(int userId, Object data, String comment) {
        sendToClient(userId, data, comment);
    }


    private SseEmitter createEmitter(int tableNum) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(tableNum, emitter);

        emitter.onCompletion(() -> emitterRepository.deleteByNum(tableNum));
        emitter.onTimeout(() -> emitterRepository.deleteByNum(tableNum));

        return emitter;
    }

    private void sendToClient(int tableNum, Object data, String comment) {
        SseEmitter emitter = emitterRepository.get(tableNum);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .id(String.valueOf(tableNum))
                        .name("sse")
                        .data(data)
                        .comment(comment));
            } catch (IOException e) {
                emitterRepository.deleteByNum(tableNum);
                emitter.completeWithError(e);
            }
        }
    }

    private <T> void sendToClient(int tableNum, Object data, String comment, String type) {
        SseEmitter emitter = emitterRepository.get(tableNum);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .id(String.valueOf(tableNum))
                        .name(type)
                        .data(data)
                        .comment(comment));
            } catch (IOException e) {
                emitterRepository.deleteByNum(tableNum);
                emitter.completeWithError(e);
            }
        }
    }




}

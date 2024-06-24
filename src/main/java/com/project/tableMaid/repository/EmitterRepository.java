package com.project.tableMaid.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class EmitterRepository {

    private final Map<Integer, SseEmitter> emitters = new ConcurrentHashMap<>();

    public void save(int tableNum, SseEmitter emitter) {
        emitters.put(tableNum, emitter);
    }

    public void deleteByNum(int tableNum) {
        emitters.remove(tableNum);
    }

    public SseEmitter get(int tableNum) {
        return emitters.get(tableNum);
    }

}

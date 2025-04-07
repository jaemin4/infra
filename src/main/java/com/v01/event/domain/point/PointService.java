package com.v01.event.domain.point;

import org.springframework.stereotype.Service;

@Service
public class PointService {
    public void usePoints(Long userId, int amount) {
        System.out.println("[Point] 포인트 사용: userId=" + userId + ", amount=" + amount);
    }
}

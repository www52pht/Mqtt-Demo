package com.example.mqttdemo.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UavState {
    private String uavId;
    private Double latitude;
    private Double longitude;
    private Double altitude;
    private Double speed;
    private String status;
    private LocalDateTime timestamp;
}
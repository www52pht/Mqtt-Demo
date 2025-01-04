package com.example.mqttdemo.common.exception;

public class MqttProcessException extends RuntimeException {
    public MqttProcessException(String message) {
        super(message);
    }

    public MqttProcessException(String message, Throwable cause) {
        super(message, cause);
    }
} 
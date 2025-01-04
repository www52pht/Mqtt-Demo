package com.example.mqttdemo.mqtt.processor;

public interface MqttMessageProcessor {
    boolean canProcess(String topic);
    void processMessage(String topic, String payload);
} 
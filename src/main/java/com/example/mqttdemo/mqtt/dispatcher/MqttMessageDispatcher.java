package com.example.mqttdemo.mqtt.dispatcher;

import com.example.mqttdemo.mqtt.processor.MqttMessageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MqttMessageDispatcher {
    @Autowired
    private List<MqttMessageProcessor> processors;

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(Message<?> message) {
        String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
        String payload = message.getPayload().toString();

        processors.stream()
                .filter(processor -> processor.canProcess(topic))
                .forEach(processor -> {
                    try {
                        processor.processMessage(topic, payload);
                    } catch (Exception e) {
                        log.error("Process message error, topic: {}", topic, e);
                    }
                });
    }
} 
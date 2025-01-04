package com.example.mqttdemo.mqtt.processor;

import com.example.mqttdemo.redis.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class AbstractMqttMessageProcessor implements MqttMessageProcessor {
    @Autowired
    protected RedisCache redisCache;

    protected abstract String getTopicPattern();

    @Override
    public boolean canProcess(String topic) {
        return topic.matches(getTopicPattern());
    }
}
package com.example.mqttdemo.mqtt.processor;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.mqttdemo.common.constant.MqttConstants;
import com.example.mqttdemo.common.exception.MqttProcessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class UavStateMessageProcessor extends AbstractMqttMessageProcessor {
    @Value("${mqtt.redis.expire-seconds:20}")  // 默认20秒
    private int expireSeconds;
    @Override
    protected String getTopicPattern() {
        return MqttConstants.TOPIC_STATE_PATTERN;
    }

    @Override
    public void processMessage(String topic, String payload) {
        try {
            String uavId = extractUavId(topic);
            JSONObject jsonPayload = JSON.parseObject(payload);
            redisCache.setCacheObject(MqttConstants.REDIS_UAV_STATE_KEY_PREFIX + uavId, jsonPayload, expireSeconds,
                TimeUnit.SECONDS);
            log.info("处理无人机 {} 的state状态数据: {}", uavId, payload);
        } catch (Exception e) {
            log.error("处理无人机状态消息失败", e);
            throw new MqttProcessException("处理UAV状态消息失败", e);
        }
    }

    private String extractUavId(String topic) {
        return topic.split("/")[2];
    }
}
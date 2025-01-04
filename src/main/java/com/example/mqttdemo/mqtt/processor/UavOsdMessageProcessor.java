package com.example.mqttdemo.mqtt.processor;

import com.example.mqttdemo.common.constant.MqttConstants;
import com.example.mqttdemo.common.exception.MqttProcessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UavOsdMessageProcessor extends AbstractMqttMessageProcessor {
    @Override
    protected String getTopicPattern() {
        return MqttConstants.TOPIC_OSD_PATTERN;
    }

    @Override
    public void processMessage(String topic, String payload) {
        try {
            String uavId = extractUavId(topic);
            // 使用常量定义的key前缀
            redisCache.setCacheObject(MqttConstants.REDIS_UAV_ODS_KEY_PREFIX + uavId, payload);
            log.info("Processed UAV ODS for ID {}: {}", uavId, payload);
        } catch (Exception e) {
            log.error("Failed to process UAV state message", e);
            throw new MqttProcessException("处理UAV状态消息失败", e);
        }
    }

    private String extractUavId(String topic) {
        return topic.split("/")[2];
    }
}
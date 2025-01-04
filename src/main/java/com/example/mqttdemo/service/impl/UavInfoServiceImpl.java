package com.example.mqttdemo.service.impl;

import com.example.mqttdemo.redis.RedisCache;
import com.example.mqttdemo.service.IUavInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author www
 * @date 2025年01月04日 14:34
 */
@Service
public class UavInfoServiceImpl implements IUavInfoService {
    @Autowired
    private RedisCache redisCache;

    @Override
    public HashMap<String,String> selectFkUavInfoById(Long id) {
        return new HashMap<>();
    }
}
package com.example.mqttdemo.service;

import java.util.HashMap;

/**
 * @author www
 * @date 2025年01月04日 14:34
 */
public interface IUavInfoService {
    HashMap<String, String> selectFkUavInfoById(Long id);
}

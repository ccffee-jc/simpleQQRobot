package com.ccffee.NotifyRobot.service;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;

public interface HttpSenderService {
    public String send(String url, HttpMethod method, MultiValueMap<String, Object> params);
}

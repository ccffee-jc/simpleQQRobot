package com.ccffee.coolQHttpServer.service.impl;

import com.ccffee.coolQHttpServer.service.HttpSenderService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @Author 冯战魁  https://blog.51cto.com/fengzhankui/2064327?utm_source=oschina-app
 * @Date 2018/1/23 下午5:43
 */
@Service
public class HttpSenderServicelmpl implements HttpSenderService {
    @Override
    public String send(String url, HttpMethod method, MultiValueMap<String, Object> params) {
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
        //  执行HTTP请求
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
        return response.getBody();
    }
}

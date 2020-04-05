package com.ccffee.coolQHttpServer.controller;

import com.ccffee.coolQHttpServer.common.CommonPost;
import com.ccffee.coolQHttpServer.service.CoolQHttpServerService;
import com.ccffee.coolQHttpServer.service.HttpSenderService;
import com.sun.org.apache.xpath.internal.objects.XObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CoolQHttpServerController {
    @Autowired
    private CoolQHttpServerService coolQHttpServerService;

    @RequestMapping(value = "/coolQHttpServer", method = RequestMethod.POST)
    public HashMap coolQHttpServer(@RequestBody HashMap param){
        return coolQHttpServerService.coolQhttpServer(param);
    }
}

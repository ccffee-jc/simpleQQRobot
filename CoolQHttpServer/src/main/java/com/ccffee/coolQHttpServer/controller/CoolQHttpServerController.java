package com.ccffee.coolQHttpServer.controller;

import com.ccffee.coolQHttpServer.service.CqMessageEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CoolQHttpServerController {
    @Autowired
    private CqMessageEntryService cqMessageEntryService;

    @RequestMapping(value = "/coolQHttpServer", method = RequestMethod.POST)
    public HashMap coolQHttpServer(@RequestBody HashMap param){
        return cqMessageEntryService.coolQhttpServer(param);
    }
}

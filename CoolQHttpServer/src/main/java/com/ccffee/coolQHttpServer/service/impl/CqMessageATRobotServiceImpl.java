package com.ccffee.coolQHttpServer.service.impl;

import com.ccffee.coolQHttpServer.Util.HttpUtil;
import com.ccffee.coolQHttpServer.service.CqMessageATRobotService;
import com.ccffee.coolQHttpServer.service.CqUserAutoTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CqMessageATRobotServiceImpl implements CqMessageATRobotService {
    @Autowired
    private CqUserAutoTaskService cqUserAutoTaskService;

    private Logger LOGGER = LoggerFactory.getLogger(CqMessageATRobotServiceImpl.class);

    @Override
    public HashMap messageDistributor(String message, String groupId, String userId) {
        message = message.replaceAll("\\[CQ:at,qq=352419920\\] ", "");

        //定时任务
        if (message.indexOf("提醒") == 0) {
            setAutoTask(message, groupId, userId);
        }

        //图灵机器人
        return chatForTuLing(message, groupId, userId);

    }

    private HashMap setAutoTask(String message, String groupId, String userId){
        HashMap map = new HashMap();
        map.put("reply", cqUserAutoTaskService.setUserAutoTask(message, groupId, "group"));
        return map;
    }

    private HashMap chatForTuLing(String message, String groupId, String userId){
        Map tuLingMessage = HttpUtil.postTuLing(message, userId);
        String tuLingText = (String) ((Map) ((Map) ((List) tuLingMessage.get("results")).get(0)).get("values")).get("text");
        LOGGER.info("图灵: send " + groupId + " " + tuLingText);

        HashMap map = new HashMap();
        map.put("reply", tuLingText);
        return map;
    }
}

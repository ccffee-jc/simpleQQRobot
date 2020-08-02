package com.ccffee.NotifyRobot.service.impl;

import com.ccffee.NotifyRobot.mbg.mapper.CqGroupmessageMapper;
import com.ccffee.NotifyRobot.mbg.model.CqGroup;
import com.ccffee.NotifyRobot.mbg.model.CqGroupmessage;
import com.ccffee.NotifyRobot.mbg.model.CqUser;
import com.ccffee.NotifyRobot.service.CqMessageNoATService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class CqMessageNoATServiceImpl implements CqMessageNoATService {
    @Autowired
    private CqGroupmessageMapper cqGroupmessageMapper;

    @Override
    public HashMap messageDistributor(String message, String groupId, String userId) {
        return null;
    }

    private void saveMessage(String message, CqUser cqUser, CqGroup cqGroup) {
        CqGroupmessage cqMessage = new CqGroupmessage();
        cqMessage.setGroupId(cqGroup.getId());
        cqMessage.setUserId(cqUser.getId());
        cqMessage.setMessage(message);
        cqMessage.setTime(new Date());
        cqGroupmessageMapper.insert(cqMessage);
    }
}

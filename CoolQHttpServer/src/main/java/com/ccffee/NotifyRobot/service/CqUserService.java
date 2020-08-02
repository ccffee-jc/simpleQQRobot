package com.ccffee.NotifyRobot.service;

import com.ccffee.NotifyRobot.mbg.model.CqUser;

public interface CqUserService {
    CqUser getUserByQQNum(String senderQQNum);

    CqUser saveUser(String nickname, String senderQQNum);
}

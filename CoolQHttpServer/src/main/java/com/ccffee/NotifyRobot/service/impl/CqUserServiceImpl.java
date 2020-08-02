package com.ccffee.NotifyRobot.service.impl;

import com.ccffee.NotifyRobot.mbg.mapper.CqUserMapper;
import com.ccffee.NotifyRobot.mbg.model.CqUser;
import com.ccffee.NotifyRobot.mbg.model.CqUserExample;
import com.ccffee.NotifyRobot.service.CqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CqUserServiceImpl implements CqUserService {
    @Autowired
    private CqUserMapper cqUserMapper;


    @Override
    public CqUser getUserByQQNum(String senderQQNum) {
        CqUserExample cqUserExample = new CqUserExample();
        cqUserExample.createCriteria().andQqnumEqualTo(senderQQNum);
        List<CqUser> cqUserList = cqUserMapper.selectByExample(cqUserExample);
        CqUser cqUser;
        if (cqUserList.size() == 0){
            cqUser = null;
        }else cqUser = cqUserList.get(0);
        return cqUser;
    }

    @Override
    public CqUser saveUser(String nickname, String senderQQNum) {
        CqUser cqUser = new CqUser();
        cqUser.setName(nickname);
        cqUser.setQqnum(senderQQNum);
        cqUser.setSaveimagestatus(0);
        cqUserMapper.insert(cqUser);

        return cqUser;
    }
}

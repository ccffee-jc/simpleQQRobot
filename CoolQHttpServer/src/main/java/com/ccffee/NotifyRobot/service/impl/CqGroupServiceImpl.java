package com.ccffee.NotifyRobot.service.impl;

import com.ccffee.NotifyRobot.mbg.mapper.CqGroupMapper;
import com.ccffee.NotifyRobot.mbg.model.CqGroup;
import com.ccffee.NotifyRobot.mbg.model.CqGroupExample;
import com.ccffee.NotifyRobot.service.CqGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CqGroupServiceImpl implements CqGroupService {
    @Autowired
    private CqGroupMapper cqGroupMapper;

    @Override
    public CqGroup getGroupByGroupNum(String groupNum) {
        CqGroupExample cqGroupExample = new CqGroupExample();
        cqGroupExample.createCriteria().andNumEqualTo(groupNum);
        List<CqGroup> cqGroupList = cqGroupMapper.selectByExample(cqGroupExample);
        CqGroup cqGroup;
        if (cqGroupList.size() == 0){
            cqGroup = null;
        }else cqGroup = cqGroupList.get(0);
        return cqGroup;
    }

    @Override
    public CqGroup saveGroup(String groupNum) {
        CqGroup cqGroup = new CqGroup();
        cqGroup.setNum(groupNum);
        cqGroupMapper.insert(cqGroup);

        return cqGroup;
    }
}

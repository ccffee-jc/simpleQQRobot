package com.ccffee.coolQHttpServer.service.impl;

import com.ccffee.coolQHttpServer.mbg.mapper.CqGroupMapper;
import com.ccffee.coolQHttpServer.mbg.mapper.CqTaskMapper;
import com.ccffee.coolQHttpServer.mbg.mapper.CqTimeMapper;
import com.ccffee.coolQHttpServer.mbg.mapper.CqUserMapper;
import com.ccffee.coolQHttpServer.mbg.model.*;
import com.ccffee.coolQHttpServer.service.CqUserAutoTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CqUserAutoTaskServiceImpl implements CqUserAutoTaskService {
    @Autowired
    private CqUserMapper cqUserMapper;
    @Autowired
    private CqGroupMapper cqGroupMapper;
    @Autowired
    private CqTimeMapper cqTimeMapper;
    @Autowired
    private CqTaskMapper cqTaskMapper;

    @Override
    public int addTask(String type, String day, String hour, String message, String targerNum) {
        CqUser cqUser = null;
        CqGroup cqGroup = null;
        if (type.equals("user")) {
            CqUserExample cqUserExample = new CqUserExample();
            cqUserExample.createCriteria().andQqnumEqualTo(targerNum);
            cqUser = cqUserMapper.selectByExample(cqUserExample).get(0);
        }else if (type.equals("group")){
            CqGroupExample cqGroupExample = new CqGroupExample();
            cqGroupExample.createCriteria().andNumEqualTo(targerNum);
            cqGroup = cqGroupMapper.selectByExample(cqGroupExample).get(0);
        }

        CqTask cqTask = new CqTask();
        cqTask.setTargetype(type);
        cqTask.setTargeid(type.equals("user")?cqUser.getId():cqGroup.getId());
        cqTask.setMessage(message);

        int stu = cqTaskMapper.insert(cqTask);

        if (stu != 1)return -1;

        CqTime cqTime = new CqTime();
        cqTime.setTimeday(day);
        cqTime.setTimehour(hour);
        cqTime.setStatus(1);
        cqTime.setTaskid(cqTask.getId());

        stu = cqTimeMapper.insert(cqTime);

        if (stu != 1)return -1;

        return 1;
    }
}

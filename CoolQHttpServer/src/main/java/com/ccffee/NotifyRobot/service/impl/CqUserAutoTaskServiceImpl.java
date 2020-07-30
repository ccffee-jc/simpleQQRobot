package com.ccffee.NotifyRobot.service.impl;

import com.ccffee.NotifyRobot.Util.DateUtil;
import com.ccffee.NotifyRobot.mbg.mapper.*;
import com.ccffee.NotifyRobot.mbg.model.*;
import com.ccffee.NotifyRobot.service.CqUserAutoTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private CqTimenameMapper cqTimenameMapper;

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

    @Override
    public String setUserAutoTask(String message, String targerId, String type) {
        //将参数分割
        String[] messageArr = message.split(" ");

        //todo 检测hour格式是否正确

        String[] hourArr = messageArr[2].split("\\.");
        for (int i = 0; i < hourArr.length; i++){
            if (hourArr[i].length() != 2)
                hourArr[i] = "0" + hourArr[i];
        }
        messageArr[2] = hourArr[0] + "." + hourArr[1];


        if (messageArr.length != 4)
            return "输入的参数数量错误，请确认是否输入三个参数，输入格式应该为：@机器人 提醒 日期 时间 消息";

        //检测day是否合法
        //判断有无在timeName表中
        CqTimenameExample cqTimenameExample = new CqTimenameExample();
        cqTimenameExample.createCriteria().andTimenameEqualTo(messageArr[1]);
        List<CqTimename> cqTimenameList = cqTimenameMapper.selectByExample(cqTimenameExample);
        int stu;
        if (cqTimenameList.size() == 1){
            stu = addTask(type, messageArr[1], messageArr[2], messageArr[3], targerId);
            if (stu != 1)return "定时提醒设置失败";
            return "定时提醒设置成功";
        }

        //timeName表中无day，此时形式应该为2020/5/24类似
        messageArr[1] = DateUtil.checkCan2yyyyMMdd(messageArr[1]);
        if (messageArr[1] == null)return "日期格式错误，请修改后重试";
        stu = addTask(type, messageArr[1], messageArr[2], messageArr[3], targerId);
        if (stu != 1)return "定时提醒设置失败";
        return "定时提醒设置成功";

    }
}

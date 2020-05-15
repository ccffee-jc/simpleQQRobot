package com.ccffee.coolQHttpServer.Component;

import com.ccffee.coolQHttpServer.Util.DateUtil;
import com.ccffee.coolQHttpServer.common.CommonPost;
import com.ccffee.coolQHttpServer.mbg.mapper.CqGroupMapper;
import com.ccffee.coolQHttpServer.mbg.mapper.CqTaskMapper;
import com.ccffee.coolQHttpServer.mbg.mapper.CqTimeMapper;
import com.ccffee.coolQHttpServer.mbg.mapper.CqUserMapper;
import com.ccffee.coolQHttpServer.mbg.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class RemindTask {

    private Logger LOGGER = LoggerFactory.getLogger(RemindTask.class);

    @Autowired
    private CqTimeMapper cqTimeMapper;
    @Autowired
    private CqTaskMapper cqTaskMapper;
    @Autowired
    private CqGroupMapper cqGroupMapper;
    @Autowired
    private CqUserMapper cqUserMapper;

    @Scheduled(cron = "0 * * ? * ?")
    private void remind(){
        Date time = new Date();

        CqTimeExample cqTimeExample = new CqTimeExample();

        cqTimeExample.createCriteria().andTimedayEqualTo(DateUtil.getTimeString_yyyyMMdd(time)).andTimehourEqualTo(DateUtil.getTimeString_HHdd(time)).andStatusEqualTo(1);
        cqTimeExample.or().andTimedayEqualTo("每天").andTimehourEqualTo(DateUtil.getTimeString_HHdd(time)).andStatusEqualTo(1);
        cqTimeExample.or().andTimedayEqualTo("星期"+getChineseWeekDay()).andTimehourEqualTo(DateUtil.getTimeString_HHdd(time)).andStatusEqualTo(1);
        cqTimeExample.or().andTimedayEqualTo("周"+getChineseWeekDay()).andTimehourEqualTo(DateUtil.getTimeString_HHdd(time)).andStatusEqualTo(1);
        cqTimeExample.or().andTimedayEqualTo(DateUtil.checkTimeForWork()?"工作日":"周末").andTimehourEqualTo(DateUtil.getTimeString_HHdd(time)).andStatusEqualTo(1);


        List<CqTime> cqTimeList = cqTimeMapper.selectByExample(cqTimeExample);

        for (CqTime cqtime: cqTimeList){
            CqTask cqTask = cqTaskMapper.selectByPrimaryKey(cqtime.getTaskid());
            if (cqTask.getTargetype().equals("user")){
                CqUser cqUser = cqUserMapper.selectByPrimaryKey(cqTask.getTargeid());
                CommonPost.sendPrivateMsg(cqUser.getQqnum(), cqTask.getMessage(), false);
                LOGGER.info("send task user: "+ cqUser.getQqnum() + " message:" + cqTask.getMessage());
            }else if (cqTask.getTargetype().equals("group")){
                CqGroup cqGroup = cqGroupMapper.selectByPrimaryKey(cqTask.getTargeid());
                CommonPost.sendGroupMsg(cqGroup.getNum(), cqTask.getMessage());
                LOGGER.info("send task group: "+ cqGroup.getNum() + " message:" + cqTask.getMessage());
            }
        }

    }

    private String getChineseWeekDay(){
        int weekDay = DateUtil.checkTimeOfWeek();
        switch (weekDay){
            case 1:return "一";
            case 2:return "二";
            case 3:return "三";
            case 4:return "四";
            case 5:return "五";
            case 6:return "六";
            case 7:return "日";
        }
        return null;
    }




}

package com.ccffee.NotifyRobot.service.impl;

import com.ccffee.NotifyRobot.Util.cqCode.CqCode;
import com.ccffee.NotifyRobot.Util.cqCode.CqCodeUtil;
import com.ccffee.NotifyRobot.mbg.mapper.CqGroupMapper;
import com.ccffee.NotifyRobot.mbg.mapper.CqGroupmessageMapper;
import com.ccffee.NotifyRobot.mbg.mapper.CqUserMapper;
import com.ccffee.NotifyRobot.mbg.model.*;
import com.ccffee.NotifyRobot.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CqMessageEntryServicelmpl implements CqMessageEntryService {
    @Autowired
    private CqMessageATGroupOtherMemberService cqMessageATGroupOtherMemberService;
    @Autowired
    private CqMessageATRobotService cqMessageATRobotService;
    @Autowired
    private CqMessageNoATService cqMessageNoATService;
    @Autowired
    private CqUserService cqUserService;
    @Autowired
    private CqGroupService cqGroupService;
    @Autowired
    private CqGroupMessageService cqGroupMessageService;


    @Value("${qq.num.robot}")
    private String robotQQNum;

    @Override
    public HashMap coolQhttpServer(HashMap param) {
        String postType = (String) param.get("post_type");
        if (postType.equals("message")){
            String massageType = (String) param.get("message_type");
            if (massageType.equals("private")){
                return privateMassage(param);
            }else if (massageType.equals("group")){
                return groupMassage(param);
            }
        }
        return null;
    }

    private HashMap privateMassage(HashMap param){
        HashMap map = new HashMap();
        map.put("reply", param.get("message"));
        return map;
    }

    private HashMap groupMassage(HashMap param){
        //判断该消息是否为普通消息
        String subType = (String)param.get("sub_type");
        if (!subType.equals("normal"))return null;

        //获取参数
        String groupNum = ((Integer)param.get("group_id")).toString();
        HashMap sender = (HashMap)param.get("sender");
        String senderQQNum = null;
        if (sender.get("user_id").getClass() == Integer.class) {
            senderQQNum = ((Integer)sender.get("user_id")).toString();
        }else if (sender.get("user_id").getClass() == Long.class) {
            senderQQNum = ((Long)sender.get("user_id")).toString();
        }
        String nickname = (String)sender.get("nickname");
        String message = (String)param.get("message");
        Integer time = (Integer)param.get("time");

        /**
         * 获取用户
         * 若数据库中无该用户信息，则存储该用户
         */
        CqUser cqUser = cqUserService.getUserByQQNum(senderQQNum);
        if (cqUser == null )cqUser = cqUserService.saveUser(nickname, senderQQNum);

        /**
         * 获取群组
         * 若数据库中无该群组信息，则存储该群组
         */
        CqGroup cqGroup = cqGroupService.getGroupByGroupNum(groupNum);
        if (cqGroup == null) cqGroup = cqGroupService.saveGroup(groupNum);

        /**
         * 存储群租信息
         */
        cqGroupMessageService.saveMessage(message, cqUser, cqGroup);

        //群组有@消息
        if (CqCodeUtil.checkMessageIsAT2QQNum(message, "*")) {
            //艾特了小黑机器人
            if (CqCodeUtil.checkMessageIsAT2QQNum(message, robotQQNum)) {
                return cqMessageATRobotService.messageDistributor(message, groupNum, senderQQNum);
            } else {
                return cqMessageATGroupOtherMemberService.messageDistributor(message, groupNum, senderQQNum);
            }
        }else {
            return cqMessageNoATService.messageDistributor(message, groupNum, senderQQNum);
        }
    }
}

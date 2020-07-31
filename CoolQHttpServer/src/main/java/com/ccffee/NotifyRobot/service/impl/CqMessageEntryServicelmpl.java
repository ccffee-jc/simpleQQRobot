package com.ccffee.NotifyRobot.service.impl;

import com.ccffee.NotifyRobot.Util.cqCode.CqCode;
import com.ccffee.NotifyRobot.Util.cqCode.CqCodeUtil;
import com.ccffee.NotifyRobot.mbg.mapper.CqGroupMapper;
import com.ccffee.NotifyRobot.mbg.mapper.CqGroupmessageMapper;
import com.ccffee.NotifyRobot.mbg.mapper.CqUserMapper;
import com.ccffee.NotifyRobot.mbg.model.*;
import com.ccffee.NotifyRobot.service.CqMessageATRobotService;
import com.ccffee.NotifyRobot.service.CqMessageEntryService;
import com.ccffee.NotifyRobot.service.CqMessageATGroupOtherMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CqMessageEntryServicelmpl implements CqMessageEntryService {
    private Logger LOGGER = LoggerFactory.getLogger(CqMessageEntryServicelmpl.class);

    @Autowired
    private CqGroupMapper cqGroupMapper;
    @Autowired
    private CqGroupmessageMapper cqGroupmessageMapper;
    @Autowired
    private CqUserMapper cqUserMapper;
    @Autowired
    private CqMessageATGroupOtherMemberService cqMessageATGroupOtherMemberService;
    @Autowired
    private CqMessageATRobotService cqMessageATRobotService;

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
        String groupId = ((Integer)param.get("group_id")).toString();
        HashMap sender = (HashMap)param.get("sender");
        String userId = null;
        if (sender.get("user_id").getClass() == Integer.class) {
            userId = ((Integer)sender.get("user_id")).toString();
        }else if (sender.get("user_id").getClass() == Long.class) {
            userId = ((Long)sender.get("user_id")).toString();
        }
        String nickname = (String)sender.get("nickname");
        String message = (String)param.get("message");
        Integer time = (Integer)param.get("time");


        /**
         * 获取用户
         * 若数据库中无该用户信息，则存储该用户
         */
        CqUser cqUser = getUserByQQNum(userId, nickname);

        /**
         * 获取群组
         * 若数据库中无该群组信息，则存储该群组
         */
        CqGroup cqGroup = getGroupByGroupId(groupId);

        /**
         * 存储群租信息
         */
        saveMessage(message, cqUser, cqGroup);

        //群组有@消息
        if (CqCodeUtil.checkMessageIsAT2QQNum(message, "*")) {
            //艾特了小黑机器人
            if (CqCodeUtil.checkMessageIsAT2QQNum(message, robotQQNum)) {
                return cqMessageATRobotService.messageDistributor(message, groupId, userId);
            } else {
                return cqMessageATGroupOtherMemberService.messageDistributor(message, groupId, userId);
            }
        }
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


    private CqGroup getGroupByGroupId(String groupId) {
        CqGroupExample cqGroupExample = new CqGroupExample();
        cqGroupExample.createCriteria().andNumEqualTo(groupId);
        List<CqGroup> cqGroupList = cqGroupMapper.selectByExample(cqGroupExample);
        CqGroup cqGroup = null;
        if (cqGroupList.size() == 0){
            cqGroup = new CqGroup();
            cqGroup.setNum(groupId);
            cqGroupMapper.insert(cqGroup);
        }else cqGroup = cqGroupList.get(0);
        return cqGroup;
    }


    private CqUser getUserByQQNum(String userId, String nickname) {
        CqUserExample cqUserExample = new CqUserExample();
        cqUserExample.createCriteria().andQqnumEqualTo(userId);
        List<CqUser> cqUserList = cqUserMapper.selectByExample(cqUserExample);
        CqUser cqUser = null;
        if (cqUserList.size() == 0){
            cqUser = new CqUser();
            cqUser.setName(nickname);
            cqUser.setQqnum(userId);
            cqUserMapper.insert(cqUser);
        }else cqUser = cqUserList.get(0);
        return cqUser;
    }
}

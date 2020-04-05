package com.ccffee.coolQHttpServer.service.impl;

import com.ccffee.coolQHttpServer.common.CommonPost;
import com.ccffee.coolQHttpServer.mbg.mapper.CqGroupMapper;
import com.ccffee.coolQHttpServer.mbg.mapper.CqMessageMapper;
import com.ccffee.coolQHttpServer.mbg.mapper.CqUserMapper;
import com.ccffee.coolQHttpServer.mbg.model.*;
import com.ccffee.coolQHttpServer.service.CoolQHttpServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CoolQHttpServerServicelmpl implements CoolQHttpServerService {
    @Autowired
    private CqGroupMapper cqGroupMapper;
    @Autowired
    private CqMessageMapper cqMessageMapper;
    @Autowired
    private CqUserMapper cqUserMapper;

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

        System.out.println(message);
        if (message.indexOf("大转盘") != -1 && message.indexOf("CQ:at") != -1){
            String regEx="[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(message);
            String qqNum = m.replaceAll("").trim();
            Integer num = null;
            while (num == null || num < 0)
                num = new Random().nextInt() / 100000;

            CommonPost.setGroupBan(Integer.parseInt(groupId), Long.parseLong(qqNum), num);
        }

        //判断数据库中有无该群组
        CqGroupExample cqGroupExample = new CqGroupExample();
        cqGroupExample.createCriteria().andNumEqualTo(groupId);

        List<CqGroup> cqGroupList = cqGroupMapper.selectByExample(cqGroupExample);
        if (cqGroupList.size() == 0){
            CqGroup cqGroup = new CqGroup();
            cqGroup.setNum(groupId);
            cqGroupMapper.insert(cqGroup);
            cqGroupList = cqGroupMapper.selectByExample(cqGroupExample);
        }

        //判断有无该用户
        CqUserExample cqUserExample = new CqUserExample();
        cqUserExample.createCriteria().andQqnumEqualTo(userId);

        List<CqUser> cqUserList = cqUserMapper.selectByExample(cqUserExample);
        if (cqUserList.size() == 0){
            CqUser user = new CqUser();
            user.setName(nickname);
            user.setQqnum(userId);
            cqUserMapper.insert(user);
            cqUserList = cqUserMapper.selectByExample(cqUserExample);
        }else {
            if (!cqUserList.get(0).getName().equals(nickname)){
                CqUser user = cqUserList.get(0);
                user.setName(nickname);
                cqUserMapper.updateByPrimaryKey(user);
            }
        }

        //存储message
        CqMessage cqMessage = new CqMessage();
        cqMessage.setGroupId(cqGroupList.get(0).getId());
        cqMessage.setUserId(cqUserList.get(0).getId());
        cqMessage.setMessage(message);
        long time1 = time;  //Integer类型无法*1000
        cqMessage.setTime(new Date(time1*1000));

        cqMessageMapper.insert(cqMessage);

        return null;
    }
}

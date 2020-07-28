package com.ccffee.coolQHttpServer.service.impl;

import com.ccffee.coolQHttpServer.Component.GoodDayTask;
import com.ccffee.coolQHttpServer.Util.DateUtil;
import com.ccffee.coolQHttpServer.Util.HttpUtil;
import com.ccffee.coolQHttpServer.common.CommonPost;
import com.ccffee.coolQHttpServer.mbg.mapper.CqGroupMapper;
import com.ccffee.coolQHttpServer.mbg.mapper.CqMessageMapper;
import com.ccffee.coolQHttpServer.mbg.mapper.CqTimenameMapper;
import com.ccffee.coolQHttpServer.mbg.mapper.CqUserMapper;
import com.ccffee.coolQHttpServer.mbg.model.*;
import com.ccffee.coolQHttpServer.service.CoolQHttpServerService;
import com.ccffee.coolQHttpServer.service.CqUserAutoTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CoolQHttpServerServicelmpl implements CoolQHttpServerService {
    private Logger LOGGER = LoggerFactory.getLogger(CoolQHttpServerServicelmpl.class);

    @Autowired
    private CqGroupMapper cqGroupMapper;
    @Autowired
    private CqMessageMapper cqMessageMapper;
    @Autowired
    private CqUserMapper cqUserMapper;
    @Autowired
    private CqTimenameMapper cqTimenameMapper;
    @Autowired
    private CqUserAutoTaskService cqUserAutoTaskService;

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

        /**
         * 获取群组
         * 若数据库中无该群组信息，则存储该群组
         */
        CqGroupExample cqGroupExample = new CqGroupExample();
        cqGroupExample.createCriteria().andNumEqualTo(groupId);
        List<CqGroup> cqGroupList = cqGroupMapper.selectByExample(cqGroupExample);
        CqGroup cqGroup = null;
        if (cqGroupList.size() == 0){
            cqGroup = new CqGroup();
            cqGroup.setNum(groupId);
            cqGroupMapper.insert(cqGroup);
        }else cqGroup = cqGroupList.get(0);

        /**
         * 存储群租信息
         */
        CqMessage cqMessage = new CqMessage();
        cqMessage.setGroupId(cqGroup.getId());
        cqMessage.setUserId(cqUser.getId());
        cqMessage.setMessage(message);
        cqMessage.setTime(new Date());
        cqMessageMapper.insert(cqMessage);

        //群组有@消息
        if (message.indexOf("[CQ:at") != -1) {

            //艾特了小黑机器人
            if (message.indexOf("[CQ:at,qq=352419920]") != -1) {
                message = message.replaceAll("\\[CQ:at,qq=352419920\\] ", "");

                //定时任务
                if (message.indexOf("提醒") == 0) {
                    HashMap map = new HashMap();
                    map.put("reply", setUserAutoTask(message, groupId, "group"));
                    return map;
                }


                //图灵机器人
                Map tuLingMessage = HttpUtil.postTuLing(message, userId);
                String tuLingText = (String) ((Map) ((Map) ((List) tuLingMessage.get("results")).get(0)).get("values")).get("text");
                LOGGER.info("图灵: send " + groupId + " " + tuLingText);

                HashMap map = new HashMap();
                map.put("reply", tuLingText);
                return map;
            } else if (message.indexOf("大转盘") != -1){
//                if (!userId.equals("1013154158"))
//                    return null;

                String atString = message.substring(message.indexOf("[CQ:at,qq="));

                String atQQ = atString.split("]")[0].split("qq=")[1];


                int second = (int)(Math.random() * 60);

                if (atQQ.equals("1013154158")){
                    CommonPost.setGroupBan(groupId, userId, 60 * second );

                    HashMap result = new HashMap();
                    result.put("reply", "臭不要脸");
                    return result;
                }


                CommonPost.setGroupBan(groupId, atQQ, 60 * second );

                CommonPost.sendGroupMsg(groupId, "[CQ:at,qq="+atQQ+"] 恭喜抽到"+second+"分钟");

            } else if (message.indexOf("解禁") != -1){
                String atString = message.substring(message.indexOf("[CQ:at,qq="));

                String atQQ = atString.split("]")[0].split("qq=")[1];

                CommonPost.setGroupBan(groupId, atQQ, 0 );

                CommonPost.sendGroupMsg(groupId, "[CQ:at,qq="+atQQ+"] 已经将奖品撤销");
            }else if (message.indexOf("臭不要脸") != -1){
                String atString = message.substring(message.indexOf("[CQ:at,qq="));

                String atQQ = atString.split("]")[0].split("qq=")[1];

                if (atQQ.equals("1013154158")){
                    int second = (int)(Math.random() * 60);

                    CommonPost.setGroupBan(groupId, userId, 60 * second );

                    HashMap result = new HashMap();
                    result.put("reply", "臭不要脸");
                    return result;
                }

//                for (int i = 0; i < 10; i++)
//                    CommonPost.sendGroupMsg(groupId, "[CQ:at,qq="+atQQ+"] 真不要脸");
                CommonPost.sendGroupMsg(groupId, "[CQ:at,qq="+atQQ+"] 真不要脸");
                CommonPost.sendGroupMsg(groupId,
                        "——————/´ ¯/)\n" +
                        "—————-/—-/\n" +
                        "—————-/—-/\n" +
                        "———--/´¯/'--'/´¯`·_\n" +
                        "———-/'/--/—-/—--/¨¯\\\n" +
                        "——--('(———- ¯~/'--')\n" +
                        "———\\————-'—--/\n" +
                        "———-'\\'————_-·´\n" +
                        "————\\———--(");

            }

        }

        return null;
    }

    private String setUserAutoTask(String message, String targerId, String type){
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
            stu = cqUserAutoTaskService.addTask(type, messageArr[1], messageArr[2], messageArr[3], targerId);
            if (stu != 1)return "定时提醒设置失败";
            return "定时提醒设置成功";
        }

        //timeName表中无day，此时形式应该为2020/5/24类似
        messageArr[1] = DateUtil.checkCan2yyyyMMdd(messageArr[1]);
        if (messageArr[1] == null)return "日期格式错误，请修改后重试";
        stu = cqUserAutoTaskService.addTask(type, messageArr[1], messageArr[2], messageArr[3], targerId);
        if (stu != 1)return "定时提醒设置失败";
        return "定时提醒设置成功";

    }
}

package com.ccffee.NotifyRobot.service.impl;

import com.ccffee.NotifyRobot.common.CommonPost;
import com.ccffee.NotifyRobot.service.CqMessageATGroupOtherMemberService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CqMessageATGroupOtherMemberServiceImpl implements CqMessageATGroupOtherMemberService {

    @Override
    public HashMap messageDistributor(String message, String groupId, String userId) {
        if (message.indexOf("大转盘") != -1){
            return randomBan0_60Second(message, groupId, userId);

        } else if (message.indexOf("解禁") != -1){
            return removeBan(message, groupId, userId);
        }else if (message.indexOf("臭不要脸") != -1){
            return justKidding(message, groupId, userId);
        }
        return null;
    }

    private HashMap randomBan0_60Second(String message, String groupId, String userId) {
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

        return null;
    }

    private HashMap removeBan(String message, String groupId, String userId) {
        String atString = message.substring(message.indexOf("[CQ:at,qq="));

        String atQQ = atString.split("]")[0].split("qq=")[1];

        CommonPost.setGroupBan(groupId, atQQ, 0 );

        CommonPost.sendGroupMsg(groupId, "[CQ:at,qq="+atQQ+"] 已经将奖品撤销");

        return null;
    }

    private HashMap justKidding(String message, String groupId, String userId) {
        String atString = message.substring(message.indexOf("[CQ:at,qq="));

        String atQQ = atString.split("]")[0].split("qq=")[1];

        if (atQQ.equals("1013154158")){
            int second = (int)(Math.random() * 60);

            CommonPost.setGroupBan(groupId, userId, 60 * second );

            HashMap result = new HashMap();
            result.put("reply", "臭不要脸");
            return result;
        }

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


        return null;
    }

}

package com.ccffee.NotifyRobot.service.impl;

import com.ccffee.NotifyRobot.Util.cqCode.CqCode;
import com.ccffee.NotifyRobot.Util.cqCode.CqCodeUtil;
import com.ccffee.NotifyRobot.common.CommonPost;
import com.ccffee.NotifyRobot.service.CqMessageATGroupOtherMemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CqMessageATGroupOtherMemberServiceImpl implements CqMessageATGroupOtherMemberService {

    @Value("${qq.num.superAdmin}")
    private String superAdminQQNum;

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
        List<CqCode> atCqCodeList = CqCodeUtil.getATCqCodeByMessage(message);

        int second = (int)(Math.random() * 60);

        if (CqCodeUtil.checkMessageIsAT2QQNum(message, superAdminQQNum)){
            CommonPost.setGroupBan(groupId, userId, 60 * second );

            HashMap result = new HashMap();
            result.put("reply", "臭不要脸");
            return result;
        }

        for (CqCode cqCode: atCqCodeList) {
            CommonPost.setGroupBan(groupId, cqCode.getParam().get("qq"), 60 * second);
            CommonPost.sendGroupMsg(groupId, CqCodeUtil.getCqCodeStrByCqCode(cqCode) + " 恭喜抽到" + second + "分钟");
        }
        return null;
    }

    private HashMap removeBan(String message, String groupId, String userId) {
        List<CqCode> atCqCodeList = CqCodeUtil.getATCqCodeByMessage(message);

        for (CqCode cqCode: atCqCodeList) {
            CommonPost.setGroupBan(groupId, cqCode.getParam().get("qq"), 0);
            CommonPost.sendGroupMsg(groupId, CqCodeUtil.getCqCodeStrByCqCode(cqCode) + " 已经将奖品撤销");
        }
        return null;
    }

    private HashMap justKidding(String message, String groupId, String userId) {
        List<CqCode> atCqCodeList = CqCodeUtil.getATCqCodeByMessage(message);

        if (CqCodeUtil.checkMessageIsAT2QQNum(message, superAdminQQNum)){
            int second = (int)(Math.random() * 60);

            CommonPost.setGroupBan(groupId, userId, 60 * second );

            HashMap result = new HashMap();
            result.put("reply", "臭不要脸");
            return result;
        }

        for (CqCode cqCode: atCqCodeList) {
            CommonPost.sendGroupMsg(groupId, CqCodeUtil.getCqCodeStrByCqCode(cqCode) + " 真不要脸");
        }

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

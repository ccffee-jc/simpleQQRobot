package com.ccffee.NotifyRobot.service.impl;

import com.ccffee.NotifyRobot.Util.FileUtil;
import com.ccffee.NotifyRobot.Util.HttpUtil;
import com.ccffee.NotifyRobot.Util.cqCode.CqCode;
import com.ccffee.NotifyRobot.Util.cqCode.CqCodeUtil;
import com.ccffee.NotifyRobot.common.CommonPost;
import com.ccffee.NotifyRobot.mbg.mapper.CqImageMapper;
import com.ccffee.NotifyRobot.mbg.model.CqImage;
import com.ccffee.NotifyRobot.service.CqMessageATRobotService;
import com.ccffee.NotifyRobot.service.CqUserAutoTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CqMessageATRobotServiceImpl implements CqMessageATRobotService {
    @Autowired
    private CqUserAutoTaskService cqUserAutoTaskService;
    @Autowired
    private CqImageMapper cqImageMapper;

    @Value("${qq.image.path}")
    private String imagePhth;
    @Value("${qq.image.url}")
    private String imageUrl;

    private Logger LOGGER = LoggerFactory.getLogger(CqMessageATRobotServiceImpl.class);

    @Override
    public HashMap messageDistributor(String message, String groupId, String userId) {
        message = message.replaceAll("\\[CQ:at,qq=352419920\\] ", "");

        //定时任务
        if (message.indexOf("提醒") == 0) {
            return setAutoTask(message, groupId, userId);
        }

        if (message.indexOf("存图") == 0) {
            message = message.replace("存图", "");
            return uploadImage(message, groupId, userId);
        }

        //图灵机器人
        return chatForTuLing(message, groupId, userId);

    }

    private HashMap uploadImage(String message, String groupId, String userId){
        //获取CqCode对象
        List<CqCode> cqCodes = CqCodeUtil.getIMAGECqCodeByMessage(message);

        for (CqCode cqCode: cqCodes){
            String url = cqCode.getParam().get("url");
            System.out.println(cqCode.getParam().get("file").split("\\.")[1]);
            String name = cqCode.getParam().get("file").split("\\.")[1];
            String imageName = null;
            try {
                imageName = FileUtil.downloadImage(url, imagePhth, name);
                String lastImageUrl = imageUrl + imageName;

                CqImage cqImage = new CqImage();
                cqImage.setUrl(lastImageUrl);
                cqImageMapper.insert(cqImage);

                CommonPost.sendGroupMsg(groupId, lastImageUrl);

            } catch (MalformedURLException e) {
                e.printStackTrace();
                CommonPost.sendGroupMsg(groupId, e.getMessage());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                CommonPost.sendGroupMsg(groupId, e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                CommonPost.sendGroupMsg(groupId, e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                CommonPost.sendGroupMsg(groupId, e.getMessage());
            }
        }
        HashMap map = new HashMap();
        map.put("reply", "保存完毕，如有错误请检查上述信息");
        return map;
    }

    private HashMap setAutoTask(String message, String groupId, String userId){
        HashMap map = new HashMap();
        map.put("reply", cqUserAutoTaskService.setUserAutoTask(message, groupId, "group"));
        return map;
    }

    private HashMap chatForTuLing(String message, String groupId, String userId){
        Map tuLingMessage = HttpUtil.postTuLing(message, userId);
        String tuLingText = (String) ((Map) ((Map) ((List) tuLingMessage.get("results")).get(0)).get("values")).get("text");
        LOGGER.info("图灵: send " + groupId + " " + tuLingText);

        HashMap map = new HashMap();
        map.put("reply", tuLingText);
        return map;
    }
}

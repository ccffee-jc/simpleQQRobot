package com.ccffee.coolQHttpServer.common;

import com.alibaba.fastjson.JSONObject;
import com.ccffee.coolQHttpServer.service.HttpSenderService;
import com.ccffee.coolQHttpServer.service.impl.HttpSenderServicelmpl;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * Describe: 用于向coolq http 发送post请求, 每一个对应的api都有一个对应的方法
 *
 * @author ccffee
 * @time 2020/4/3
 */
public class CommonPost {
    private static HttpSenderService httpSenderService = new HttpSenderServicelmpl();

    //当环境发生变化时需要修改
    private static String headUrl = "http://127.0.0.1:5700/";

    private static HttpMethod method = HttpMethod.POST;

    private static String sendPrivateMsg = "/send_private_msg";
    private static String sendGroupMsg = "/send_group_msg";
    private static String sendDiscussMsg = "/send_discuss_msg";
    private static String sendMsg = "/send_msg";
    private static String deleteMsg = "/delete_msg";
    private static String sendLike = "/send_like";
    private static String setGroupKick = "/set_group_kick";
    private static String setGroupBan = "/set_group_ban";
    private static String setGroupAnonymousBan = "/set_group_anonymous_ban";
    private static String setGroupWholeBan = "/set_group_whole_ban";
    private static String setGroupAdmin = "/set_group_admin";
//    private static String

    public static Map sendPrivateMsg(String user_id, String message){
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        params.add("user_id", user_id);
        params.add("message", message);

        String url = headUrl + sendPrivateMsg;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;

    }
    public static Map sendGroupMsg(String group_id, String message){
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        params.add("group_id", group_id);
        params.add("message", message);

        String url = headUrl + sendGroupMsg;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map setGroupBan(Integer groupId, Long userId, Integer duration){
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        params.add("group_id", groupId.toString());
        params.add("user_id", userId.toString());
        params.add("duration", duration.toString());

        String url = headUrl + setGroupBan;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
}

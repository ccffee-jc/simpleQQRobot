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
    private static String setGroupAnonymous = "/set_group_anonymous";
    private static String setGroupCard = "/set_group_card";
    private static String setGroupLeave = "/set_group_leave";
    private static String setGroupSpecialTitle = "/set_group_special_title";
    private static String setDiscussLeave = "/set_discuss_leave";
    private static String setFriendAddRequest = "/set_friend_add_request";
    private static String setGroupAddRequest = "/set_group_add_request";
    private static String getLoginInfo = "/get_login_info";
    private static String getStrangerInfo = "/get_stranger_info";
    private static String getFriendList = "/get_friend_list";
    private static String getGroupList = "/get_group_list";
    private static String getGroupInfo = "/get_group_info";
    private static String getGroupMemberInfo = "/get_group_member_info";
    private static String getGroupMemberList = "/get_group_member_list";
    private static String getCookies = "/get_cookies";
    private static String getCsrfToken = "/get_csrf_token";
    private static String getCredentials = "/get_credentials";
    private static String getRecord = "/get_record";
    private static String getImage = "/get_image";
    private static String canSendImage = "/can_send_image";
    private static String canSendRecord = "/can_send_record";
    private static String getStatus = "/get_status";
    private static String getVersionInfo = "/get_version_info";
    private static String setRestartPlugin = "/set_restart_plugin";
    private static String cleanDataDir = "/clean_data_dir";
    private static String cleanPluginLog = "/clean_plugin_log";

    public static Map sendPrivateMsg(String userId, String message, Boolean autoEscape){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("user_id", userId);
        params.add("message", message);
        params.add("auto_escape", autoEscape);

        String url = headUrl + sendPrivateMsg;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;

    }
    public static Map sendGroupMsg(String group_id, String message){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("group_id", group_id);
        params.add("message", message);

        String url = headUrl + sendGroupMsg;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map sendDiscussMsg(Long discussId, String message, Boolean autoEscape){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("discuss_id", discussId);
        params.add("message", message);
        params.add("auto_escape", autoEscape);

        String url = headUrl + sendDiscussMsg;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }


    public static Map setGroupBan(Integer groupId, Long userId, Integer duration){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
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

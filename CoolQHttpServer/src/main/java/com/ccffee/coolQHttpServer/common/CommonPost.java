package com.ccffee.coolQHttpServer.common;

import com.alibaba.fastjson.JSONObject;
import com.ccffee.coolQHttpServer.service.HttpSenderService;
import com.ccffee.coolQHttpServer.service.impl.HttpSenderServicelmpl;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
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
    private static String headUrl = "http://www.jincheng.run:5700/";

    private static HttpMethod method = HttpMethod.POST;

    private static String sendPrivateMsg = "/send_private_msg";
    private static String sendGroupMsg = "/send_group_msg_rate_limited";
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
    public static Map sendMsg(String messageType, Long userId, Long groupId, Long discussId, String message, Boolean autoEscape){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("message_type", messageType);
        params.add("user_id", userId);
        params.add("group_id", groupId);
        params.add("discuss_id", discussId);
        params.add("message", message);
        params.add("auto_escape", autoEscape);

        String url = headUrl + sendMsg;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map deleteMsg(Long messageId){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("message_id", messageId);

        String url = headUrl + deleteMsg;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map sendLike(Long userId, Integer times){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("user_id", userId);
        params.add("times", times);

        String url = headUrl + sendLike;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map setGroupKick(Long groupId, Long userId, Boolean rejectAddRequest){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("group_id", groupId);
        params.add("user_id", userId);
        params.add("reject_add_request", rejectAddRequest);

        String url = headUrl + setGroupKick;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map setGroupBan(String groupId, String userId, Integer duration){
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
    public static Map setGroupAnonymousBan(Long groupId, Object anonymous, String flag, Integer duration){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("group_id", groupId);
        params.add("anonymous", anonymous);
        params.add("flag", flag);
        params.add("duration", duration);

        String url = headUrl + setGroupAnonymousBan;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map setGroupWholeBan(Long groupId, Boolean enable){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("group_id", groupId);
        params.add("enable", enable);

        String url = headUrl + setGroupWholeBan;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map setGroupAdmin(Long groupId, Long userId, Boolean enable){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("group_id", groupId);
        params.add("user_id", userId);
        params.add("enable", enable);

        String url = headUrl + setGroupAdmin;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map setGroupAnonymous(Long groupId, Boolean enable){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("group_id", groupId);
        params.add("enable", enable);

        String url = headUrl + setGroupAnonymous;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map setGroupCard(Long groupId, Long userId, String card){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("group_id", groupId);
        params.add("user_id", userId);
        params.add("card", card);

        String url = headUrl + setGroupCard;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map setGroupLeave(Long groupId, Boolean isDismiss){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("group_id", groupId);
        params.add("is_dismiss", isDismiss);

        String url = headUrl + setGroupLeave;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map setGroupSpecialTitle(Long groupId, Long userId, String specialTitle, Integer duration){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("group_id", groupId);
        params.add("user_id", userId);
        params.add("special_title", specialTitle);
        params.add("duration", duration);

        String url = headUrl + setGroupSpecialTitle;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map setDiscussLeave(Long discussId){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("discuss_id", discussId);

        String url = headUrl + setDiscussLeave;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map setFriendAddRequest(String flag, Boolean approve, String remark){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("flag", flag);
        params.add("approve", approve);
        params.add("remark", remark);

        String url = headUrl + setFriendAddRequest;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map setGroupAddRequest(String flag, String type, Boolean approve, String reason){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("flag", flag);
        params.add("type", type);
        params.add("approve", approve);
        params.add("reason", reason);

        String url = headUrl + setGroupAddRequest;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map getLoginInfo(){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();

        String url = headUrl + getLoginInfo;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map getStrangerInfo(Long userId, Boolean noCache){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("user_id", userId);
        params.add("no_cache", noCache);

        String url = headUrl + getStrangerInfo;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map getFriendList(){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();

        String url = headUrl + getFriendList;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map getGroupList(){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();

        String url = headUrl + getGroupList;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map getGroupInfo(Long groupId, Boolean noCache){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("group_id", groupId);
        params.add("no_cache", noCache);

        String url = headUrl + getGroupInfo;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map getGroupMemberInfo(Long groupId, Long userId, Boolean noCache){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("group_id", groupId);
        params.add("user_id", userId);
        params.add("no_cache", noCache);

        String url = headUrl + getGroupMemberInfo;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map getGroupMemberList(Long groupId){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("group_id", groupId);

        String url = headUrl + getGroupMemberList;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map getCookies(String domain){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("domain", domain);

        String url = headUrl + getCookies;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map getCsrfToken(Long token){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("token", token);

        String url = headUrl + getCsrfToken;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map getCredentials(String domain){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("domain", domain);

        String url = headUrl + getCredentials;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map getRecord(String file, String outFormat, Boolean fullPath){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("file", file);
        params.add("out_format", outFormat);
        params.add("full_path", fullPath);

        String url = headUrl + getRecord;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map getImage(String file){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();
        params.add("file", file);

        String url = headUrl + getImage;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map canSendImage(){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();

        String url = headUrl + canSendImage;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map canSendRecord(){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();

        String url = headUrl + canSendRecord;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map getStatus(){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();

        String url = headUrl + getStatus;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map getVersionInfo(){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();

        String url = headUrl + getVersionInfo;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map setRestartPlugin(){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();

        String url = headUrl + setRestartPlugin;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map cleanDataDir(){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();

        String url = headUrl + cleanDataDir;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }
    public static Map cleanPluginLog(){
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<String, Object>();

        String url = headUrl + cleanPluginLog;

        String result = httpSenderService.send(url, method, params);

        JSONObject resultJson = JSONObject.parseObject(result);
        Map resultMap = (Map) resultJson;

        return resultMap;
    }


}

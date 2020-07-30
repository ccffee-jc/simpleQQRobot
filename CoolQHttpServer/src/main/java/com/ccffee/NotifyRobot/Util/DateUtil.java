package com.ccffee.NotifyRobot.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * author: ccffee
     * last change: 2020/5/14
     *
     * 判断今天是星期几
     * @return 返回一个1-7之间的值，代表今天是星期几
     */
    public static int checkTimeOfWeek(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date time = new Date();
        cal.setTime(time);
        int w = cal.get(Calendar.DAY_OF_WEEK)-1;
        if( w == 0) w=7;

        return w;

    }

    /**
     * author: ccffee
     * last change: 2020/5/14
     *
     * 判断今天是否为工作日
     * @return
     */
    public static boolean checkTimeForWork(){
        if (checkTimeOfWeek() < 6)
            return true;
        else return false;
    }

    /**
     * author: ccffee
     * last change: 2020/5/15
     *
     * 返回yyyy/MM/dd格式的日期字符串
     * @param time 对应的日期对象
     * @return
     */
    public static String getTimeString_yyyyMMdd(Date time){
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        return f.format(time);
    }

    /**
     * author: ccffee
     * last change: 2020/5/15
     *
     * 返回HHdd格式的日期字符串
     * @param time 对应的日期对象
     * @return
     */
    public static String getTimeString_HHdd(Date time){
        SimpleDateFormat f = new SimpleDateFormat("HH.mm");
        return f.format(time);
    }

    /**
     * author: ccffee
     * last change: 2020/5/15
     *
     * 检测是否为yyyy/MM/dd格式，若否则返回空，若是则返回修正过格式的string字符串
     * @param time
     * @return
     */
    public static String checkCan2yyyyMMdd(String time){
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        Date date;
        try {
            date = f.parse(time);
        } catch (ParseException e) {
            return null;
        }
        return f.format(date);

    }

    public static void main(String[] args){
        System.out.println(checkCan2yyyyMMdd("2020/5/15/10"));
    }

}

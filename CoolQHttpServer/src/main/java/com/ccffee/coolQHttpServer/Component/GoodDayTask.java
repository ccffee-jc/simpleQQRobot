package com.ccffee.coolQHttpServer.Component;

import com.ccffee.coolQHttpServer.common.CommonPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GoodDayTask {

    private Logger LOGGER = LoggerFactory.getLogger(GoodDayTask.class);

    @Scheduled(cron = "0 0 8 ? * ?")
    private void goodMorning(){
        CommonPost.sendGroupMsg("764290285", "早上好鸭");
        LOGGER.info("send 764290285:早上好鸭");
    }

    @Scheduled(cron = "0 15 12 ? * ?")
    private void goodNoon(){
        CommonPost.sendGroupMsg("764290285", "中午好鸭，吃了吗？");
        LOGGER.info("send 764290285:中午好鸭，吃了吗？");
    }

    @Scheduled(cron = "0 0 15 ? * ?")
    private void goodAfternoon(){
        CommonPost.sendGroupMsg("764290285", "下午好鸭");
        LOGGER.info("send 764290285:下午好鸭");
    }

    @Scheduled(cron = "0 0 19 ? * ?")
    private void goodEvening(){
        CommonPost.sendGroupMsg("764290285", "晚上好鸭");
        LOGGER.info("send 764290285:晚上好鸭");
    }

}

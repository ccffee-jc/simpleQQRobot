package com.ccffee.coolQHttpServer.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class CqTime implements Serializable {
    @ApiModelProperty(value = "时间表id")
    private Integer id;

    @ApiModelProperty(value = "时间表 日单位")
    private String timeday;

    @ApiModelProperty(value = "时间表 时单位")
    private String timehour;

    @ApiModelProperty(value = "任务表id")
    private Integer taskid;

    @ApiModelProperty(value = "1启用 0弃用")
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimeday() {
        return timeday;
    }

    public void setTimeday(String timeday) {
        this.timeday = timeday;
    }

    public String getTimehour() {
        return timehour;
    }

    public void setTimehour(String timehour) {
        this.timehour = timehour;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", timeday=").append(timeday);
        sb.append(", timehour=").append(timehour);
        sb.append(", taskid=").append(taskid);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
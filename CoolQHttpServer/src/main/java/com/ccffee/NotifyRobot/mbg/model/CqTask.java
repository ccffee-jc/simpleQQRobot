package com.ccffee.NotifyRobot.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class CqTask implements Serializable {
    @ApiModelProperty(value = "任务表id")
    private Integer id;

    @ApiModelProperty(value = "发送对象的id")
    private Integer targeid;

    @ApiModelProperty(value = "对象的类型：user获取group")
    private String targetype;

    @ApiModelProperty(value = "发送的消息")
    private String message;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTargeid() {
        return targeid;
    }

    public void setTargeid(Integer targeid) {
        this.targeid = targeid;
    }

    public String getTargetype() {
        return targetype;
    }

    public void setTargetype(String targetype) {
        this.targetype = targetype;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", targeid=").append(targeid);
        sb.append(", targetype=").append(targetype);
        sb.append(", message=").append(message);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
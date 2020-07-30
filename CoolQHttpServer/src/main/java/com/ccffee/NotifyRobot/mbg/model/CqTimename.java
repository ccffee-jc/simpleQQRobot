package com.ccffee.NotifyRobot.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class CqTimename implements Serializable {
    @ApiModelProperty(value = "时间名称表id（此表有的时间名称才可用来记录时间）")
    private Integer id;

    @ApiModelProperty(value = "时间名称")
    private String timename;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimename() {
        return timename;
    }

    public void setTimename(String timename) {
        this.timename = timename;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", timename=").append(timename);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
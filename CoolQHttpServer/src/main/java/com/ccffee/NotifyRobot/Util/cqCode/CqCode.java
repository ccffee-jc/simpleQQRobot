package com.ccffee.NotifyRobot.Util.cqCode;

import lombok.Data;

import java.util.HashMap;

@Data
public class CqCode {
    private Cq CQ;
    private HashMap<String, String> param;
}

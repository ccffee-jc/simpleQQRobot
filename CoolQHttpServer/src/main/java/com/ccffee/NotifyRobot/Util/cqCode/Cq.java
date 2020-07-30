package com.ccffee.NotifyRobot.Util.cqCode;

public enum Cq {
    AT("at"),IMAGE("image")
    ;

    private String cq;

    Cq(String cq) {
        this.cq = cq;
    }

    public String getCq() {
        return cq;
    }

    public void setCq(String cq) {
        this.cq = cq;
    }
}

package com.ccffee.coolQHttpServer.service;

import java.util.HashMap;

public interface CqMessageATRobotService {
    HashMap messageDistributor(String message, String groupId, String userId);
}

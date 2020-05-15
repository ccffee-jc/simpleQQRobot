package com.ccffee.coolQHttpServer.service;

public interface CqUserAutoTaskService {
    int addTask(String type, String day, String hour, String message, String targerNum);
}

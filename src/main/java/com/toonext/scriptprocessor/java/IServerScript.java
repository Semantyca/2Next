package com.toonext.scriptprocessor.java;


import com.toonext.UserSession;
import com.toonext.domain.AppEnv;
import com.toonext.scriptprocessor.ServerTaskOutcome;

public interface IServerScript {
    void setSession(UserSession ses, AppEnv env);

    void setOutcome(ServerTaskOutcome outcome);

    ServerTaskOutcome processCode();

    String getName();

}

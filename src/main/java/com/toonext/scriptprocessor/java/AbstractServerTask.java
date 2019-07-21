package com.toonext.scriptprocessor.java;


import com.toonext.UserSession;
import com.toonext.domain.AppEnv;

import com.toonext.scriptprocessor.InfoMessageType;
import com.toonext.scriptprocessor.ScriptHelper;
import com.toonext.scriptprocessor.ServerTaskOutcome;

public abstract class AbstractServerTask extends ScriptHelper implements IServerScript {
    protected ServerTaskOutcome outcome;


    @Override
    public ServerTaskOutcome processCode() {
        doTask(getCurrentAppEnv(), getSes());
        return outcome;
    }

    @Override
    public void setSession(UserSession ses, AppEnv env) {
        super.setSession(ses, env);
    }

    @Override
    public void setOutcome(ServerTaskOutcome outcome) {
        this.outcome = outcome;

    }

    protected void setError(Exception e) {
        outcome.setType(InfoMessageType.SERVER_TASK_ERROR);
        outcome.setException(e);
    }



    @Override
    public String getName() {
        return this.getClass().getSimpleName().toLowerCase();
    }

    public abstract void doTask(AppEnv appEnv, UserSession session);

}

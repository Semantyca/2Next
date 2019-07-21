package com.toonext.scriptprocessor;


import com.toonext.Environment;
import com.toonext.domain.AppEnv;
import com.toonext.EnvConst;

import com.toonext.log.Lg;
import com.toonext.scriptprocessor.java.IServerScript;

import java.util.HashMap;
import java.util.Map;

public class TaskRunner {
    protected Map<ServerTaskClass, AppEnv> tasks = new HashMap<ServerTaskClass, AppEnv>();

    public ServerTaskOutcome processCode(String taskCommand, AppEnv env) {
        ServerTaskClass sc = TasksHelper.getTaskClass(taskCommand);
        return processCode(sc, env);
    }

    public ServerTaskOutcome processCode(ServerTaskClass sc) {
        Package pack = sc.getInitializerClass().getPackage();
        String app = pack.getName();
        AppEnv env = null;
        if ((EnvConst.MAIN_PACKAGE + ".task").equals(app)) {
            env = Environment.getAppEnvByAlias(EnvConst.ADMINISTRATOR_MODULE_NAME);
        } else {
            env = Environment.getAppEnvByAlias(app.substring(0, app.indexOf(".")));
        }
        return processCode(sc, env);
    }

    public ServerTaskOutcome processCode(ServerTaskClass sc, AppEnv env) {
        ServerTaskOutcome outcome = new ServerTaskOutcome();
        try {
            IServerScript myObject = sc.getInitializerClass().newInstance();
            outcome.setName(myObject.getName());
            myObject.setOutcome(outcome);
         //       myObject.setSession(new UserSession(new UserDAO().findAdministrator()), env);
             return myObject.processCode();
        } catch (Exception e) {
            Lg.exception(e);
            outcome.setException(e);
            outcome.setType(InfoMessageType.SERVER_TASK_ERROR);
            return outcome;
        }
    }

    public void addTask(ServerTaskClass sc, AppEnv env) {
        tasks.put(sc, env);
    }

    public Map<ServerTaskClass, AppEnv> getTasks() {
        return tasks;
    }


}

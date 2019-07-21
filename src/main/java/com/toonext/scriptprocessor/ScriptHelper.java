package com.toonext.scriptprocessor;


import com.toonext.Environment;
import com.toonext.UserSession;
import com.toonext.domain.AppEnv;
import com.toonext.log.Lg;


public class ScriptHelper {
    private AppEnv appEnv;
    private UserSession session;


    protected static void doTasks(AppEnv appEnv, String...tasks){
        for (String task : tasks) {
            Lg.info(">>run task " + task);
            ServerTaskOutcome outcome = new TaskRunner().processCode(task, appEnv);
            if (outcome.getType() != InfoMessageType.OK) {
                Lg.error(outcome.getException());
            }
        }
    }

    public UserSession getSes() {
        return session;
    }

    public void setSession(UserSession ses, AppEnv appEnv) {
        this.session = ses;
        this.appEnv = appEnv;
    }

    protected AppEnv getCurrentAppEnv() {
        return appEnv;
    }


    public static void devPrint(Object text) {
        if (Environment.isDevMode()) {
            System.out.println(text.toString());
        }
    }

    public static void println(Object text) {
        System.out.println(text.toString());
    }

    public static void log(String text) {
        Lg.info(text);
    }

    public static void logError(Exception e) {
        Lg.exception(e);
    }


}

package com.toonext.domain;


import com.toonext.EnvConst;
import com.toonext.Environment;
import com.toonext.dataengine.IDatabase;
import com.toonext.log.Lg;
import com.toonext.scriptprocessor.ServerTaskClass;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class AppEnv {
    private static final String[] extensions = {"groovy"};
    public boolean isValid;
    public String appName;
    public String appCode;
 //   private Vocabulary vocabulary;
    private IDatabase dataBase;
    private String modulesPath;
    private String webResPath;
    private String defaultPage;
    private List<ServerTaskClass> min5Tasks = new ArrayList<ServerTaskClass>();
    private List<ServerTaskClass> hourTasks = new ArrayList<ServerTaskClass>();
    private List<ServerTaskClass> nightTasks = new ArrayList<ServerTaskClass>();
    private List<ServerTaskClass> mondayTasks = new ArrayList<ServerTaskClass>();


    public AppEnv(String n, IDatabase db) {
        appName = n;
       // appCode = (String) ReflectionUtil.getAppConstValue(appName, "CODE");
        this.dataBase = db;

        if (Environment.isDevMode()) {
            if (EnvConst.ADMINISTRATOR_MODULE_NAME.equals(appName)) {
                modulesPath = Environment.getKernelDir() + "modules";
                webResPath = Environment.getKernelDir() + EnvConst.WEB_APPS_FOLDER + File.separator + appName;
            } else if (ArrayUtils.contains(EnvConst.OFFICEFRAME_APPLICATION_MODULES, appName)) {
                modulesPath = Environment.getOfficeFrameDir() + "modules";
                webResPath = Environment.getOfficeFrameDir() + EnvConst.WEB_APPS_FOLDER + File.separator + appName;
                Lg.debug("Server using  \"" + appName + "\" as external module (path=" + Environment.getOfficeFrameDir() + ")");
            } else {
                modulesPath = "modules";
                webResPath = EnvConst.WEB_APPS_FOLDER + File.separator + appName;
            }
        } else {
            modulesPath = "modules";
            webResPath = EnvConst.WEB_APPS_FOLDER + File.separator + appName;
        }


        modulesPath += File.separator + appName;


     //   loadTemplateSet();
     //   compileScenarios();

       // vocabulary = new Vocabulary(this);
    }


  /*  public Vocabulary getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }*/

    public IDatabase getDataBase() {
        return dataBase;
    }

    @Override
    public String toString() {
        return "[ ]" +  "-" + appName;
    }

    public String getModulesPath() {
        return modulesPath;
    }

    public String getWebAppsPath() {
        return webResPath;
    }

    public String getPackageName() {
        return appName.toLowerCase();
    }

    public String getURL() {
        return  appName;
    }



    public String getDefaultPage() {
        return defaultPage;
    }

    public void setDefaultPage(String defaultPage) {
        this.defaultPage = defaultPage;
    }

    public void addMin5Task(ServerTaskClass task) {
        min5Tasks.add(task);
    }

    public void addHourTask(ServerTaskClass task) {
        hourTasks.add(task);
    }

    public void addNightTask(ServerTaskClass task) {
        nightTasks.add(task);
    }

    public void addDayOfWeekTask(ServerTaskClass task, DayOfWeek dayOfWeek) {
        mondayTasks.add(task);
    }

    public List<ServerTaskClass> getMin5Tasks() {
        return min5Tasks;
    }

    public List<ServerTaskClass> getHourTasks() {
        return hourTasks;
    }

    public List<ServerTaskClass> getNightTasks() {
        return nightTasks;
    }

}

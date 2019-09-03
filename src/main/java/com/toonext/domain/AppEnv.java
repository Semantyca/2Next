package com.toonext.domain;


import com.toonext.EnvConst;
import com.toonext.GlobalEnv;
import com.toonext.dataengine.IDatabase;
import com.toonext.log.Lg;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;

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


    public AppEnv(String n, IDatabase db) {
        appName = n;
       // appCode = (String) ReflectionUtil.getAppConstValue(appName, "CODE");
        this.dataBase = db;

        if (GlobalEnv.isDevMode()) {
            if (EnvConst.ADMINISTRATOR_MODULE_NAME.equals(appName)) {
                modulesPath = GlobalEnv.getKernelDir() + "modules";
                webResPath = GlobalEnv.getKernelDir() + EnvConst.WEB_APPS_FOLDER + File.separator + appName;
            } else if (ArrayUtils.contains(EnvConst.OFFICEFRAME_APPLICATION_MODULES, appName)) {
                modulesPath = GlobalEnv.getOfficeFrameDir() + "modules";
                webResPath = GlobalEnv.getOfficeFrameDir() + EnvConst.WEB_APPS_FOLDER + File.separator + appName;
                Lg.debug("Server using  \"" + appName + "\" as external module (path=" + GlobalEnv.getOfficeFrameDir() + ")");
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

}

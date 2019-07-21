package com.toonext;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toonext.domain.IUser;
import com.toonext.localization.constants.LanguageCode;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UserSession {
    private IUser user;
    private LanguageCode lang = EnvConst.getDefaultLang();
    private int pageSize = EnvConst.DEFAULT_PAGE_SIZE;
    private Map<String, Object> valuesMap = new HashMap<>();
    private String token;

    public UserSession(IUser user) {
        this.user = user;
    }



    public LanguageCode getLang() {
        return lang;
    }

    public String getLangAltCode() {
        return this.lang.getAlternateCode();
    }

    public IUser getUser() {
        return user;
    }



    @Override
    public String toString() {
        return user.getUserID() + ", lang=" + lang;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int ps) {
        pageSize = ps;
    }


    public void setAttribute(String varName, Object fn) {
        if (!valuesMap.containsKey(varName)) {
            valuesMap.put(varName, fn);
        }
    }

    public void setAttribute(String varName, Object fn, TimeUnit time) {
        if (!valuesMap.containsKey(varName)) {
            valuesMap.put(varName, fn);
        }
    }

    @JsonIgnore
    public Object getAttribute(String varName) {
        return valuesMap.get(varName);
    }

    public void removeAttribute(String varName) {
        valuesMap.remove(varName);
    }

    @JsonIgnore
    public Map<String, Object> getAttributes() {
        return valuesMap;
    }

    @JsonIgnore
    public File getTmpDir() {
        File userTmpDir = new File(Environment.tmpDir + File.separator + getUser().getUserID());
        if (!userTmpDir.exists()) {
            userTmpDir.mkdir();
        }
        return userTmpDir;
    }

}

package com.toonext.core.api;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toonext.EnvConst;
import com.toonext.core.api.constants.UserStatusCode;
import com.toonext.domain.user.IUser;
import com.toonext.localization.constants.LanguageCode;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements IUser, Principal {

    private long id;

    protected Date regDate;

    protected Integer timeZone;

    private UserStatusCode status = UserStatusCode.UNKNOWN;

    private List<Long> substitutes = new ArrayList();

    private List<String> roles = new ArrayList();

    private String login;

    private String email = "";

    private boolean sendPwd;

    private String pwd;

    private String pwdConfirm;

    private String pwdHash;

    private List userApplications = new ArrayList();

    private LanguageCode defaultLang = EnvConst.getDefaultLang();

    private boolean isSuperUser;

    @JsonIgnore
    private boolean isAuthorized;

    private String theme = "";

/*

    @ConstructorProperties({ "login" })
    public User( String login) {
        //this.id = id;
        this.login = login;
    }
*/


    public Date getRegDate() {
        return regDate;
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {

    }

    @Override
    public String getPwdHash() {
        return null;
    }

    @Override
    public void setPwdHash(String pwdHash) {

    }

    @Override
    public String getPwd() {
        return null;
    }

    @Override
    public void setPwd(String value) {

    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String string) {

    }

    @JsonIgnore
    @Override
    public boolean isAuthorized() {
        return false;
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {

    }

    @Override
    public String getUserID() {
        return Long.toString(id);
    }

    @Override
    public String getUserName() {
        return login;
    }

    @Override
    public void setUserName(String name) {

    }

    @Override
    public boolean isSuperUser() {
        return false;
    }

    @Override
    public List<String> getRoles() {
        return null;
    }

    @Override
    public void setRoles(List<String> allRoles) {

    }

    @Override
    public LanguageCode getDefaultLang() {
        return null;
    }

    @Override
    public void setDefaultLang(LanguageCode defaultLang) {

    }

    @Override
    public boolean isAllowed(String appName) {
        return false;
    }

    @Override
    public void setEditable(boolean b) {

    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public void setEmail(String value) {

    }

    @Override
    public String getSlack() {
        return null;
    }

    @Override
    public void setRegDate(Date date) {

    }

    @Override
    public String getExtKey() {
        return null;
    }

    @Override
    public void setExtKey(String extKey) {

    }

    @Override
    public List<Long> getSubstitutes() {
        return null;
    }

    @Override
    public void setSubstitutes(List<Long> substitutes) {

    }


    @Override
    public String getName() {
        return login;
    }
}

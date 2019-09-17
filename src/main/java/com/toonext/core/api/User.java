package com.toonext.core.api;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toonext.EnvConst;
import com.toonext.api.IUser;
import com.toonext.constants.LanguageCode;
import com.toonext.constants.UserStatusCode;
import com.toonext.util.StringUtil;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import javax.security.auth.Subject;
import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class User implements IUser, Principal {

    private long id;

    @ColumnName("reg_date")
    private ZonedDateTime regDate;

    @ColumnName("last_mod_date")
    private ZonedDateTime lastModifiedDate;

    private String title;

    private long author;

    protected Integer timeZone;

    private UserStatusCode status = UserStatusCode.UNKNOWN;

    private List<Long> substitutes = new ArrayList<>();

    private List<String> roles = new ArrayList<>();

    private String login;

    private String email = "";

    private boolean sendPwd;

    private String pwd;

    private String pwdConfirm;

    @ColumnName("pwd_hash")
    private String pwdHash;

    @ColumnName("last_mod_user")
    private Long lastModifier;

    private List userApplications = new ArrayList<>();

    private LanguageCode defaultLang = EnvConst.getDefaultLang();

    private boolean isSuperUser;

    private boolean isAuthorized;

    public ZonedDateTime getRegDate() {
        return regDate;
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {

    }

    public void setId(long id) {
        this.id = id;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getTitle() {
        if (title == null){
            return login;
        }else {
            return title;
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public Integer getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(Integer timeZone) {
        this.timeZone = timeZone;
    }

    public UserStatusCode getStatus() {
        return status;
    }

    public void setStatus(UserStatusCode status) {
        this.status = status;
    }

    public boolean isSendPwd() {
        return sendPwd;
    }

    public void setSendPwd(boolean sendPwd) {
        this.sendPwd = sendPwd;
    }

    public String getPwdConfirm() {
        return pwdConfirm;
    }

    public void setPwdConfirm(String pwdConfirm) {
        this.pwdConfirm = pwdConfirm;
    }

    public Long getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(Long lastModifier) {
        this.lastModifier = lastModifier;
    }

    public List getUserApplications() {
        return userApplications;
    }

    public void setUserApplications(List userApplications) {
        this.userApplications = userApplications;
    }

    public void setSuperUser(boolean superUser) {
        isSuperUser = superUser;
    }

    @Override
    public String getPwdHash() {
        return pwdHash;
    }

    @Override
    public void setPwd(String value) {
        pwd = value;
        pwdHash = StringUtil.encode(pwd);
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean isAuthorized() {
        return false;
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    @Override
    public boolean isSuperUser() {
        return false;
    }

    @Override
    public boolean isAnonymous() {
        return false;
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }

    @Override
    public void setRoles(List<String> allRoles) {
        this.roles = roles;
    }

    public void addRole(String role) {
        roles.add(role);
    }

    @Override
    public void setDefaultLang(LanguageCode defaultLang) {
        this.defaultLang = defaultLang;
    }

    @Override
    public LanguageCode getDefaultLang() {
        return defaultLang;
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
    public void setRegDate(ZonedDateTime date) {
        regDate = date;
    }

    @Override
    @JsonIgnore
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

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}

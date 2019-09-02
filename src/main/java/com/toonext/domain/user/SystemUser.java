package com.toonext.domain.user;


import com.toonext.core.api.constants.UserStatusCode;
import com.toonext.localization.constants.LanguageCode;

import javax.security.auth.Subject;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kayra on 17/03/16.
 */

public abstract class SystemUser implements IUser {

    @Override
    public String getPwdHash() {
        return null;
    }

    @Override
    public void setPwd(String value) {

    }

    @Override
    public boolean isAuthorized() {
        return true;
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {

    }

    @Override
    public abstract String getUserID();

    @Override
    public abstract String getUserName();

    @Override
    public void setUserName(String name) {

    }

    @Override
    public boolean isSuperUser() {
        return false;
    }

    @Override
    public boolean isAllowed(String app) {
        return false;
    }


    @Override
    public List<String> getRoles() {
        return new ArrayList<>();
    }

    @Override
    public void setRoles(List<String> allRoles) {

    }

    @Override
    public void addRole(String role) {

    }


    @Override
    public LanguageCode getDefaultLang() {
        return LanguageCode.ENG;
    }

    @Override
    public void setDefaultLang(LanguageCode defaultLang) {

    }

    @Override
    public abstract Long getId();

    @Override
    public void setId(Long id) {

    }

    @Override
    public abstract String getLogin();

    @Override
    public void setLogin(String string) {

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
    public void setRegDate(ZonedDateTime date) {

    }

    public UserStatusCode getStatus() {
        return UserStatusCode.REGISTERED;
    }

    public void setStatus(UserStatusCode status) {

    }


    @Override
    public List<Long> getSubstitutes() {
        return new ArrayList<>();
    }

    @Override
    public void setSubstitutes(List<Long> substitutes) {

    }

    @Override
    public abstract String getName();

    @Override
    public boolean implies(Subject subject) {
        return false;
    }

}

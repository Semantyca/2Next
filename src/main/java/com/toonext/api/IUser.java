package com.toonext.api;

import com.toonext.constants.LanguageCode;
import com.toonext.constants.UserStatusCode;

import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.List;


public interface IUser extends ISimpleAppEntity<Long>, Principal {

    Long getId();

    String getPwdHash();

    void setPwd(String value);

    String getLogin();

    void setLogin(String string);

    boolean isAuthorized();

    void setAuthorized(boolean isAuthorized);

    boolean isSuperUser();

    default boolean isAnonymous(){
        return false;
    };

    List<String> getRoles();

    void setRoles(List<String> allRoles);

    void addRole(String role);

    LanguageCode getDefaultLang();

    void setDefaultLang(LanguageCode defaultLang);

    boolean isAllowed(String appName);

    void setEditable(boolean b);

    String getEmail();

    void setEmail(String value);

    void setRegDate(ZonedDateTime date);

    UserStatusCode getStatus();

    void setStatus(UserStatusCode status);

    List<Long> getSubstitutes();

    void setSubstitutes(List<Long> substitutes);


}

package com.toonext.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.toonext.localization.constants.LanguageCode;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@JsonRootName("user")
public interface IUser extends ISimpleAppEntity<Long>, Principal {

    Long getId();

    @JsonIgnore
    String getPwdHash();

    void setPwdHash(String pwdHash);

    @JsonIgnore
    String getPwd();

    void setPwd(String value);

    String getLogin();

    void setLogin(String string);

    boolean isAuthorized();

    void setAuthorized(boolean isAuthorized);

    String getUserID();

    String getUserName();

    void setUserName(String name);



    boolean isSuperUser();

    @JsonIgnore
    default boolean isAnonymous(){
        return false;
    };


    List<String> getRoles();

    void setRoles(List<String> allRoles);

    LanguageCode getDefaultLang();

    void setDefaultLang(LanguageCode defaultLang);


    boolean isAllowed(String appName);

    void setEditable(boolean b);

    String getEmail();

    void setEmail(String value);

    String getSlack();

    void setRegDate(Date date);

  /*  UserStatusCode getStatus();

    void setStatus(UserStatusCode status);*/

    String getExtKey();

    void setExtKey(String extKey);

    List<Long> getSubstitutes();

    void setSubstitutes(List<Long> substitutes);
}

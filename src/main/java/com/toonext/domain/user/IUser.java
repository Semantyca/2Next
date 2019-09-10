package com.toonext.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.toonext.domain.ISimpleAppEntity;
import com.toonext.localization.constants.LanguageCode;

import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.List;

@JsonRootName("user")
public interface IUser extends ISimpleAppEntity<Long>, Principal {

    Long getId();

    @JsonIgnore
    String getPwdHash();

    void setPwd(String value);

    String getLogin();

    void setLogin(String string);

    boolean isAuthorized();

    void setAuthorized(boolean isAuthorized);

    boolean isSuperUser();

    @JsonIgnore
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

  /*  UserStatusCode getStatus();

    void setStatus(UserStatusCode status);*/

    List<Long> getSubstitutes();

    void setSubstitutes(List<Long> substitutes);


}

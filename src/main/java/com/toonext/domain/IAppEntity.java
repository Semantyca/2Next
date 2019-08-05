package com.toonext.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toonext.domain.user.IUser;

import java.util.Date;

public interface IAppEntity<K> extends ISimpleAppEntity<K> {

    IUser getAuthor();

    @JsonIgnore
    void setAuthor(IUser user);


    void setLastModifier(IUser user);

    Date getRegDate();

    boolean isEditable();

    void setEditable(boolean isEditable);

    default boolean wasRead(){
        return true;
    }

    String getTitle();

    String getURL();

    void setTitle(String title);

    default String getEntityType() {
        return this.getClass().getSimpleName();
    }

    boolean isNew();



}

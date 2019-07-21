package com.toonext.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

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

    default String getEntityKind() {
        return this.getClass().getSimpleName();
    }

    boolean isNew();


    default List getExtAttachments() {
        return null;
    }

}

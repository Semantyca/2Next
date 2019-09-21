package com.toonext.api;

import java.time.ZonedDateTime;

public interface IAppEntity<K> extends ISimpleAppEntity<K> {

    long getAuthor();

    ZonedDateTime getRegDate();

    boolean isEditable();

    default boolean wasRead(){
        return true;
    }

    String getTitle();

    void setTitle(String title);

    default String getEntityType() {
        return this.getClass().getSimpleName();
    }

    boolean isNew();
}

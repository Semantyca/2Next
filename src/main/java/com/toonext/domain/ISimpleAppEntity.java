package com.toonext.domain;

public interface ISimpleAppEntity<K> extends IModel {

    K getId();

    void setId(K id);

}

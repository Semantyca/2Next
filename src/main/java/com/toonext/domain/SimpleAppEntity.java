package com.toonext.domain;



public abstract class SimpleAppEntity implements ISimpleAppEntity<Long> {

    protected Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getKind() {
        return getClass().getSimpleName();
    }
}

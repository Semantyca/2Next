package com.semantyca.entity;

import javax.inject.Inject;

import com.semantyca.entity.transport.Payload;

public class UiPage {
    private String id = "undefined";
    private String type = "page";

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public void setWindowTitle(final String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(final Payload payload) {
        this.payload = payload;
    }

    private String windowTitle = "Welcome to Semantyca";
    private Payload payload;

    @Inject
    public UiPage(Payload payload) {
        this.payload = payload;
    }
}

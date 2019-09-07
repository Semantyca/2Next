package com.toonext.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.toonext.EnvConst;
import com.toonext.UserSession;
import com.toonext.domain.IAppEntity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@JsonPropertyOrder({"type", "title", "pageName", "payload"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Outcome {
    protected OutcomeType type = OutcomeType.DEFAULT;
    protected String title = EnvConst.APP_ID;
    protected String pageName;
    protected LinkedHashMap<String, Object> payload = new LinkedHashMap<>();

    public Outcome() {

    }

    public void setType(OutcomeType type) {
        this.type = type;
    }

    public OutcomeType getType() {
        return type;
    }

    public LinkedHashMap<String, Object> getPayload() {
        return payload;
    }

    public Outcome setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Outcome addPayload(String name, String val) {
        this.payload.put(name, val);
        return this;
    }

    public Outcome addPayload(List val) {
        this.payload.put("list", val);
        return this;
    }

    public Outcome addPayload(String name, Collection val) {
        this.payload.put(name, val);
        return this;
    }

    public Outcome addPayload(IAppEntity entity) {
        this.payload.put(entity.getEntityType(), entity);
        return this;
    }

    public Outcome addPayload(UserSession session) {
        this.payload.put("user_session", session);
        return this;
    }

    public Outcome addPayload(UUID uuid) {
        this.payload.put("uuid", uuid);
        return this;
    }

    public String toString() {
        return "type=" + type + ", title=" + title + " " + payload;
    }



}

package com.toonext.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.toonext.EnvConst;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@JsonPropertyOrder({"id", "title", "message", "payload"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Outcome {
    public final static String UNDEFINED_ID = "undefined";

    protected List<String> message = new ArrayList<>();
    protected String id = UNDEFINED_ID;
    protected String title = EnvConst.APP_ID;
    protected LinkedHashMap<String, Object> payload = new LinkedHashMap<>();

    public Outcome() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Outcome setTitle(String title) {
        this.title = title;
        return this;
    }

    public Outcome setMessage(String msg) {
        message.clear();
        this.message.add(msg);
        return this;
    }

    public Outcome addPayload(Object val) {
        this.payload.put(val.getClass().getSimpleName().toLowerCase(), val);
        return this;
    }


    public Outcome setError(String msg){
        setId("error");
        setTitle(msg);
        setMessage(msg);
        return this;
    }

    public String toString() {
        return "id=" + id + ", title=" + title + " " + payload;
    }
}

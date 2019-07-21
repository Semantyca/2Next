package com.toonext.scriptprocessor;


public class ServerTaskOutcome {
    public String name;
    private InfoMessageType type = InfoMessageType.OK;
    private Exception exception;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InfoMessageType getType() {
        return type;
    }

    public void setType(InfoMessageType type) {
        this.type = type;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        type = InfoMessageType.SERVER_TASK_ERROR;
        this.exception = exception;
    }

}

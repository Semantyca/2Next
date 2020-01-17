package com.semantyca.entity.transport;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import com.semantyca.entity.User;
import com.semantyca.entity.traits.Signed;

@Default
public class Message extends Signed {
    private String event;
    private User user;
    private Payload payload;

    @Inject
    public Message(Payload payload) {
        this.payload = payload;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(final String event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(final Payload payload) {
        this.payload = payload;
    }
}

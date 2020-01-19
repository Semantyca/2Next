package com.semantyca.transport;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import com.semantyca.entity.SemantycaUser;

import com.semantyca.traits.Signed;

@Default
@RequestScoped
@Named
public class SeMessage extends Signed {
    private String event;
    private SemantycaUser user;
    private SePayload sePayload;

    @Inject
    public SeMessage(SePayload sePayload, SemantycaUser user) {
        this.sePayload = sePayload;
        this.user = user;
    }

    public SeMessage() {

    }

    public SeMessage(SePayload sePayload) {
        this.sePayload = sePayload;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(final String event) {
        this.event = event;
    }

    public SemantycaUser getUser() {
        return user;
    }

    public void setUser(final SemantycaUser user) {
        this.user = user;
    }

    public SePayload getSePayload() {
        return sePayload;
    }

    public void setSePayload(final SePayload sePayload) {
        this.sePayload = sePayload;
    }
}

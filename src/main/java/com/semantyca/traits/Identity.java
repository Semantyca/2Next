package com.semantyca.traits;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;
import javax.persistence.Id;


public interface Identity {


    public String getId();

    public void setId(final String id);


}

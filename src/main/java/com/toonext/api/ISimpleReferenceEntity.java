package com.toonext.api;

import java.util.UUID;

public interface ISimpleReferenceEntity extends IAppEntity<UUID> {
    void setIdentifer(String val);
}

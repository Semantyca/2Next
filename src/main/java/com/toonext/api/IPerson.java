package com.toonext.api;


import java.util.List;
import java.util.UUID;


public interface IPerson {

    UUID getId();

    Long getUserID();

    String getName();

    List<String> getAllRoles();

    IUser getUser();

}

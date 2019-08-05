package com.toonext.domain.user;


import java.util.List;
import java.util.UUID;


public interface IPerson {

    UUID getId();

    Long getUserID();

    String getName();

    List<String> getAllRoles();

    IUser getUser();

}

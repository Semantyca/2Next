package com.toonext.domain;

import java.util.UUID;

public interface IOfficeFrame {

    void init();

    IPerson getEmployee(long id);

    IRole createRole();

    boolean saveRole(IRole role);

    IPerson findById(UUID id);

    String getUserName(IUser user);


}

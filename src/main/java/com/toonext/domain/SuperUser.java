package com.toonext.domain;

/**
 * @author Kayra on 17/03/16.
 */

public class SuperUser extends SystemUser {
    public final static long ID = -1;
    public static String USER_NAME = "supervisor";

    @Override
    public String getUserID() {
        return USER_NAME;
    }

    @Override
    public String getUserName() {
        return USER_NAME;
    }

    @Override
    public void setUserName(String name) {
        USER_NAME = name;
    }

    @Override
    public Long getId() {
        return (long) ID;
    }

    @Override
    public String getLogin() {
        return USER_NAME;
    }

    @Override
    public boolean isAllowed(String app) {
        return true;
    }

    @Override
    public boolean isSuperUser() {
        return true;
    }

    @Override
    public String getName() {
        return USER_NAME;
    }
}

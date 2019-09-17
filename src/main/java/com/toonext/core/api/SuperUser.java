package com.toonext.core.api;

import com.toonext.adapter.ServerRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kayra on 17/03/16.
 */

public class SuperUser extends SystemUser {
    public final static long ID = -1;
    public final static String USER_NAME = "supervisor";

    @Override
    public List<String> getRoles() {
        return new ArrayList<>(Arrays.asList(ServerRole.AUTHENTICATED));
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

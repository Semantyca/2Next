package com.toonext.core.jdbi;


import com.toonext.UserSession;
import com.toonext.core.api.Application;

import java.util.ArrayList;
import java.util.List;

public class ModuleDAO {
    public ModuleDAO(UserSession ses) {
    }

    public List<Application> findAllActivated() {
        return new ArrayList<Application>();
    }
}

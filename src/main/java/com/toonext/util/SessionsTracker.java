package com.toonext.util;

import com.toonext.UserSession;

import java.util.HashMap;

public class SessionsTracker {
    private static HashMap<Long, UserSession> sessions = new HashMap<Long, UserSession>();

    public static void addSession(UserSession userSession){
        sessions.put(userSession.getUser().getId(), userSession);
    }

    public static UserSession getSession(long id){
       return sessions.get(id);
    }

}

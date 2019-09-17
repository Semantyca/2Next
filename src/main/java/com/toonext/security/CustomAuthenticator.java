package com.toonext.security;

import com.toonext.UserSession;

import com.toonext.adapter.ServerRole;
import com.toonext.api.IUser;
import com.toonext.core.api.AnonymousUser;
import com.toonext.core.dao.IUserDAO;
import com.toonext.util.SessionsTracker;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.util.Optional;

public class CustomAuthenticator implements Authenticator<CustomCredentials, IUser> {
    private IUserDAO userDAO;

    public CustomAuthenticator(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<IUser> authenticate(CustomCredentials credentials) throws AuthenticationException {
        IUser authenticatedUser = null;
        if (!credentials.isValid()) {
            authenticatedUser = new AnonymousUser();
        } else {
            IUser user = userDAO.findById(credentials.getUserId());
            if (user != null) {
                user.addRole(ServerRole.AUTHENTICATED);
                SessionsTracker.addSession(new UserSession(user));
                authenticatedUser = user;
            }
        }
        return Optional.ofNullable(authenticatedUser);
    }
}

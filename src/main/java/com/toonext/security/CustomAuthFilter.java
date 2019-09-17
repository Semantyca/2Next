package com.toonext.security;

import com.toonext.core.api.Token;
import com.toonext.core.dao.ITokenDAO;
import com.toonext.log.Lg;
import io.dropwizard.auth.AuthFilter;
import org.jdbi.v3.core.Jdbi;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.PreMatching;
import java.security.Principal;

@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class CustomAuthFilter<P extends Principal> extends AuthFilter<CustomCredentials, P> {
  protected  Jdbi dbi;

  protected CustomAuthFilter(Jdbi jdbi){
      super();
      this.dbi = jdbi;
  }


  public void filter(ContainerRequestContext requestContext) {
      CustomCredentials credentials = getCredentials(requestContext);
      authenticate(requestContext, credentials, "custom");
  }


  private CustomCredentials getCredentials(ContainerRequestContext requestContext) {
    CustomCredentials credentials = new CustomCredentials();
    try {
      String tokenString = requestContext.getHeaderString("Authorization");
      if (tokenString != null) {
        ITokenDAO dao = dbi.onDemand(ITokenDAO.class);
        Token token = dao.findToken(tokenString.substring(6));
        if (token != null) {
          credentials.setToken(token.getToken());
          credentials.setUserId(token.getUserId());
          credentials.setValid(true);
        }
      }else{
        Lg.warning("There is no token trace");
      }
    } catch (Exception e) {
      Lg.exception(e);
    }
    return credentials;
  }

  public static class Builder<P extends Principal> extends AuthFilterBuilder<CustomCredentials, P, CustomAuthFilter<P>> {
    private static Jdbi dbi;

    public Builder(Jdbi dbi) {
      this.dbi = dbi;
    }

    protected CustomAuthFilter<P> newInstance() {
      return new CustomAuthFilter(dbi);
    }
  }
}

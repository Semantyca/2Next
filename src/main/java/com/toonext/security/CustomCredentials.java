package com.toonext.security;


import com.toonext.core.api.AnonymousUser;

public class CustomCredentials {
  private String token = "0";
  private Long userId = AnonymousUser.ID;
  private boolean isValid;

 public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getUserId() {
    return userId;
  }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}

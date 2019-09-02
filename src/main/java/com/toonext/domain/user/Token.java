package com.toonext.domain.user;

import org.jdbi.v3.core.mapper.reflect.ColumnName;
import java.time.ZonedDateTime;

public class Token {

    private long id;

    @ColumnName("user_id")
    private long userId;

    @ColumnName("reg_date")
    private ZonedDateTime regDate;

    private String token;

    @ColumnName("expiration_time")
    private ZonedDateTime expirationTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public ZonedDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(ZonedDateTime regDate) {
        this.regDate = regDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ZonedDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(ZonedDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}

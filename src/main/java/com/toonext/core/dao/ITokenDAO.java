package com.toonext.core.dao;

import com.toonext.domain.user.Token;
import org.jdbi.v3.sqlobject.config.RegisterFieldMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.ZonedDateTime;


public interface ITokenDAO {

    @SqlQuery("SELECT * FROM public._tokens WHERE user_id = :userId")
    @RegisterFieldMapper(Token.class)
    Token findTokenForUser(@Bind("tokenString") long userId);


    @SqlQuery("SELECT * FROM public._tokens WHERE token = :tokenString")
    @RegisterFieldMapper(Token.class)
    Token findToken(@Bind("tokenString") String tokenString);


    @SqlUpdate("INSERT INTO public._tokens(user_id, reg_date, token, expiration_time)VALUES (?,?,?,?);")
    @GetGeneratedKeys("id")
    Long insert(long userId, ZonedDateTime regDate, String token, ZonedDateTime expirationTime);

    @SqlUpdate("DELETE FROM public._tokens WHERE token = :tokenString")
    void delete(@Bind("tokenString") String tokenString);


    @SqlUpdate("\n" +
            "CREATE TABLE_FULL_NAME public._tokens" +
            "(" +
            "  id SERIAL PRIMARY KEY," +
            "  user_id bigint NOT NULL," +
            "  reg_date timestamp with time zone NOT NULL," +
            "  token character varying(255) NOT NULL," +
            "  expiration_time timestamp with time zone" +
            ");")
    void createTable();


}

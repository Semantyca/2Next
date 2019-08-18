package com.toonext.core.jdbi;


import com.toonext.core.api.User;
import com.toonext.domain.user.IUser;
import org.jdbi.v3.sqlobject.config.RegisterFieldMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface IUserDAO {

    @SqlUpdate("\n" +
            "CREATE TABLE public._users\n" +
            "(\n" +
            "  id SERIAL PRIMARY KEY,\n" +
            "  login character varying(100),\n" +
            "  pwd character varying(50),\n" +
            "  pwd_hash character varying(100),\n" +
            "  last_mod_date timestamp without time zone NOT NULL,\n" +
            "  last_mod_user bigint NOT NULL,\n" +
            "  reg_date timestamp without time zone NOT NULL,\n" +
            "  title character varying(255),\n" +
            "  author bigint NOT NULL\n" +
            ");")
    void createTable();

    @SqlUpdate("INSERT INTO public._users(login, pwd, pwd_hash, last_mod_date, last_mod_user, \n" +
            "reg_date, title, author)VALUES (?,?,?,?,?,?,?,?);")
    @GetGeneratedKeys("id")
    Long insert(String login, String pwd, String pwdHash, ZonedDateTime lastModificationTime, long lastModificationUser, ZonedDateTime regDate, String title, long author);

    @SqlUpdate("INSERT INTO public._users(id, login, pwd, pwd_hash, last_mod_date, last_mod_user, \n" +
            "reg_date, title, author)VALUES (?,?,?,?,?,?,?,?,?);")
    @GetGeneratedKeys("id")
    Long insert(long id, String login, String pwd, String pwdHash, ZonedDateTime lastModificationTime, long lastModificationUser, ZonedDateTime regDate, String title, long author);


    @SqlUpdate("insert into something (id, name) values (:id, :name)")
    void insert(@Bind("id") int id, @Bind("name") String name);


    @SqlQuery("SELECT * FROM public._users WHERE login = :login")
    @RegisterFieldMapper(User.class)
    User findNameByLogin(@Bind("login") String login);

    @SqlQuery("SELECT * FROM public._users WHERE id = :id")
    @RegisterFieldMapper(User.class)
    IUser findById(@Bind("id") long login);

    @SqlQuery("SELECT * FROM public._users;")
    @RegisterFieldMapper(User.class)
    List<User> findAll();

    @SqlUpdate("DROP TABLE public._langs CASCADE;")
    void drop();

}

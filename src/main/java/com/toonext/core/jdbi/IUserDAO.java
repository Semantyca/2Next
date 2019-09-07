package com.toonext.core.jdbi;


import com.toonext.core.api.User;
import org.jdbi.v3.sqlobject.config.RegisterFieldMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.ZonedDateTime;
import java.util.List;

public interface IUserDAO {

    @SqlQuery("SELECT * FROM public._users;")
    @RegisterFieldMapper(User.class)
    List<User> findAll();

    @SqlQuery("SELECT * FROM public._users WHERE id = :id")
    @RegisterFieldMapper(User.class)
    User findById(@Bind("id") long login);

    @SqlUpdate(
            "CREATE TABLE_FULL_NAME public._users" +
            "(" +
            "  id SERIAL PRIMARY KEY," +
            "  login character varying(100) UNIQUE," +
            "  pwd character varying(50)," +
            "  pwd_hash character varying(100)," +
            "  last_mod_date timestamp with time zone NOT NULL," +
            "  last_mod_user bigint NOT NULL," +
            "  reg_date timestamp with time zone NOT NULL," +
            "  title character varying(255)," +
            "  author bigint NOT NULL" +
            ");")
    void createTable();


    @SqlUpdate("insert into public._users (login, pwd_hash, last_mod_date, last_mod_user, reg_date, title, author) values (:getLogin, :getPwdHash, :getLastModifiedDate, :getLastModifier, :getRegDate, :getTitle, :getAuthor)")
    void insert(@BindMethods User user);

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




    @SqlUpdate("DROP TABLE_FULL_NAME public._langs CASCADE;")
    void drop();

}

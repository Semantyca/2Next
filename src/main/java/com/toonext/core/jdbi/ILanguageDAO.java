package com.toonext.core.jdbi;


import com.toonext.localization.constants.LanguageCode;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.ZonedDateTime;
import java.util.UUID;

public interface ILanguageDAO {

    @SqlUpdate("CREATE TABLE public._langs\n" +
            "(\n" +
            "  id uuid DEFAULT uuid_generate_v4(),\n" +
            "  code character varying(7),\n" +
            "  is_cyr boolean,\n" +
            "  is_on boolean,\n" +
            "  last_mod_date timestamp without time zone NOT NULL,\n" +
            "  last_mod_user bigint NOT NULL,\n" +
            "  loc_name jsonb,\n" +
            "  name character varying(255),\n" +
            "  stance integer,\n" +
            "  reg_date timestamp without time zone NOT NULL,\n" +
            "  title character varying(255),\n" +
            "  author bigint NOT NULL,\n" +
            "  CONSTRAINT _langs_pkey PRIMARY KEY (id),\n" +
            "  CONSTRAINT fk__langs_author FOREIGN KEY (author)\n" +
            "      REFERENCES public._users (id) MATCH SIMPLE\n" +
            "      ON UPDATE NO ACTION ON DELETE NO ACTION,\n" +
            "  CONSTRAINT _langs_code_key UNIQUE (code)\n" +
            ");")
    void createTable();


    @SqlUpdate("INSERT INTO public._langs(code, is_cyr, is_on, last_mod_date, last_mod_user, \n" +
            "loc_name, name, stance, reg_date, title, author)VALUES (?,?,?,?,?,?::json,?,?,?,?,?);")
    @GetGeneratedKeys("id")
    UUID insert(LanguageCode code, boolean isCyrillic, boolean isOn, ZonedDateTime dateTime, long user, @BindJson("loc_name") String locName, String name, int stance,
                ZonedDateTime regDate, String title, long author);

    @SqlUpdate("insert into something (id, name) values (:id, :name)")
    void insert(@Bind("id") int id, @Bind("name") String name);

    @SqlQuery("select name from something where id = :id")
    String findNameById(@Bind("id") int id);

    @SqlUpdate("DROP TABLE public._langs CASCADE;")
    void drop();
}

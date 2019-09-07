package com.toonext.core.jdbi;


import com.toonext.core.api.Language;
import com.toonext.localization.constants.LanguageCode;
import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface ILanguageDAO {

    @SqlQuery("SELECT * FROM public._langs;")
    @RegisterColumnMapper(LanguageMapper.class)
    List<Language> findAll();

    @SqlQuery("SELECT * FROM public._langs as l WHERE l.is_on = TRUE")
    @RegisterColumnMapper(LanguageMapper.class)
    List<Language> findAllActivated();

    @SqlUpdate("CREATE TABLE_FULL_NAME public._langs" +
            "(" +
            "  id uuid DEFAULT uuid_generate_v4()," +
            "  code character varying(7)," +
            "  is_cyr boolean," +
            "  is_on boolean," +
            "  last_mod_date timestamp with time zone NOT NULL," +
            "  last_mod_user bigint NOT NULL," +
            "  loc_name jsonb," +
            "  identifier character varying(64)," +
            "  stance integer," +
            "  reg_date timestamp with time zone NOT NULL," +
            "  title character varying(255)," +
            "  author bigint NOT NULL," +
            "  CONSTRAINT _langs_pkey PRIMARY KEY (id)," +
            "  CONSTRAINT fk__langs_author FOREIGN KEY (author)" +
            "      REFERENCES public._users (id) MATCH SIMPLE" +
            "      ON UPDATE NO ACTION ON DELETE NO ACTION," +
            "  CONSTRAINT _langs_code_key UNIQUE (code)" +
            ");")
    void createTable();


    @SqlUpdate("INSERT INTO public._langs(code, is_cyr, is_on, last_mod_date, last_mod_user," +
            "loc_name, identifier, stance, reg_date, title, author)VALUES (?,?,?,?,?,?::json,?,?,?,?,?);")
    @GetGeneratedKeys("id")
    UUID insert(LanguageCode code, boolean isCyrillic, boolean isOn, ZonedDateTime dateTime, long user, @BindJson("loc_name") String locName, String name, int stance,
                ZonedDateTime regDate, String title, long author);

    @SqlUpdate("insert into something (id, name) values (:id, :name)")
    void insert(@Bind("id") int id, @Bind("name") String name);

    @SqlQuery("select name from something where id = :id")
    String findNameById(@Bind("id") int id);

    @SqlUpdate("DROP TABLE_FULL_NAME public._langs CASCADE;")
    void drop();

}

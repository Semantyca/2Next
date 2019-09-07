package com.toonext.core.dao;

public interface IDAO {
    String SCHEMA = "public";

    String ENTITY_DDL_PIECE =
            "  id uuid DEFAULT uuid_generate_v4()," +
            "  reg_date timestamp with time zone NOT NULL," +
            "  title character varying(255)," +
            "  author bigint NOT NULL," +
            "  last_mod_date timestamp with time zone NOT NULL," +
            "  last_mod_user bigint NOT NULL,";

    String MANDATORY_REF_DDL_PIECE =
            "  loc_name jsonb," +
            "  identifier character varying(64) UNIQUE," +
               ENTITY_DDL_PIECE +
            "  CONSTRAINT _pkey PRIMARY KEY(id)," +
            "  CONSTRAINT fk_author FOREIGN KEY(author) REFERENCES public._users (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION";


}

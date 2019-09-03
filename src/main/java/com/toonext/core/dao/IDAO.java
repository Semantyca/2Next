package com.toonext.core.dao;

public interface IDAO {

    final static String MANDATORY_REF_DDL_PIECE =
            "  id uuid DEFAULT uuid_generate_v4()," +
            "  loc_name jsonb," +
            "  identifier character varying(64)," +
            "  reg_date timestamp with time zone NOT NULL," +
            "  title character varying(255)," +
            "  author bigint NOT NULL," +
            "  last_mod_date timestamp with time zone NOT NULL," +
            "  last_mod_user bigint NOT NULL," +
            "  CONSTRAINT _pkey PRIMARY KEY(id)," +
            "  CONSTRAINT fk_author FOREIGN KEY(author) REFERENCES public._users (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION";


}

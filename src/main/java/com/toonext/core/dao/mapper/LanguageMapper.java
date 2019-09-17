package com.toonext.core.dao.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.toonext.constants.LanguageCode;
import com.toonext.core.api.Language;
import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.postgresql.util.PGobject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LanguageMapper implements ColumnMapper<Language> {
    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public Language map(ResultSet rs, int columnNumber, StatementContext ctx) throws SQLException {
        Language language = new Language();
        language.setId(rs.getObject("id", UUID.class));
        language.setCode(LanguageCode.valueOf(rs.getString("code")));
        language.setIsCyrillic(rs.getBoolean("is_cyr"));
        language.setIsOn(rs.getBoolean("is_on"));
        language.setLastModifiedDate(getDateTime(rs.getTimestamp("last_mod_date")));
        language.setLastModifier(rs.getLong("last_mod_user"));
        try {
            TypeFactory typeFactory = mapper.getTypeFactory();
            MapType mapType = typeFactory.constructMapType(Map.class, LanguageCode.class, String.class);
            PGobject po = rs.getObject("loc_name", PGobject.class);
            language.setLocName(mapper.readValue(po.getValue(), mapType));
        } catch (Exception e) {
            language.setLocName(new HashMap());
        }
        language.setIdentifier(rs.getString("identifier"));
        language.setStance(rs.getInt("stance"));
        language.setRegDate(getDateTime(rs.getTimestamp("reg_date")));
        language.setTitle(rs.getString("title"));
        language.setAuthor(rs.getLong("author"));
       return language;
    }

    @Override
    public Language map(ResultSet r, String columnLabel, StatementContext ctx) throws SQLException {
        return null;
    }

    private static ZonedDateTime getDateTime(Timestamp timestamp) {
        return timestamp != null ? ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp.getTime()), ZoneOffset.UTC) : null;
    }

}

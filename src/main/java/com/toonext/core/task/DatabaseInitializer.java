package com.toonext.core.task;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.toonext.core.api.Language;
import com.toonext.core.jdbi.ILanguageDAO;
import com.toonext.core.jdbi.IUserDAO;
import com.toonext.domain.user.AnonymousUser;
import com.toonext.domain.user.SuperUser;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;

import java.io.InputStream;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class DatabaseInitializer extends CommonTask {
    private final Jdbi dbi;


    public DatabaseInitializer(Jdbi jdbi) {
        super("init_db");
        this.dbi = jdbi;
    }


    @Override
    public void execute(Map<String, List<String>> map, PrintWriter printWriter) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        IUserDAO userDAO = dbi.onDemand(IUserDAO.class);
        try {
            userDAO.createTable();
        }catch (UnableToExecuteStatementException e){
            logDatabaseException(e, "42P07");
        }

        try {
            userDAO.insert(SuperUser.ID, SuperUser.USER_NAME, "12345", "", ZonedDateTime.now(), SuperUser.ID, ZonedDateTime.now(), SuperUser.USER_NAME, SuperUser.ID);
        }catch (UnableToExecuteStatementException e){
            logDatabaseException(e, "23505");
        }

        try{
            userDAO.insert(AnonymousUser.ID, AnonymousUser.USER_NAME, "12345", "", ZonedDateTime.now(), SuperUser.ID, ZonedDateTime.now(), AnonymousUser.USER_NAME, SuperUser.ID);
        }catch (UnableToExecuteStatementException e){
            logDatabaseException(e, "23505");
        }

        ILanguageDAO dao = dbi.onDemand(ILanguageDAO.class);
        try {
            dao.createTable();
        }catch (UnableToExecuteStatementException e){
            logDatabaseException(e, "42P07");
        }


        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        yamlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Language> languages = new ArrayList<>();
        InputStream is = DatabaseInitializer.class.getResourceAsStream("/initData.yml");
        JsonNode rootNode = yamlMapper.readTree(is);
        JsonNode drNode = rootNode.path("languages");
        Iterator<JsonNode> itr = drNode.elements();
        while (itr.hasNext()) {
            Language language = yamlMapper.treeToValue(itr.next(), Language.class);
            languages.add(language);

            String jsonText = jsonMapper.writeValueAsString(language.getLocName());
            try {
                dao.insert(language.getCode(), language.isCyrillic(), language.isOn(), ZonedDateTime.now(), SuperUser.ID, jsonText, language.getIdentifier(),
                        language.getStance(), ZonedDateTime.now(), "title", SuperUser.ID);
            }catch (UnableToExecuteStatementException e){
                logDatabaseException(e, "23505");
            }
        }

    }

}

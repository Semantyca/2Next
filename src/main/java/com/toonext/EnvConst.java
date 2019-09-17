package com.toonext;

import com.toonext.constants.LanguageCode;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author Kaira created 23-01-2016
 */

public class EnvConst {
    public static final String SERVER_VERSION = "3.3.4";
    public static final String FRAMEWORK_NAME = "nb";
    public static final String FRAMEWORK_LOGO = "nextbase_logo_small.png";
    public static final String MAIN_PACKAGE = "com.toonext";
    public static final String SUPPOSED_CODE_PAGE = "utf-8";
    public static final String DEFAULT_XML_ENC = "utf-8";
    public static final String APP_ATTR = "app";
    public static final String DEFAULT_PAGE = "index";
    public final static String SESSION_ATTR = "usersession";
    public static final String TEMP_FILES_CONTAINER = "temp_files";
    public static final int LANG_COOKIE_LIFETIME = 31536000;
    public static final String PAGE_SIZE_COOKIE_NAME = "pagesize";
    public static final String ADMINISTRATOR_MODULE_NAME = "Administrator";
    public final static String SHARED_RESOURCES_APP_NAME = "SharedResources";
    public static final String ERROR_XSLT = "error.xsl";
    public static final String FSID_FIELD_NAME = "fsid";
    public static final String TIME_FIELD_NAME = "time";
    public static final String SPARE_SCHEMA = "spare";
    public static final int DEFAULT_HTTP_PORT = 38700;
 //   public static final String OFFICEFRAME_APPLICATION_FOLDER_NAME = AvailableApplicationTypes.OFFICEFRAME.name().toLowerCase();
    public static final String WORKSPACE_MODULE_NAME = "Workspace";
    public static final String STAFF_MODULE_NAME = "Staff";
    public static final String REFERENCE_MODULE_NAME = "Reference";
    public static final String MONITORING__MODULE_NAME = "Monitoring";
    public static final String MONITORING_MODULE_DAO_CLASS = "monitoring.dao.ActivityRecorder";
    public static final String[] OFFICEFRAME_APPLICATION_MODULES_MINIMAL = {STAFF_MODULE_NAME, REFERENCE_MODULE_NAME, WORKSPACE_MODULE_NAME, MONITORING__MODULE_NAME};
    public static final String[] OFFICEFRAME_APPLICATION_MODULES = ArrayUtils.addAll(OFFICEFRAME_APPLICATION_MODULES_MINIMAL, "Integration", "DataExport",
            MONITORING__MODULE_NAME, "Discussing", "Calendar");
    public static final String WEB_APPS_FOLDER = "webapps";
    // https://en.wikipedia.org/wiki/List_of_ISO_639-2_codes
    public static final String[] DEFAULT_LANGS = {"ENG", "RUS", "KAZ"};
    public static final String DEFAULT_COLOR = "#FFFFFF";
    public static final String DEFAULT_WALLPAPER = "";


    public static String AVAILABLE_THEME[] = {"azul", "cinzento", "branco", "preto", "taraz"};
    public static String APP_MODE = "client";
    public static String NIGHT_TASK_LAUNCH_TIME = "03:01";
    public static String DEVELOPER_EMAIL = "dev@semantyca.com";
    public static String CFG_FILE = "cfg.xml";
    public static int SCHEDULER_START_DELAY = 120;
    public static String DATABASE_SCHEMA_UPDATE = "OFF";
    public static String REST_PREFIX = "/api";
    public static int TIME_ZONE = 0;
    public static String CLI = "ON";
    public static String APP_ID = Paths.get(System.getProperty("user.dir")).getFileName().toString();
    public static String DEAFAULT_APP_LOGO = "/" + EnvConst.SHARED_RESOURCES_APP_NAME + "/logos/" + FRAMEWORK_LOGO;
    public static String AUTH_COOKIE_NAME = APP_ID + "nb3ses";
    public static String CALLING_PAGE_COOKIE_NAME = APP_ID + "cp";
    public static String LANG_COOKIE_NAME = APP_ID + "lang";
    public static String DUMMY_USER = "admin";
    public static String DUMMY_PASSWORD = "secret";
    public static String JPA_LOG_LEVEL = "OFF";
    public static String DEFAULT_LANG = DEFAULT_LANGS[0];
    public static String DEFAULT_MODULE = WORKSPACE_MODULE_NAME;
    public static String WELCOME_MODULE = "";
    public static int DEFAULT_PAGE_SIZE = 20;
    public static int DEFAULT_HTTP_SESSION_TIMEOUT = -1;
    public static String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";
    public static String DEFAULT_DATETIME_FORMAT = "dd.MM.yyyy kk:mm";
    public static String DEFAULT_TIME_FORMAT = "kk:mm";
    public static String DEFAULT_COUNTRY_OF_NUMBER_FORMAT = "ru";
    public static final String DB_TYPE = "postgresql";
    public static String DB_USER = "postgres";
    public static String DB_PWD = "12345";
    public static String DB_TEMPLATE = "";
    public static String DATABASE_NAME = APP_ID;
    public static String DATABASE_HOST = "127.0.0.1";
    public static String CONN_PORT = "5432";
    public static final String RESOURCES_DIR = "resources";
    public static String BACKUP_DIR = RESOURCES_DIR + File.separator + "backup";
    public static String DEFAULT_TRANSLATOR_ENGINE = "yandex";
    public static String DEFAULT_MAP_SERVICE_ENGINE = "google";
    public static String DEFAULT_WEATHER_SERVICE_ENGINE = "openweather.org";

    public static LanguageCode getDefaultLang() {
        return LanguageCode.valueOf(EnvConst.DEFAULT_LANG);
    }

    public static final LanguageCode[] getDefaultLangs(){
        return Arrays.stream(DEFAULT_LANGS).map((v)-> LanguageCode.valueOf(v)).toArray(LanguageCode[]::new);
    }
}

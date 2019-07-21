package com.toonext.core.cli;


import com.toonext.log.ILogger;
import org.hibernate.validator.internal.util.Version;
import org.joda.time.DateTime;

import java.io.File;
import java.lang.reflect.Field;

public class Info {

    @SuppressWarnings("static-access")
    public static void showServerInfo() {
        //System.out.printf(Console.format, "server version", EnvConst.SERVER_VERSION);
        System.out.printf(Console.format, "os",
                System.getProperty("os.version") + "(" + System.getProperty("os.arch") + ")");
        System.out.printf(Console.format, "jvm", System.getProperty("java.version"));
      //  System.out.printf(Console.format, "status", Server.state);
        DateTime now = DateTime.now();
      //  Minutes mins = Minutes.minutesBetween(new DateTime(Environment.startTime), now);
   //     System.out.printf(Console.format, "started at", TimeUtil.dateToStringSilently(Environment.startTime)
   //             + ", duration=" + TimeUtil.timeConvert(mins.getMinutes()));
   /*     System.out.printf(Console.format, "web host name", Environment.getHostName());
        System.out.printf(Console.format, "web port", Environment.httpPort);
        System.out.printf(Console.format, "web virtual host name", Environment.getVirtualHostName());
        String timeZone = Integer.toString(EnvConst.TIME_ZONE);
        if (EnvConst.TIME_ZONE > 0) {
            timeZone = "+" + timeZone;
        }*/
   //     System.out.printf(Console.format, "server timezone", timeZone);
        System.out.printf(Console.format, "server directory", new File("").getAbsolutePath());
     /*   System.out.printf(Console.format, "database version", Environment.database.getVersion());
        System.out.printf(Console.format, "database name", EnvConst.DATABASE_NAME);
        System.out.printf(Console.format, "database", Environment.adminApplication.getDataBase().getInfo());*/
        System.out.printf(Console.format, "JPA", Version.getVersionString());
      /*  System.out.printf(Console.format, "web server host", Environment.getFullHostName());
        System.out.printf(Console.format, "default language", EnvConst.DEFAULT_LANG);*/
      /*  System.out.printf(Console.format, "utility database URI",
                ((UtilityDatabase) Environment.utilityDatabase).getJDBCURI());
        System.out.printf(Console.format, "session cookie name", EnvConst.AUTH_COOKIE_NAME);

        try {
            LanguageDAO dao = new LanguageDAO();
            System.out.printf(Console.format, "languages", dao.findAll().stream().map(v -> v.getCode().name()).collect(Collectors.joining(",")));
        } catch (Exception e) {
            System.err.printf(Console.format, "languages", "error during of getting a information");
        }
        if (Environment.translatorEnable) {
            System.out.printf(Console.format, "translator", "ON" + " (" + EnvConst.DEFAULT_TRANSLATOR_ENGINE + ")");
        } else {
            System.out.printf(Console.format, "translator", "OFF");
        }
        if (Environment.elasticSearchEnable) {
            System.out.printf(Console.format, "ElasticSearch", "ON");
        } else {
            System.out.printf(Console.format, "ElasticSearch", "OFF");
        }
        if (Environment.mapServiceEnable) {
            System.out.printf(Console.format, "map service", "ON" + " (" + EnvConst.DEFAULT_MAP_SERVICE_ENGINE + ")");
        } else {
            System.out.printf(Console.format, "map service", "OFF");
        }

        if (Environment.weatherServiceEnable) {
            System.out.printf(Console.format, "weather service", "ON" + " (" + EnvConst.DEFAULT_WEATHER_SERVICE_ENGINE + ", locality=" + Environment.weatherServiceLocality + ")");
        } else {
            System.out.printf(Console.format, "weather service", "OFF");
        }*/

        File file = new File(File.separator);
        long totalSpace = file.getTotalSpace();
        long freeSpace = file.getFreeSpace();
        System.out.printf(Console.format, "total disk size", totalSpace / 1024 / 1024 / 1024 + " gb");
        System.out.printf(Console.format, "space free", freeSpace / 1024 / 1024 / 1024 + " gb");
      /*  if (Environment.mailEnable) {
            System.out.printf(Console.format, "mail agent", "ON");
        } else {
            System.out.printf(Console.format, "mail agent", "OFF");
        }*/
    /*    System.out.printf(Console.format, "smtp port", Environment.smtpPort);
        System.out.printf(Console.format, "smtp auth", Environment.smtpAuth);
        System.out.printf(Console.format, "smtp server", Environment.smtpHost);
        System.out.printf(Console.format, "smtp user", Environment.smtpUser);
        System.out.printf(Console.format, "smtp user name", Environment.smtpUserName);
        if (Environment.isDevMode()) {
            System.out.printf(Console.format, "developer mode", "ON");
        } else {
            System.out.printf(Console.format, "developer mode", "OFF");
        }
        System.out.printf(Console.format, "external server core folder", Environment.getKernelDir());
        System.out.printf(Console.format, "external " + EnvConst.OFFICEFRAME_APPLICATION_FOLDER_NAME + " folder",
                Environment.getOfficeFrameDir());

        System.out.printf(Console.format, "temporary files", TempFileCleaner.getFileToDelete().size());
    }

    public static String showDatabaseSpeed() {
        double index = Environment.database.checkSpeed();
        return showDatabaseSpeed(index);*/
    }

    public static String showDatabaseSpeed(double index) {
        String addInfo = "";
        if (index < 0.7) {
            addInfo = " (very good)";
        } else if (index > 0.7 && index < 1.5) {
            addInfo = " (good)";
        } else if (index > 1.5 && index < 2.5) {
            addInfo = " (satisfactorily)";
        } else if (index > 2.5 && index < 3.5) {
            addInfo = " (slow)";
        } else if (index > 3.5 && index < 10) {
            addInfo = " (very slow)";
        } else if (index > 10) {
            addInfo = " (extremly slow)";
        }

        return index + addInfo;
    }

    public static void showDatabaseInfo() {
   /*     System.out.println("database " + Environment.adminApplication.getDataBase().getInfo());
        List<String[]> info = Environment.adminApplication.getDataBase().getCountsOfRec();
        System.out.printf(Console.format, "Table", "Count");
        System.out.printf(Console.format, "--------------", "-----");
        for (String[] entry : info) {
            System.out.printf(Console.format, entry[0], entry[1]);
        }
        System.out.printf(Console.format, "            ", "-----");
        System.out.printf(Console.format, "     Total  ", Environment.adminApplication.getDataBase().getCount());*/
    }

    public static void showUsersInfo(boolean verbose) {
    /*    int countOfAuthSess = 0;
        if (verbose) {
            System.out.printf(Console.shortFormat, "User", "Description (from-last activity, context)");
            System.out.printf(Console.shortFormat, "----------", "-----");
            System.out.printf(Console.advancedFormat, "User", "Token", "Context");
            System.out.printf(Console.advancedFormat, "--------------", "-----", "-------");
            for (HttpSession entry : ServerSessionPool.getSessions()) {
                UserSession ses = (UserSession) entry.getAttribute(EnvConst.SESSION_ATTR);
                if (ses != null) {
                    System.out.printf(Console.advancedFormat, ses.getUser().getLogin(), ses.getToken(),
                            entry.getServletContext().getServletContextName());
                    if (!ses.getUser().equals(AnonymousUser.USER_NAME)) {
                        countOfAuthSess++;
                    }
                }

            }
            System.out.printf(Console.shortFormat, "            ", "-----");
            System.out.printf(Console.shortFormat, "     Total actual ",
                    countOfAuthSess + "(" + ServerSessionPool.getSessions().size() + ")");

        } else {
            List<HttpSession> actualUsers = new ArrayList<>();
            for (HttpSession hs : ServerSessionPool.getSessions()) {
                try {
                    hs.getCreationTime();
                    actualUsers.add(hs);
                } catch (IllegalStateException ise) {

                }
            }

            if (actualUsers.size() > 0) {
                System.out.printf(Console.shortFormat, "User", "Description (from-last activity, context)");
                System.out.printf(Console.shortFormat, "----------", "-----");
                for (HttpSession entry : actualUsers) {
                    String firstVal = "", secondVal = "";
                    UserSession ses = (UserSession) entry.getAttribute(EnvConst.SESSION_ATTR);
                    if (ses != null) {
                        firstVal = ses.getUser().getLogin();
                        if (!firstVal.equals(AnonymousUser.USER_NAME)) {
                            secondVal = TimeUtil.DATE_TIME_FORMAT.format(entry.getCreationTime()) + "-"
                                    + TimeUtil.DATE_TIME_FORMAT.format(entry.getLastAccessedTime());
                            countOfAuthSess++;
                            secondVal += " " + entry.getServletContext().getServletContextName();
                            System.out.printf(Console.shortFormat, firstVal, secondVal);
                        }

                    } else {
                        firstVal = entry.getId();
                        firstVal = "from=" + TimeUtil.DATE_TIME_FORMAT.format(entry.getCreationTime())
                                + ", last activity=" + TimeUtil.DATE_TIME_FORMAT.format(entry.getLastAccessedTime());
                    }

                }
                System.out.printf(Console.shortFormat, "            ", "-----");
                System.out.printf(Console.shortFormat, "     Total actual ",
                        countOfAuthSess + "(" + actualUsers.size() + ")");
            } else {
                System.out.println("There is no user sessions still");
            }
        }*/
    }


    private static void showVarInfo(Field field) {
        Class<?> t = field.getType();
        try {
            if (t == int.class) {
                System.out.printf(Console.format, field.getName(), field.getInt(null));
            } else {
                String name = field.getName();
                if (name.contains("PWD") || name.contains("PASSWORD")) {
                    System.out.printf(Console.format, field.getName(), "*****");
                } else {
                    System.out.printf(Console.format, field.getName(), field.get(null));
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {

        }
    }

    public static void showJVMOptions(ILogger logger) {
        logger.info("available processors (cores): " + Runtime.getRuntime().availableProcessors());
        logger.info("free memory (mb): " + Runtime.getRuntime().freeMemory() / 1024 / 1024);
        long maxMemory = Runtime.getRuntime().maxMemory();
        logger.info("maximum memory (mb): " + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory / 1024 / 1024));
        logger.info("total memory (mb): " + Runtime.getRuntime().totalMemory() / 1024 / 1024);
        File[] roots = File.listRoots();
        for (File root : roots) {
            logger.info("file system root: " + root.getAbsolutePath());
            logger.info("total space (mb): " + root.getTotalSpace() / 1024 / 1024 / 1024);
            logger.info("free space (mb): " + root.getFreeSpace() / 1024 / 1024 / 1024);
            logger.info("usable space (mb): " + root.getUsableSpace() / 1024 / 1024 / 1024);
        }
    }

}

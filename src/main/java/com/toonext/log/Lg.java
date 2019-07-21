package com.toonext.log;


import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lg {
    final static LoggerContext factory = (LoggerContext) LoggerFactory.getILoggerFactory();
    final static Logger root = factory.getLogger(Logger.ROOT_LOGGER_NAME);

    public static void info(String logtext) {
        root.info(logtext);    }

    public static void info(String agent, String logtext) {
        root.info(agent, logtext);
    }

    public static void error(String logtext) {
        root.error(logtext);
    }

    public static void error(Exception e) {
        root.error(e.toString());
    }

    public static void error(String process, String logtext) {
        root.error(process, logtext);
    }

    public static void warning(String logtext) {
        root.warn(logtext);
    }

    public static void debug(String logtext) {
        root.debug(logtext);
    }

    public static void debug(String agent, String logtext) {
        root.debug(agent, logtext);
    }

    public static void exception(Exception e) {
        root.error("error",e);
    }

    public static void fatal(String logtext) {
        root.error("fatal error",logtext);
    }


    public static void p(String s) {
        System.out.println(s);
    }

    public static void p(Exception e) {
        System.err.println(e);
    }


}

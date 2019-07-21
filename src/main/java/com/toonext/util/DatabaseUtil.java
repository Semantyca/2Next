package com.toonext.util;



import com.toonext.log.Lg;

import java.sql.SQLException;

public class DatabaseUtil {


    public static void errorPrint(Throwable e) {
        if (e instanceof SQLException) {
            SQLException sqle = (SQLException) e;
            SQLExceptionPrint(sqle);
        } else {
            Lg.error(e.toString());
            e.printStackTrace();
        }
    }

    public static void debugErrorPrint(Throwable e) {
        if (e instanceof SQLException) {
            SQLException sqle = (SQLException) e;
            SQLExceptionPrintDebug(sqle);
        } else {
            Lg.error(e.toString());
            e.printStackTrace();
        }
    }

    public static void errorPrint(Throwable e, String sql) {
        Lg.error(sql);
        if (e instanceof SQLException) {
            SQLException sqle = (SQLException) e;
            SQLExceptionPrintDebug(sqle);
        } else {
            Lg.error(e.toString());
            e.printStackTrace();
        }
    }

    public static void SQLExceptionPrint(SQLException sqle) {
        while (sqle != null) {
            Lg.error("SQLState:   " + sqle.getSQLState());
            Lg.error("Severity: " + sqle.getErrorCode());
            Lg.error("Message:  " + sqle.getMessage());
            sqle = sqle.getNextException();
        }
    }

    public static void SQLExceptionPrintDebug(SQLException sqle) {
        while (sqle != null) {
            Lg.error("SQLState:   " + sqle.getSQLState());
            Lg.error("Severity: " + sqle.getErrorCode());
            Lg.error("Message:  " + sqle.getMessage());
            Lg.exception(sqle);
            sqle.printStackTrace();
            sqle = sqle.getNextException();
        }
    }

}

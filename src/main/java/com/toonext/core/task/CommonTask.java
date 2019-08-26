package com.toonext.core.task;

import com.toonext.log.Lg;
import io.dropwizard.servlets.tasks.Task;
import org.postgresql.util.PSQLException;


public abstract class CommonTask extends Task {

    protected CommonTask(String name) {
        super(name);
    }

    protected CommonTask(String name, String responseContentType) {
        super(name, responseContentType);
    }

    protected void logDatabaseException(Exception e, String warningExcept){
        if (e.getCause() instanceof PSQLException && ((PSQLException)e.getCause()).getSQLState().equals(warningExcept)){
            Lg.warning(e.getCause().getMessage());
        }else {
            Lg.exception(e);
        }
    }
}

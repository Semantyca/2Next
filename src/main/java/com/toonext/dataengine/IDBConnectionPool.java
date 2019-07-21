package com.toonext.dataengine;

import java.sql.Connection;

public interface IDBConnectionPool {
    void initConnectionPool(String driver, String dbURL, String userName, String password)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException;

    void initConnectionPool(String driver, String dbURL)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException;

    Connection getConnection();

    void returnConnection(Connection con);

    int getNumActive();

    public String toXML();

    String getDatabaseVersion();

    void closeAll();

    void close(Connection conn);

}

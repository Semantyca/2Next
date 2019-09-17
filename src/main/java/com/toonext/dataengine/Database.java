package com.toonext.dataengine;


import com.toonext.EnvConst;
import com.toonext.api.IAppEntity;
import com.toonext.api.IUser;

import com.toonext.log.Lg;
import com.toonext.util.DatabaseUtil;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database implements IDatabase {
    public static final String COUNTER_TABLE_NAME = "_counters";
    public static boolean isNascence;
    protected static String connectionURL = "";
    protected IDBConnectionPool dbPool;

    private Properties props = new Properties();

    public Database()  {
        props.setProperty("user", EnvConst.DB_USER);
        props.setProperty("password", EnvConst.DB_PWD);
        String sysDbURL = "jdbc:postgresql://" + EnvConst.DATABASE_HOST + ":" + EnvConst.CONN_PORT + "/postgres";
        try {
            if (!hasDatabase(EnvConst.DATABASE_NAME, sysDbURL, props)) {
                Lg.info(getVersion(sysDbURL));
                Lg.warning("Creating database \"" + EnvConst.DATABASE_NAME + "\"...");
                if (createDatabase(EnvConst.DATABASE_NAME, EnvConst.DB_USER, sysDbURL, props) == 0) {
                    Lg.info("The database has been created");
                    isNascence = true;
                    EnvConst.DATABASE_SCHEMA_UPDATE = "ON";
                }
            }else{
                Lg.info("The " + EnvConst.DATABASE_NAME + " database is exist");
            }
        } catch (SQLException e) {
            Lg.exception(e);
        }

        connectionURL = "jdbc:postgresql://" + EnvConst.DATABASE_HOST + ":" + EnvConst.CONN_PORT + "/" + EnvConst.DATABASE_NAME;
        Lg.debug("Connection=" + connectionURL);

        Jdbi jdbi = Jdbi.create(connectionURL, props).installPlugin(new PostgresPlugin());
        jdbi.useHandle(handle -> {
            handle.execute("create table _users (id int primary key, login varchar(100), pwd varchar(50), pwdHash varchar(100))");
            handle.execute("insert into _users (id, login, pwd, pwdHash) values (?, ?, ?, ?)", 1, "alice","123", "");
            handle.execute("insert into _users (id, login, pwd, pwdHash) values (?, ?, ?, ?)", 2, "bob" ,"123", "");
        });


    }


    public int createDatabase(String dbName, String dbUser, String dbURL, Properties prop) throws SQLException {
        return 1;
    }

    @Override
    public String toString() {
        return "version NB3";
    }


    private boolean hasDatabase(String dbName, String dbURL, Properties prop) throws SQLException {
        Jdbi jdbi = Jdbi.create(dbURL, prop).installPlugin(new PostgresPlugin());
        try {
            List<String> names = jdbi.withHandle(handle ->
                    handle.createQuery("SELECT 1 FROM pg_database WHERE datname = '" + dbName + "'")
                            .mapTo(String.class)
                            .list());

            if (names.size() > 0) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            return false;
        }
    }

    @Override
    public int getRegNum(String key) {
        int lastNum = 1;
        Connection conn = dbPool.getConnection();
        try {
            conn.setAutoCommit(false);
            String sql = "select * from " + COUNTER_TABLE_NAME + " where KEYS='" + key + "'";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            String keyValue = "";
            if (rs.next()) {
                keyValue = rs.getString("KEYS");
                lastNum = rs.getInt("LASTNUM");
            }
            if (keyValue != "") {
                lastNum++;
            }
            rs.close();
            pst.close();
            conn.commit();
        } catch (SQLException e) {
            DatabaseUtil.errorPrint(e);
        } finally {
            dbPool.returnConnection(conn);
        }
        return lastNum;
    }

    @SuppressWarnings("resource")
    @Override
    public int postRegNum(int num, String key) {
        int lastNum = 0;
        boolean isExists = false;
        Connection conn = dbPool.getConnection();
        try {
            conn.setAutoCommit(false);
            String sql = "select *from " + COUNTER_TABLE_NAME + " where KEYS='" + key + "'";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                lastNum = rs.getInt("LASTNUM");
                isExists = true;
            }
            rs.close();
            String getNum = null;
            conn.setAutoCommit(false);
            if (isExists) {
                getNum = "update " + COUNTER_TABLE_NAME + " set LASTNUM = ? where KEYS = ? ";
                pst = conn.prepareStatement(getNum);
                lastNum++;
                pst.setInt(1, num);
                pst.setString(2, key);
            } else {
                getNum = "insert into " + COUNTER_TABLE_NAME + "(KEYS, LASTNUM)values(?,?)";
                pst = conn.prepareStatement(getNum);
                pst.setString(1, key);
                pst.setInt(2, num);
            }
            pst.executeUpdate();
            conn.commit();
            pst.close();
        } catch (SQLException e) {
            DatabaseUtil.errorPrint(e);
            lastNum = -1;
        } finally {
            dbPool.returnConnection(conn);
        }
        return lastNum;
    }

    public int markAsRead(IUser user, IAppEntity entity) {
       return 0;
    }


    @Override
    public String getInfo() {
        return "url=" + connectionURL;
    }

    @Override
    public double checkSpeed() {
        double difference = 0;
        return difference;
    }

    @Override
    public String getVersion() {
        return getVersion(connectionURL);
    }

    @Override
    public String getVersion(String sysDbURL) {
        String version = "unknown";
        try {
            Connection conn = DriverManager.getConnection(sysDbURL, props);
            conn.setAutoCommit(false);
            Statement s = conn.createStatement();
            String sql = "SELECT version()";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                version = rs.getString(1);
            }
            s.close();
            conn.commit();

        } catch (Throwable e) {
            DatabaseUtil.debugErrorPrint(e);
        }
        return version;
    }

    @Override
    public List<String[]> getCountsOfRec() {
        List<String[]> result = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(connectionURL, props);
            conn.setAutoCommit(false);
            Statement s = conn.createStatement();
            String sql = "SELECT relname,n_live_tup FROM pg_stat_user_tables ORDER BY relname ASC;";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String[] a = {rs.getString(1), Long.toString(rs.getLong(2))};
                result.add(a);
            }
            s.close();
            conn.commit();
        } catch (Throwable e) {
            DatabaseUtil.debugErrorPrint(e);
        }
        return result;
    }

    @Override
    public long getCount() {
        int countOfRecords = 0;

        try {
            Connection conn = DriverManager.getConnection(connectionURL, props);
            conn.setAutoCommit(false);
            Statement s = conn.createStatement();
            String sql = "SELECT sum(n_live_tup) FROM pg_stat_user_tables";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                countOfRecords = rs.getInt(1);
            }
            s.close();
            conn.commit();
        } catch (Throwable e) {
            DatabaseUtil.debugErrorPrint(e);
        }
        return countOfRecords;
    }

}

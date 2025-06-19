package com.immortalman01.randomevents.bbdd.runnables;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;

import com.immortalman01.randomevents.bbdd.callback.Callback;

public class RunnableConstructorTest {

    private static class DummyDataSource implements DataSource {
        @Override
        public Connection getConnection() throws SQLException { return null; }
        @Override
        public Connection getConnection(String username, String password) throws SQLException { return null; }
        @Override
        public PrintWriter getLogWriter() throws SQLException { return null; }
        @Override
        public void setLogWriter(PrintWriter out) throws SQLException { }
        @Override
        public void setLoginTimeout(int seconds) throws SQLException { }
        @Override
        public int getLoginTimeout() throws SQLException { return 0; }
        @Override
        public Logger getParentLogger() { return Logger.getGlobal(); }
        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException { throw new SQLException("unwrap"); }
        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException { return false; }
    }

    private static final DataSource VALID_DATASOURCE = new DummyDataSource();
    @SuppressWarnings("rawtypes")
    private static final Callback DUMMY_CALLBACK = (result, thrown) -> {};

    @Test
    public void queryRequiresDataSource() {
        assertThrows(IllegalArgumentException.class,
            () -> new QueryBukkitRunnable(null, "SELECT 1", (Callback<ResultSet, SQLException>) DUMMY_CALLBACK));
    }

    @Test
    public void queryRequiresStatement() {
        assertThrows(IllegalArgumentException.class,
            () -> new QueryBukkitRunnable(VALID_DATASOURCE, null, (Callback<ResultSet, SQLException>) DUMMY_CALLBACK));
    }

    @Test
    public void queryRequiresCallback() {
        assertThrows(IllegalArgumentException.class,
            () -> new QueryBukkitRunnable(VALID_DATASOURCE, "SELECT 1", null));
    }

    @Test
    public void executeRequiresDataSource() {
        assertThrows(IllegalArgumentException.class,
            () -> new ExecuteBukkitRunnable(null, "", null));
    }

    @Test
    public void executeRequiresStatement() {
        assertThrows(IllegalArgumentException.class,
            () -> new ExecuteBukkitRunnable(VALID_DATASOURCE, null, null));
    }

    @Test
    public void updateRequiresDataSource() {
        assertThrows(IllegalArgumentException.class,
            () -> new UpdateBukkitRunnable(null, "", null));
    }

    @Test
    public void updateRequiresStatement() {
        assertThrows(IllegalArgumentException.class,
            () -> new UpdateBukkitRunnable(VALID_DATASOURCE, null, null));
    }

    @Test
    public void connectionRequiresDataSource() {
        assertThrows(IllegalArgumentException.class,
            () -> new ConnectionBukkitRunnable(null, (Callback<Connection, SQLException>) DUMMY_CALLBACK));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void connectionRequiresCallback() {
        assertThrows(IllegalArgumentException.class,
            () -> new ConnectionBukkitRunnable(VALID_DATASOURCE, null));
    }
}

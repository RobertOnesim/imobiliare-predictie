package ro.uaic.info.data_mining.database_controller;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Suhani on 27\04\16.
 */
public class DatabaseConnectionTest {
    @Test
    public void SingletonTest() throws Exception {
        DatabaseConnection firstConnection = DatabaseConnection.getInstance();
        DatabaseConnection secondConnection = DatabaseConnection.getInstance();

        assertEquals(firstConnection, secondConnection);
    }

    @Test
    public void testGetConnection() throws Exception {
        DatabaseConnection testConnection = DatabaseConnection.getInstance();
        assertNotEquals(testConnection.getConnection(),null);
    }

}
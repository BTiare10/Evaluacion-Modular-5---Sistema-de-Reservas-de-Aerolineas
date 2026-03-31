package cl.example.configuration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    @Test
    void testGetConnection() {
        try {
            assertNotNull(databaseConnection.getConnection(), "Connection should not be null");
            assertFalse(databaseConnection.getConnection().isClosed(), "Connection should be open");
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

}
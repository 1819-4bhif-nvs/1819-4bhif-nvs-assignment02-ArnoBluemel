package at.htl.naturalHistoryMuseum;

import org.apache.derby.client.am.SqlException;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NaturalHistoryMuseum_test
{
    public static final String DRIVER_STRING = "org.apache.derby.jdbc.ClientDriver";
    static final String CONNECTION_STRING = "jdbc:derby://localhost:1527/db";
    static final String USER = "app", PASSWORD = "app";
    private static Connection conn;

    @BeforeClass
    public static void init()
    {
        try
        {
            Class.forName(DRIVER_STRING);
            conn = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.out.println("Verbindung zur Datenbank nicht möglich:\n" + e.getMessage() + "\n");
            System.exit(1);
        }
    }
}
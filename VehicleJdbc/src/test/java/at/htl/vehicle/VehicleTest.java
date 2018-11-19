package at.htl.vehicle;

import org.apache.derby.client.am.SqlException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.transform.Result;
import java.sql.*;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VehicleTest
{
    public static final String DRIVER_STRING = "org.apache.derby.jdbc.ClientDriver";
    public static final String CONNECTION_STRING = "jdbc:derby://localhost:1527/db";
    public static final String USER = "app";
    public static final String PASSWORD = "app";
    public static Connection conn;

    @BeforeClass
    public static void initJdbc()
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
            System.err.println("Verbindung zur Datenbank nicht möglich\n" + e.getMessage());
            System.exit(1);
        }

        try
        {
            Statement stmt = conn.createStatement();
            String sql = "create table VEHICLE(ID int constraint VEHICLE_PK primary key, BRAND varchar(255) not null, TYPE varchar(255) not null)";
            stmt.execute(sql);
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }

    @AfterClass
    public static void teardownJdbc()
    {
        try
        {
            conn.createStatement().execute("drop table VEHICLE");
            System.out.println("Tabelle VEHICLE gelöscht");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        try
        {
            if(conn != null && !conn.isClosed())
            {
                conn.close();
                System.out.println("さよんなら。");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void ddl()
    {

    }

    @Test
    public void dml()
    {
        int countInserts = 0;
        try
        {
            Statement stmt = conn.createStatement();
            String sql = "insert into vehicle (ID, BRAND, TYPE) values (1, 'Opel', 'Commodore')";
            countInserts += stmt.executeUpdate(sql);
            sql = "insert into vehicle (ID, BRAND, TYPE) values (2, 'Opel', 'Kapitän')";
            countInserts += stmt.executeUpdate(sql);
            sql = "insert into vehicle (ID, BRAND, TYPE) values (3, 'Opel', 'Kadett')";
            countInserts += stmt.executeUpdate(sql);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        assertThat(countInserts, is(3));

        try
        {
            PreparedStatement pstmt = conn.prepareStatement("select ID, BRAND, TYPE from vehicle");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            assertThat(rs.getString("BRAND"), is("Opel"));
            assertThat(rs.getString("TYPE"), is("Commodore"));
            rs.next();
            assertThat(rs.getString("BRAND"), is("Opel"));
            assertThat(rs.getString("TYPE"), is("Kapitän"));
            rs.next();
            assertThat(rs.getString("BRAND"), is("Opel"));
            assertThat(rs.getString("TYPE"), is("Kadett"));
        }
        catch
        (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
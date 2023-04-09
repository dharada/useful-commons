package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SftpConnectionTest {

    public static void main(String[] args) {
        try {
            // Load the HXTT Text JDBC driver
            Class.forName("com.hxtt.sql.text.TextDriver");

            // Define the JDBC URL for the SFTP server
            //String jdbcUrl = "jdbc:csv:sftp://20.222.143.73/home/daisuke?user=daisuke&password=Iccpf12171217";

            String jdbcUrl = "jdbc:csv:sftp://20.89.134.84?user=daisuke&password=Iccpf12171217";
            // Connect to the SFTP server
            Connection conn = DriverManager.getConnection(jdbcUrl);

            System.out.println(conn.getMetaData().getDatabaseProductName());
            System.out.println(conn.getMetaData().getDriverName());




            // Test the connection by querying a file on the server
            String sql = "SELECT * FROM Employee";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }

            // Close the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

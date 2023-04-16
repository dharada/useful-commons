package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class SftpConnectionTest {


    public static void main(String[] args) {
        try {
            // Load the HXTT Text JDBC driver
            Class.forName("com.hxtt.sql.text.TextDriver");

            // Define the JDBC URL for the SFTP server
            String jdbcUrl = "jdbc:csv:/sftp://52.140.192.205:22/sqlloader?user=daisuke&password=" +
                    getPassword();

            Properties props = new Properties();
            props.put("_CSV_Header", "true");
            props.put("_CSV_Separator", ",");

            // Connect to the SFTP server
            Connection conn = DriverManager.getConnection(jdbcUrl, props);


            // Test the connection by querying a file on the server
            String sql =
                    "SELECT e.empId as empId, e.fullName as fullName, e.email, e.contactNo, d.deptName as deptName, d.role as role\n" +
                            "                    FROM Employee e \n" +
                            "                    LEFT OUTER JOIN EmpDept d ON e.empId = d.empId";

//            sql = "select roles.role as role, roles.description as description from Roles roles INNER JOIN EmpDept d ON roles.role = d.role group by roles.role";

            //     sql  = "select r.role as role, r.description as description from Roles r";


            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            int rowIndex = 0;
            while (rs.next()) {

                if (rowIndex == 0) {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        System.out.print(rs.getMetaData().getColumnName(i) + "\t");
                    }
                    System.out.println();
                }

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
                rowIndex++;
            }

            // Close the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getPassword() {


        Properties properties = new Properties();

        try (InputStream inputStream = SftpConnectionTest.class.getResourceAsStream("/sftp-connection-config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Retrieve properties
        return properties.getProperty("password");

    }

}


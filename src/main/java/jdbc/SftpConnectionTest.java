package jdbc;

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
            //String jdbcUrl = "jdbc:csv:sftp://20.222.143.73/home/daisuke?user=daisuke&password=Iccpf12171217";

            String jdbcUrl = "jdbc:csv:/sftp://52.140.192.205:22?user=daisuke&password=Iccpf12171217";

            Properties props =new Properties();
            props.put("_CSV_Header","true");
            props.put("_CSV_Separator", ",");

            // Connect to the SFTP server
            Connection conn = DriverManager.getConnection(jdbcUrl,props);


            // Test the connection by querying a file on the server
            String sql = "SELECT e.empId as empId, e.fullName as fullName, e.email, e.contactNo, d.deptName as deptName, d.role as role \n" +
                    "FROM Employee e \n " +
                    "LEFT OUTER JOIN Dept d ON e.deptId = d.id " ;



            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("fullName") + " " + rs.getString("deptName") + " " +  rs.getString("role"));
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
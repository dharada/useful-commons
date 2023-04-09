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

            Properties props = new Properties();
            props.put("_CSV_Header", "true");
            props.put("_CSV_Separator", ",");

            // Connect to the SFTP server
            Connection conn = DriverManager.getConnection(jdbcUrl, props);


            // Test the connection by querying a file on the server
            String sql = "SELECT e.empId as empId, e.fullName as fullName, e.email, e.contactNo, d.deptName as deptName, d.role as role, CONCAT('\"', GROUP_CONCAT(gs.groupName SEPARATOR ','), '\"') AS groupName \n" +
                    "FROM Employee e LEFT OUTER JOIN Dept d ON e.deptId = d.id INNER JOIN EmployeeGroups eg ON e.empId = eg.empId INNER JOIN Groups gs ON eg.groupId = gs.groupId GROUP BY empId";

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

}

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
      // https://documentation.sailpoint.com/connectors/sql_loader/help/integrating_sql_loader/data_files_url.html
      String jdbcUrl = "jdbc:csv:/sftp://4.216.148.102:22/oneerp?user=daisuke&password=" +
              getPassword();

      Properties props = new Properties();
      props.put("_CSV_Header", "true");
      // for tsv
      //props.put("_CSV_Separator", "\t");
      props.put("_CSV_Separator", ",");

      // Connect to the SFTP server
      Connection conn = DriverManager.getConnection(jdbcUrl, props);

      //login,first,last,status,locked

      // Test the connection by querying a file on the server
      String sql =
              "SELECT u.login as login, u.first as first, u.last as last, u.status as status, u.locked as locked, " +
                      "ug.group_name as groups," +

                      " CASE\n" +
                      "    WHEN status = 'inactive' THEN 'true'\n" +
                      "    ELSE 'false'\n" +
                      "  END AS IIQDisabled\n" +

                      "                    FROM Users u \n" +
                      "                    LEFT OUTER JOIN Users_groups ug ON u.login = ug.login ORDER BY login";


      // for tsv
      //sql = "SELECT * from Userstsv";

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


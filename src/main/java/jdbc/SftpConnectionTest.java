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

      boolean tsv = true;

      // Load the HXTT Text JDBC driver
      Class.forName("com.hxtt.sql.text.TextDriver");
      String jdbcUrl = "jdbc:csv:/sftp://" + sftpHostIPAdress() + ":" + port22() + "/" + rootDirName() + "?user=daisuke&password=" + getPassword();

      Properties props = new Properties();
      props.put("_CSV_Header", "true");
      props.put("_CSV_Separator", ",");

      if (tsv) {
        props.put("_CSV_Separator", "\t");
      }

      Connection conn = DriverManager.getConnection(jdbcUrl, props);

      // Test the connection by querying a file on the server
      String sql = joinSqlSample();

      if (tsv) {

        sql = tsvSqlExamples();

        //sql = "DELETE FROM UsersCopy as U WHERE U.MAIL_ADDRESS = 'Demo14.User14@sailpointdemodemoaaaa.com'";

      }

      Statement stmt = conn.createStatement();

      if (isDelete(sql)) {
        System.out.println(stmt.execute(sql));
        return;
      }

      ResultSet rs = null;
      if (isSelect(sql)) {
        rs = stmt.executeQuery(sql);
      }

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

  private static String rootDirName() {
    return "oneerp";
  }

  private static String port22() {
    return "22";
  }

  private static String sftpHostIPAdress() {
    return "20.243.234.118";
  }

  private static String joinSqlSample() {
    String sql = "SELECT u.login as login, u.first as first, u.last as last, u.status as status, u.locked as locked, " + "ug.group_name as groups," +

            " CASE\n" + "    WHEN status = 'inactive' THEN 'true'\n" + "    ELSE 'false'\n" + "  END AS IIQDisabled\n" +

            "                    FROM Users u \n" + "                    LEFT OUTER JOIN Users_groups ug ON u.login = ug.login ORDER BY login";
    return sql;
  }

  private static String tsvSqlExamples() {
    String sql;


    sql = "SELECT u.MAIL_ADDRESS as MAIL_ADDRESS, u.ROLE_SPECIALISM as ROLE_SPECIALISM, 'BillingSystemUser' as groups FROM Users u ORDER BY MAIL_ADDRESS";

    //BillingSystemUser
//        sql = "SELECT MAX(MAIL_ADDRESS) as MAIL_ADDRESS FROM Users as U WHERE U.MAIL_ADDRESS = 'Demo1@sai.com'";
//        sql = "SELECT MAX(MAIL_ADDRESS) as MAIL_ADDRESS FROM Users u";
//        sql = "SELECT MAIL_ADDRESS FROM Users u WHERE u.MAIL_ADDRESS = 'Demo1@sai.com'";
//        sql = "SELECT MAX(MAIL_ADDRESS) as MAIL_ADDRESS FROM Users U WHERE U.MAIL_ADDRESS = 'Demo1@sai.com'";
//        sql = "SELECT MAX(MAIL_ADDRESS) as MAIL_ADDRESS FROM Users u WHERE u.MAIL_ADDRESS = 'Demo1@sai.com'";
//        sql = "SELECT u.MAIL_ADDRESS as MAIL_ADDRESS, u.ROLE_SPECIALISM as ROLE_SPECIALISM, u.GROUP_NAME as group FROM Users u ORDER BY MAIL_ADDRESS";
    sql = "SELECT MAX(MAIL_ADDRESS) as MAIL_ADDRESS FROM Users u WHERE u.MAIL_ADDRESS = 'Demo11.User11@sailpointdemodemoaaaa.com'";
    //sql = "select ug.MAIL_ADDRESS as MAIL_ADDRESS, ug.GROUP_NAME aWs groups from UsersGroups ug";

//        sql = "SELECT u.MAIL_ADDRESS as MAIL_ADDRESS, u.ROLE_SPECIALISM as ROLE_SPECIALISM, 'BillingSystemUser' as groups FROM Users u ORDER BY MAIL_ADDRESS";
//        sql = "SELECT u.MAIL_ADDRESS as MAIL_ADDRESS, u.ROLE_SPECIALISM as ROLE_SPECIALISM, 'BillingSystemUser' as groups FROM Users u WHERE MAIL_ADDRESS = 'Demo12.User12@sailpointdemodemoaaaa.com'";

    sql = "SELECT u.MAIL_ADDRESS as MAIL_ADDRESS, u.ROLE_SPECIALISM as ROLE_SPECIALISM, ug.GROUP_NAME as groups FROM Users u\n" +
            "              LEFT OUTER JOIN UsersGroups ug ON u.MAIL_ADDRESS = ug.MAIL_ADDRESS ORDER BY COLLATE(MAIL_ADDRESS,'GERMAN')";
    return sql;
  }

  private static boolean isDelete(String sql) {
    return sql.trim().startsWith("DELETE");
  }

  private static boolean isSelect(String sql) {
    return sql.trim().startsWith("SELECT") || sql.trim().startsWith("select");
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


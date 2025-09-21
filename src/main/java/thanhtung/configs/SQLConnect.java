package thanhtung.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLConnect {

    private final String serverName = "ADMIN-PC\\THANHTUNG";
    private final String dbName = "LTWEBTUXA";
    private final String portNumber = "1433";
    private final String instance = "";
    private final String userID = "";
    private final String password = "";

    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ";encrypt=true;trustServerCertificate=true;databaseName="
                + dbName;
        if (instance == null || instance.trim().isEmpty())
            url = "jdbc:sqlserver://" + serverName + ";encrypt=true;trustServerCertificate=true;databaseName=" + dbName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }

    public Connection getConnectionW() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ";integratedSecurity=true;databaseName=" + dbName;
        if (instance == null || instance.trim().isEmpty())
            url = "jdbc:sqlserver://" + serverName + ";integratedSecurity=true;databaseName=" + dbName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url);
    }

    public static void main(String[] args) {
        try {
            Connection conn = new SQLConnect().getConnectionW();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO GiaoVien (id, name, address) VALUES (1, 'Trung', 'HCM')");
            ResultSet rs = stmt.executeQuery("SELECT * FROM GiaoVien");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("address"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
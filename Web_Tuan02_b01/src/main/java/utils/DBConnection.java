package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=QLNV1;encrypt=true;trustServerCertificate=true;";
    private String user = "sa2";
    private String password = "123";
    public Connection connection() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, password);
    }
}

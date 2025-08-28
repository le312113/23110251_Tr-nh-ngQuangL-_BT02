package B01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@WebServlet(urlPatterns = {"/test"})
public class b1  {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QLNV1;encrypt=true;trustServerCertificate=true;";
        String user = "sa2";
        String password = "123";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Kết nối thành công!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

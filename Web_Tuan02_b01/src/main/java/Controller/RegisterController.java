package Controller;

import Service.UserService;
import Service.UserServiceImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    private final UserService userService = new UserServiceImplement();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String email    = req.getParameter("email");
        String username = req.getParameter("username");
        String fullname = req.getParameter("fullname");
        String phone    = req.getParameter("phone");
        String password = req.getParameter("password");
        String repass   = req.getParameter("repass");

        if (email==null||username==null||password==null||repass==null
                || email.isEmpty()||username.isEmpty()||password.isEmpty()||repass.isEmpty()) {
            req.setAttribute("alert","Vui lòng nhập đủ thông tin bắt buộc");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }
        if (!password.equals(repass)) {
            req.setAttribute("alert","Mật khẩu nhập lại không khớp");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        boolean ok = userService.register(email, password, username, fullname, phone);
        if (ok) {
            // có thể auto-login, nhưng ở đây chuyển qua login
            resp.sendRedirect(req.getContextPath()+"/login");
        } else {
            req.setAttribute("alert","Email/Username/SĐT đã tồn tại");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        }
    }
}

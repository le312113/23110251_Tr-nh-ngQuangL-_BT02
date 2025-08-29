package Controller;

import Service.ForgetPasswordServiceImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = {"/forgetpassword"})
public class ForgetPasswordController extends HttpServlet {
    ForgetPasswordServiceImplement forgetPasswordServiceImplement = new ForgetPasswordServiceImplement();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/forgetpassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String Email = req.getParameter("email");
        String NewPassword = req.getParameter("newpassword");
        String NewPasswordConfirm = req.getParameter("confirmPassword");
        if (Email == null || NewPassword == null || Email.isEmpty() || NewPassword.isEmpty()) {
            req.setAttribute("alert","Vui lòng nhập đủ thông tin bắt buộc");
            req.getRequestDispatcher("/forgetpassword.jsp").forward(req, resp);
        }else if(!Objects.equals(NewPasswordConfirm, NewPassword)){
            req.setAttribute("alert","Mật khẩu khong khớp");
            req.getRequestDispatcher("/forgetpassword.jsp").forward(req, resp);
        }
        else {
            forgetPasswordServiceImplement.UpdatePassword(Email, NewPassword);
            resp.sendRedirect(req.getContextPath()+"/login");
        }
    }
}

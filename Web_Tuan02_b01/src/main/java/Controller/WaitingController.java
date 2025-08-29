package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/waiting")
public class WaitingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession();
        if(session != null && session.getAttribute("account") != null) {
            User u=(User) session.getAttribute("account");
            req.setAttribute("username", u.getUserName());
            if(u.getRoleid()==1) {
                req.getRequestDispatcher("/WEB-INF/views/admin/home.jsp").forward(req, resp);
            }else if(u.getRoleid()==2) {
                req.getRequestDispatcher("/WEB-INF/views/manager/home.jsp").forward(req, resp);
            }else {
                req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
            }
        }else {
            resp.sendRedirect(req.getContextPath()+"/login");
        }

    }
}

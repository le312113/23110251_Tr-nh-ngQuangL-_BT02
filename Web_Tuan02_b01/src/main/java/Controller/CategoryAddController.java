package Controller;

import Service.CategoryService;
import Service.CategoryServiceImplement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Category;
import model.User;
import utils.Constant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
@jakarta.servlet.annotation.MultipartConfig
@WebServlet(urlPatterns = { "/admin/category/add" })
public class CategoryAddController  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService cateService = new CategoryServiceImplement();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/views/admin/category/add-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            // LẤY SESSION ĐÚNG KEY
            jakarta.servlet.http.HttpSession session = req.getSession(false);
            model.User auth = (session != null) ? (model.User) session.getAttribute(utils.Constant.SESSION_ACCOUNT) : null;
            if (auth == null) {
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }
            int userId = auth.getId();

            String name = req.getParameter("name");
            System.out.println("DEBUG name=" + name + " | userId=" + userId);

            Category category = new Category();
            category.setCatename(name);
            category.setUserId(userId);

            // (A) TẠM THỜI BỎ UPLOAD ĐỂ KHOANH VÙNG LỖI
            category.setIcon("");  // nếu cột icons NOT NULL; nếu cho NULL thì dùng null

            // (B) NHỚ @MultipartConfig NẾU DÙNG getPart (đang tắt upload nên khỏi gọi getPart)
            // Part filePart = req.getPart("icon"); ...

            // INSERT
            cateService.insert(category);
            System.out.println("DEBUG: insert called OK");
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
        } catch (Exception ex) {
            ex.printStackTrace(); // BẮT BUỘC: xem lỗi thật trong console
            throw ex;             // để Tomcat trả 500 + log đầy đủ
        }
    }
}

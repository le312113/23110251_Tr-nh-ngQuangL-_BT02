package Controller;

import Service.CategoryService;
import Service.CategoryServiceImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.User;

import java.io.IOException;

@WebServlet(urlPatterns = { "/admin/category/delete" })
public class CategoryDeleteController extends HttpServlet {
    CategoryService cateService = new CategoryServiceImplement();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User auth = (User) req.getSession().getAttribute("auth");
        if (auth == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        int userId = auth.getId();

        String idStr = req.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu id");
            return;
        }
        int id = Integer.parseInt(idStr);
        Category c = cateService.get(id);
        if (c == null || c.getUserId() != userId) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Không có quyền xoá category này");
            return;
        }
        cateService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }
}

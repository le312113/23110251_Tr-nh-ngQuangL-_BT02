package Controller;

import Service.CategoryService;
import Service.CategoryServiceImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = { "/admin/category/delete" })
public class CategoryDeleteController extends HttpServlet {
    CategoryService cateService = new CategoryServiceImplement();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        cateService.delete(Integer.parseInt(id));
        resp.sendRedirect(req.getContextPath() + "/WEB-INF/views/admin/category/list-category.jsp");
    }
}

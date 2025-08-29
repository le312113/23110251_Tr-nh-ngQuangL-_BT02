package Controller;

import Service.CategoryService;
import Service.CategoryServiceImplement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Category;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;
import utils.Constant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
@WebServlet(urlPatterns = { "/admin/category/edit" })
public class CategoryEditController extends HttpServlet {
    CategoryService cateService = new CategoryServiceImplement();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        String id = req.getParameter("id");
        Category category = cateService.get(Integer.parseInt(id));
        req.setAttribute("category", category);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/category/edit-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String idStr   = req.getParameter("id");
        String name    = req.getParameter("name");
        String oldIcon = req.getParameter("oldIcon");
        Category category = new Category();
        if (idStr != null && !idStr.trim().isEmpty()) {
            category.setCateid(Integer.parseInt(idStr));
        }
        category.setCatename(name);
        Part filePart = req.getPart("icon");
        if (filePart != null && filePart.getSize() > 0) {
            File folder = new File(Constant.DIR, "category");
            if (!folder.exists()) folder.mkdirs();

            String original = Paths.get(filePart.getSubmittedFileName())
                    .getFileName().toString();
            String ext = "dat";
            int dot = original.lastIndexOf('.');
            if (dot >= 0 && dot < original.length() - 1) {
                ext = original.substring(dot + 1);
            }
            String fileName = System.currentTimeMillis() + "." + ext;
            filePart.write(new File(folder, fileName).getAbsolutePath());
            category.setIcon("category/" + fileName);
        } else {
            category.setIcon(oldIcon);
        }
        cateService.edit(category);
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }
}

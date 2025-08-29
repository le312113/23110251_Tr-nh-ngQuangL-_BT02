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
import utils.Constant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet(urlPatterns = { "/admin/category/add" })
public class CategoryAddController  extends HttpServlet {
    CategoryService cateService = new CategoryServiceImplement();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/category/add-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // 1) Lấy dữ liệu text
        String name = req.getParameter("name");

        Category category = new Category();
        category.setCatename(name);   // nếu field là setName thì đổi lại

        // 2) Xử lý file (nếu có chọn)
        Part filePart = req.getPart("icon"); // name="icon" trong form
        if (filePart != null && filePart.getSize() > 0) {
            File folder = new File(Constant.DIR, "category"); // Constant.DIR là thư mục gốc (vd C:/upload)
            if (!folder.exists()) folder.mkdirs();

            String original = Paths.get(filePart.getSubmittedFileName())
                    .getFileName().toString(); // tránh path traversal
            String ext = "dat";
            int dot = original.lastIndexOf('.');
            if (dot >= 0 && dot < original.length() - 1) {
                ext = original.substring(dot + 1);
            }
            String fileName = System.currentTimeMillis() + "." + ext;

            // Ghi file
            filePart.write(new File(folder, fileName).getAbsolutePath());

            // Lưu đường dẫn tương đối vào DB
            category.setIcon("category/" + fileName);
        } else {
            category.setIcon(null); // hoặc "" nếu DB cho phép rỗng
        }

        // 3) Lưu DB
        cateService.insert(category);

        // 4) Điều hướng về danh sách
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }
}

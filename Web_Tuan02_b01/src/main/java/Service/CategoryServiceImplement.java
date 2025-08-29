package Service;

import dao.CategoryDao;
import dao.CategoryDaoImplement;
import model.Category;

import java.util.List;

public class CategoryServiceImplement implements CategoryService  {
    CategoryDao categoryDao = new CategoryDaoImplement();
    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }
    @Override
    public void edit(Category newCategory) {
        Category oldCate = categoryDao.get(newCategory.getCateid());
        oldCate.setCatename(newCategory.getCatename());
        categoryDao.edit(oldCate);
    }
    @Override
    public void delete(int id) {
        categoryDao.delete(id);
    }
    @Override
    public Category get(int id) {
        return categoryDao.get(id);
    }
    @Override
    public Category get(String name) {
        return categoryDao.get(name);
    }
    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }
    @Override
    public List<Category> search(String catename) {
        return categoryDao.search(catename);
    }
}

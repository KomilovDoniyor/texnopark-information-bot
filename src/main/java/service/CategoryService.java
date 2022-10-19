package service;

import entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {

    Category save(Category subject);

    void saveAll(List<Category> categoryList) throws SQLException;

    List<Category> findAll() throws SQLException;

    Category findById(Long id);
}

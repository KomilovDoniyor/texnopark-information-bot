/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:4:08 PM
 * Project Name:texnopark-information-bot
 */
package service.impl;

import entity.Category;
import repository.CategoryRepository;
import repository.impl.CategoryRepositoryImpl;
import service.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    public static CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public void saveAll(List<Category> categoryList) throws SQLException {
        categoryRepository.saveAll(categoryList);
    }

    @Override
    public List<Category> findAll() throws SQLException {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return null;
    }
}

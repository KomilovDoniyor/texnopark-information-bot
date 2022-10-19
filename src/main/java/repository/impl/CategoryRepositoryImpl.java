/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:4:13 PM
 * Project Name:texnopark-information-bot
 */
package repository.impl;

import entity.Category;
import repository.CategoryRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static config.DbConfig.connection;

public class CategoryRepositoryImpl implements CategoryRepository {


    @Override
    public void saveAll(List<Category> categoryList) throws SQLException {
        for (Category category : categoryList) {
            Category byId = findById(category.getId());
            if (byId == null) {
                String INSERT_INTO = "INSERT INTO categories(prefix,name) values (?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
                preparedStatement.setString(1, category.getPrefix());
                preparedStatement.setString(2, category.getName());
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public Category save(Category subject) {
        return null;
    }

    @Override
    public List<Category> findAll() throws SQLException {
        List<Category> subjectList = new ArrayList<>();
        String SELECT_FIND_ALL = "SELECT * FROM categories";
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FIND_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            subjectList.add(new Category(
                    resultSet.getLong("id"),
                    resultSet.getString("prefix"),
                    resultSet.getString("name")));
        }
        return subjectList;
    }

    @Override
    public Category findById(Long id) throws SQLException {
        String SELECT_CATEGORY_FIND_BY_ID = "SELECT * FROM categories WHERE id = '" + id + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_FIND_BY_ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Category(
                    resultSet.getLong("id"),
                    resultSet.getString("prefix"),
                    resultSet.getString("name"));
        }
        return null;
    }
}

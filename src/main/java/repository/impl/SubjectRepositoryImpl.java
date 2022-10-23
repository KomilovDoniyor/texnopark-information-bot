/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:4:47 PM
 * Project Name:texnopark-information-bot
 */
package repository.impl;

import entity.Subject;
import repository.SubjectRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static config.DbConfig.connection;


public class SubjectRepositoryImpl implements SubjectRepository {

    @Override
    public void saveAll(List<Subject> subjectList) throws SQLException {
        for (Subject subject : subjectList) {
            Subject byId = findById(subject.getId());
            if (byId == null) {
                String INSERT_INTO_SAVE_ALL = "INSERT INTO subjects(category_id, name, image_url, description,price) values (?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_SAVE_ALL);
                preparedStatement.setLong(1, subject.getCategoryId());
                preparedStatement.setString(2, subject.getName());
                preparedStatement.setString(3, subject.getImageUrl());
                preparedStatement.setString(4, subject.getDescription());
                preparedStatement.setDouble(5, subject.getPrice());
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public Subject save(Subject subject) {
        return null;
    }

    @Override
    public List<Subject> findAll() {
        return null;
    }

    @Override
    public Subject findById(Long id) throws SQLException {
        String SELECT_FROM_FIND_BY_ID = "SELECT * FROM subjects WHERE id = '" + id + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_FIND_BY_ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Subject(
                    resultSet.getLong("id"),
                    resultSet.getLong("category_id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getString("image_url"),
                    resultSet.getString("description"));
        }
        return null;
    }

    @Override
    public List<Subject> findAllCategoryId(long categoryId) throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        String SELECT_ALL_BY_CATEGORY = "SELECT * FROM subjects WHERE category_id = " + categoryId;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_CATEGORY);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            subjects.add(new Subject(
                    resultSet.getLong("id"),
                    resultSet.getLong("category_id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getString("image_url"),
                    resultSet.getString("description")));
        }
        return subjects;
    }
}

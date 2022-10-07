/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:4:13 PM
 * Project Name:texnopark-information-bot
 */
package repository.impl;

import entity.Subject;
import repository.SubjectRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static config.DbConfig.connection;

public class SubjectRepositoryImpl implements SubjectRepository {


    @Override
    public void saveAll(List<Subject> subjectList) throws SQLException {
        for (Subject subject : subjectList) {
            Subject byId = findById(subject.getId());
            if (byId == null) {
                String INSERT_INTO = "INSERT INTO subjects(name, price, image_url, duration_time) values (?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
                preparedStatement.setString(1, subject.getName());
                preparedStatement.setDouble(2, subject.getPrice());
                preparedStatement.setString(3, subject.getImageUrl());
                preparedStatement.setString(4, subject.getDurationTime());
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
        String SELECT_CATEGORY_FIND_BY_ID = "SELECT * FROM subjects WHERE id = '" + id + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_FIND_BY_ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return new Subject(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getString("image_url"),
                    resultSet.getString("duration_time"));
        }
        return null;
    }
}

/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:4:47 PM
 * Project Name:texnopark-information-bot
 */
package repository.impl;

import entity.SubjectInfo;
import repository.SubjectInfoRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static config.DbConfig.connection;


public class SubjectInfoRepositoryImpl implements SubjectInfoRepository {

    @Override
    public void saveAll(List<SubjectInfo> subjectInfoList) throws SQLException {
        for (SubjectInfo subjectInfo : subjectInfoList) {
            SubjectInfo byId = findById(subjectInfo.getId());
            if (byId == null) {
                String INSERT_INTO_SAVE_ALL = "INSERT INTO subject_infos(name, image_url, description) values (?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_SAVE_ALL);
                preparedStatement.setString(1, subjectInfo.getName());
                preparedStatement.setString(2, subjectInfo.getImageUrl());
                preparedStatement.setString(3, subjectInfo.getDescription());
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public SubjectInfo save(SubjectInfo subjectInfo) {
        return null;
    }

    @Override
    public List<SubjectInfo> findAll() {
        return null;
    }

    @Override
    public SubjectInfo findById(Long id) throws SQLException {
        String SELECT_FROM_FIND_BY_ID = "SELECT * FROM subject_infos WHERE id = '" + id + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_FIND_BY_ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return new SubjectInfo(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("image_url"),
                    resultSet.getString("description"));
        }
        return null;
    }
}

package repository;

import entity.SubjectInfo;

import java.sql.SQLException;
import java.util.List;

public interface SubjectInfoRepository {

    void saveAll(List<SubjectInfo> subjectInfoList) throws SQLException;

    SubjectInfo save(SubjectInfo subjectInfo);

    List<SubjectInfo> findAll();

    SubjectInfo findById(Long id) throws SQLException;
}

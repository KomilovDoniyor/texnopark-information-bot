package service;

import entity.SubjectInfo;

import java.sql.SQLException;
import java.util.List;

public interface SubjectInfoService {
    void saveAll(List<SubjectInfo> subjectInfoList) throws SQLException;

    SubjectInfo save(SubjectInfo subjectInfo);

    SubjectInfo findById(Long id);

    List<SubjectInfo> findAll();
}

package service;

import entity.Subject;

import java.sql.SQLException;
import java.util.List;

public interface SubjectService {
    void saveAll(List<Subject> subjectList) throws SQLException;

    Subject save(Subject subject);

    Subject findById(Long id) throws SQLException;

    List<Subject> findAll();

    List<Subject> findAllCategoryId(long categoryId) throws SQLException;
}

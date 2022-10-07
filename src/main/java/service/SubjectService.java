package service;

import entity.Subject;

import java.sql.SQLException;
import java.util.List;

public interface SubjectService {

    Subject save(Subject subject);

    void saveAll(List<Subject> subjectList) throws SQLException;

    List<Subject> findAll();

    Subject findById(Long id);
}

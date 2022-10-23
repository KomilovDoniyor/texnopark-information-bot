package repository;

import entity.Subject;

import java.sql.SQLException;
import java.util.List;

public interface SubjectRepository {

    void saveAll(List<Subject> subjectList) throws SQLException;

    Subject save(Subject subject);

    List<Subject> findAll();

    Subject findById(Long id) throws SQLException;

    List<Subject> findAllCategoryId(long categoryId) throws SQLException;
}

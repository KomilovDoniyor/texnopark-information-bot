/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:4:43 PM
 * Project Name:texnopark-information-bot
 */
package service.impl;

import entity.Subject;
import repository.SubjectRepository;
import repository.impl.SubjectRepositoryImpl;
import service.SubjectService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    public static SubjectRepository subjectRepository = new SubjectRepositoryImpl();

    @Override
    public void saveAll(List<Subject> subjectList) throws SQLException {
        subjectRepository.saveAll(subjectList);
    }

    @Override
    public Subject save(Subject subject) {
        return null;
    }

    @Override
    public Subject findById(Long id) throws SQLException {
        return subjectRepository.findById(id);
    }

    @Override
    public List<Subject> findAll() {
        return null;
    }

    @Override
    public List<Subject> findAllCategoryId(long categoryId) throws SQLException {
        return subjectRepository.findAllCategoryId(categoryId);
    }
}

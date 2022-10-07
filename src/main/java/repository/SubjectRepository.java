/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:4:11 PM
 * Project Name:texnopark-information-bot
 */
package repository;

import entity.Subject;

import java.sql.SQLException;
import java.util.List;

public interface SubjectRepository {

    void saveAll(List<Subject> subjectList) throws SQLException;

    Subject save(Subject subject);

    List<Subject> findAll();

    Subject findById(Long id) throws SQLException;

}

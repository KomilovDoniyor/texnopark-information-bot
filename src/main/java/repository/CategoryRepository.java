/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:4:11 PM
 * Project Name:texnopark-information-bot
 */
package repository;

import entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryRepository {

    void saveAll(List<Category> subjectList) throws SQLException;

    Category save(Category subject);

    List<Category> findAll() throws SQLException;

    Category findById(Long id) throws SQLException;

}

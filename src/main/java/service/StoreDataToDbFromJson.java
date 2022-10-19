/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:2:46 PM
 * Project Name:texnopark-information-bot
 */
package service;

import com.google.gson.Gson;
import entity.Category;
import entity.Subject;
import service.impl.SubjectServiceImpl;
import service.impl.CategoryServiceImpl;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreDataToDbFromJson {

    public static void store() throws SQLException {
        Gson gson = new Gson();

        List<Category> categories = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/main/resources/category.json")))) {
            Category[] subjects = gson.fromJson(bufferedReader, Category[].class);
            categories.addAll(Arrays.asList(subjects));
        } catch (IOException io) {
            io.printStackTrace();
        }

        CategoryService categoryService = new CategoryServiceImpl();
        categoryService.saveAll(categories);

        List<Subject> subjectInfoList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/main/resources/subject.json")))) {
            Subject[] subjectInfos = gson.fromJson(bufferedReader, Subject[].class);
            subjectInfoList.addAll(Arrays.asList(subjectInfos));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubjectService subjectService = new SubjectServiceImpl();
        subjectService.saveAll(subjectInfoList);
    }
}

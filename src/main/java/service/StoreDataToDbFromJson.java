/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:2:46 PM
 * Project Name:texnopark-information-bot
 */
package service;

import com.google.gson.Gson;
import entity.Subject;
import entity.SubjectInfo;
import service.impl.SubjectInfoServiceImpl;
import service.impl.SubjectServiceImpl;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreDataToDbFromJson {

    public static void store() throws SQLException {
        Gson gson = new Gson();

        List<Subject> subjectList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/main/resources/subject.json")))) {
            Subject[] subjects = gson.fromJson(bufferedReader, Subject[].class);
            subjectList.addAll(Arrays.asList(subjects));
        } catch (IOException io) {
            io.printStackTrace();
        }

        SubjectService subjectService = new SubjectServiceImpl();
        subjectService.saveAll(subjectList);

        List<SubjectInfo> subjectInfoList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/main/resources/subjectInfo.json")))) {
            SubjectInfo[] subjectInfos = gson.fromJson(bufferedReader, SubjectInfo[].class);
            subjectInfoList.addAll(Arrays.asList(subjectInfos));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SubjectInfoService subjectInfoService = new SubjectInfoServiceImpl();
        subjectInfoService.saveAll(subjectInfoList);
    }
}

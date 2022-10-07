/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:4:43 PM
 * Project Name:texnopark-information-bot
 */
package service.impl;

import entity.SubjectInfo;
import repository.SubjectInfoRepository;
import repository.impl.SubjectInfoRepositoryImpl;
import service.SubjectInfoService;

import java.sql.SQLException;
import java.util.List;

public class SubjectInfoServiceImpl implements SubjectInfoService {
    public static SubjectInfoRepository subjectInfoRepository = new SubjectInfoRepositoryImpl();

    @Override
    public void saveAll(List<SubjectInfo> subjectInfoList) throws SQLException {
        subjectInfoRepository.saveAll(subjectInfoList);
    }

    @Override
    public SubjectInfo save(SubjectInfo subjectInfo) {
        return null;
    }

    @Override
    public SubjectInfo findById(Long id) {
        return null;
    }

    @Override
    public List<SubjectInfo> findAll() {
        return null;
    }
}

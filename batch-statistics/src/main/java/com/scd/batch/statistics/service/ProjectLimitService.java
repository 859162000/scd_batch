package com.scd.batch.statistics.service;

import com.scd.batch.common.entity.ProjectLimitEntity;
import com.scd.batch.statistics.dao.ProjectLimitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectLimitService {

    @Autowired
    private ProjectLimitDao projectLimitDao;

    public List<ProjectLimitEntity> getList(long start, long num) {
        return projectLimitDao.getList(start, num);
    }


}
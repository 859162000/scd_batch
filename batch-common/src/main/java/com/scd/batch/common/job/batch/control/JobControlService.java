package com.scd.batch.common.job.batch.control;

import com.scd.batch.common.daycut.service.DayCutService;
import com.scd.batch.common.utils.ShortDate;
import com.scd.batch.common.utils.TableSpec;
import com.scd.batch.common.job.batch.control.dao.JobDao;
import com.scd.batch.common.job.constants.JobType;
import com.scd.batch.common.job.constants.PhaseStatus;
import com.scd.batch.common.job.constants.PhaseType;
import com.scd.batch.common.job.executor.ExecutorContext;
import com.fasterxml.uuid.Generators;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JobControlService {

    private final int maxRetry = 3;

    @Resource
    private JobDao jobDao;
    @Resource
    private DayCutService dayCutService;

    /**
     * PrepareJob 初始化 Job 控制表数据使用
     * @param jobControl obj
     * @return insert counts successfully
     */
    public int insert(JobControl jobControl) {
        return jobDao.insertOne(jobControl);
    }

    public int insertList(List<JobControl> controlList) {
        return CollectionUtils.isNotEmpty(controlList) ? jobDao.insert(controlList) : 0;
    }

    /**
     * 从 Job 控制表中获取一个未处理或处理失败的 job
     * 开启事务的原因是: 此函数先更新立即查询, 避免主从不一致
     *
     * @param accountDate 账务日期
     * @param jobType job 类型, see {@link JobType}
     * @param phaseType see {@link PhaseType}
     * @return 一条控制表中符合条件的记录
     */
    @Transactional
    public JobControl getAvailable(ShortDate accountDate, JobType jobType, PhaseType phaseType) {
        JobCondition condition = new JobCondition(accountDate.toDate(), jobType.type, phaseType.type);
        condition.setMaxRetry(maxRetry);

        // 生成 uuid
        String uuid = Generators.randomBasedGenerator().generate().toString();
        JobControl control;

        while ((control = jobDao.selectOneAvailableJob(condition)) != null) {
            condition.setId(control.getId());
            int record = jobDao.updateOneAvailableJob(condition, uuid);
            // 说明在查询后已经被其他进程或线程更新掉, 需要继续查询
            if (record == 0) {
                continue;
            }
            return jobDao.getByUUID(uuid);
        }
        return null;
    }

    /**
     * 一个阶段处理结束后更新状态
     * @param uuid 更新的 job 的 UUID
     * @param phaseStatus 要更新的状态
     * @return update counts
     */
    public int updatePhaseStatus(String uuid, PhaseType phaseType, PhaseStatus phaseStatus) {
        return jobDao.updatePhaseStatusByUUID(uuid, phaseType.type, phaseStatus.type);
    }

    @Transactional
    public JobControl grabJobControl(ExecutorContext context, JobType jobType, PhaseType phase) {
        ShortDate accountDate = context.getAttach(ShortDate.class);

        if (accountDate == null) {
            accountDate = ShortDate.valueOf(dayCutService.load());
            context.addAttach(ShortDate.class, accountDate);
        }

        JobControl control = getAvailable(accountDate, jobType, phase);

        if (control != null) {
            String dbId = control.getDatabaseId();
            String tableId = control.getTableId();

            TableSpec tableSpec = new TableSpec(dbId, tableId);

            context.addAttach(JobControl.class, control)
                    .addAttach(TableSpec.class, tableSpec);

            return control;
        }
        return null;
    }

    @Transactional
    public List<JobControl> getAllJobs(ShortDate accountDate, JobType jobType) {
        return jobDao.selectJobs(accountDate.toDate(), jobType.type);
    }

}

package com.scd.batch.common.job.command;

import com.scd.batch.common.job.LifeCycle;
import org.springframework.context.ApplicationContext;

import com.google.common.base.Preconditions;

public class JobCommand extends AbstractCommand {

    /**
     * Sub commands
     */
    enum Job {
        /**
         * Switch date
         */
        SWITCH("switch"),
        
        /**
         * Prepare job
         */
        PREPARE("prepare"),
        
        /**
         * Load loan to txt
         */
        LOAD_LOAN("loadLoan"),
        /**
         * Calculate loan and installment plans
         */
        CAL_LOAN("calLoan"),
        /**
         * Update the data to loan
         */
        UPDATE_LOAN("updateLoan"),

        /**
         * retry loan failed records
         */
        RETRY_LOAN("retryLoan"),

        /**
         * load loan expedite to txt
         */
        EXPEDITE_LOAN("expediteLoan"),

        /**
         * load loan voa to txt
         */
        RECONCILIATION_LOAN("reconciliationLoan"),

        /**
         * load loan old voa to txt
         */
        OLDRECONCILIATION_LOAN("oldReconciliationLoan"),

        /**
         * auto repay
         */
        AUTO_REPAY_LOAN("autoRepayLoan"),
        AUTO_REPAY_POST_LOAN("autoRepayPostLoan"),

        /**
         * Load credit to txt
         */
        LOAD_CREDIT("loadCredit"),
        /**
         * Calculate credit and installment plans
         */
        CAL_CREDIT("calCredit"),
        /**
         * Update the data to credit
         */
        UPDATE_CREDIT("updateCredit"),

        /**
         * retry credit failed records
         */
        RETRY_CREDIT("retryCredit"),

        /**
         * load credit old voa to txt
         */
        RECONCILIATION_CREDIT("reconciliationCredit"),

        /**
         * load credit expedite to txt
         */
        EXPEDITE_CREDIT("expediteCredit"),

        /**
         * ACCG-Request handle jobs
         */
        LOAD_ACCG_REQ("loadAccgReqs"),
        HANDLE_ACCG_REQ("handleAccgReqs"),
        HANDLE_FAILURE_ACCG_REQ("handleFailureAccgReqs"),
        
        /**
         * ACCG-Provision jobs
         */
        LOAD_PROVISION("loadProvision"),
        CAL_PROVISION("calProvision"),
        UPDATE_PROVISION("updateProvision"),
        
        /**
         * ACCG-Transaction jobs
         */
        LOAD_TRANS_ACCG_REQ("loadTransAccgReq"),
        CAL_TRANS_ACCG_REQ("calTransAccgReq"),
        UPDATE_TRANS_ACCG_REQ("updateTransAccgReq"),
        
        /**
         * ACCG-Batch penalty jobs
         */
        LOAD_PENALTY_ACCG_REQ("loadPenaltyAccgReq"),
        CAL_PENALTY_ACCG_REQ("calPenaltyAccgReq"),
        UPDATE_PENALTY_ACCG_REQ("updatePenaltyAccgReq");

        /**
         * Job name
         */
        public final String name;

        Job(String name) {
            this.name = name;
        }

        public static Job nameOf(String jobName) {
            for (Job job : Job.values()) {
                if (job.name.equals(jobName)) {
                    return job;
                }
            }
            return null;
        }
    }

    public JobCommand(CommandOptions commandOptions, ApplicationContext context) {
        super(commandOptions, context);
    }

    @Override
    public void validateOptions() {
        Preconditions.checkState(commandOptions != null, "Missing command options!");
    }

    @Override
    public void run() {
        LifeCycle executor = (LifeCycle) context.getBean(commandOptions.getJobName() + ".job");

        // start the executor
        executor.start();
    }
}

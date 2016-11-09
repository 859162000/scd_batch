package com.scd.batch.executor.job;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class BatchLauncherTest {

    /**
     * Execption case 1: Args is null
     */
    @Test(expected = NullPointerException.class)
    @Ignore
    public void testException4NullArgs() {
        BatchLauncher.main(null);
    }

    /**
     * Execption case 2: Invalid command option for -a, -s
     */
    @Test(expected = RuntimeException.class)
    @Ignore
    public void testException4InvalidOption() {
        BatchLauncher.main(new String[]{"-a", "-s", "-h"});
    }

    /**
     * Execption case 3: Invalid command type
     */
    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void testException4MissingCommandType() {
        BatchLauncher.main(new String[]{"-n", "prepare", "-t", "fake_type"});
    }

    /**
     * Execption case 4: Invalid job desc
     */
    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void testException4InvalidName() {
        BatchLauncher.main(new String[]{"-n", "fake_name"});
    }

    /**
     * Success case
     */
    @Test
    @Ignore
    public void testProcessJobSuccessSwitch() {
        BatchLauncher.main(new String[]{"-n", "switch", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
    }

    /**
     * Usage print case
     */
    @Test()
    @Ignore
    public void testUsagePrint() {
        BatchLauncher.main(new String[]{"-h"});
    }

    /**
     * Success case
     */
    @Test
    @Ignore
    public void testProcessJobSuccess() {
        BatchLauncher.main(new String[]{"-n", "prepare", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
    }


    /**
     * Success case
     */
    @Test()
    public void testProcessJobSuccess_projectLimit() {

        BatchLauncher.main(new String[]{"-n", "projectLimit", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});

        Assert.assertTrue(true);
    }

    @Test()
    public void testProcessJobSuccess_borrowerRepayPlan() {


        BatchLauncher.main(new String[]{"-n", "borrowerRepayPlan", "-p",
                "classpath:META-INF/ApplicationContext-executor" +
                        ".xml", "-f"});
        Assert.assertTrue(true);
    }


    @Test()
    public void testProcessJobSuccess_fundStat() {


        BatchLauncher.main(new String[]{"-n", "fundStat", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});

        Assert.assertTrue(true);

    }


    @Test()
    public void testProcessJobSuccess_fixPlanDue() {

        BatchLauncher.main(new String[]{"-n", "fixPlanDue", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);

    }

    @Test()
    public void testProcessJobSuccess_fixProjectDue() {

        BatchLauncher.main(new String[]{"-n", "fixProjectDue", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);

    }


    @Test()
    public void testProcessJobSuccess_expentidure() {

        BatchLauncher.main(new String[]{"-n", "expentidure", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);

    }

    @Deprecated
    @Test()
    public void testProcessJobSuccess_assetsProject() {

        BatchLauncher.main(new String[]{"-n", "assetsProject", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);
    }


    @Test()
    public void testProcessJobSuccess_assetsBalance() {

        BatchLauncher.main(new String[]{"-n", "assetsBalance", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);
    }

    /**
     * cash
     */


    @Test()
    public void testProcessJobSuccess_cashCrawler() {

        BatchLauncher.main(new String[]{"-n", "cashCrawler", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);
    }

    @Test()
    public void testProcessJobSuccess_cashLoader() {

        BatchLauncher.main(new String[]{"-n", "cashLoader", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);
    }

    @Test()
    public void testProcessJobSuccess_withDrawlLoader() {

        BatchLauncher.main(new String[]{"-n", "withDrawlLoader", "-p",
                "classpath:META-INF/ApplicationContext-executor" + ".xml", "-f"});
        Assert.assertTrue(true);
    }

    @Test()
    public void testProcessJobSuccess_cashCalculator() {

        BatchLauncher.main(new String[]{"-n", "cashCalculator", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);
    }


    /**
     * save
     */


    @Test()
    public void testProcessJobSuccess_saveCrawler() {

        BatchLauncher.main(new String[]{"-n", "saveCrawler", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);
    }


    @Test()
    public void testProcessJobSuccess_saveLoader() {

        BatchLauncher.main(new String[]{"-n", "saveLoader", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);
    }


    @Test()
    public void testProcessJobSuccess_rechargeLoader() {

        BatchLauncher.main(new String[]{"-n", "rechargeLoader", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);
    }


    @Test()
    public void testProcessJobSuccess_saveCalculator() {

        BatchLauncher.main(new String[]{"-n", "saveCalculator", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);
    }

    /**
     * loan
     */

    @Test()
    public void testProcessJobSuccess_loanCrawler() {

        BatchLauncher.main(new String[]{"-n", "loanCrawler", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);
    }

    @Test()
    public void testProcessJobSuccess_loanLoader() {

        BatchLauncher.main(new String[]{"-n", "loanLoader", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);
    }

    @Test()
    public void testProcessJobSuccess_loanEntityLoader() {

        BatchLauncher.main(new String[]{"-n", "loanEntityLoader", "-p",
                "classpath:META-INF/ApplicationContext-executor" +
                        ".xml", "-f"});
        Assert.assertTrue(true);
    }

    @Test()
    public void testProcessJobSuccess_loanCalculator() {

        BatchLauncher.main(new String[]{"-n", "loanCalculator", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);
    }

    /**
     * payment
     */

    @Test()
    public void testProcessJobSuccess_paymentCrawler() {

        BatchLauncher.main(new String[]{"-n", "paymentCrawler", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);
    }

    @Test()
    public void testProcessJobSuccess_paymentLoader() {

        BatchLauncher.main(new String[]{"-n", "paymentLoader", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);
    }

    @Test()
    public void testProcessJobSuccess_creditRepayRealLoader() {

        BatchLauncher.main(new String[]{"-n", "creditRepayRealLoader", "-p",
                "classpath:META-INF/ApplicationContext-executor" +
                        ".xml", "-f"});
        Assert.assertTrue(true);
    }

    @Test()
    public void testProcessJobSuccess_paymentCalculator() {

        BatchLauncher.main(new String[]{"-n", "paymentCalculator", "-p",
                "classpath:META-INF/ApplicationContext-executor" +
                        ".xml", "-f"});
    }


    /**
     * trf
     */

    @Ignore
    @Test()
    public void testProcessJobSuccess_trfCrawler() {

        BatchLauncher.main(new String[]{"-n", "trfCrawler", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);
    }

    @Ignore
    @Test()
    public void testProcessJobSuccess_trfLoader() {

        BatchLauncher.main(new String[]{"-n", "trfLoader", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
        Assert.assertTrue(true);
    }

    /**
     * interest
     */

    @Test()
    public void testProcessJobSuccess_userCurrentProfitCalculate() {

        BatchLauncher.main(new String[]{"-n", "userCurrentProfitCalculate", "-p",
                "classpath:META-INF/ApplicationContext-executor" +
                        ".xml", "-f"});
    }


    @Test()
    public void testProcessJobSuccess_userDailyProfitCalculate() {

        BatchLauncher.main(new String[]{"-n", "userDailyProfitCalculate", "-p",
                "classpath:META-INF/ApplicationContext-executor" +
                        ".xml", "-f"});
    }


    @Test()
    public void testProcessJobSuccess_lastDayAssetsCalculate() {

        BatchLauncher.main(new String[]{"-n", "lastDayAssetsCalculate", "-p",
                "classpath:META-INF/ApplicationContext-executor" +
                        ".xml", "-f"});
    }

    /**
     * schedule
     */

    @Test()
    public void testProcessJobSuccess_redeemSchedule() {

        BatchLauncher.main(new String[]{"-n", "redeemSchedule", "-p", "classpath:META-INF/ApplicationContext-executor" +
                ".xml", "-f"});
    }

    @Test()
    public void testProcessJobSuccess_updateBankCardQuotaSchedule() {

        BatchLauncher.main(new String[]{"-n", "updateBankCardQuotaSchedule", "-p",
                "classpath:META-INF/ApplicationContext-executor" +
                        ".xml", "-f"});
    }


    @Test()
    public void testProcessJobSuccess_bidLoanSchedule() {

        BatchLauncher.main(new String[]{"-n", "bidLoanSchedule", "-p",
                "classpath:META-INF/ApplicationContext-executor" +
                        ".xml", "-f"});
    }


    @Test()
    public void testProcessJobSuccess_bidBuyBackSchedule() {

        BatchLauncher.main(new String[]{"-n", "bidBuyBackSchedule", "-p",
                "classpath:META-INF/ApplicationContext-executor" +
                        ".xml", "-f"});
    }

    @Test()
    public void testProcessJobSuccess_preAutoBuySchedule() {

        BatchLauncher.main(new String[]{"-n", "preAutoBuySchedule", "-p",
                "classpath:META-INF/ApplicationContext-executor" +
                        ".xml", "-f"});
    }

    @Test()
    public void testProcessJobSuccess_autoBuySchedule() {

        BatchLauncher.main(new String[]{"-n", "autoBuySchedule", "-p",
                "classpath:META-INF/ApplicationContext-executor" +
                        ".xml", "-f"});
    }


    @Test()
    public void testProcessJobSuccess_postHandling() {

        BatchLauncher.main(new String[]{"-n", "postHandling", "-p",
                "classpath:META-INF/ApplicationContext-executor" +
                        ".xml", "-f"});
    }


    @Test()
    public void testProcessJobSuccess_notice() {

        BatchLauncher.main(new String[]{"-n", "notice", "-p",
                "classpath:META-INF/ApplicationContext-executor" +
                        ".xml", "-f"});
    }
}

#!/usr/bin/env bash
bin/start.sh -n switch
bin/start.sh -n prepare

bin/start.sh -n projectLimit
bin/start.sh -n borrowerRepayPlan
bin/start.sh -n fundStat
bin/start.sh -n fixPlanDue
bin/start.sh -n fixProjectDue
bin/start.sh -n expentidure
bin/start.sh -n assetsProject
bin/start.sh -n assetsBalance

bin/start.sh -n userCurrentProfitCalculate
bin/start.sh -n userDailyProfitCalculate
bin/start.sh -n lastDayAssetsCalculate

bin/start.sh -n loanCrawler
bin/start.sh -n loanLoader
bin/start.sh -n loanEntityLoader
bin/start.sh -n loanCalculator

bin/start.sh -n paymentCrawler
bin/start.sh -n paymentLoader
bin/start.sh -n creditRepayRealLoader
bin/start.sh -n paymentCalculator

bin/start.sh -n cashCrawler
bin/start.sh -n cashLoader
bin/start.sh -n withDrawlLoader
bin/start.sh -n cashCalculator

bin/start.sh -n saveCrawler
bin/start.sh -n saveLoader
bin/start.sh -n rechargeLoader
bin/start.sh -n saveCalculator

bin/start.sh -n redeemSchedule
bin/start.sh -n updateBankCardQuotaSchedule
bin/start.sh -n updateUserRegisterCountToRedis
bin/start.sh -n bidLoanSchedule
bin/start.sh -n bidBuybackSchedule
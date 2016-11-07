package com.scd.batch.common.utils;

import org.springframework.beans.factory.annotation.Value;

public class Settings {

    private static Settings instance;

    public static Settings getInstance() {
        return instance;
    }

    public Settings() {
        instance = this;
    }

    /**
     * bidloan
     */
    @Value("#{configProperties['redis.bidloan.name']}")
    private String bidLoanName;

    @Value("#{configProperties['redis.bidloan.msg']}")
    private String bidLoanMsg;

    @Value("#{configProperties['redis.bidloan.timeout']}")
    private int bidLoanTimeout;

    @Value("#{configProperties['redis.bidloan.retry']}")
    private int bidLoanRetry;

    public String getBidLoanName() {
        return bidLoanName;
    }

    public void setBidLoanName(String bidLoanName) {
        this.bidLoanName = bidLoanName;
    }

    public String getBidLoanMsg() {
        return bidLoanMsg;
    }

    public void setBidLoanMsg(String bidLoanMsg) {
        this.bidLoanMsg = bidLoanMsg;
    }

    public int getBidLoanTimeout() {
        return bidLoanTimeout;
    }

    public void setBidLoanTimeout(int bidLoanTimeout) {
        this.bidLoanTimeout = bidLoanTimeout;
    }

    public int getBidLoanRetry() {
        return bidLoanRetry;
    }

    public void setBidLoanRetry(int bidLoanRetry) {
        this.bidLoanRetry = bidLoanRetry;
    }


    /**
     * redeem
     */
    @Value("#{configProperties['redis.redeem.name']}")
    private String redeemName;

    @Value("#{configProperties['redis.redeem.msg']}")
    private String redeemMsg;

    @Value("#{configProperties['redis.redeem.timeout']}")
    private int redeemTimeout;

    @Value("#{configProperties['redis.redeem.retry']}")
    private int redeemRetry;

    public String getRedeemName() {
        return redeemName;
    }

    public void setRedeemName(String redeemName) {
        this.redeemName = redeemName;
    }

    public String getRedeemMsg() {
        return redeemMsg;
    }

    public void setRedeemMsg(String redeemMsg) {
        this.redeemMsg = redeemMsg;
    }

    public int getRedeemTimeout() {
        return redeemTimeout;
    }

    public void setRedeemTimeout(int redeemTimeout) {
        this.redeemTimeout = redeemTimeout;
    }

    public int getRedeemRetry() {
        return redeemRetry;
    }

    public void setRedeemRetry(int redeemRetry) {
        this.redeemRetry = redeemRetry;
    }

    /**
     * bid buyback
     */

    @Value("#{configProperties['redis.buyBack.name']}")
    private String buyBackName;

    @Value("#{configProperties['redis.buyBack.msg']}")
    private String buyBackMsg;

    @Value("#{configProperties['redis.buyBack.timeout']}")
    private int buyBackTimeout;

    @Value("#{configProperties['redis.buyBack.retry']}")
    private int buyBackRetry;

    public String getBuyBackName() {
        return buyBackName;
    }

    public void setBuyBackName(String buyBackName) {
        this.buyBackName = buyBackName;
    }

    public String getBuyBackMsg() {
        return buyBackMsg;
    }

    public void setBuyBackMsg(String buyBackMsg) {
        this.buyBackMsg = buyBackMsg;
    }

    public int getBuyBackTimeout() {
        return buyBackTimeout;
    }

    public void setBuyBackTimeout(int buyBackTimeout) {
        this.buyBackTimeout = buyBackTimeout;
    }

    public int getBuyBackRetry() {
        return buyBackRetry;
    }

    public void setBuyBackRetry(int buyBackRetry) {
        this.buyBackRetry = buyBackRetry;
    }


    /**
     * bid preAutoBuy
     */

    @Value("#{configProperties['redis.preAutoBuy.name']}")
    private String preAutoBuyName;

    @Value("#{configProperties['redis.preAutoBuy.msg']}")
    private String preAutoBuyMsg;

    @Value("#{configProperties['redis.preAutoBuy.timeout']}")
    private int preAutoBuyTimeout;

    @Value("#{configProperties['redis.preAutoBuy.retry']}")
    private int preAutoBuyRetry;

    public String getPreAutoBuyName() {
        return preAutoBuyName;
    }

    public void setPreAutoBuyName(String preAutoBuyName) {
        this.preAutoBuyName = preAutoBuyName;
    }

    public String getPreAutoBuyMsg() {
        return preAutoBuyMsg;
    }

    public void setPreAutoBuyMsg(String preAutoBuyMsg) {
        this.preAutoBuyMsg = preAutoBuyMsg;
    }

    public int getPreAutoBuyTimeout() {
        return preAutoBuyTimeout;
    }

    public void setPreAutoBuyTimeout(int preAutoBuyTimeout) {
        this.preAutoBuyTimeout = preAutoBuyTimeout;
    }

    public int getPreAutoBuyRetry() {
        return preAutoBuyRetry;
    }

    public void setPreAutoBuyRetry(int preAutoBuyRetry) {
        this.preAutoBuyRetry = preAutoBuyRetry;
    }


    /**
     * autoBuy
     */

    @Value("#{configProperties['redis.autoBuy.name']}")
    private String autoBuyName;

    @Value("#{configProperties['redis.autoBuy.msg']}")
    private String autoBuyMsg;

    @Value("#{configProperties['redis.autoBuy.timeout']}")
    private int autoBuyTimeout;

    @Value("#{configProperties['redis.autoBuy.retry']}")
    private int autoBuyRetry;

    public String getAutoBuyName() {
        return autoBuyName;
    }

    public void setAutoBuyName(String autoBuyName) {
        this.autoBuyName = autoBuyName;
    }

    public String getAutoBuyMsg() {
        return autoBuyMsg;
    }

    public void setAutoBuyMsg(String autoBuyMsg) {
        this.autoBuyMsg = autoBuyMsg;
    }

    public int getAutoBuyTimeout() {
        return autoBuyTimeout;
    }

    public void setAutoBuyTimeout(int autoBuyTimeout) {
        this.autoBuyTimeout = autoBuyTimeout;
    }

    public int getAutoBuyRetry() {
        return autoBuyRetry;
    }

    public void setAutoBuyRetry(int autoBuyRetry) {
        this.autoBuyRetry = autoBuyRetry;
    }
}

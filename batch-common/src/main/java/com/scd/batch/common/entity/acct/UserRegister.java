package com.scd.batch.common.entity.acct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class UserRegister implements Serializable {
    private Integer id;

    private String uid;

    private String nickName;

    private String mobile;

    private String password;

    private String payPassword;

    private String invitedId;

    private String userCustId;

    private String status;

    private Integer accountType;

    private String accountStatus;

    private String ip;

    private String channel;

    private Date createTime;

    private Date updateTime;

    private ArrayList<String> accountStatusList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    public String getInvitedId() {
        return invitedId;
    }

    public void setInvitedId(String invitedId) {
        this.invitedId = invitedId == null ? null : invitedId.trim();
    }

    public String getUserCustId() {
        return userCustId;
    }

    public void setUserCustId(String userCustId) {
        this.userCustId = userCustId == null ? null : userCustId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus == null ? null : accountStatus.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public ArrayList<String> getAccountStatusList() {
        return accountStatusList;
    }

    public void setAccountStatusList(ArrayList<String> accountStatusList) {
        this.accountStatusList = accountStatusList;
    }

    @Override
    public String toString() {
        return "UserRegister{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", nickName='" + nickName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", payPassword='" + payPassword + '\'' +
                ", invitedId='" + invitedId + '\'' +
                ", userCustId='" + userCustId + '\'' +
                ", status='" + status + '\'' +
                ", accountType=" + accountType +
                ", ip='" + ip + '\'' +
                ", channel='" + channel + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
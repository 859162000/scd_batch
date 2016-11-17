package com.scd.batch.common.constant.bid;

/**
 * Created by zhxj on 2016/10/10.
 */
public enum LoanStatus {
    PREPARED_LOAN(0,"待放款"),
    PUSH_THIRD_FAIL(1,"调用第三方存管系统失败"),
    PUSH_THIRD_SUCCESS(2,"调用第三方存管系统成功"),
    PUSH_TRANS_FAILD(3,"调用交易系统失败"),
    LOAN_SUCCESS(4,"调用交易系统成功，放款成功");
    private int value;
    private String desc;
    LoanStatus(int veule, String desc){
        this.value=veule;
        this.desc=desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

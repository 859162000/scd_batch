package com.scd.batch.common.constant.bid;

/**
 *
 */
public enum ProductStatus {
    CREATE(1, "创建"),
    COMMITTED(2, "待审核"),
    AUDITED(3, "待上线"),
    RELEASE(4, "待开标"),
    INVEST_START(5,"开标"),
    INVEST_FULL(6,"满标"),
    OFF_SHELVE(7, "下架");


    private final int code;
    private final String name;

    ProductStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean equalsCode(Integer code) {
        if (code != null) {
            return this.code==code;
        }
        return false;
    }
    public static ProductStatus getByCode(Integer code) {
        if (null!=code) {
            ProductStatus[] statuss = ProductStatus.values();
            for (ProductStatus status : statuss) {
                if (code.equals(status.code)) {
                    return status;
                }
            }
        }
        return null;
    }
}

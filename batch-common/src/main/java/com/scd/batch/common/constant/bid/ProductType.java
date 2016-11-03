package com.scd.batch.common.constant.bid;

/**
 *
 */
public enum ProductType {

    FIX_PROJECT(1,"定期赚"),
    FIX_PLAN(2,"定期计划"),
    CURRENT(3,"活期"),
    OTHER(99,"其他");

    public final int code;

    private final String name;

    ProductType(int code, String name) {
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
    public static ProductType getByCode(Integer code) {
        if (null!=code) {
            ProductType[] productTypes = ProductType.values();
            for (ProductType productType : productTypes) {
                if (code.equals(productType.code)) {
                    return productType;
                }
            }
        }
        return null;
    }

}

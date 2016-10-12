package com.scd.batch.common.constant;

/**
 * <p>
 * <ul>
 * <li>COM: 公用
 * <li>BAT: 批跑模块
 * </ul>
 * </p>
 *
 */
public class CommonErrorCode {

    // 公共Success
    public static final String COM_SUCCESS_DESC = "Success";

    // SUCCESS
    public static final int COM_SUCCESS = 0;

    public static String getCommonErrorCodeStr(int commonErrorCode) {

        return String.valueOf(commonErrorCode);
    }

}

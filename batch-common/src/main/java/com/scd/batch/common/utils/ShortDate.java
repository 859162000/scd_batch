package com.scd.batch.common.utils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class ShortDate implements Serializable, Comparable<ShortDate> {
    private static final long serialVersionUID = -1956142224157920976L;
    
    /**
     * 数字日期格式: yyyyMMdd
     */
    private int intValue;

    /**
     * 用于 Json 序列化初始化, 禁止调用
     */
    @Deprecated
    public ShortDate() {
    }

    public ShortDate(int intValue) {
        this.intValue = intValue;
    }

    /**
     * 返回数字日期格式: yyyyMMdd
     */
    public int getIntValue() {
        return intValue;
    }

    /**
     * 仅提供序列化使用, 禁止直接调用, 请使用 new ShortDate(intValue);
     * 
     * @see ShortDate(int)
     * @param intValue 格式: yyyyMMdd
     */
    @Deprecated
    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    /**
     * 格式: yyyy-MM-dd
     */
    public String toString() {
        return (int) (Math.floor(intValue / 10000)) + "-"
                + fill((int) Math.floor(intValue / 100 % 100)) + "-"
                + fill((int) Math.floor(intValue % 100));
    }

    /**
     * 格式: yyyy-MM-dd hh:mm:ss
     */
    public String toTimeString() {
        return (int) (Math.floor(intValue / 10000)) + "-"
                + fill((int) Math.floor(intValue / 100 % 100)) + "-"
                + fill((int) Math.floor(intValue % 100)) + " 00:00:00";
    }
    
    /**
     * 返回 Date 类型
     */
    public Date toDate() {
        return parseDate(intValue);
    }

    @Override
    public int hashCode() {
        return intValue;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ShortDate && intValue == ((ShortDate) obj).intValue;
    }

    
    public static ShortDate today() {
        return valueOf(new Date());
    }
    
    /**
     * 使用 yyyy-MM-dd 格式转义
     * @param str 字符串日期格式: yyyy-MM-dd
     * @return ShortDate
     */
    public static ShortDate valueOf(String str) {
        if (str == null) {
            throw new IllegalArgumentException("null string");
        }
        if (str.length() != 10) {
            throw new IllegalArgumentException("ShortDate format must be yyyy-MM-dd: " + str);
        }
        int year = Integer.parseInt(str.substring(0, 4));
        int month = Integer.parseInt(str.substring(5, 7));
        int day = Integer.parseInt(str.substring(8, 10));

        return valueOf(year * 10000 + month * 100 + day);
    }

    /**
     * 从 Date 转义
     */
    @SuppressWarnings("deprecation")
    public static ShortDate valueOf(Date date) {
        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;
        int day = date.getDate();

        return new ShortDate(year * 10000 + month * 100 + day);
    }

    /**
     * @param numDate 数字类型日期: yyyy-MM-dd
     * @return ShortDate
     */
    public static ShortDate valueOf(int numDate) {
        return new ShortDate(normalize(numDate));
    }

    @SuppressWarnings("deprecation")
    public static int normalize(int numDate) {
        Date date = parseDate(numDate);
        return (date.getYear() + 1900) * 10000 + (date.getMonth() + 1) * 100 + date.getDate();
    }

    @Override
    public int compareTo(ShortDate o) {
        int thisVal = this.intValue;
        int anotherVal = o.intValue;
        return (thisVal < anotherVal ? -1 : (thisVal == anotherVal ? 0 : 1));
    }

    public boolean isAfter(ShortDate o) {
        return this.intValue > o.intValue;
    }

    public boolean isBefore(ShortDate o) {
        return this.intValue < o.intValue;
    }

    public ShortDate addMonths(int amount) {
        return add(toDate().getTime(), Calendar.MONTH, amount);
    }
    
    public ShortDate addWeeks(int amount) {
        return add(toDate().getTime(), Calendar.WEEK_OF_YEAR, amount);
    }
    
    public ShortDate addDays(int amount) {
        return add(toDate().getTime(), Calendar.DAY_OF_MONTH, amount);
    }

    private static ShortDate add(long date, int calendarField, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        c.add(calendarField, amount);
        return valueOf(c.getTime());
    }

    /**
     * @param numDate 数字日期格式: yyyy-MM-dd
     * @return Date
     */
    @SuppressWarnings("deprecation")
    public static Date parseDate(int numDate) {
        return new Date((int) (Math.floor(numDate / 10000)) - 1900,
                (int) Math.floor(numDate / 100 % 100) - 1,
                (int) Math.floor(numDate % 100));
    }

    private static String fill(int i) {
        return i < 10 ? "0" + i : Integer.toString(i, 10);
    }

}

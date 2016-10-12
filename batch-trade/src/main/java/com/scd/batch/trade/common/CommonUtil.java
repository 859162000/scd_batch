package com.scd.batch.trade.common;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.scd.batch.common.utils.JsonUtils;
import com.scd.batch.common.utils.ShortDate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class CommonUtil {
    /**
     * ZoneId has two implementations, both of which are immutable and thread-safe.
     */
    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

    /**
     * 获取两个日期之间的间隔天数
     *
     * @param d1Inclusive 日期1
     * @param d2Exclusive 日期2
     * @return the amount of days between d1Inclusive and d2Exclusive;
     *  positive if d2Exclusive is later than d1Inclusive, negative if earlier
     */
    public static int betweenTwoDays(Date d1Inclusive, Date d2Exclusive) {
        return (int) ChronoUnit.DAYS.between(
                d1Inclusive.toInstant().atZone(DEFAULT_ZONE_ID).toLocalDate(),
                d2Exclusive.toInstant().atZone(DEFAULT_ZONE_ID).toLocalDate());
    }

    /**
     * 获取两个日期之间的间隔月数
     *
     * @param d1Inclusive 日期1
     * @param d2Exclusive 日期2
     * @return the amount of months between d1Inclusive and d2Exclusive;
     *  positive if d2Exclusive is later than d1Inclusive, negative if earlier
     */
    public static int betweenTwoMonths(Date d1Inclusive, Date d2Exclusive) {
        return (int) ChronoUnit.MONTHS.between(
                d1Inclusive.toInstant().atZone(DEFAULT_ZONE_ID).toLocalDate(),
                d2Exclusive.toInstant().atZone(DEFAULT_ZONE_ID).toLocalDate());
    }

    public static int betweenTwoMonths(ShortDate d1Inclusive, ShortDate d2Exclusive) {
        return betweenTwoMonths(d1Inclusive.toDate(), d2Exclusive.toDate());
    }


    /**
     * check if the given date is the first day of the month
     *
     * @param date the date to check
     * @return true if the date is the first of day of the month, false if not
     */
    public static boolean isFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return  dayOfMonth == 1;
    }

    /**
     * d1 的后一天是不是 d2
     *
     * @param d1 d1
     * @param d2 d2
     * @return true if the day after d1 is d2, false if not
     */
    public static boolean expiringPoint(Date d1, Date d2) {
        return betweenTwoDays(d1, d2) == 1;
    }

    /**
     * d1 的后一天是不是 d2
     *
     * @param d1 d1
     * @param d2 d2
     * @return true if the day after d1 is d2, false if not
     */
    public static boolean expiringPoint(ShortDate d1, ShortDate d2) {
        return expiringPoint(d1.toDate(), d2.toDate());
    }

    public static <T> List<T> transform2Bean(List<String> lines, Class<T> type) {
        return Lists.newArrayList(Iterables.transform(lines,
                line -> JsonUtils.toBean(line, type)));
    }

    public static <T> List<String> transform2Lines(Collection<T> list) {
        return Lists.newArrayList(Iterables.transform(list, JsonUtils::toJson));
    }

    public static <T> List<String> joinerFormBean(Collection<T> list){
        return Lists.newArrayList(Iterables.transform(list, T::toString));
    }

    public static int ceilDiv(int x, int y){
        return -Math.floorDiv(-x,y);
    }

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        System.out.println(c.getActualMinimum(Calendar.DAY_OF_MONTH));
        System.out.println(ShortDate.valueOf(20160517).equals(ShortDate.today()));

        ShortDate b1 = ShortDate.valueOf(20160518);
        ShortDate b2 = ShortDate.valueOf(20160502);
        System.out.println(betweenTwoMonths(b1, b2));

        System.out.println(ceilDiv(500, 400) == 2);
        System.out.println(ceilDiv(500, 500) == 1);
        System.out.println(ceilDiv(500, 600) == 1);
    }

}

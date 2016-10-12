package com.scd.batch.common.utils;

import org.apache.commons.lang.text.StrBuilder;

public class StringUtils extends org.apache.commons.lang.StringUtils {

    /**
     * Substring Before. Ignore case.
     */
    public static String substringBeforeIgnoreCase(String str, String separator) {
        if (isEmpty(str) || separator == null) {
            return str;
        }
        if (separator.length() == 0) {
            return EMPTY;
        }
        int pos = indexOfIgnoreCase(str, separator);
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * Substring After. Ignore case.
     */
    public static String substringAfterIgnoreCase(String str, String separator) {
        if (isEmpty(str) || separator == null) {
            return str;
        }
        if (separator.length() == 0) {
            return EMPTY;
        }
        int pos = indexOfIgnoreCase(str, separator);
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }
        return str.substring(pos + separator.length());
    }

    public static String replaceIgnoreCase(String text, String searchString, String replacement, int max) {
        if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0) {
            return text;
        }
        int start = 0;
        int end = indexOfIgnoreCase(text, searchString, start);
        if (end == INDEX_NOT_FOUND) {
            return text;
        }
        int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = (increase < 0 ? 0 : increase);
        increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
        StrBuilder buf = new StrBuilder(text.length() + increase);
        while (end != INDEX_NOT_FOUND) {
            buf.append(text.substring(start, end)).append(replacement);
            start = end + replLength;
            if (--max == 0) {
                break;
            }
            end = indexOfIgnoreCase(text, searchString, start);
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

    public static String replaceIgnoreCase(String text, String searchString, String replacement) {
        return replaceIgnoreCase(text, searchString, replacement, -1);
    }

}

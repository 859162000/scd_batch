package com.scd.batch.common.utils;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Currency;

/**
 * Money represents a kind of currency, consists of an amount unit as cent and a currency code.<br>
 * Provide features as below:<br>
 * <li><b>Money compare</b>:compareTo, greaterThan</li>
 * <li><b>Money calculations</b>: add, subtract, negate, multiply, allocate(helpful be used in Installments scenario)
 * </li>
 * <li><b>Money format</b>: for human readable
 */
public final class Money implements Serializable, Comparable<Money> {
    private static final long serialVersionUID = -5655014726174327069L;

    /**
     * Default currency: RMB
     */
    public static final String RMB_CURRENCY_CODE = "CNY";
    public static final Currency RMB_CURRENCY = Currency.getInstance(RMB_CURRENCY_CODE);

    /**
     * Amount unit as cent
     */
    private long cent;
    /**
     * Currency type
     */
    private Currency currency;

    /**
     * Default constructor. Zero cent for RMB
     */
    public Money() {
        this(0L);
    }

    /**
     * Specified cent for default currency RMB
     *
     * @param cent
     */
    public Money(long cent) {
        this.cent = cent;
        this.currency = RMB_CURRENCY;
    }

    /**
     * Specified cent & currency
     *
     * @param cent
     * @param currency
     */
    public Money(long cent, Currency currency) {
        this.cent = cent;
        this.currency = currency;
    }

    /**
     * Money compare
     *
     * @param other
     */
    @Override
    public int compareTo(Money other) {
        Assert.notNull(other);
        assertSameCurrencyAs(other);

        if (this.cent < other.cent) {
            return -1;
        } else if (this.cent == other.cent) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Whether the current money is greater than the other one
     *
     * @param other
     * @return
     */
    public boolean greaterThan(Money other) {
        return compareTo(other) > 0;
    }

    /**
     * Money add: <p>
     * If these two money are not in the same currency, <code>java.lang.IllegalArgumentException</code> would be
     * thrown up.<br>
     * Otherwise, a new Money object with a sum cent will be created and return.
     *
     * @param other
     * @return new Money
     * @throw IllegalArgumentException
     */
    public Money add(Money other) {
        Assert.notNull(other);
        assertSameCurrencyAs(other);

        return new Money(this.cent + other.cent, this.getCurrency());
    }

    /**
     * Money subtract: <p>
     * If these two money are not in the same currency, <code>java.lang.IllegalArgumentException</code> would be
     * thrown up.<br>
     * Otherwise, a new Money object with a subtracted cent will be created and return.
     *
     * @param other
     * @return new Money
     * @throw IllegalArgumentException
     */
    public Money subtract(Money other) {
        Assert.notNull(other);
        assertSameCurrencyAs(other);

        return new Money(this.cent - other.cent, this.getCurrency());
    }

    /**
     * Money negate: <p>
     * Create a new Money object with a negate cent.
     *
     * @return new Money
     */
    public Money negate() {
        return new Money(-1 * this.cent, this.getCurrency());
    }

    /**
     * Money multiply: <p>
     * Create a new Money object with a multiplied cent.
     *
     * @param val multiplier
     * @return new Money
     */
    public Money multiply(long val) {
        return new Money(this.cent * val, this.getCurrency());
    }

    /**
     * Money allocate: <p>
     * We allocate this money average into <code>parts</code> pieces, ensure that no money odds will be lost.<br>
     * The return moneys sort by cent desc, the high cent will be greater than the low by 1cent.
     * <p>
     * Sample:<br>
     * We want to allocate $5 into 3 parts, each result will be:$1.67, $1.67, $1.66
     *
     * @param parts the pieces u want to allocate
     * @return Money array
     */
    public Money[] allocate(int parts) {
        Money[] result = new Money[parts];

        Money low = new Money(this.cent / parts, this.getCurrency());
        Money high = new Money(low.cent + 1, this.getCurrency());
        int remainder = (int) this.cent % parts;

        for (int i = 0; i < remainder; i++) {
            result[i] = high;
        }
        for (int i = remainder; i < parts; i++) {
            result[i] = low;
        }

        return result;
    }

    /**
     * Get human readable amount<br>
     * Default no scale. Sample: 310cent -> 3.1
     *
     * @return
     */
    public String format() {
        return format(false);
    }

    /**
     * Get human readable amount<br>
     * Default no scale. Sample: 310cent -> 3.1<br>
     * With scale as two. Sample:310cent -> 3.10
     *
     * @return
     */
    public String format(boolean withScale) {
        BigDecimal decimal = BigDecimal.valueOf(this.cent).divide(new BigDecimal(100));
        if (withScale) {
            return new DecimalFormat("0.00").format(decimal);
        }

        return decimal.toString();

    }

    /**
     * Getters & Setters
     */
    public long getCent() {
        return cent;
    }

    public void setCent(long cent) {
        this.cent = cent;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Object methods
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Money)) {
            return false;
        }

        Money other = (Money) obj;
        return this.currency.equals(other.currency) && (this.cent == other.cent);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cent).toHashCode();
    }

    @Override
    public String toString() {
        return "Money [cent=" + cent + ", currency=" + currency + "]";
    }

    /**
     * Whether with the same currency
     *
     * @param other
     */
    private void assertSameCurrencyAs(Money other) {
        Assert.isTrue(this.currency.equals(other.currency), "Mismatched currency");
    }

}

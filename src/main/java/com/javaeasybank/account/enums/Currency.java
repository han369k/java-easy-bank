package com.javaeasybank.account.enums;

public enum Currency {
    TWD(2, 365),
    USD(2, 360),
    EUR(2, 360),
    JPY(0, 360),
    GBP(2, 365),
    CNY(2, 365),
    AUD(2, 365),
    CAD(2, 365),
    CHF(2, 360),
    HKD(2, 365);

    private final int decimalPlaces;
    private final int dayCountBasis;

    Currency(int decimalPlaces, int dayCountBasis) {
        this.decimalPlaces = decimalPlaces;
        this.dayCountBasis = dayCountBasis;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    public int getDayCountBasis() {
        return dayCountBasis;
    }
}

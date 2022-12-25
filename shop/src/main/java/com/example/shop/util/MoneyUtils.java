package com.example.shop.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class MoneyUtils {
    private final static BigDecimal DECIMAL_HUNDRED = BigDecimal.valueOf(100);

    public static BigDecimal fromKopecks(long kopecks) {
        return BigDecimal.valueOf(kopecks)
                .divide(DECIMAL_HUNDRED, RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public static long toKopecks(BigDecimal decimal) {
        return decimal.multiply(DECIMAL_HUNDRED).longValue();
    }
}

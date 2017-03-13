package org.jks.utils;

import java.math.BigDecimal;

/**
 * @author liaojian
 * @version 02/08/2017
 */
public class DoubleUtil {

    public static void main(String args[]) {
        double f = 111231.5585;
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1);
    }
}

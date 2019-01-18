package com.kanhaoyi.www.util;

import java.math.BigDecimal;

public class MoneyUtil {

	public static double add(double d1, double d2){        // 进行加法运算
             BigDecimal b1 = new BigDecimal(d1);
             BigDecimal b2 = new BigDecimal(d2);
            return b1.add(b2).doubleValue();
    }
    public static double sub(double d1, double d2){        // 进行减法运算
             BigDecimal b1 = new BigDecimal(d1);
             BigDecimal b2 = new BigDecimal(d2);
             return b1.subtract(b2).doubleValue();
    }
    public static double mul(double d1, double d2){        // 进行乘法运算
             BigDecimal b1 = new BigDecimal(d1);
             BigDecimal b2 = new BigDecimal(d2);
            return b1.multiply(b2).doubleValue();
    }
    public static double div(double d1,double d2,int len) {// 进行除法运算
             BigDecimal b1 = new BigDecimal(d1);
             BigDecimal b2 = new BigDecimal(d2);
            return b1.divide(b2,len,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
	
}

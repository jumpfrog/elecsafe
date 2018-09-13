package com.holley.elecsafe.common.cache;

import java.math.BigDecimal;

import com.holley.platform.common.util.NumberUtil;

public class FactorUtil {

    /**
     * 千克转化成吨
     * 
     * @param value
     * @return
     */
    public static BigDecimal kg2Ton(BigDecimal value) {
        if (value == null) return null;
        BigDecimal newvalue = value.divide(new BigDecimal(1000));
        return newvalue;
    }

    /**
     * 千克转化成千吨
     * 
     * @param value
     * @return
     */
    public static BigDecimal kg2Kton(BigDecimal value) {
        if (value == null) return null;
        BigDecimal newvalue = value.divide(new BigDecimal(1000000));
        return newvalue;
    }

    /**
     * 瓦转化成千瓦
     * 
     * @param value
     * @return
     */
    public static BigDecimal w2kw(BigDecimal value) {
        if (value == null) return null;
        BigDecimal newvalue = value.divide(new BigDecimal(1000));
        return newvalue;
    }

    /**
     * 千瓦时转化成兆瓦时
     * 
     * @param value
     * @return
     */
    public static BigDecimal kwh2Mwh(BigDecimal value) {
        if (value == null) return null;
        BigDecimal newvalue = value.divide(new BigDecimal(1000));
        return newvalue;
    }

    /**
     * 千瓦时转化成万千瓦时
     * 
     * @param value
     * @return
     */
    public static BigDecimal kwh2Wkwh(BigDecimal value) {
        if (value == null) return null;
        BigDecimal newvalue = value.divide(new BigDecimal(10000));
        return newvalue;
    }

    /**
     * 千瓦时转化成G瓦时
     * 
     * @param value
     * @return
     */
    public static BigDecimal kwh2Gwh(BigDecimal value) {
        if (value == null) return null;
        BigDecimal newvalue = value.divide(new BigDecimal(1000000));
        return newvalue;
    }

    /**
     * 千瓦时动态单位匹配
     * 
     * @param value
     * @return
     */
    public static String formatkWhUnits(BigDecimal value) {
        if (value == null) return "";
        BigDecimal GWh = new BigDecimal("9999999.99999");
        String newvalue = "";
        BigDecimal tempvalue;
        if (value.compareTo(GWh) < 0) {
            newvalue = NumberUtil.formateScale2Str(value) + " kWh";
        } else if (value.compareTo(GWh) > 0) {
            tempvalue = value.divide(BigDecimal.valueOf(1000000));
            newvalue = NumberUtil.formateScale2Str(tempvalue) + " GWh";
        }
        return newvalue;

    }

    /**
     * 千克动态匹配单位
     * 
     * @param value
     * @return
     */
    public static String formatkgUnits(BigDecimal value) {
        if (value == null) return "";
        BigDecimal kg = new BigDecimal("999.99999");
        BigDecimal t = new BigDecimal("999999.99999");
        String newvalue = "";
        BigDecimal tempvalue;
        // 根据数值范围转换不同单位
        if (value.compareTo(kg) < 0) {
            newvalue = NumberUtil.formateScale2Str(value) + " kg";
        } else if (value.compareTo(kg) > 0 && value.compareTo(t) < 0) {
            tempvalue = value.divide(BigDecimal.valueOf(1000));
            newvalue = NumberUtil.formateScale2Str(tempvalue) + " t";
        } else if (value.compareTo(t) > 0) {
            tempvalue = value.divide(BigDecimal.valueOf(1000000));
            newvalue = NumberUtil.formateScale2Str(tempvalue) + " kt";
        }
        return newvalue;
    }
}

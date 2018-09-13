package com.holley.elecsafe.common.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * 设备类型
 * 
 * @author lenovo
 */
public enum DetectorTypeEnum {
    SINGLE_PHASE(1, "单相"), THREE_PHASE(2, "三相");

    private final int    value;
    private final String text;

    DetectorTypeEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public Short getShortValue() {
        return ((Integer) value).shortValue();
    }

    public String getText() {
        return text;
    }

    /**
     * 通过传入的字符串匹配枚举，传入值
     * 
     * @param value
     * @return
     */
    public static DetectorTypeEnum getEnmuByValue(int value) {
        for (DetectorTypeEnum powerType : DetectorTypeEnum.values()) {
            if (value == powerType.getValue()) {
                return powerType;
            }
        }
        return null;
    }

    /**
     * 通过传入的字符串匹配枚举,传入名字
     * 
     * @param name
     * @return
     */
    public static DetectorTypeEnum getEnmuByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        for (DetectorTypeEnum powerType : DetectorTypeEnum.values()) {
            if (StringUtils.equals(name, powerType.toString())) {
                return powerType;
            }
        }
        return null;
    }
}

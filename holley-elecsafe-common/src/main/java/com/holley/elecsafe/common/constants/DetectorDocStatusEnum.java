package com.holley.elecsafe.common.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * 设备档案状态
 * 
 * @author sc
 */
public enum DetectorDocStatusEnum {
    NORMAL(1, "正常"), DELETE(2, "删除");

    private final int    value;
    private final String text;

    DetectorDocStatusEnum(int value, String text) {
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
    public static DetectorDocStatusEnum getEnmuByValue(int value) {
        for (DetectorDocStatusEnum powerType : DetectorDocStatusEnum.values()) {
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
    public static DetectorDocStatusEnum getEnmuByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        for (DetectorDocStatusEnum powerType : DetectorDocStatusEnum.values()) {
            if (StringUtils.equals(name, powerType.toString())) {
                return powerType;
            }
        }
        return null;
    }
}

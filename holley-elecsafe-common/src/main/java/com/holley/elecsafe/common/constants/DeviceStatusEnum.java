package com.holley.elecsafe.common.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * 设备状态
 * 
 * @author sc
 */
public enum DeviceStatusEnum {
    NORMAL(1, "正常"), WARING(2, "告警"), FAULT(3, "故障"), OFFLINE(4, "离线");

    private final int    value;
    private final String text;

    DeviceStatusEnum(int value, String text) {
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
    public static DeviceStatusEnum getEnmuByValue(int value) {
        for (DeviceStatusEnum powerType : DeviceStatusEnum.values()) {
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
    public static DeviceStatusEnum getEnmuByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        for (DeviceStatusEnum powerType : DeviceStatusEnum.values()) {
            if (StringUtils.equals(name, powerType.toString())) {
                return powerType;
            }
        }
        return null;
    }
}

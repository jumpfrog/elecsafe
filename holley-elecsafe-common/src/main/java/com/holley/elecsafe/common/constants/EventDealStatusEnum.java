package com.holley.elecsafe.common.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * 事件处理状态
 * 
 * @author zdd
 */
public enum EventDealStatusEnum {
                                 UNPROCESSED(1, "未处理"), // <br/>
                                 PROCESSED(2, "已处理");// <br/>

    private final int    value;
    private final String text;

    EventDealStatusEnum(int value, String text) {
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
    public static EventDealStatusEnum getEnmuByValue(int value) {
        for (EventDealStatusEnum powerType : EventDealStatusEnum.values()) {
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
    public static EventDealStatusEnum getEnmuByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        for (EventDealStatusEnum powerType : EventDealStatusEnum.values()) {
            if (StringUtils.equals(name, powerType.toString())) {
                return powerType;
            }
        }
        return null;
    }
}

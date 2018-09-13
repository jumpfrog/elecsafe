package com.holley.elecsafe.common.constants;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 事件类型
 * 
 * @author zdd
 */
public enum DetectorEventTypeEnum {
                                   ALARM_1(1, "漏电流报警"), // <br/>
                                   ALARM_2(2, "温度报警"), // <br/>
                                   ALARM_3(3, "欠压报警"), // <br/>
                                   ALARM_4(4, "过压报警"), // <br/>
                                   ALARM_5(5, "过流报警"), // <br/>
                                   ALARM_10(10, "压力过压"), // <br/>

                                   FAULT_101(101, "漏电流故障"), // <br/>
                                   FAULT_102(102, "温度故障"), // <br/>
                                   FAULT_103(103, "供电中断"), // <br/>
                                   FAULT_104(104, "错相"), // <br/>
                                   FAULT_105(105, "缺相"), // <br/>
                                   FAULT_106(106, "电弧故障"), // <br/>
                                   FAULT_107(107, "负载故障"), // <br/>
                                   FAULT_108(108, "短路故障"), // <br/>
                                   FAULT_109(109, "断路故障"); // <br/>;

    private final int    value;
    private final String text;

    DetectorEventTypeEnum(int value, String text) {
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
    public static DetectorEventTypeEnum getEnmuByValue(int value) {
        for (DetectorEventTypeEnum powerType : DetectorEventTypeEnum.values()) {
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
    public static DetectorEventTypeEnum getEnmuByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        for (DetectorEventTypeEnum powerType : DetectorEventTypeEnum.values()) {
            if (StringUtils.equals(name, powerType.toString())) {
                return powerType;
            }
        }
        return null;
    }

    /**
     * 告警事件类型<br/>
     * value <= 100
     * 
     * @return
     */
    public static List<DetectorEventTypeEnum> getAlarmEvent() {
        List<DetectorEventTypeEnum> list = new ArrayList<DetectorEventTypeEnum>();
        for (DetectorEventTypeEnum record : DetectorEventTypeEnum.values()) {
            if (record.getValue() <= 100) {
                list.add(record);
            }
        }
        return list;
    }

    /**
     * 故障事件类型<br/>
     * value > 100
     * 
     * @return
     */
    public static List<DetectorEventTypeEnum> getFaultEvent() {
        List<DetectorEventTypeEnum> list = new ArrayList<DetectorEventTypeEnum>();
        for (DetectorEventTypeEnum record : DetectorEventTypeEnum.values()) {
            if (record.getValue() > 100) {
                list.add(record);
            }
        }
        return list;
    }
}

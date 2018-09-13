package com.holley.elecsafe.common.cache;

/**
 * 缓存主键定义规则
 * 
 * @author zdd
 */
public class CacheKeyProvide {

    public static String KEY_ES_IMG_VALIDATE                  = "keyEsImgValidate_";              // 图片验证码

    public static String KEY_ES_WEB_LOGIN_BEAN                = "keyEsWebLoginBean_";             // 用户登录失败次数

    public static String KEY_ES_WEB_SESSION                   = "keyEsWebSession_";               // 登录session

    public static String KEY_ES_DCS_DETECTOR_UPDATETIME       = "keyEsDcsDetectorUpdateTime";     // 前置漏电探测器更新时间

    public static String KEY_ES_DCS_DAT_ES_DETECTOR_L1_REAL   = "keyEsDcsDatEsDetectorL1Real";    // 一级数据

    public static String KEY_ES_DCS_DAT_ES_DETECTOR_L2_REAL   = "keyEsDcsDatEsDetectorL2Real";    // 二级数据

    public static String KEY_ES_DCS_DAT_ES_DETECTOR_EVENT     = "keyEsDcsDatEsDetectorEvent";     // 告警记录

    public static String KEY_ES_DCS_DAT_ES_DETECTOR_CURSTATUS = "keyEsDcsDatEsDetectorCurstatus"; // 设备状态

    // 采集数据
    // public static String KEY_ES_LIST_INVERTER = "keyPvListInverterMsg"; // 逆变器采集数据
    // public static String KEY_ES_LIST_COMBINERBOX = "keyPvListCombinerboxMsg"; // 汇流箱采集数据
    // public static String KEY_ES_LIST_EMI = "keyPvListEmiMsg"; // 环境监测仪采集数据
    //
    // public static String KEY_ES_LIST_INVERTER_HIS = "keyPvListInverterHisMsg"; // 逆变器采集数据
    // public static String KEY_ES_LIST_COMBINERBOX_HIS = "keyPvListCombinerboxHisMsg"; // 汇流箱采集数据
    // public static String KEY_ES_LIST_EMI_HIS = "keyPvListEmiHisMsg"; // 环境监测仪采集数据
    //
    // public static String KEY_ES_LIST_DEVICE_FAULT = "keyPvListDeviceFaultMsg"; // 设备故障记录

    public static String getKey(String suffex, String key) {
        return suffex + key;
    }

}

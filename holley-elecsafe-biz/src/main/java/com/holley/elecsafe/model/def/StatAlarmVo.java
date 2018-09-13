package com.holley.elecsafe.model.def;

import java.util.List;

/**
 * 报警统计VO
 * 
 * @author sc
 */
public class StatAlarmVo {

    private String              startTime;
    private String              endTime;
    private String              entName;
    private List<StatDetDataVo> statDetList; // echart数据
    private int                 allAlarm;    // 总报警次数
    private int                 allIlAlarm;  // 总剩余电流报警次数
    private int                 allIAlarm;   // 总电流报警次数
    private int                 allTAlarm;   // 总温度报警次数
    private int                 allFault;    // 总故障次数
    private List<DetectorEvent> detEventList; // 事件数据

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public List<StatDetDataVo> getStatDetList() {
        return statDetList;
    }

    public void setStatDetList(List<StatDetDataVo> statDetList) {
        this.statDetList = statDetList;
    }

    public int getAllAlarm() {
        return allAlarm;
    }

    public void setAllAlarm(int allAlarm) {
        this.allAlarm = allAlarm;
    }

    public int getAllIlAlarm() {
        return allIlAlarm;
    }

    public void setAllIlAlarm(int allIlAlarm) {
        this.allIlAlarm = allIlAlarm;
    }

    public int getAllIAlarm() {
        return allIAlarm;
    }

    public void setAllIAlarm(int allIAlarm) {
        this.allIAlarm = allIAlarm;
    }

    public int getAllTAlarm() {
        return allTAlarm;
    }

    public void setAllTAlarm(int allTAlarm) {
        this.allTAlarm = allTAlarm;
    }

    public int getAllFault() {
        return allFault;
    }

    public void setAllFault(int allFault) {
        this.allFault = allFault;
    }

    public List<DetectorEvent> getDetEventList() {
        return detEventList;
    }

    public void setDetEventList(List<DetectorEvent> detEventList) {
        this.detEventList = detEventList;
    }

}

package com.holley.elecsafe.model.def;

public class EntInfoStat {

    private String scale;       // 装机容量
    private int    stationcount;// 电站数量
    private String totalenergy; // 累计发电量
    private String saving;      // 节能减排量
    private String unit;        // 节能减排单位

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public int getStationcount() {
        return stationcount;
    }

    public void setStationcount(int stationcount) {
        this.stationcount = stationcount;
    }

    public String getTotalenergy() {
        return totalenergy;
    }

    public void setTotalenergy(String totalenergy) {
        this.totalenergy = totalenergy;
    }

    public String getSaving() {
        return saving;
    }

    public void setSaving(String saving) {
        this.saving = saving;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}

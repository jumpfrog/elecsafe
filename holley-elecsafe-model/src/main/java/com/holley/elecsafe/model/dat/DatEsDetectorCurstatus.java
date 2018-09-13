package com.holley.elecsafe.model.dat;

import java.util.Date;

public class DatEsDetectorCurstatus {
    private Integer detid;

    private Date datatime;

    private Short isalarm;

    private Short isfault;

    private Short isoffline;

    public Integer getDetid() {
        return detid;
    }

    public void setDetid(Integer detid) {
        this.detid = detid;
    }

    public Date getDatatime() {
        return datatime;
    }

    public void setDatatime(Date datatime) {
        this.datatime = datatime;
    }

    public Short getIsalarm() {
        return isalarm;
    }

    public void setIsalarm(Short isalarm) {
        this.isalarm = isalarm;
    }

    public Short getIsfault() {
        return isfault;
    }

    public void setIsfault(Short isfault) {
        this.isfault = isfault;
    }

    public Short getIsoffline() {
        return isoffline;
    }

    public void setIsoffline(Short isoffline) {
        this.isoffline = isoffline;
    }
}
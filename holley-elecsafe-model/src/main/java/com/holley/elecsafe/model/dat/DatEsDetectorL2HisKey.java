package com.holley.elecsafe.model.dat;

import java.util.Date;

public class DatEsDetectorL2HisKey {
    private Date datatime;

    private Integer detid;

    public Date getDatatime() {
        return datatime;
    }

    public void setDatatime(Date datatime) {
        this.datatime = datatime;
    }

    public Integer getDetid() {
        return detid;
    }

    public void setDetid(Integer detid) {
        this.detid = detid;
    }
}
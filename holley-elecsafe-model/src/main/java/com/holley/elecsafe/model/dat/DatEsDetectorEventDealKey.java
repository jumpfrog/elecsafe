package com.holley.elecsafe.model.dat;

import java.util.Date;

public class DatEsDetectorEventDealKey {
    private Date dealtime;

    private Integer evtid;

    public Date getDealtime() {
        return dealtime;
    }

    public void setDealtime(Date dealtime) {
        this.dealtime = dealtime;
    }

    public Integer getEvtid() {
        return evtid;
    }

    public void setEvtid(Integer evtid) {
        this.evtid = evtid;
    }
}
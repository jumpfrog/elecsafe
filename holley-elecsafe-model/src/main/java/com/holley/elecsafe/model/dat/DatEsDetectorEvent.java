package com.holley.elecsafe.model.dat;

import java.util.Date;

public class DatEsDetectorEvent {
    private Integer evtid;

    private Integer detid;

    private Date datatime;

    private Short evttype;

    private String evtdesc;

    private Short dealstatus;

    private Date dealtime;

    private String dealremark;

    private String dealaccount;

    private Date updatetime;

    public Integer getEvtid() {
        return evtid;
    }

    public void setEvtid(Integer evtid) {
        this.evtid = evtid;
    }

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

    public Short getEvttype() {
        return evttype;
    }

    public void setEvttype(Short evttype) {
        this.evttype = evttype;
    }

    public String getEvtdesc() {
        return evtdesc;
    }

    public void setEvtdesc(String evtdesc) {
        this.evtdesc = evtdesc == null ? null : evtdesc.trim();
    }

    public Short getDealstatus() {
        return dealstatus;
    }

    public void setDealstatus(Short dealstatus) {
        this.dealstatus = dealstatus;
    }

    public Date getDealtime() {
        return dealtime;
    }

    public void setDealtime(Date dealtime) {
        this.dealtime = dealtime;
    }

    public String getDealremark() {
        return dealremark;
    }

    public void setDealremark(String dealremark) {
        this.dealremark = dealremark == null ? null : dealremark.trim();
    }

    public String getDealaccount() {
        return dealaccount;
    }

    public void setDealaccount(String dealaccount) {
        this.dealaccount = dealaccount == null ? null : dealaccount.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
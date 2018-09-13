package com.holley.elecsafe.model.es;

import java.math.BigDecimal;
import java.util.Date;

public class EsDetectorThreshold {
    private Integer id;

    private BigDecimal overu;

    private BigDecimal underu;

    private BigDecimal overi;

    private BigDecimal il;

    private BigDecimal ta;

    private BigDecimal tb;

    private BigDecimal tc;

    private BigDecimal tn;

    private BigDecimal tl;

    private BigDecimal ti;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getOveru() {
        return overu;
    }

    public void setOveru(BigDecimal overu) {
        this.overu = overu;
    }

    public BigDecimal getUnderu() {
        return underu;
    }

    public void setUnderu(BigDecimal underu) {
        this.underu = underu;
    }

    public BigDecimal getOveri() {
        return overi;
    }

    public void setOveri(BigDecimal overi) {
        this.overi = overi;
    }

    public BigDecimal getIl() {
        return il;
    }

    public void setIl(BigDecimal il) {
        this.il = il;
    }

    public BigDecimal getTa() {
        return ta;
    }

    public void setTa(BigDecimal ta) {
        this.ta = ta;
    }

    public BigDecimal getTb() {
        return tb;
    }

    public void setTb(BigDecimal tb) {
        this.tb = tb;
    }

    public BigDecimal getTc() {
        return tc;
    }

    public void setTc(BigDecimal tc) {
        this.tc = tc;
    }

    public BigDecimal getTn() {
        return tn;
    }

    public void setTn(BigDecimal tn) {
        this.tn = tn;
    }

    public BigDecimal getTl() {
        return tl;
    }

    public void setTl(BigDecimal tl) {
        this.tl = tl;
    }

    public BigDecimal getTi() {
        return ti;
    }

    public void setTi(BigDecimal ti) {
        this.ti = ti;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
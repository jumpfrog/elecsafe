package com.holley.elecsafe.model.def;

import java.math.BigDecimal;

import com.holley.elecsafe.common.constants.DetectorTypeEnum;
import com.holley.elecsafe.model.es.EsDetector;
import com.holley.platform.common.util.DateUtil;

public class EsDetectorVo extends EsDetector {

    private String     entName;
    private String     brand;       // 监测点型号
    private String     protocolName; // 协议名称
    // 阀值
    private BigDecimal overu;

    private BigDecimal underu;

    private BigDecimal overi;

    private BigDecimal il;

    private BigDecimal tb;

    private BigDecimal ta;

    private BigDecimal tc;

    private BigDecimal tn;

    private BigDecimal tl;

    private BigDecimal ti;

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
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

    public BigDecimal getTb() {
        return tb;
    }

    public void setTb(BigDecimal tb) {
        this.tb = tb;
    }

    public BigDecimal getTa() {
        return ta;
    }

    public void setTa(BigDecimal ta) {
        this.ta = ta;
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

    public String getUpdatetimeStr() {
        return DateUtil.DateToStr(getUpdatetime(), "yyyy-MM-dd HH:mm:ss");
    }

    public String getTypeStr() {
        return getType() == null ? "" : DetectorTypeEnum.getEnmuByValue(getType()).getText();
    }
}

package com.holley.elecsafe.model.def;

import java.math.BigDecimal;

import com.holley.elecsafe.common.constants.DetectorTypeEnum;
import com.holley.elecsafe.model.dat.DatEsDetectorL1His;
import com.holley.platform.common.util.DateUtil;

public class DatEsDetectorL1HisVo extends DatEsDetectorL1His {

    private Short      type;      // 单相 三相
    private String     name;      // 设备名称
    // 阀值
    private BigDecimal holdOveru;

    private BigDecimal holdUnderu;

    private BigDecimal holdOveri;

    private BigDecimal holdIl;

    private BigDecimal holdTb;

    private BigDecimal holdTa;

    private BigDecimal holdTc;

    private BigDecimal holdTn;

    private BigDecimal holdTl;

    private BigDecimal holdTi;

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getHoldOveru() {
        return holdOveru;
    }

    public void setHoldOveru(BigDecimal holdOveru) {
        this.holdOveru = holdOveru;
    }

    public BigDecimal getHoldUnderu() {
        return holdUnderu;
    }

    public void setHoldUnderu(BigDecimal holdUnderu) {
        this.holdUnderu = holdUnderu;
    }

    public BigDecimal getHoldOveri() {
        return holdOveri;
    }

    public void setHoldOveri(BigDecimal holdOveri) {
        this.holdOveri = holdOveri;
    }

    public BigDecimal getHoldIl() {
        return holdIl;
    }

    public void setHoldIl(BigDecimal holdIl) {
        this.holdIl = holdIl;
    }

    public BigDecimal getHoldTb() {
        return holdTb;
    }

    public void setHoldTb(BigDecimal holdTb) {
        this.holdTb = holdTb;
    }

    public BigDecimal getHoldTa() {
        return holdTa;
    }

    public void setHoldTa(BigDecimal holdTa) {
        this.holdTa = holdTa;
    }

    public BigDecimal getHoldTc() {
        return holdTc;
    }

    public void setHoldTc(BigDecimal holdTc) {
        this.holdTc = holdTc;
    }

    public BigDecimal getHoldTn() {
        return holdTn;
    }

    public void setHoldTn(BigDecimal holdTn) {
        this.holdTn = holdTn;
    }

    public BigDecimal getHoldTl() {
        return holdTl;
    }

    public void setHoldTl(BigDecimal holdTl) {
        this.holdTl = holdTl;
    }

    public BigDecimal getHoldTi() {
        return holdTi;
    }

    public void setHoldTi(BigDecimal holdTi) {
        this.holdTi = holdTi;
    }

    public String getDatatimeStr() {
        return DateUtil.DateToStr(getDatatime(), "MM-dd HH:mm:ss");
    }

    public String getLongDatatimeStr() {
        return DateUtil.DateToStr(getDatatime(), "yyyy-MM-dd HH:mm:ss");
    }

    public String getTypeStr() {
        return type == null ? "" : DetectorTypeEnum.getEnmuByValue(type).getText();
    }
}

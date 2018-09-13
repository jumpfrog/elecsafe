package com.holley.elecsafe.model.def;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.holley.elecsafe.model.dat.DatEsDetectorCurstatus;
import com.holley.platform.common.util.DateUtil;

/**
 * 设备状态
 * 
 * @author sc
 */
public class RealDataDetVo extends DatEsDetectorCurstatus {

    private String                     name;
    private String                     brand;
    private List<DatEsDetectorL1HisVo> detL1HisList;
    private Short                      type;        // 单相，三相

    // 阀值
    private BigDecimal                 overu;

    private BigDecimal                 underu;

    private BigDecimal                 overi;

    private BigDecimal                 il;

    private BigDecimal                 tb;

    private BigDecimal                 ta;

    private BigDecimal                 tc;

    private BigDecimal                 tn;

    private BigDecimal                 tl;

    private BigDecimal                 ti;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<DatEsDetectorL1HisVo> getDetL1HisList() {
        return detL1HisList;
    }

    public void setDetL1HisList(List<DatEsDetectorL1HisVo> detL1HisList) {
        this.detL1HisList = detL1HisList;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
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

    public String getUpdateTimeStr() {
        Date temp = getDatatime();
        return DateUtil.DateToLongStr(temp);
    }
}

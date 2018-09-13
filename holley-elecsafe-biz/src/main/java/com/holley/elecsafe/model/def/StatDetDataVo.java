package com.holley.elecsafe.model.def;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StatDetDataVo {

    private String                     detName;                                              // 监测点名称
    private Integer                    detId;                                                // 监测点Id
    private Short                      type;                                                 // 单相三相
    private int                        ilAlarm;                                              // 剩余电流报警次数
    private int                        iAlarm;                                               // 线缆电流报警次数
    private int                        tAlarm;
    private int                        fault;
    // 线缆温度报警次数
    private List<DatEsDetectorL1HisVo> detDataIList  = new ArrayList<DatEsDetectorL1HisVo>(); // 电流数据
    private List<DatEsDetectorL1HisVo> detDataTList  = new ArrayList<DatEsDetectorL1HisVo>(); // 温度数据
    private List<DatEsDetectorL1HisVo> detDataILList = new ArrayList<DatEsDetectorL1HisVo>(); // 剩余电流数据
    // 阀值
    private BigDecimal                 holdOveru;

    private BigDecimal                 holdUnderu;

    private BigDecimal                 holdOveri;

    private BigDecimal                 holdIl;

    private BigDecimal                 holdTb;

    private BigDecimal                 holdTa;

    private BigDecimal                 holdTc;

    private BigDecimal                 holdTn;

    private BigDecimal                 holdTl;

    private BigDecimal                 holdTi;

    public String getDetName() {
        return detName;
    }

    public void setDetName(String detName) {
        this.detName = detName;
    }

    public Integer getDetId() {
        return detId;
    }

    public void setDetId(Integer detId) {
        this.detId = detId;
    }

    public int getIlAlarm() {
        return ilAlarm;
    }

    public void setIlAlarm(int ilAlarm) {
        this.ilAlarm = ilAlarm;
    }

    public int getiAlarm() {
        return iAlarm;
    }

    public void setiAlarm(int iAlarm) {
        this.iAlarm = iAlarm;
    }

    public int gettAlarm() {
        return tAlarm;
    }

    public void settAlarm(int tAlarm) {
        this.tAlarm = tAlarm;
    }

    public int getFault() {
        return fault;
    }

    public void setFault(int fault) {
        this.fault = fault;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public List<DatEsDetectorL1HisVo> getDetDataIList() {
        return detDataIList;
    }

    public void setDetDataIList(List<DatEsDetectorL1HisVo> detDataIList) {
        this.detDataIList = detDataIList;
    }

    public List<DatEsDetectorL1HisVo> getDetDataTList() {
        return detDataTList;
    }

    public void setDetDataTList(List<DatEsDetectorL1HisVo> detDataTList) {
        this.detDataTList = detDataTList;
    }

    public List<DatEsDetectorL1HisVo> getDetDataILList() {
        return detDataILList;
    }

    public void setDetDataILList(List<DatEsDetectorL1HisVo> detDataILList) {
        this.detDataILList = detDataILList;
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

}

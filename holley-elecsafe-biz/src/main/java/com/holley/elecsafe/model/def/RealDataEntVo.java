package com.holley.elecsafe.model.def;

import java.math.BigDecimal;

import com.holley.platform.common.util.NumberUtil;

public class RealDataEntVo {

    private int    eid;          // 运营商/企业id
    private String name;         // 运营商/企业名称
                                  // private String normalPer = "0.0"; // 正常率
                                  // private String waringPer = "0.0"; // 告警率
                                  // private String faultPer = "0.0"; // 故障率
                                  // private String offLinePer = "0.0"; // 离线率
    private int    normal;
    private int    waring;
    private int    fault;
    private int    offLine;
    private int    detCount;
    private String updateTimeStr; // YYYY-MM-DD HH:MM:SS

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNormalPer() {
        int temtotal = getTotalStatus();
        if (temtotal == 0) {
            return "0.00";
        }
        return NumberUtil.div(new BigDecimal(normal * 100), new BigDecimal(temtotal)).toString();
    }

    public String getWaringPer() {
        int temtotal = getTotalStatus();
        if (temtotal == 0) {
            return "0.00";
        }
        return NumberUtil.div(new BigDecimal(waring * 100), new BigDecimal(temtotal)).toString();
    }

    public String getFaultPer() {
        int temtotal = getTotalStatus();
        if (temtotal == 0) {
            return "0.00";
        }
        return NumberUtil.div(new BigDecimal(fault * 100), new BigDecimal(temtotal)).toString();
    }

    public String getOffLinePer() {
        int temtotal = getTotalStatus();
        if (temtotal == 0) {
            return "0.00";
        }
        return NumberUtil.div(new BigDecimal(offLine * 100), new BigDecimal(temtotal)).toString();
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getWaring() {
        return waring;
    }

    public void setWaring(int waring) {
        this.waring = waring;
    }

    public int getFault() {
        return fault;
    }

    public void setFault(int fault) {
        this.fault = fault;
    }

    public int getOffLine() {
        return offLine;
    }

    public void setOffLine(int offLine) {
        this.offLine = offLine;
    }

    public int getDetCount() {
        return detCount;
    }

    public void setDetCount(int detCount) {
        this.detCount = detCount;
    }

    public int getTotalStatus() {
        return normal + waring + fault + offLine;
    }
}

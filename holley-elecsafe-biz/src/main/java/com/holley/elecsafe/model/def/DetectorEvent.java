package com.holley.elecsafe.model.def;

import java.util.Arrays;
import java.util.List;

import com.holley.elecsafe.common.constants.DetectorEventTypeEnum;
import com.holley.elecsafe.common.constants.EventDealStatusEnum;
import com.holley.elecsafe.model.dat.DatEsDetectorEvent;
import com.holley.platform.common.util.DateUtil;

public class DetectorEvent extends DatEsDetectorEvent {

    private String detname;  // 监测点名称
    private String ownername; // 所属单位

    public String getDetname() {
        return detname;
    }

    public void setDetname(String detname) {
        this.detname = detname;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    // desc*********************************************
    public String getDatatimestr() {
        if (getDatatime() == null) return "";
        return DateUtil.DateToLongStr(getDatatime());
    }

    public String getEvttypename() {
        if (getEvttype() == null) return "";
        DetectorEventTypeEnum type = DetectorEventTypeEnum.getEnmuByValue(getEvttype().intValue());
        return type == null ? "" : type.getText();
    }

    public String getDealstatusname() {
        if (getDealstatus() == null) return "";
        EventDealStatusEnum status = EventDealStatusEnum.getEnmuByValue(getDealstatus().intValue());
        return status == null ? "" : status.getText();
    }

    public String getDealtimestr() {
        if (getDealtime() == null) return "";
        return DateUtil.DateToLongStr(getDealtime());
    }

    public boolean getIsAlarm() {
        Short[] alarms = { 1, 2, 3, 4, 5, 10 };
        List<Short> list = Arrays.asList(alarms);
        return list.contains(getEvttype());
    }
}

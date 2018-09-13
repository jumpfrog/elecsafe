package com.holley.elecsafe.model.def;

import com.holley.elecsafe.model.dat.DatEsDetectorEventDeal;
import com.holley.platform.common.util.DateUtil;

public class DetEventDeal extends DatEsDetectorEventDeal {

    private String dealaccountname;

    public String getDealaccountname() {
        return dealaccountname;
    }

    public void setDealaccountname(String dealaccountname) {
        this.dealaccountname = dealaccountname;
    }

    // desc*********************************************
    public String getDealtimestr() {
        if (getDealtime() == null) return "";
        return DateUtil.DateToLongStr(getDealtime());
    }

}

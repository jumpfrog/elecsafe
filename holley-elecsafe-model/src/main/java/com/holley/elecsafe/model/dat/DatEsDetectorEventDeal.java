package com.holley.elecsafe.model.dat;

public class DatEsDetectorEventDeal extends DatEsDetectorEventDealKey {
    private String dealremark;

    private String dealaccount;

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
}
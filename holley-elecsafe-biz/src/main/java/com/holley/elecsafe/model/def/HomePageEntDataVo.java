package com.holley.elecsafe.model.def;

public class HomePageEntDataVo {

    private int    eid;
    private String lng;
    private String lat;
    private int    detCount;
    private int    normal;
    private String entname;
    private int    waring;
    private int    fault;
    private int    offLine;

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

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public int getDetCount() {
        return detCount;
    }

    public void setDetCount(int detCount) {
        this.detCount = detCount;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public String getEntname() {
        return entname;
    }

    public void setEntname(String entname) {
        this.entname = entname;
    }

}

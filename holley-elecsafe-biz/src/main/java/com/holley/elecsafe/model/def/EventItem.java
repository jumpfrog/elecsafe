package com.holley.elecsafe.model.def;

public class EventItem {

    private Short  eventitem;    // 事件数据项
    private String eventitemname;// 事件数据项名称

    public Short getEventitem() {
        return eventitem;
    }

    public void setEventitem(Short eventitem) {
        this.eventitem = eventitem;
    }

    public String getEventitemname() {
        return eventitemname;
    }

    public void setEventitemname(String eventitemname) {
        this.eventitemname = eventitemname;
    }

}

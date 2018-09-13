package com.holley.elecsafe.model.def;

import java.util.List;

public class EventClassItem {

    private Short           eventclass;    // 事件分类
    private String          eventclassname;// 事件分类名称
    private List<EventItem> eventitems;    // 事件数据项

    public Short getEventclass() {
        return eventclass;
    }

    public void setEventclass(Short eventclass) {
        this.eventclass = eventclass;
    }

    public String getEventclassname() {
        return eventclassname;
    }

    public void setEventclassname(String eventclassname) {
        this.eventclassname = eventclassname;
    }

    public List<EventItem> getEventitems() {
        return eventitems;
    }

    public void setEventitems(List<EventItem> eventitems) {
        this.eventitems = eventitems;
    }

}

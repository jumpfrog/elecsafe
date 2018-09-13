package com.holley.elecsafe.save.run;

import java.util.List;

import com.holley.elecsafe.model.dat.DatEsDetectorEvent;
import com.holley.elecsafe.save.dao.DatEsDetectorEventMapper;

public class DatEsDetectorEventRunable implements Runnable {

    List<DatEsDetectorEvent> eventList;
    DatEsDetectorEventMapper datEsDetectorEventMapper;

    public DatEsDetectorEventRunable(List<DatEsDetectorEvent> eventList, DatEsDetectorEventMapper datEsDetectorEventMapper) {
        this.eventList = eventList;
        this.datEsDetectorEventMapper = datEsDetectorEventMapper;
    }

    @Override
    public void run() {
        if (eventList != null && eventList.size() > 0) {

            datEsDetectorEventMapper.insertEventList(eventList);

        }
    }

}

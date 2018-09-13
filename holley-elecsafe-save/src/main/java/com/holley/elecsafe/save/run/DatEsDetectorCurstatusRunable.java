package com.holley.elecsafe.save.run;

import java.util.List;

import com.holley.elecsafe.model.dat.DatEsDetectorCurstatus;
import com.holley.elecsafe.save.dao.DatEsDetectorCurstatusMapper;

public class DatEsDetectorCurstatusRunable implements Runnable {

    List<DatEsDetectorCurstatus>         curstatusList;
    private DatEsDetectorCurstatusMapper datEsDetectorCurstatusMapper;

    public DatEsDetectorCurstatusRunable(List<DatEsDetectorCurstatus> curstatusList, DatEsDetectorCurstatusMapper datEsDetectorCurstatusMapper) {
        this.curstatusList = curstatusList;
        this.datEsDetectorCurstatusMapper = datEsDetectorCurstatusMapper;
    }

    @Override
    public void run() {
        if (curstatusList != null && curstatusList.size() > 0) {

            datEsDetectorCurstatusMapper.updateCurstatusList(curstatusList);

        }
    }

}

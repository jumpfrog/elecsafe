package com.holley.elecsafe.save.run;

import java.util.List;

import com.holley.elecsafe.model.dat.DatEsDetectorL2Real;
import com.holley.elecsafe.save.dao.DatEsDetectorL2RealMapper;

public class DatEsDetectorL2RealRunable implements Runnable {

    List<DatEsDetectorL2Real> detectorList;
    DatEsDetectorL2RealMapper datEsDetectorL2RealMapper;

    public DatEsDetectorL2RealRunable(List<DatEsDetectorL2Real> detectorList, DatEsDetectorL2RealMapper datEsDetectorL2RealMapper) {
        this.detectorList = detectorList;
        this.datEsDetectorL2RealMapper = datEsDetectorL2RealMapper;
    }

    @Override
    public void run() {
        if (detectorList != null && detectorList.size() > 0) {

            datEsDetectorL2RealMapper.insertDetectorList(detectorList);

            datEsDetectorL2RealMapper.insertDetectorListHis(detectorList);
        }
    }

}

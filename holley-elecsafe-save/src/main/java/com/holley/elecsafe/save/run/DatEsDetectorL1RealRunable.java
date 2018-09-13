package com.holley.elecsafe.save.run;

import java.util.List;

import com.holley.elecsafe.model.dat.DatEsDetectorL1Real;
import com.holley.elecsafe.save.dao.DatEsDetectorL1RealMapper;

public class DatEsDetectorL1RealRunable implements Runnable {

    List<DatEsDetectorL1Real> detectorList;
    DatEsDetectorL1RealMapper datEsDetectorL1RealMapper;

    public DatEsDetectorL1RealRunable(List<DatEsDetectorL1Real> detectorList, DatEsDetectorL1RealMapper datEsDetectorL1RealMapper) {
        this.detectorList = detectorList;
        this.datEsDetectorL1RealMapper = datEsDetectorL1RealMapper;
    }

    @Override
    public void run() {
        if (detectorList != null && detectorList.size() > 0) {

            datEsDetectorL1RealMapper.insertDetectorList(detectorList);

            datEsDetectorL1RealMapper.insertDetectorListHis(detectorList);
        }
    }

}

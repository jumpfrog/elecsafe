package com.holley.elecsafe.save.dao;

import java.util.List;

import com.holley.elecsafe.model.dat.DatEsDetectorL1Real;

public interface DatEsDetectorL1RealMapper {

    void insertDetectorList(List<DatEsDetectorL1Real> detectorList);

    void insertDetectorListHis(List<DatEsDetectorL1Real> detectorList);

}

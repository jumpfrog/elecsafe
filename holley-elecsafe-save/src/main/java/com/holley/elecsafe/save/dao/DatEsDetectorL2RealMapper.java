package com.holley.elecsafe.save.dao;

import java.util.List;

import com.holley.elecsafe.model.dat.DatEsDetectorL2Real;

public interface DatEsDetectorL2RealMapper {

    void insertDetectorList(List<DatEsDetectorL2Real> detectorList);

    void insertDetectorListHis(List<DatEsDetectorL2Real> detectorList);

}

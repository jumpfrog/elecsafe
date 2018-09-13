package com.holley.elecsafe.save.dao;

import java.util.List;

import com.holley.elecsafe.model.dat.DatEsDetectorEvent;

public interface DatEsDetectorEventMapper {

    void insertEventList(List<DatEsDetectorEvent> list);

}

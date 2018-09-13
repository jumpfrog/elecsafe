package com.holley.elecsafe.service;

import java.util.Date;
import java.util.List;

import com.holley.elecsafe.model.es.EsDetector;

public interface IDataBaseService {

    public void initDataBase();

    public List<EsDetector> loadDevices();

    public List<EsDetector> reLoadDevices(Date freshTime);

}

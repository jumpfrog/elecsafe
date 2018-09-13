package com.holley.elecsafe.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.holley.elecsafe.dao.EsDetectorMapper;
import com.holley.elecsafe.model.es.EsDetector;

public class DataBaseService extends BaseService implements IDataBaseService {

    @Autowired
    EsDetectorMapper esDetectorMapper;

    public void initDataBase() {

    }

    @Override
    public List<EsDetector> loadDevices() {
        List<EsDetector> list = esDetectorMapper.selectAllEsDetector(new HashMap<String, Object>());
        return list;
    }

    @Override
    public List<EsDetector> reLoadDevices(Date freshTime) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("updatetime", freshTime);
        List<EsDetector> list = esDetectorMapper.selectAllEsDetector(param);
        return list;
    }

}

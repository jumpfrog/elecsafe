package com.holley.elecsafe.dao;

import java.util.List;
import java.util.Map;

import com.holley.elecsafe.model.es.EsDetector;

public interface EsDetectorMapper {

    List<EsDetector> selectAllEsDetector(Map<String, Object> param);

}

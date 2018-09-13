package com.holley.elecsafe.serviceimpl.es;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.holley.elecsafe.dao.dat.DatEsDetectorL1HisMapper;
import com.holley.elecsafe.model.def.DatEsDetectorL1HisVo;
import com.holley.elecsafe.service.es.HistoryDataService;

public class HistoryDataServiceImpl implements HistoryDataService {

    @Resource
    private DatEsDetectorL1HisMapper datEsDetectorL1HisMapper;

    @Override
    public List<DatEsDetectorL1HisVo> selectDatEsDetectorL1HisByPage(Map<String, Object> map) {
        return datEsDetectorL1HisMapper.selectDatEsDetectorL1HisByPage(map);
    }

}

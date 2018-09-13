package com.holley.elecsafe.serviceimpl.dat;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.holley.elecsafe.dao.dat.DatEsDetectorEventMapper;
import com.holley.elecsafe.dao.dat.DatEsDetectorL1HisMapper;
import com.holley.elecsafe.dao.es.EsDetectorMapper;
import com.holley.elecsafe.model.dat.DatEsDetectorEvent;
import com.holley.elecsafe.model.dat.DatEsDetectorEventExample;
import com.holley.elecsafe.model.dat.DatEsDetectorL1His;
import com.holley.elecsafe.model.dat.DatEsDetectorL1HisExample;
import com.holley.elecsafe.model.def.DetectorEvent;
import com.holley.elecsafe.model.def.EsDetectorVo;
import com.holley.elecsafe.service.dat.DatStaEsService;

public class DatStaEsServiceImpl implements DatStaEsService {

    @Resource
    private DatEsDetectorEventMapper datEsDetectorEventMapper;
    @Resource
    private EsDetectorMapper         esDetectorMapper;
    @Resource
    private DatEsDetectorL1HisMapper datEsDetectorL1HisMapper;

    @Override
    public List<DatEsDetectorEvent> selectDetectorEventByExample(DatEsDetectorEventExample example) {
        return datEsDetectorEventMapper.selectByExample(example);
    }

    @Override
    public List<EsDetectorVo> selectDetectorByEid(Map<String, Object> map) {
        return esDetectorMapper.selectDetectorByEid(map);
    }

    @Override
    public List<DatEsDetectorL1His> selectDetectorL1HisByExample(DatEsDetectorL1HisExample example) {
        return datEsDetectorL1HisMapper.selectByExample(example);
    }

    @Override
    public List<DetectorEvent> selectDetEventForStat(Map<String, Object> param) {
        return datEsDetectorEventMapper.selectDetEventForStat(param);
    }

}

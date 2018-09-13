package com.holley.elecsafe.serviceimpl.es;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.holley.elecsafe.dao.dat.DatEsDetectorCurstatusMapper;
import com.holley.elecsafe.dao.dat.DatEsDetectorEventMapper;
import com.holley.elecsafe.dao.dat.DatEsDetectorL1HisMapper;
import com.holley.elecsafe.dao.es.EsDetectorMapper;
import com.holley.elecsafe.dao.obj.ObjEnterpriseMapper;
import com.holley.elecsafe.model.dat.DatEsDetectorCurstatus;
import com.holley.elecsafe.model.dat.DatEsDetectorCurstatusExample;
import com.holley.elecsafe.model.def.DatEsDetectorL1HisVo;
import com.holley.elecsafe.model.def.DetectorEvent;
import com.holley.elecsafe.model.def.RealDataDetVo;
import com.holley.elecsafe.model.es.EsDetector;
import com.holley.elecsafe.model.es.EsDetectorExample;
import com.holley.elecsafe.service.es.RealDataService;
import com.holley.platform.model.def.EntBaseInfo;

public class RealDataServiceImpl implements RealDataService {

    @Resource
    private EsDetectorMapper     esDetectorMapper;
    @Resource
    ObjEnterpriseMapper          objEnterpriseMapper;
    @Resource
    DatEsDetectorL1HisMapper     DatEsDetectorL1HisMapper;
    @Resource
    DatEsDetectorCurstatusMapper datEsDetectorCurstatusMapper;
    @Resource
    DatEsDetectorEventMapper     DatEsDetectorEventMapper;

    @Override
    public List<EntBaseInfo> selectChildEntSelfByPage(Map<String, Object> map) {
        return objEnterpriseMapper.selectChildEntSelfByPage(map);
    }

    @Override
    public List<RealDataDetVo> selectDetStatusByPage(Map<String, Object> map) {
        return esDetectorMapper.selectDetStatusByPage(map);
    }

    @Override
    public RealDataDetVo selectSingleDetStatus(Integer detid) {
        return esDetectorMapper.selectSingleDetStatus(detid);
    }

    @Override
    public List<DatEsDetectorL1HisVo> selectDetByIdAndDate(Map<String, Object> map) {
        return DatEsDetectorL1HisMapper.selectDetByIdAndDate(map);
    }

    @Override
    public List<DatEsDetectorCurstatus> selectDetectorCurstatusByExample(DatEsDetectorCurstatusExample example) {
        return datEsDetectorCurstatusMapper.selectByExample(example);
    }

    @Override
    public List<EsDetector> selectDetectorByExample(EsDetectorExample example) {
        return esDetectorMapper.selectByExample(example);
    }

    @Override
    public DetectorEvent selectNewEventByMap(Map<String, Object> param) {
        return DatEsDetectorEventMapper.selectNewEventByMap(param);
    }

}

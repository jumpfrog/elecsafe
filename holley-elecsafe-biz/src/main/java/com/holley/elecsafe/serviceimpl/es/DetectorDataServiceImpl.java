package com.holley.elecsafe.serviceimpl.es;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;

import com.holley.elecsafe.dao.dat.DatEsDetectorCurstatusMapper;
import com.holley.elecsafe.dao.dic.DicDetectorProtocolMapper;
import com.holley.elecsafe.dao.es.EsDetectorMapper;
import com.holley.elecsafe.dao.es.EsDetectorModelMapper;
import com.holley.elecsafe.dao.es.EsDetectorThresholdMapper;
import com.holley.elecsafe.model.dat.DatEsDetectorCurstatus;
import com.holley.elecsafe.model.def.EsDetectorVo;
import com.holley.elecsafe.model.def.RealDataEntVo;
import com.holley.elecsafe.model.dic.DicDetectorProtocol;
import com.holley.elecsafe.model.dic.DicDetectorProtocolExample;
import com.holley.elecsafe.model.es.EsDetector;
import com.holley.elecsafe.model.es.EsDetectorExample;
import com.holley.elecsafe.model.es.EsDetectorModel;
import com.holley.elecsafe.model.es.EsDetectorModelExample;
import com.holley.elecsafe.model.es.EsDetectorThreshold;
import com.holley.elecsafe.service.es.DetectorDataService;

public class DetectorDataServiceImpl implements DetectorDataService {

    @Resource
    private EsDetectorMapper             esDetectorMapper;
    @Resource
    private DicDetectorProtocolMapper    dicDetectorProtocolMapper;
    @Resource
    private EsDetectorModelMapper        esDetectorModelMapper;
    @Resource
    private EsDetectorThresholdMapper    esDetectorThresholdMapper;
    @Resource
    private DatEsDetectorCurstatusMapper datEsDetectorCurstatusMapper;

    @Override
    public List<EsDetectorVo> selectDetectorByPage(Map<String, Object> map) {
        return esDetectorMapper.selectDetectorByPage(map);
    }

    @Override
    public List<DicDetectorProtocol> selectDetectorProtocolByExample(DicDetectorProtocolExample example) {
        return dicDetectorProtocolMapper.selectByExample(example);
    }

    @Override
    public List<EsDetectorModel> selectDetectorModelByExample(EsDetectorModelExample example) {
        return esDetectorModelMapper.selectByExample(example);
    }

    @Override
    public EsDetectorVo selectSingleDetector(Map<String, Object> map) {
        return esDetectorMapper.selectSingleDetector(map);
    }

    @Override
    public int updateDetectorByPrimaryKeySelective(EsDetector record) throws Exception {
        int count = esDetectorMapper.updateByPrimaryKeySelective(record);
        EsDetectorThreshold hold = new EsDetectorThreshold();
        BeanUtils.copyProperties(hold, record);
        esDetectorThresholdMapper.updateByPrimaryKey(hold);
        return count;
    }

    @Override
    public int insertDetectorSelective(EsDetector record) throws Exception {
        int count = esDetectorMapper.insertSelective(record);
        EsDetectorThreshold hold = new EsDetectorThreshold();
        BeanUtils.copyProperties(hold, record);
        count += esDetectorThresholdMapper.insert(hold);
        DatEsDetectorCurstatus detStatus = new DatEsDetectorCurstatus();
        detStatus.setDetid(record.getId());
        count += datEsDetectorCurstatusMapper.insert(detStatus);
        return count;
    }

    @Override
    public int deleteDetectorByPrimaryKeySelective(EsDetector record) throws Exception {
        return esDetectorMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int countDetectorByExample(EsDetectorExample example) {
        return esDetectorMapper.countByExample(example);
    }

    @Override
    public RealDataEntVo countHomePageDataByEid(Integer eid) {
        return esDetectorMapper.countHomePageDataByEid(eid);
    }

}

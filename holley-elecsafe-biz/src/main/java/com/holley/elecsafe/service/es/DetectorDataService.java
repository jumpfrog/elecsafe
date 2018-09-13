package com.holley.elecsafe.service.es;

import java.util.List;
import java.util.Map;

import com.holley.elecsafe.model.def.EsDetectorVo;
import com.holley.elecsafe.model.def.RealDataEntVo;
import com.holley.elecsafe.model.dic.DicDetectorProtocol;
import com.holley.elecsafe.model.dic.DicDetectorProtocolExample;
import com.holley.elecsafe.model.es.EsDetector;
import com.holley.elecsafe.model.es.EsDetectorExample;
import com.holley.elecsafe.model.es.EsDetectorModel;
import com.holley.elecsafe.model.es.EsDetectorModelExample;

public interface DetectorDataService {

    /**
     * 分页查询设备
     * 
     * @param map
     * @return
     */
    List<EsDetectorVo> selectDetectorByPage(Map<String, Object> map);

    List<DicDetectorProtocol> selectDetectorProtocolByExample(DicDetectorProtocolExample example);

    List<EsDetectorModel> selectDetectorModelByExample(EsDetectorModelExample example);

    EsDetectorVo selectSingleDetector(Map<String, Object> map);

    int updateDetectorByPrimaryKeySelective(EsDetector record) throws Exception;

    int insertDetectorSelective(EsDetector record) throws Exception;

    int deleteDetectorByPrimaryKeySelective(EsDetector record) throws Exception;

    int countDetectorByExample(EsDetectorExample example);

    RealDataEntVo countHomePageDataByEid(Integer eid);
}

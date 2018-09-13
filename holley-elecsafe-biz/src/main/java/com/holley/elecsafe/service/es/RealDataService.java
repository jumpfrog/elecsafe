package com.holley.elecsafe.service.es;

import java.util.List;
import java.util.Map;

import com.holley.elecsafe.model.dat.DatEsDetectorCurstatus;
import com.holley.elecsafe.model.dat.DatEsDetectorCurstatusExample;
import com.holley.elecsafe.model.def.DatEsDetectorL1HisVo;
import com.holley.elecsafe.model.def.DetectorEvent;
import com.holley.elecsafe.model.def.RealDataDetVo;
import com.holley.elecsafe.model.es.EsDetector;
import com.holley.elecsafe.model.es.EsDetectorExample;
import com.holley.platform.model.def.EntBaseInfo;

public interface RealDataService {

    /**
     * 分页查询子企业，包括自己
     * 
     * @param map
     * @return
     */
    List<EntBaseInfo> selectChildEntSelfByPage(Map<String, Object> map);

    /**
     * 分页查询监测点状态
     * 
     * @param map
     * @return
     */
    List<RealDataDetVo> selectDetStatusByPage(Map<String, Object> map);

    /**
     * 查询单一监测点状态
     * 
     * @param detid
     * @return
     */
    RealDataDetVo selectSingleDetStatus(Integer detid);

    List<DatEsDetectorL1HisVo> selectDetByIdAndDate(Map<String, Object> map);

    List<DatEsDetectorCurstatus> selectDetectorCurstatusByExample(DatEsDetectorCurstatusExample example);

    List<EsDetector> selectDetectorByExample(EsDetectorExample example);

    DetectorEvent selectNewEventByMap(Map<String, Object> param);
}

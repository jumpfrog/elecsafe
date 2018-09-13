package com.holley.elecsafe.service.dat;

import java.util.List;
import java.util.Map;

import com.holley.elecsafe.model.dat.DatEsDetectorEvent;
import com.holley.elecsafe.model.dat.DatEsDetectorEventExample;
import com.holley.elecsafe.model.dat.DatEsDetectorL1His;
import com.holley.elecsafe.model.dat.DatEsDetectorL1HisExample;
import com.holley.elecsafe.model.def.DetectorEvent;
import com.holley.elecsafe.model.def.EsDetectorVo;

/**
 * 对象统计数据相关查询Service
 * 
 * @author zdd
 */
public interface DatStaEsService {

    List<DatEsDetectorEvent> selectDetectorEventByExample(DatEsDetectorEventExample example);

    List<EsDetectorVo> selectDetectorByEid(Map<String, Object> map);

    List<DatEsDetectorL1His> selectDetectorL1HisByExample(DatEsDetectorL1HisExample example);

    List<DetectorEvent> selectDetEventForStat(Map<String, Object> param);
}

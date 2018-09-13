package com.holley.elecsafe.service.es;

import java.util.List;
import java.util.Map;

import com.holley.elecsafe.model.def.DatEsDetectorL1HisVo;

/**
 * 历史数据
 * 
 * @author sc
 */
public interface HistoryDataService {

    /**
     * 分页查询历史数据
     * 
     * @param map
     * @return
     */
    List<DatEsDetectorL1HisVo> selectDatEsDetectorL1HisByPage(Map<String, Object> map);
}

package com.holley.elecsafe.service.dat;

import java.util.List;
import java.util.Map;

import com.holley.elecsafe.model.def.DetEventDeal;
import com.holley.elecsafe.model.def.DetectorEvent;
import com.holley.elecsafe.model.def.HomePageDataVo;

/**
 * 设备采集数据相关查询Service
 * 
 * @author zdd
 */
public interface DatDcsEsService {

    /*** dat_es_detector_event **********************************************/
    List<DetectorEvent> queryDetectorEventByPage(Map<String, Object> param);

    int updateDetectorEventDealStatus(Integer evtid, String dealremark, String dealaccount);

    int updateDetectorEventDealStatusBatch(List<Integer> evtidList, String dealremark, String dealaccount);

    /*** dat_es_detector_event_deal **********************************************/
    List<DetEventDeal> queryDetEventDealLog(Integer evtid);

    List<HomePageDataVo> countHomePageData(Map<String, Object> param);

    /**
     * 统计现场服务次数
     * 
     * @param map
     * @return
     */
    int countService(Map<String, Object> param);
}

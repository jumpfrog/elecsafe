package com.holley.elecsafe.serviceimpl.dat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.holley.elecsafe.common.constants.EventDealStatusEnum;
import com.holley.elecsafe.dao.dat.DatEsDetectorEventDealMapper;
import com.holley.elecsafe.dao.dat.DatEsDetectorEventMapper;
import com.holley.elecsafe.model.dat.DatEsDetectorEvent;
import com.holley.elecsafe.model.dat.DatEsDetectorEventDeal;
import com.holley.elecsafe.model.def.DetEventDeal;
import com.holley.elecsafe.model.def.DetectorEvent;
import com.holley.elecsafe.model.def.HomePageDataVo;
import com.holley.elecsafe.service.dat.DatDcsEsService;

public class DatDcsEsServiceImpl implements DatDcsEsService {

    @Autowired
    DatEsDetectorEventMapper     datEsDetectorEventMapper;
    @Autowired
    DatEsDetectorEventDealMapper datEsDetectorEventDealMapper;

    /*** dat_es_detector_event **********************************************/
    @Override
    public List<DetectorEvent> queryDetectorEventByPage(Map<String, Object> param) {
        return datEsDetectorEventMapper.selectDetectorEventByPage(param);
    }

    @Override
    public int updateDetectorEventDealStatus(Integer evtid, String dealremark, String dealaccount) {
        int row = 0;
        Date dealtime = new Date();
        DatEsDetectorEvent record = new DatEsDetectorEvent();
        record.setEvtid(evtid);
        record.setDealstatus(EventDealStatusEnum.PROCESSED.getShortValue());
        record.setDealremark(dealremark);
        record.setDealtime(dealtime);
        record.setDealaccount(dealaccount);
        row += datEsDetectorEventMapper.updateByPrimaryKeySelective(record);

        DatEsDetectorEventDeal eventDeal = new DatEsDetectorEventDeal();
        eventDeal.setEvtid(evtid);
        eventDeal.setDealremark(dealremark);
        eventDeal.setDealtime(dealtime);
        eventDeal.setDealaccount(dealaccount);
        row += datEsDetectorEventDealMapper.insert(eventDeal);
        return row;
    }

    @Override
    public int updateDetectorEventDealStatusBatch(List<Integer> evtidList, String dealremark, String dealaccount) {
        int row = 0;
        List<DatEsDetectorEvent> list = new ArrayList<DatEsDetectorEvent>();
        List<DatEsDetectorEventDeal> dealList = new ArrayList<DatEsDetectorEventDeal>();

        Date dealtime = new Date();

        DatEsDetectorEvent record;
        DatEsDetectorEventDeal eventDeal;
        for (Integer evtid : evtidList) {
            record = new DatEsDetectorEvent();
            record.setEvtid(evtid);
            record.setDealstatus(EventDealStatusEnum.PROCESSED.getShortValue());
            record.setDealremark(dealremark);
            record.setDealtime(dealtime);
            record.setDealaccount(dealaccount);
            list.add(record);

            eventDeal = new DatEsDetectorEventDeal();
            eventDeal.setEvtid(evtid);
            eventDeal.setDealremark(dealremark);
            eventDeal.setDealtime(dealtime);
            eventDeal.setDealaccount(dealaccount);
            dealList.add(eventDeal);
        }
        // 批量更新事件记录
        row += datEsDetectorEventMapper.updateDetectorEventDealStatusBatch(list);

        // 批量更新事件处理记录
        row += datEsDetectorEventDealMapper.insertDetectorEventDealBatch(dealList);
        return row;
    }

    /*** dat_es_detector_event_deal **********************************************/
    @Override
    public List<DetEventDeal> queryDetEventDealLog(Integer evtid) {
        return datEsDetectorEventDealMapper.selectDetEventDealLog(evtid);
    }

    @Override
    public List<HomePageDataVo> countHomePageData(Map<String, Object> param) {
        return datEsDetectorEventMapper.countHomePageData(param);
    }

    @Override
    public int countService(Map<String, Object> param) {
        return datEsDetectorEventDealMapper.countService(param);
    }

}

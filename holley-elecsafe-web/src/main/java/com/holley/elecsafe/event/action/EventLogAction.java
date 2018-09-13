package com.holley.elecsafe.event.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.holley.elecsafe.action.BaseAction;
import com.holley.elecsafe.common.JxlXlsUtil;
import com.holley.elecsafe.common.constants.DetectorDocStatusEnum;
import com.holley.elecsafe.common.constants.DetectorEventTypeEnum;
import com.holley.elecsafe.common.constants.EventDealStatusEnum;
import com.holley.elecsafe.model.def.DetEventDeal;
import com.holley.elecsafe.model.def.DetectorEvent;
import com.holley.elecsafe.model.def.EventClassItem;
import com.holley.elecsafe.model.def.EventItem;
import com.holley.elecsafe.service.dat.DatDcsEsService;
import com.holley.platform.common.constants.Globals;
import com.holley.platform.common.dataobject.Page;
import com.holley.platform.common.util.DateUtil;
import com.holley.platform.common.util.JsonUtil;
import com.holley.platform.common.util.ListUtil;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.model.def.WebUser;

public class EventLogAction extends BaseAction {

    private static final long  serialVersionUID = 1L;
    private DatDcsEsService    datDcsEsService;
    private Page               page;
    private List<DetEventDeal> evtDealList;

    public String init() {
        // Date defaultEndTime = new Date();
        // Date defaultStartTime = DateUtil.addDays(defaultEndTime, -1);

        genEventItem();
        this.getRequest().setAttribute("dealStatusList", EventDealStatusEnum.values());
        // this.getRequest().setAttribute("defaultStartTime", DateUtil.DateToLongStr(defaultStartTime));
        // this.getRequest().setAttribute("defaultEndTime", DateUtil.DateToLongStr(defaultEndTime));
        return SUCCESS;
    }

    /**
     * 分页查询设备事件
     * 
     * @return
     * @throws Exception
     */
    public String queryList() throws Exception {
        String eid = this.getParameter("eid");
        String starttime = this.getParameter("starttime");
        String endtime = this.getParameter("endtime");
        String eventtypes = this.getParameter("eventtypes");
        String dealstatus = this.getParameter("dealstatus");
        String keyword = this.getParameter("keyword");

        Map<String, Object> param = new HashMap<String, Object>();
        WebUser webUser = getSessionWebUser();
        param.put("eid", webUser.getDepartmentid());
        param.put("status", DetectorDocStatusEnum.NORMAL.getShortValue());
        if (StringUtil.isDigits(eid) && !"-1".equals(eid)) {
            param.put("eid", Integer.valueOf(eid));
        }
        if (StringUtil.isNotEmpty(starttime)) {
            param.put("starttime", DateUtil.LongStrToDate(starttime));
        }
        if (StringUtil.isNotEmpty(endtime)) {
            param.put("endtime", DateUtil.LongStrToDate(endtime));
        }
        List<Short> eventTypeList = ListUtil.splitToShortList(eventtypes, ",");
        if (eventTypeList != null && eventTypeList.size() > 0) {
            param.put("evttypes", eventTypeList);
        }
        if (StringUtil.isDigits(dealstatus) && !"0".equals(dealstatus)) {
            param.put("dealstatus", Short.valueOf(dealstatus));
        }
        if (StringUtil.isNotEmpty(keyword)) {
            param.put("keyword", StringUtil.trim(keyword));
        }

        if (isExportExcel()) {
            List<DetectorEvent> list = datDcsEsService.queryDetectorEventByPage(param);

            String[] headsName = { "监测点", "所属单位", "产生时间", "事件类型", "事件描述", "处理状态", "处理时间" };
            String[] properiesName = { "detname", "ownername", "datatimestr", "evttypename", "evtdesc", "dealstatusname", "dealtimestr" };
            JxlXlsUtil jxl = new JxlXlsUtil(this.getRequest(), this.getResponse());
            jxl.exportXLS(list, properiesName, headsName, DetectorEvent.class);
            return null;
        }
        String pageindex = this.getParameter("pageindex");
        String pagelimit = this.getParameter("pagelimit");

        if (StringUtil.isNotDigits(pageindex, pagelimit)) {
            this.success = false;
            this.message = "参数非法";
            return SUCCESS;
        }
        Page page = this.returnPage(Integer.valueOf(pageindex), Integer.valueOf(pagelimit));
        param.put(Globals.PAGE, page);
        List<DetectorEvent> list = datDcsEsService.queryDetectorEventByPage(param);
        page.setRoot(list);
        this.page = page;

        return SUCCESS;
    }

    /**
     * 查询事件处理的日志
     * 
     * @return
     */
    public String queryEventDealLog() {
        String evtid = this.getParameter("evtid");
        if (StringUtil.isNotDigits(evtid)) {
            this.success = false;
            this.message = "参数非法";
            return SUCCESS;
        }
        List<DetEventDeal> list = datDcsEsService.queryDetEventDealLog(Integer.valueOf(evtid));
        this.evtDealList = list;
        return SUCCESS;
    }

    /**
     * 处理事件
     * 
     * @return
     */
    public String saveEventDeal() {
        String evtids = this.getParameter("evtids");
        String dealremark = this.getParameter("dealremark");
        if (StringUtil.isEmpty(evtids)) {
            this.success = false;
            this.message = "请选择要处理的事件";
            return SUCCESS;
        }
        if (StringUtil.isEmpty(dealremark)) {
            this.success = false;
            this.message = "请输入处理意见";
            return SUCCESS;
        }
        List<Integer> evtidList = ListUtil.splitToIntList(evtids, ",");
        if (evtidList == null || evtidList.size() == 0) {
            this.success = false;
            this.message = "请选择要处理的事件";
            return SUCCESS;
        }
        if (evtidList.size() == 1) {// 单个事件处理
            datDcsEsService.updateDetectorEventDealStatus(evtidList.get(0), StringUtil.trim(dealremark), getUserAccount());
        } else {// 批量事件处理
            datDcsEsService.updateDetectorEventDealStatusBatch(evtidList, StringUtil.trim(dealremark), getUserAccount());
        }
        return SUCCESS;
    }

    /**
     * 产生事件类型
     */
    private void genEventItem() {
        List<EventClassItem> eventClassItemList = new ArrayList<EventClassItem>();
        EventClassItem record = new EventClassItem();
        record.setEventclass((short) 1);
        record.setEventclassname("告警事件");
        record.setEventitems(genAlarmEvent());
        eventClassItemList.add(record);

        record = new EventClassItem();
        record.setEventclass((short) 2);
        record.setEventclassname("故障事件");
        record.setEventitems(genFaultEvent());
        eventClassItemList.add(record);

        this.getRequest().setAttribute("eventClassItemList", JsonUtil.list2json(eventClassItemList));
    }

    /**
     * 产生告警事件
     * 
     * @return
     */
    private List<EventItem> genAlarmEvent() {
        List<EventItem> list = new ArrayList<EventItem>();
        EventItem record;
        for (DetectorEventTypeEnum type : DetectorEventTypeEnum.getAlarmEvent()) {
            record = new EventItem();
            record.setEventitem(type.getShortValue());
            record.setEventitemname(type.getText());
            list.add(record);
        }
        return list;
    }

    /**
     * 产生故障事件
     * 
     * @return
     */
    private List<EventItem> genFaultEvent() {
        List<EventItem> list = new ArrayList<EventItem>();
        EventItem record;
        for (DetectorEventTypeEnum type : DetectorEventTypeEnum.getFaultEvent()) {
            record = new EventItem();
            record.setEventitem(type.getShortValue());
            record.setEventitemname(type.getText());
            list.add(record);
        }
        return list;
    }

    /*** SET ********************************/
    public void setDatDcsEsService(DatDcsEsService datDcsEsService) {
        this.datDcsEsService = datDcsEsService;
    }

    /*** GET ********************************/
    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public Page getPage() {
        return page;
    }

    public List<DetEventDeal> getEvtDealList() {
        return evtDealList;
    }

}

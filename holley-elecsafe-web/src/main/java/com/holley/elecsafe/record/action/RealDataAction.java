package com.holley.elecsafe.record.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.holley.elecsafe.action.BaseAction;
import com.holley.elecsafe.common.constants.DetectorDocStatusEnum;
import com.holley.elecsafe.model.dat.DatEsDetectorCurstatus;
import com.holley.elecsafe.model.dat.DatEsDetectorCurstatusExample;
import com.holley.elecsafe.model.def.DetectorEvent;
import com.holley.elecsafe.model.def.RealDataDetVo;
import com.holley.elecsafe.model.def.RealDataEntVo;
import com.holley.elecsafe.model.es.EsDetector;
import com.holley.elecsafe.model.es.EsDetectorExample;
import com.holley.elecsafe.service.es.RealDataService;
import com.holley.platform.common.dataobject.Page;
import com.holley.platform.common.util.DateUtil;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.model.def.EntBaseInfo;
import com.holley.platform.model.def.WebUser;

/**
 * 实时数据
 * 
 * @author sc
 */
public class RealDataAction extends BaseAction {

    @Resource
    private RealDataService realDataService;
    private Page            page;
    private RealDataDetVo   realData;
    private DetectorEvent   detectorEvent;

    /**
     * 企业列表
     * 
     * @return
     */
    public String init() {
        // List<DicCity> provinceList = provinceList = CachedCityUtil.getProvince();
        getRequest().setAttribute("eid", getSessionWebUser().getDepartmentid());
        // getRequest().setAttribute("provinceList", provinceList);
        return SUCCESS;
    }

    /**
     * 查询企业
     * 
     * @return
     */
    public String queryEnt() {
        int eid = getParamInt("eid");
        int pageIndex = getParamInt("pageIndex");
        int pageLimit = getParamInt("pageLimit");
        int province = getParamInt("province");// 省
        int city = getParamInt("city");// 市
        String keyword = getParameter("keyword");
        if (eid == 0) {
            errorParam();
            return SUCCESS;
        }
        WebUser user = getSessionWebUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("eid", eid);
        if (city > 0) {
            map.put("city", city);
        } else if (province > 0 && city == 0) {
            map.put("city", province);
        }

        if (StringUtil.isNotEmpty(keyword)) {
            map.put("keyword", keyword);
        }
        page = returnPage(pageIndex, pageLimit);
        map.put("page", page);

        List<EntBaseInfo> list = realDataService.selectChildEntSelfByPage(map);
        page.setRoot(wrapRealDataEnt(list));
        return SUCCESS;
    }

    private List<RealDataEntVo> wrapRealDataEnt(List<EntBaseInfo> list) {
        List<RealDataEntVo> realDataEntList = new ArrayList<RealDataEntVo>();
        RealDataEntVo vo = null;
        List<DatEsDetectorCurstatus> slist = null;
        if (list != null && list.size() > 0) {

            EsDetectorExample demp = new EsDetectorExample();
            EsDetectorExample.Criteria dcr = demp.createCriteria();
            dcr.andStatusEqualTo(DetectorDocStatusEnum.NORMAL.getShortValue());
            dcr.andOwneridIn(returnEidList(list));
            List<EsDetector> dlist = realDataService.selectDetectorByExample(demp);// 所有设备

            if (dlist != null && dlist.size() > 0) {
                DatEsDetectorCurstatusExample semp = new DatEsDetectorCurstatusExample();
                DatEsDetectorCurstatusExample.Criteria scr = semp.createCriteria();
                scr.andDetidIn(returnDetidList(dlist));
                slist = realDataService.selectDetectorCurstatusByExample(semp);// 所有设备状态
            }

            for (EntBaseInfo einfo : list) {
                vo = new RealDataEntVo();
                vo.setEid(einfo.getEid());
                vo.setName(einfo.getDisc());
                // vo.setUpdateTimeStr(DateUtil.DateToLongStr(new Date()));
                for (EsDetector det : dlist) {
                    if (vo.getEid() == det.getOwnerid()) {
                        vo.setDetCount(vo.getDetCount() + 1);
                        for (DatEsDetectorCurstatus status : slist) {
                            if (status.getDetid() == det.getId()) {
                                String temDateStr = vo.getUpdateTimeStr();

                                if (StringUtil.isEmpty(temDateStr)) {
                                    vo.setUpdateTimeStr(DateUtil.DateToLongStr(status.getDatatime()));
                                } else {
                                    Date temDate = DateUtil.StrToDate(temDateStr, DateUtil.TIME_LONG);
                                    Date temDate2 = status.getDatatime();
                                    if (temDate2 != null && (temDate2.getTime() > temDate.getTime())) {
                                        vo.setUpdateTimeStr(DateUtil.DateToLongStr(temDate2));
                                    }
                                }

                                if (status.getIsalarm() != null && status.getIsalarm() == 1) {
                                    vo.setWaring(vo.getWaring() + 1);
                                }
                                if (status.getIsfault() != null && status.getIsfault() == 1) {
                                    vo.setFault(vo.getFault() + 1);
                                }
                                if (status.getIsoffline() != null && status.getIsoffline() == 1) {
                                    vo.setOffLine(vo.getOffLine() + 1);
                                }
                                if ((status.getIsalarm() != null && status.getIsalarm() == 0) && (status.getIsfault() != null && status.getIsfault() == 0)
                                    && (status.getIsoffline() != null && status.getIsoffline() == 0)) {
                                    vo.setNormal(vo.getNormal() + 1);
                                }
                                if (status.getIsalarm() == null && status.getIsfault() == null && status.getIsoffline() == null) {
                                    vo.setOffLine(vo.getOffLine() + 1);
                                }
                            }
                        }
                    }
                }
                realDataEntList.add(vo);
            }

        }
        return realDataEntList;
    }

    private List<Integer> returnEidList(List<EntBaseInfo> list) {
        List<Integer> eidList = new ArrayList<Integer>();
        for (EntBaseInfo info : list) {
            eidList.add(info.getEid());
        }
        return eidList;
    }

    private List<Integer> returnDetidList(List<EsDetector> list) {
        List<Integer> detidList = new ArrayList<Integer>();
        for (EsDetector det : list) {
            detidList.add(det.getId());
        }
        return detidList;
    }

    /**
     * 设备列表
     * 
     * @return
     */

    public String deviceInit() {
        int eid = getParamInt("eid");
        if (eid == 0) {
            message("非法参数");
            return MSG;
        }
        getRequest().setAttribute("eid", eid);
        getRequest().setAttribute("currentDateTime", DateUtil.DateToLongStr(DateUtil.StrToDate(DateUtil.DateToStr(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd")));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("eid", eid);
        DetectorEvent e = realDataService.selectNewEventByMap(map);
        if (e != null) {
            getRequest().setAttribute("preTime", e.getDatatimestr());
        } else {
            getRequest().setAttribute("preTime", DateUtil.DateToLongStr(new Date()));
        }

        return SUCCESS;
    }

    /**
     * 查询设备列表状态
     * 
     * @return
     */
    public String queryDetStatus() {
        int eid = getParamInt("eid");
        int pageIndex = getParamInt("pageIndex");
        int pageLimit = getParamInt("pageLimit");
        String keyword = getParameter("keyword");
        if (eid == 0) {
            errorParam();
            return SUCCESS;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("eid", eid);
        if (StringUtil.isNotEmpty(keyword)) {
            map.put("keyword", keyword);
        }
        page = returnPage(pageIndex, pageLimit);
        map.put("page", page);
        map.put("status", DetectorDocStatusEnum.NORMAL.getShortValue());
        List<RealDataDetVo> list = this.realDataService.selectDetStatusByPage(map);
        page.setRoot(list);
        return SUCCESS;
    }

    /**
     * 单一设备实时状态数据
     * 
     * @return
     */
    public String queryDetailDetStatus() {
        int detid = getParamInt("detid");
        if (detid == 0) {
            errorParam();
            return SUCCESS;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        realData = realDataService.selectSingleDetStatus(detid);
        if (realData == null) {
            waringParam("设备不存在");
            return SUCCESS;
        }
        map.put("detid", detid);
        map.put("dataTime", new Date());
        realData.setDetL1HisList(realDataService.selectDetByIdAndDate(map));
        return SUCCESS;
    }

    /**
     * 查询最新报警信息
     * 
     * @return
     */
    public String queryNewAlarm() {
        int eid = getParamInt("eid");
        String preTime = getParameter("preTime");
        if (eid == 0) {
            errorParam();
            return SUCCESS;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("eid", eid);
        map.put("preTime", DateUtil.LongStrToDate(preTime));
        detectorEvent = realDataService.selectNewEventByMap(map);
        return SUCCESS;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public Page getPage() {
        return page;
    }

    public RealDataDetVo getRealData() {
        return realData;
    }

    public DetectorEvent getDetectorEvent() {
        return detectorEvent;
    }

}

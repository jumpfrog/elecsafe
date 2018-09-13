package com.holley.elecsafe.frame.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;

import com.holley.elecsafe.action.BaseAction;
import com.holley.elecsafe.common.constants.DetectorDocStatusEnum;
import com.holley.elecsafe.common.constants.EventDealStatusEnum;
import com.holley.elecsafe.model.dat.DatEsDetectorCurstatus;
import com.holley.elecsafe.model.dat.DatEsDetectorCurstatusExample;
import com.holley.elecsafe.model.def.DetectorEvent;
import com.holley.elecsafe.model.def.Enterprise;
import com.holley.elecsafe.model.def.HomePageDataVo;
import com.holley.elecsafe.model.def.HomePageEntDataVo;
import com.holley.elecsafe.model.def.RealDataEntVo;
import com.holley.elecsafe.model.es.EsDetector;
import com.holley.elecsafe.model.es.EsDetectorExample;
import com.holley.elecsafe.model.obj.ObjEnterprise;
import com.holley.elecsafe.service.dat.DatDcsEsService;
import com.holley.elecsafe.service.es.DetectorDataService;
import com.holley.elecsafe.service.es.RealDataService;
import com.holley.elecsafe.service.obj.EntDevService;
import com.holley.platform.common.constants.Globals;
import com.holley.platform.common.dataobject.Page;
import com.holley.platform.common.util.DateUtil;
import com.holley.platform.common.util.JsonUtil;
import com.holley.platform.model.def.WebUser;

public class HomepageAction extends BaseAction {

    private static final long   serialVersionUID = 1L;
    @Resource
    private EntDevService       entDevService;
    @Resource
    private DetectorDataService detectorDataService;
    @Resource
    private DatDcsEsService     datDcsEsService;
    @Resource
    private RealDataService     realDataService;
    private List<DetectorEvent> enventList;
    private HomePageEntDataVo   enterprise;

    public String init() {
        WebUser webUser = this.getSessionWebUser();
        Integer eid = webUser.getDepartmentid();
        int year = DateUtil.getYearValue(new Date());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("eid", eid);
        map.put("year", year);
        Enterprise enterprise = entDevService.selectSingleEntById(eid);
        RealDataEntVo countdata = detectorDataService.countHomePageDataByEid(eid);
        List<Enterprise> entList = entDevService.selectEntInfoByEid(eid);
        int countService = datDcsEsService.countService(map);

        List<HomePageDataVo> list1 = datDcsEsService.countHomePageData(map);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", "报警次数");
        map1.put("value", list1.size() > 0 ? list1.get(0).getCount() : 0);

        map.put("isGroup", true);
        List<HomePageDataVo> list2 = datDcsEsService.countHomePageData(map);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", "报警点数");
        map2.put("value", list2.size());

        map.remove("isGroup");
        map.put("dealStatus", EventDealStatusEnum.PROCESSED.getShortValue());
        List<HomePageDataVo> list3 = datDcsEsService.countHomePageData(map);
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("name", "已排除隐患");
        map3.put("value", list3.size() > 0 ? list3.get(0).getCount() : 0);

        map.put("dealStatus", EventDealStatusEnum.UNPROCESSED.getShortValue());
        List<HomePageDataVo> list4 = datDcsEsService.countHomePageData(map);
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("name", "待排除隐患");
        map4.put("value", list4.size() > 0 ? list4.get(0).getCount() : 0);
        List<Map<String, Object>> homePageContData = new ArrayList<Map<String, Object>>();
        homePageContData.add(map1);
        homePageContData.add(map2);
        homePageContData.add(map3);
        homePageContData.add(map4);
        getRequest().setAttribute("homePageCountJson", JsonUtil.list2json(homePageContData));
        getRequest().setAttribute("entListJson", JsonUtil.list2json(wrapHomePageEntData(entList)));
        getRequest().setAttribute("countService", countService);
        getRequest().setAttribute("enterprise", enterprise);
        getRequest().setAttribute("countdata", countdata);
        getRequest().setAttribute("year", year);

        return SUCCESS;
    }

    public String queryAlarm() {
        int limit = getParamInt("limit");
        Map<String, Object> param = new HashMap<String, Object>();
        WebUser webUser = getSessionWebUser();
        param.put("eid", webUser.getDepartmentid());
        param.put("status", DetectorDocStatusEnum.NORMAL.getShortValue());
        param.put("dealstatus", EventDealStatusEnum.UNPROCESSED.getShortValue());
        Page page = this.returnPage(1, limit);
        param.put(Globals.PAGE, page);
        this.enventList = datDcsEsService.queryDetectorEventByPage(param);
        return SUCCESS;
    }

    /**
     * 查询单个企业 状态统计信息formap
     * 
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String queryEntCount() throws Exception {
        int eid = getParamInt("eid");
        if (eid == 0) {
            errorParam();
            return SUCCESS;
        }
        ObjEnterprise ent = entDevService.selectEnterpriseByPK(eid);
        Enterprise entInfo = new Enterprise();
        BeanUtils.copyProperties(entInfo, ent);
        List<Enterprise> list = new ArrayList<Enterprise>();
        list.add(entInfo);
        List<HomePageEntDataVo> entData = wrapHomePageEntData(list);
        if (entData != null && entData.size() > 0) {
            enterprise = entData.get(0);
        }
        return SUCCESS;
    }

    private List<HomePageEntDataVo> wrapHomePageEntData(List<Enterprise> list) {
        List<HomePageEntDataVo> homePageEntDataList = new ArrayList<HomePageEntDataVo>();
        HomePageEntDataVo vo = null;
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

            for (Enterprise einfo : list) {
                vo = new HomePageEntDataVo();
                vo.setEid(einfo.getEid());
                vo.setEntname(einfo.getDisc());
                vo.setLng(einfo.getLongitude() == null ? "" : einfo.getLongitude().toString());
                vo.setLat(einfo.getLatitude() == null ? "" : einfo.getLatitude().toString());
                for (EsDetector det : dlist) {
                    if (vo.getEid() == det.getOwnerid()) {
                        vo.setDetCount(vo.getDetCount() + 1);
                        for (DatEsDetectorCurstatus status : slist) {
                            if (status.getDetid() == det.getId()) {
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
                homePageEntDataList.add(vo);
            }
        }
        return homePageEntDataList;
    }

    private List<Integer> returnEidList(List<Enterprise> list) {
        List<Integer> eidList = new ArrayList<Integer>();
        for (Enterprise info : list) {
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

    public HomePageEntDataVo getEnterprise() {
        return enterprise;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<DetectorEvent> getEnventList() {
        return enventList;
    }

}

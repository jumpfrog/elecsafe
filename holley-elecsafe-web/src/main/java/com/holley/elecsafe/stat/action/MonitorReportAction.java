package com.holley.elecsafe.stat.action;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;

import com.holley.elecsafe.action.BaseAction;
import com.holley.elecsafe.common.constants.DetectorDocStatusEnum;
import com.holley.elecsafe.common.constants.DetectorEventTypeEnum;
import com.holley.elecsafe.common.constants.DetectorTypeEnum;
import com.holley.elecsafe.model.dat.DatEsDetectorEvent;
import com.holley.elecsafe.model.dat.DatEsDetectorEventExample;
import com.holley.elecsafe.model.dat.DatEsDetectorL1His;
import com.holley.elecsafe.model.dat.DatEsDetectorL1HisExample;
import com.holley.elecsafe.model.def.DatEsDetectorL1HisVo;
import com.holley.elecsafe.model.def.EsDetectorVo;
import com.holley.elecsafe.model.def.StatAlarmVo;
import com.holley.elecsafe.model.def.StatDetDataVo;
import com.holley.elecsafe.model.obj.ObjEnterprise;
import com.holley.elecsafe.service.dat.DatStaEsService;
import com.holley.elecsafe.service.obj.EntDevService;
import com.holley.platform.common.util.DateUtil;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.model.def.EntBaseInfo;

/**
 * 监测报表
 * 
 * @author admin
 */
public class MonitorReportAction extends BaseAction {

    @Resource
    private EntDevService    entDevService;
    @Resource
    private DatStaEsService  DatStaEsService;
    private StatAlarmVo      statAlarmData;
    private final static int IL_ALARM = 1;   // 剩余电流告警
    private final static int I_ALARM  = 2;   // 电流告警
    private final static int T_ALARM  = 3;   // 温度告警

    private final static int CHART_IL = 1;   // 剩余电流图表
    private final static int CHART_I1 = 2;   // 单相电流图表
    private final static int CHART_T1 = 3;   // 单相温度图表
    private final static int CHART_I3 = 4;   // 3相电流图表
    private final static int CHART_T3 = 5;   // 3相温度图表

    private final static int IL       = 1;   // 剩余电流
    private final static int TL       = 2;   // 火线温度
    private final static int TN       = 3;   // 零线温度
    private final static int TI       = 4;   // 箱体温度

    private final static int TA       = 5;   // A相温度
    private final static int TB       = 6;   // B相温度
    private final static int TC       = 7;   // C相温度

    private final static int IA       = 8;   // A相电流
    private final static int IB       = 9;   // B相电流
    private final static int IC       = 10;  // C相电流

    private final static int FAULT    = 100; // 故障

    public String init() {
        int eid = getSessionWebUser().getDepartmentid();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_WEEK, 2);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        getRequest().setAttribute("startTime", calendar.getTime());
        if (eid == -1) {
            List<EntBaseInfo> list = entDevService.selectGetChildEid(eid);
            if (list != null && list.size() > 0) {
                EntBaseInfo entInfo = list.get(0);
                getRequest().setAttribute("entName", entInfo.getDisc());
                getRequest().setAttribute("eid", entInfo.getEid());
            }

        } else {
            ObjEnterprise enterprise = entDevService.selectEnterpriseByPK(eid);
            getRequest().setAttribute("entName", enterprise.getDisc());
            getRequest().setAttribute("eid", enterprise.getEid());
        }

        getRequest().setAttribute("currentDateTime", DateUtil.DateToLongStr(DateUtil.StrToDate(DateUtil.DateToStr(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd")));
        return SUCCESS;
    }

    public String query() {
        String startTime = getParameter("startTime");
        int eid = getParamInt("eid");
        // eid = 11;
        if (StringUtil.isEmpty(startTime) || eid <= 0) {
            errorParam();
            return SUCCESS;
        }
        Date startTimeDate = DateUtil.ShortStrToDate(startTime);
        Calendar cr = Calendar.getInstance();
        cr.setTime(startTimeDate);
        cr.add(Calendar.DATE, 6);
        Date endTimeDate = cr.getTime();
        ObjEnterprise enterprise = entDevService.selectEnterpriseByPK(eid);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("eid", eid);
        map.put("status", DetectorDocStatusEnum.NORMAL.getShortValue());
        List<EsDetectorVo> detList = DatStaEsService.selectDetectorByEid(map);

        Map<Integer, StatDetDataVo> detmap = returnDetMap(detList);
        List<DatEsDetectorEvent> detEventList = null;
        List<DatEsDetectorL1His> hisList = null;
        if (detList != null && detList.size() > 0) {
            DatEsDetectorEventExample detEventEmp = new DatEsDetectorEventExample();
            DatEsDetectorEventExample.Criteria detEventCr = detEventEmp.createCriteria();
            detEventCr.andDatatimeGreaterThanOrEqualTo(startTimeDate);
            detEventCr.andDatatimeLessThanOrEqualTo(endTimeDate);
            detEventCr.andDetidIn(new ArrayList<Integer>(detmap.keySet()));
            detEventList = DatStaEsService.selectDetectorEventByExample(detEventEmp);

            DatEsDetectorL1HisExample hisEmp = new DatEsDetectorL1HisExample();
            DatEsDetectorL1HisExample.Criteria hisCr = hisEmp.createCriteria();
            hisCr.andDatatimeGreaterThanOrEqualTo(startTimeDate);
            hisCr.andDatatimeLessThanOrEqualTo(endTimeDate);
            hisCr.andDetidIn(new ArrayList<Integer>(detmap.keySet()));
            hisList = DatStaEsService.selectDetectorL1HisByExample(hisEmp);
        }

        StatAlarmVo statAlarmVo = new StatAlarmVo();
        statAlarmVo.setStartTime(startTime);
        statAlarmVo.setEndTime(DateUtil.DateToShortStr(endTimeDate));
        statAlarmVo.setEntName(enterprise.getDisc());
        countDetAlarm(statAlarmVo, detEventList, detmap);
        countAllAlarm(statAlarmVo);
        wrapHisData(statAlarmVo.getStatDetList(), returnDetHisMap(hisList));

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("eid", eid);
        param.put("startTimeDate", startTimeDate);
        param.put("endTimeDate", endTimeDate);
        param.put("status", DetectorDocStatusEnum.NORMAL.getShortValue());
        statAlarmVo.setDetEventList(DatStaEsService.selectDetEventForStat(param)); // 查询设备告警事件信息
        this.statAlarmData = statAlarmVo;

        return SUCCESS;
    }

    private void wrapHisData(List<StatDetDataVo> statDetList, Map<Integer, List<DatEsDetectorL1His>> hisMap) {
        if (hisMap != null && hisMap.size() > 0 && statDetList != null && statDetList.size() > 0) {
            for (StatDetDataVo det : statDetList) {
                List<DatEsDetectorL1HisVo> listI = returnMaxHisList(hisMap, returnMaxHis(I_ALARM, det.getType(), det.getDetId(), hisMap));
                if (listI != null && listI.size() > 0) {
                    det.getDetDataIList().addAll(listI);
                }

                List<DatEsDetectorL1HisVo> listt = returnMaxHisList(hisMap, returnMaxHis(T_ALARM, det.getType(), det.getDetId(), hisMap));
                if (listt != null && listt.size() > 0) {
                    det.getDetDataTList().addAll(listt);
                }

                List<DatEsDetectorL1HisVo> listil = returnMaxHisList(hisMap, returnMaxHis(IL_ALARM, det.getType(), det.getDetId(), hisMap));
                if (listil != null && listil.size() > 0) {
                    det.getDetDataILList().addAll(listil);
                }
            }
        }
    }

    private Map<Integer, List<DatEsDetectorL1His>> returnDetHisMap(List<DatEsDetectorL1His> hisList) {
        if (hisList == null || hisList.size() == 0) {
            return null;
        }
        Map<Integer, List<DatEsDetectorL1His>> map = new HashMap<Integer, List<DatEsDetectorL1His>>();
        for (DatEsDetectorL1His his : hisList) {
            List<DatEsDetectorL1His> tempList = map.get(his.getDetid());
            if (tempList == null) {
                tempList = new ArrayList<DatEsDetectorL1His>();
                tempList.add(his);
                map.put(his.getDetid(), tempList);
            } else {
                tempList.add(his);
            }
        }
        return map;
    }

    private DatEsDetectorL1His returnMaxHis(int type, short singleOrThree, Integer detid, Map<Integer, List<DatEsDetectorL1His>> hisMap) {
        List<DatEsDetectorL1His> hisList = hisMap.get(detid);
        if (hisList == null) {
            return null;
        }
        DatEsDetectorL1His maxHis = hisList.get(0);
        if (I_ALARM == type) {
            if (DetectorTypeEnum.SINGLE_PHASE.getShortValue().equals(singleOrThree)) {
                // BigDecimal tempMaxI = maxHis.getI() == null ? BigDecimal.ZERO : maxHis.getI();
                // for (DatEsDetectorL1His his : hisList) {
                // BigDecimal tempI = his.getI() == null ? BigDecimal.ZERO : his.getI();
                // if (tempI.doubleValue() > tempMaxI.doubleValue()) {
                // tempMaxI = tempI;
                // maxHis = his;
                // }
                // }
                return returnMaxHisByType(CHART_I1, hisList);

            } else if (DetectorTypeEnum.THREE_PHASE.getShortValue().equals(singleOrThree)) {
                // BigDecimal tempMaxIa = maxHis.getIa() == null ? BigDecimal.ZERO : maxHis.getIa();
                // for (DatEsDetectorL1His his : hisList) {
                // BigDecimal tempIa = his.getIa() == null ? BigDecimal.ZERO : his.getIa();
                // if (tempIa.doubleValue() > tempMaxIa.doubleValue()) {
                // tempMaxIa = tempIa;
                // maxHis = his;
                // }
                // }
                return returnMaxHisByType(CHART_I3, hisList);
            }
        } else if (T_ALARM == type) {
            if (DetectorTypeEnum.SINGLE_PHASE.getShortValue().equals(singleOrThree)) {
                // BigDecimal tempMaxTl = maxHis.getTl() == null ? BigDecimal.ZERO : maxHis.getTl();
                // for (DatEsDetectorL1His his : hisList) {
                // BigDecimal tempTl = his.getTl() == null ? BigDecimal.ZERO : his.getTl();
                // if (tempTl.doubleValue() > tempMaxTl.doubleValue()) {
                // tempMaxTl = tempTl;
                // maxHis = his;
                // }
                // }
                return returnMaxHisByType(CHART_T1, hisList);
            } else if (DetectorTypeEnum.THREE_PHASE.getShortValue().equals(singleOrThree)) {
                // BigDecimal tempMaxTa = maxHis.getTa() == null ? BigDecimal.ZERO : maxHis.getTa();
                // for (DatEsDetectorL1His his : hisList) {
                // BigDecimal tempTa = his.getTa() == null ? BigDecimal.ZERO : his.getTa();
                // if (tempTa.doubleValue() > tempMaxTa.doubleValue()) {
                // tempMaxTa = tempTa;
                // maxHis = his;
                // }
                // }
                return returnMaxHisByType(CHART_T3, hisList);
            }
        } else if (IL_ALARM == type) {
            // BigDecimal tempMaxIl = maxHis.getIl() == null ? BigDecimal.ZERO : maxHis.getIl();
            // for (DatEsDetectorL1His his : hisList) {
            // BigDecimal tempIl = his.getIl() == null ? BigDecimal.ZERO : his.getIl();
            // if (tempIl.doubleValue() > tempMaxIl.doubleValue()) {
            // tempMaxIl = tempIl;
            // maxHis = his;
            // }
            // }
            return returnMaxHisByType(CHART_IL, hisList);
        }
        return maxHis;
    }

    private DatEsDetectorL1His returnMaxHisByType(int type, List<DatEsDetectorL1His> hisList) {
        DatEsDetectorL1His maxhis = hisList.get(0);
        Map<Integer, DatEsDetectorL1His> tempMaxHisMap = new HashMap<Integer, DatEsDetectorL1His>();
        if (type == CHART_I1) {// 单相电流
            BigDecimal tempMaxI = returnDefaultValue(maxhis.getI());
            DatEsDetectorL1His maxhisI = null;
            for (DatEsDetectorL1His his : hisList) {
                BigDecimal tempI = returnDefaultValue(his.getI());
                if (tempI.doubleValue() > tempMaxI.doubleValue()) {
                    tempMaxI = tempI;
                    maxhisI = his;
                }
            }
            return maxhisI != null ? maxhisI : maxhis;
        } else if (type == CHART_T1) {// 单相温度图表
            BigDecimal tempMaxTl = returnDefaultValue(maxhis.getTl());// 火线
            BigDecimal tempMaxTn = returnDefaultValue(maxhis.getTn());// 零线
            BigDecimal tempMaxTi = returnDefaultValue(maxhis.getTi());// 箱体
            DatEsDetectorL1His maxhisTl = null;
            DatEsDetectorL1His maxhisTn = null;
            DatEsDetectorL1His maxhisTi = null;
            for (DatEsDetectorL1His his : hisList) {
                BigDecimal tempTl = returnDefaultValue(his.getTl());
                if (tempTl.doubleValue() > tempMaxTl.doubleValue()) {
                    tempMaxTl = tempTl;
                    maxhisTl = his;
                }
            }
            if (maxhisTl != null) {
                tempMaxHisMap.put(TL, maxhisTl);
            }
            for (DatEsDetectorL1His his : hisList) {
                BigDecimal tempTn = returnDefaultValue(his.getTn());
                if (tempTn.doubleValue() > tempMaxTn.doubleValue()) {
                    tempMaxTn = tempTn;
                    maxhisTn = his;
                }
            }
            if (maxhisTn != null) {
                tempMaxHisMap.put(TN, maxhisTn);
            }
            for (DatEsDetectorL1His his : hisList) {
                BigDecimal tempTi = returnDefaultValue(his.getTi());
                if (tempTi.doubleValue() > tempMaxTi.doubleValue()) {
                    tempMaxTi = tempTi;
                    maxhisTi = his;
                }
            }
            if (maxhisTi != null) {
                tempMaxHisMap.put(TI, maxhisTi);
            }
            return tempMaxHisMap.size() > 0 ? returnMaxHisByMap(type, tempMaxHisMap) : maxhis;
        } else if (type == CHART_IL) {// 剩余电流
            BigDecimal tempMaxIl = returnDefaultValue(maxhis.getIl());
            DatEsDetectorL1His maxhisIl = null;
            for (DatEsDetectorL1His his : hisList) {
                BigDecimal tempIl = returnDefaultValue(his.getIl());
                if (tempIl.doubleValue() > tempMaxIl.doubleValue()) {
                    tempMaxIl = tempIl;
                    maxhisIl = his;
                }
            }
            return maxhisIl != null ? maxhisIl : maxhis;
        } else if (type == CHART_I3) {// 三相电流
            BigDecimal tempMaxIa = returnDefaultValue(maxhis.getIa());// A相电流
            BigDecimal tempMaxIb = returnDefaultValue(maxhis.getIb());// B相电流
            BigDecimal tempMaxIc = returnDefaultValue(maxhis.getIc());// C相电流
            DatEsDetectorL1His maxhisIa = null;
            DatEsDetectorL1His maxhisIb = null;
            DatEsDetectorL1His maxhisIc = null;
            for (DatEsDetectorL1His his : hisList) {
                BigDecimal tempIa = returnDefaultValue(his.getIa());

                if (tempIa.doubleValue() > tempMaxIa.doubleValue()) {
                    tempMaxIa = tempIa;
                    maxhisIa = his;
                }
            }
            if (maxhisIa != null) {
                tempMaxHisMap.put(IA, maxhisIa);
            }

            for (DatEsDetectorL1His his : hisList) {
                BigDecimal tempIb = returnDefaultValue(his.getIb());

                if (tempIb.doubleValue() > tempMaxIb.doubleValue()) {
                    tempMaxIb = tempIb;
                    maxhisIb = his;
                }
            }
            if (maxhisIb != null) {
                tempMaxHisMap.put(IB, maxhisIb);
            }
            for (DatEsDetectorL1His his : hisList) {
                BigDecimal tempIc = returnDefaultValue(his.getIc());
                if (tempIc.doubleValue() > tempMaxIc.doubleValue()) {
                    tempMaxIc = tempIc;
                    maxhisIc = his;
                }
            }
            if (maxhisIc != null) {
                tempMaxHisMap.put(IC, maxhisIc);
            }
            return tempMaxHisMap.size() > 0 ? returnMaxHisByMap(type, tempMaxHisMap) : maxhis;
        } else if (type == CHART_T3) {// 三相温度
            BigDecimal tempMaxTa = returnDefaultValue(maxhis.getTa());// A相温度
            BigDecimal tempMaxTb = returnDefaultValue(maxhis.getTb());// B相温度
            BigDecimal tempMaxTc = returnDefaultValue(maxhis.getTc());// C相温度
            DatEsDetectorL1His maxhisTa = null;
            DatEsDetectorL1His maxhisTb = null;
            DatEsDetectorL1His maxhisTc = null;
            for (DatEsDetectorL1His his : hisList) {
                BigDecimal tempTa = returnDefaultValue(his.getTa());
                if (tempTa.doubleValue() > tempMaxTa.doubleValue()) {
                    tempMaxTa = tempTa;
                    maxhisTa = his;
                }
            }
            if (maxhisTa != null) {
                tempMaxHisMap.put(TA, maxhisTa);
            }

            for (DatEsDetectorL1His his : hisList) {
                BigDecimal tempTb = returnDefaultValue(his.getTb());

                if (tempTb.doubleValue() > tempMaxTb.doubleValue()) {
                    tempMaxTb = tempTb;
                    maxhisTb = his;
                }
            }
            if (maxhisTb != null) {
                tempMaxHisMap.put(TB, maxhisTb);
            }
            for (DatEsDetectorL1His his : hisList) {
                BigDecimal tempTc = returnDefaultValue(his.getTc());
                if (tempTc.doubleValue() > tempMaxTc.doubleValue()) {
                    tempMaxTc = tempTc;
                    maxhisTc = his;
                }
            }
            if (maxhisTc != null) {
                tempMaxHisMap.put(TC, maxhisTc);
            }
            return tempMaxHisMap.size() > 0 ? returnMaxHisByMap(type, tempMaxHisMap) : maxhis;
        }

        return maxhis;
    }

    private BigDecimal returnDefaultValue(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

    private DatEsDetectorL1His returnMaxHisByMap(int type, Map<Integer, DatEsDetectorL1His> tempMaxHisMap) {
        BigDecimal tempMax = null;
        DatEsDetectorL1His tempMaxHis = null;
        if (type == CHART_T1) {// //求单相温度最大值
            tempMaxHis = tempMaxHisMap.get(TL);
            tempMax = tempMaxHis != null ? returnDefaultValue(tempMaxHis.getTl()) : BigDecimal.ZERO;
            for (Map.Entry<Integer, DatEsDetectorL1His> entry : tempMaxHisMap.entrySet()) {
                if (entry.getKey().equals(TN)) {
                    DatEsDetectorL1His tempHisTN = entry.getValue();
                    BigDecimal tempTN = returnDefaultValue(tempHisTN.getTn());
                    if (tempTN.doubleValue() > tempMax.doubleValue()) {
                        tempMax = tempTN;
                        tempMaxHis = tempHisTN;
                    }
                } else if (entry.getKey().equals(TI)) {
                    DatEsDetectorL1His tempHisTI = entry.getValue();
                    BigDecimal tempTI = returnDefaultValue(tempHisTI.getTi());
                    if (tempTI.doubleValue() > tempMax.doubleValue()) {
                        tempMax = tempTI;
                        tempMaxHis = tempHisTI;
                    }
                }

            }
        } else if (type == CHART_I3) {// 求三相电流最大相值
            tempMaxHis = tempMaxHisMap.get(IA);
            tempMax = tempMaxHis != null ? returnDefaultValue(tempMaxHis.getIa()) : BigDecimal.ZERO;
            for (Map.Entry<Integer, DatEsDetectorL1His> entry : tempMaxHisMap.entrySet()) {
                if (entry.getKey().equals(IB)) {
                    DatEsDetectorL1His tempHisIB = entry.getValue();
                    BigDecimal tempIB = returnDefaultValue(tempHisIB.getIb());
                    if (tempIB.doubleValue() > tempMax.doubleValue()) {
                        tempMax = tempIB;
                        tempMaxHis = tempHisIB;
                    }
                } else if (entry.getKey().equals(IC)) {
                    DatEsDetectorL1His tempHisIC = entry.getValue();
                    BigDecimal tempIC = returnDefaultValue(tempHisIC.getIc());
                    if (tempIC.doubleValue() > tempMax.doubleValue()) {
                        tempMax = tempIC;
                        tempMaxHis = tempHisIC;
                    }
                }

            }
        } else if (type == CHART_T3) {// 求三相温度最大相值
            tempMaxHis = tempMaxHisMap.get(TA);
            tempMax = tempMaxHis != null ? returnDefaultValue(tempMaxHis.getTa()) : BigDecimal.ZERO;
            for (Map.Entry<Integer, DatEsDetectorL1His> entry : tempMaxHisMap.entrySet()) {
                if (entry.getKey().equals(TB)) {
                    DatEsDetectorL1His tempHisTB = entry.getValue();
                    BigDecimal tempTB = returnDefaultValue(tempHisTB.getTb());
                    if (tempTB.doubleValue() > tempMax.doubleValue()) {
                        tempMax = tempTB;
                        tempMaxHis = tempHisTB;
                    }
                } else if (entry.getKey().equals(TC)) {
                    DatEsDetectorL1His tempHisTC = entry.getValue();
                    BigDecimal tempTC = returnDefaultValue(tempHisTC.getTc());
                    if (tempTC.doubleValue() > tempMax.doubleValue()) {
                        tempMax = tempTC;
                        tempMaxHis = tempHisTC;
                    }
                }

            }
        }
        return tempMaxHis;
    }

    private List<DatEsDetectorL1HisVo> returnMaxHisList(Map<Integer, List<DatEsDetectorL1His>> hisMap, DatEsDetectorL1His maxHis) {
        if (maxHis == null) {
            return null;
        }
        List<DatEsDetectorL1HisVo> MaxHisList = new ArrayList<DatEsDetectorL1HisVo>();
        Calendar cr = Calendar.getInstance();
        cr.setTime(maxHis.getDatatime());
        cr.add(Calendar.HOUR, -6);

        Date tempStartTime = cr.getTime();
        long tempStartTimes = tempStartTime.getTime();
        cr.setTime(maxHis.getDatatime());
        cr.add(Calendar.HOUR, 6);
        Date tempEndTime = cr.getTime();
        long tempEndTimes = tempEndTime.getTime();
        List<DatEsDetectorL1His> hisList = hisMap.get(maxHis.getDetid());
        if (hisList != null && hisList.size() > 0) {
            for (DatEsDetectorL1His his : hisList) {
                long tempTimes = his.getDatatime() == null ? 0 : his.getDatatime().getTime();
                if ((tempTimes >= tempStartTimes) && (tempTimes <= tempEndTimes) && his.getDetid().equals(maxHis.getDetid())) {
                    DatEsDetectorL1HisVo tempHis = new DatEsDetectorL1HisVo();
                    try {
                        BeanUtils.copyProperties(tempHis, his);
                        MaxHisList.add(tempHis);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return MaxHisList;
    }

    private Map<Integer, StatDetDataVo> returnDetMap(List<EsDetectorVo> detList) {
        Map<Integer, StatDetDataVo> map = new HashMap<Integer, StatDetDataVo>();
        if (detList != null && detList.size() > 0) {
            for (EsDetectorVo det : detList) {
                StatDetDataVo vo = new StatDetDataVo();
                vo.setDetId(det.getId());
                vo.setDetName(det.getName());
                vo.setType(det.getType());
                vo.setHoldOveri(det.getOveri());
                vo.setHoldOveru(det.getOveru());
                vo.setHoldIl(det.getIl());
                vo.setHoldTa(det.getTa());
                vo.setHoldTb(det.getTb());
                vo.setHoldTc(det.getTc());
                vo.setHoldTi(det.getTi());
                vo.setHoldTl(det.getTl());
                vo.setHoldTn(det.getTn());
                vo.setHoldUnderu(det.getUnderu());
                map.put(det.getId(), vo);
            }
        }
        return map;
    }

    private void countDetAlarm(StatAlarmVo statAlarmVo, List<DatEsDetectorEvent> detEventList, Map<Integer, StatDetDataVo> detmap) {
        if (detEventList == null || detEventList.size() == 0) {
            if (detmap.size() == 0) {
                return;
            } else {
                statAlarmVo.setStatDetList(new ArrayList<StatDetDataVo>(detmap.values()));
            }
        }
        for (DatEsDetectorEvent event : detEventList) {
            StatDetDataVo vo = detmap.get(event.getDetid());
            if (returnAlarmType(IL_ALARM, event.getEvttype())) {
                vo.setIlAlarm(vo.getIlAlarm() + 1);
            } else if (returnAlarmType(I_ALARM, event.getEvttype())) {
                vo.setiAlarm(vo.getiAlarm() + 1);
            } else if (returnAlarmType(T_ALARM, event.getEvttype())) {
                vo.settAlarm(vo.gettAlarm() + 1);
            } else if (returnAlarmType(FAULT, event.getEvttype())) {
                vo.setFault(vo.getFault() + 1);
            }
        }
        statAlarmVo.setStatDetList(new ArrayList<StatDetDataVo>(detmap.values()));
    }

    private boolean returnAlarmType(int type, Short evtType) {
        if (evtType != null) {
            if (IL_ALARM == type) {
                if (DetectorEventTypeEnum.ALARM_1.getShortValue().equals(evtType)) {
                    return true;
                }
            } else if (I_ALARM == type) {
                if (DetectorEventTypeEnum.ALARM_5.getShortValue().equals(evtType)) {
                    return true;
                }
            } else if (T_ALARM == type) {
                if (DetectorEventTypeEnum.ALARM_2.getShortValue().equals(evtType)) {
                    return true;
                }
            } else if (FAULT == type) {
                if (evtType > 100) {
                    return true;
                }
            }
        }
        return false;
    }

    private void countAllAlarm(StatAlarmVo statAlarmVo) {
        List<StatDetDataVo> statDetList = statAlarmVo.getStatDetList();
        StatDetDataVo data = null;
        int allAlarm = 0;// 总告警数（包含告警，故障）
        int allIlAlarm = 0;// 总剩余电流告警数
        int allIAlarm = 0;// 总电流告警数
        int allTAlarm = 0;// 总温度告警数
        int allFault = 0;// 总故障数
        if (statDetList != null && statDetList.size() > 0) {
            for (int x = 0; x < statDetList.size(); x++) {
                data = statDetList.get(x);
                int tempAll = data.getiAlarm() + data.getIlAlarm() + data.gettAlarm() + data.getFault();
                allAlarm += tempAll;
                allIlAlarm += data.getIlAlarm();
                allIAlarm += data.getiAlarm();
                allTAlarm += data.gettAlarm();
                allFault += data.getFault();
            }
            statAlarmVo.setAllAlarm(allAlarm);
            statAlarmVo.setAllIAlarm(allIAlarm);
            statAlarmVo.setAllIlAlarm(allIlAlarm);
            statAlarmVo.setAllTAlarm(allTAlarm);
            statAlarmVo.setAllFault(allFault);
        }

    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public StatAlarmVo getStatAlarmData() {
        return statAlarmData;
    }

    public static void main(String[] args) {
        MonitorReportAction a = new MonitorReportAction();
        Map<Integer, DatEsDetectorL1His> tempMaxHisMap = new HashMap<Integer, DatEsDetectorL1His>();
        DatEsDetectorL1His tl = new DatEsDetectorL1His();
        tl.setTl(new BigDecimal(25));
        tl.setDetid(1);
        tempMaxHisMap.put(TL, tl);
        tempMaxHisMap.put(null, tl);
        DatEsDetectorL1His tn = new DatEsDetectorL1His();
        tn.setTn(new BigDecimal(29));
        tn.setDetid(2);
        tempMaxHisMap.put(TN, tn);
        DatEsDetectorL1His ti = new DatEsDetectorL1His();
        ti.setTi(new BigDecimal(88));
        ti.setDetid(3);
        tempMaxHisMap.put(TI, ti);

    }
}

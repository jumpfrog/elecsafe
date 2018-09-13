package com.holley.elecsafe.record.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jxl.write.WriteException;

import com.holley.elecsafe.action.BaseAction;
import com.holley.elecsafe.common.JxlXlsUtil;
import com.holley.elecsafe.common.constants.DetectorDocStatusEnum;
import com.holley.elecsafe.common.constants.DetectorTypeEnum;
import com.holley.elecsafe.common.constants.EsGlobals;
import com.holley.elecsafe.model.def.DatEsDetectorL1HisVo;
import com.holley.elecsafe.model.def.RealDataDetVo;
import com.holley.elecsafe.service.es.HistoryDataService;
import com.holley.elecsafe.service.es.RealDataService;
import com.holley.platform.common.dataobject.Page;
import com.holley.platform.common.util.DateUtil;
import com.holley.platform.common.util.StringUtil;

/**
 * 实历史数据
 * 
 * @author sc
 */
public class HistoryDataAction extends BaseAction {

    @Resource
    private HistoryDataService historyDataService;
    @Resource
    private RealDataService    realDataService;
    private Page               page;
    private RealDataDetVo      realData;

    /**
     * 历史记录列表
     * 
     * @return
     */
    public String init() {
        Date now = new Date();
        String endHChartDateStr = DateUtil.DateToLongStr(now);
        Calendar cr = Calendar.getInstance();
        cr.setTime(now);
        cr.add(Calendar.DAY_OF_MONTH, EsGlobals.MAX_CHATR_DAY);
        String startHChartDateStr = DateUtil.DateToLongStr(cr.getTime());
        getRequest().setAttribute("startHChartDateStr", startHChartDateStr);
        getRequest().setAttribute("endHChartDateStr", endHChartDateStr);
        getRequest().setAttribute("currentDateTime", DateUtil.DateToLongStr(DateUtil.StrToDate(DateUtil.DateToStr(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd")));
        getRequest().setAttribute("detectorTypeList", DetectorTypeEnum.values());
        return SUCCESS;
    }

    /**
     * 查询历史数据
     * 
     * @return
     * @throws IOException
     * @throws WriteException
     */
    public String query() throws Exception {
        int pageIndex = getParamInt("pageIndex");
        int pageLimit = getParamInt("pageLimit");
        String keyword = getParameter("keyword");
        String startDate = getParameter("startDate");
        String endDate = getParameter("endDate");
        int type = getParamInt("type");
        int queryEid = getParamInt("eid");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", DetectorDocStatusEnum.NORMAL.getShortValue());
        List<DatEsDetectorL1HisVo> list = null;
        if (queryEid > 0) {
            map.put("eid", queryEid);
        } else {
            map.put("eid", getSessionWebUser().getDepartmentid());
        }
        if (type > 0) {
            map.put("type", type);
        }
        if (StringUtil.isNotEmpty(keyword)) {
            map.put("keyword", keyword);
        }
        if (StringUtil.isNotEmpty(startDate)) {
            map.put("startDate", DateUtil.ShortStrToDate(startDate));
        }
        if (StringUtil.isNotEmpty(endDate)) {
            map.put("endDate", DateUtil.ShortStrToDate(endDate));
        }
        if (isExportExcel()) {
            list = historyDataService.selectDatEsDetectorL1HisByPage(map);
            String[] headsName = { "监测点名称", "A相电流", "B相电流", "C相电流", "电流", "剩余电流", "A相温度", "B相温度", "C相温度", "零线温度", "火线温度", "箱体温度", "数据时间" };
            String[] properiesName = { "name", "ia", "ib", "ic", "i", "il", "ta", "tb", "tc", "tn", "tl", "ti", "longDatatimeStr" };
            JxlXlsUtil jxl = new JxlXlsUtil(this.getRequest(), this.getResponse());
            jxl.exportXLS(list, properiesName, headsName, DatEsDetectorL1HisVo.class);
            return null;
        } else {
            page = returnPage(pageIndex, pageLimit);
            map.put("page", page);
            list = historyDataService.selectDatEsDetectorL1HisByPage(map);
            page.setRoot(list);
        }
        return SUCCESS;
    }

    public String queryDetHChartData() {
        int detid = getParamInt("detid");
        String startDate = getParameter("startHChartDate");
        String endDate = getParameter("endHChartDate");
        if (detid == 0) {
            errorParam();
            return SUCCESS;
        }
        if (StringUtil.isEmpty(startDate)) {
            waringParam("开始时间不能为空");
            return SUCCESS;
        }
        if (StringUtil.isEmpty(endDate)) {
            waringParam("结束时间不能为空");
            return SUCCESS;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        realData = realDataService.selectSingleDetStatus(detid);
        map.put("detid", detid);
        map.put("startDate", DateUtil.StrToDate(startDate, DateUtil.TIME_LONG));
        map.put("endDate", DateUtil.StrToDate(endDate, DateUtil.TIME_LONG));
        realData.setDetL1HisList(realDataService.selectDetByIdAndDate(map));
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

}

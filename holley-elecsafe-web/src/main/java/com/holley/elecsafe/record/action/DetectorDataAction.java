package com.holley.elecsafe.record.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jxl.write.WriteException;
import net.sf.json.JSONObject;

import com.holley.elecsafe.action.BaseAction;
import com.holley.elecsafe.common.JxlXlsUtil;
import com.holley.elecsafe.common.cache.ESCacheUtil;
import com.holley.elecsafe.common.constants.DetectorDocStatusEnum;
import com.holley.elecsafe.common.constants.DetectorTypeEnum;
import com.holley.elecsafe.common.constants.ObjectTypeEnum;
import com.holley.elecsafe.model.def.DatEsDetectorL1HisVo;
import com.holley.elecsafe.model.def.EsDetectorVo;
import com.holley.elecsafe.model.es.EsDetectorExample;
import com.holley.elecsafe.service.es.DetectorDataService;
import com.holley.platform.common.constants.Globals;
import com.holley.platform.common.constants.LogOperatorEnum;
import com.holley.platform.common.dataobject.Page;
import com.holley.platform.common.util.JsonUtil;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.util.HostLogUtils;

/**
 * 监测点管理
 * 
 * @author sc
 */
public class DetectorDataAction extends BaseAction {

    @Resource
    private DetectorDataService detectorDataService;
    private Page                page;
    private EsDetectorVo        detector;

    /**
     * 历史记录列表
     * 
     * @return
     */
    public String init() {
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
        int eid = getParamInt("eid");
        String keyword = getParameter("keyword");
        int type = getParamInt("type");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", DetectorDocStatusEnum.NORMAL.getShortValue());
        List<EsDetectorVo> list = null;
        if (eid > 0) {
            map.put("eid", eid);
        } else {
            map.put("eid", getSessionWebUser().getDepartmentid());
        }
        if (StringUtil.isNotEmpty(keyword)) {
            map.put("keyword", keyword);
        }
        if (type > 0) {
            map.put("type", type);
        }
        if (isExportExcel()) {
            list = detectorDataService.selectDetectorByPage(map);
            String[] headsName = { "监测点名称", "所属企业", "监测点型号", "监测点类型", "通讯协议", "通讯地址", "过压阀值(V)", "欠压阀值(V)", "过流阀值(A)", "剩余电流阀值(mA)", "箱体温度阀值(℃)", "A相温度阀值(℃)", "B相温度阀值(℃)",
                    "C相温度阀值(℃)", "零线温度阀值(℃)", "火线温度阀值(℃)", "更新时间" };
            String[] properiesName = { "name", "entName", "brand", "typeStr", "protocolName", "commaddr", "overu", "underu", "overi", "il", "ti", "ta", "tb", "tc", "tn", "tl",
                    "updatetimeStr" };
            JxlXlsUtil jxl = new JxlXlsUtil(this.getRequest(), this.getResponse());
            jxl.exportXLS(list, properiesName, headsName, DatEsDetectorL1HisVo.class);
            return null;
        } else {
            page = returnPage(pageIndex, pageLimit);
            map.put("page", page);
            list = detectorDataService.selectDetectorByPage(map);
            page.setRoot(list);
        }
        return SUCCESS;
    }

    /**
     * 添加修改页面初始化
     * 
     * @return
     */
    public String addOrEditInit() {
        int requestType = getParamInt("requestType");
        int detid = getParamInt("detid");
        if (requestType == 0) {
            message(Globals.PARAM_ERROR_MESSAGE);
            return MSG;
        }
        if (Globals.REQUEST_TYPE_ADD == requestType) {// 添加
            getRequest().setAttribute("requestType", Globals.REQUEST_TYPE_ADD);
        } else if (Globals.REQUEST_TYPE_EDIT == requestType) {// 修改
            if (detid == 0) {
                message(Globals.PARAM_ERROR_MESSAGE);
                return MSG;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("detid", detid);
            map.put("eid", getSessionWebUser().getDepartmentid());
            map.put("status", DetectorDocStatusEnum.NORMAL.getShortValue());
            EsDetectorVo detectorData = detectorDataService.selectSingleDetector(map);
            if (detectorData == null) {
                message("设备不存在");
                return MSG;
            }
            getRequest().setAttribute("detectorData", detectorData);
            getRequest().setAttribute("requestType", Globals.REQUEST_TYPE_EDIT);
        }
        getRequest().setAttribute("modelList", detectorDataService.selectDetectorModelByExample(null));
        getRequest().setAttribute("typeList", DetectorTypeEnum.values());
        getRequest().setAttribute("protocolList", detectorDataService.selectDetectorProtocolByExample(null));
        return SUCCESS;
    }

    /**
     * 修改
     * 
     * @return
     * @throws Exception
     */
    public String editDetector() throws Exception {
        String detectorJson = getParameter("detectorJson");
        if (StringUtil.isEmpty(detectorJson)) {
            errorParam();
            return SUCCESS;
        }
        EsDetectorVo data = JsonToBean(detectorJson, EsDetectorVo.class);
        checkData(data);
        if (this.success) {
            EsDetectorExample emp = new EsDetectorExample();
            EsDetectorExample.Criteria cr = emp.createCriteria();
            cr.andIdNotEqualTo(data.getId());
            cr.andCommaddrEqualTo(data.getCommaddr());
            cr.andStatusEqualTo(DetectorDocStatusEnum.NORMAL.getShortValue());
            int temcount = detectorDataService.countDetectorByExample(emp);
            if (temcount > 0) {
                waringParam("通讯地址重复");
                return SUCCESS;
            }

            data.setUpdatetime(new Date());
            int count = detectorDataService.updateDetectorByPrimaryKeySelective(data);
            if (count > 0) {
                ESCacheUtil.setDcsDetectorUpdateTime(data.getUpdatetime());
                String content = JSONObject.fromObject(data, JsonUtil.returnJosnConfig(false, Date.class)).toString();
                content = "表名:ES_DETECTOR;" + content;
                HostLogUtils.recordDocumentlog(getSessionWebUser().getAccount(), LogOperatorEnum.ADD, getRemoteIP(), null, content);
            } else {
                waringParam("修改失败");
                return SUCCESS;
            }
        }
        return SUCCESS;
    }

    /**
     * 添加
     * 
     * @return
     * @throws Exception
     */
    public String addDetector() throws Exception {
        String detectorJson = getParameter("detectorJson");
        if (StringUtil.isEmpty(detectorJson)) {
            errorParam();
            return SUCCESS;
        }
        EsDetectorVo data = JsonToBean(detectorJson, EsDetectorVo.class);
        checkData(data);
        if (this.success) {
            EsDetectorExample emp = new EsDetectorExample();
            EsDetectorExample.Criteria cr = emp.createCriteria();
            cr.andCommaddrEqualTo(data.getCommaddr());
            cr.andStatusEqualTo(DetectorDocStatusEnum.NORMAL.getShortValue());
            int temcount = detectorDataService.countDetectorByExample(emp);
            if (temcount > 0) {
                waringParam("通讯地址重复");
                return SUCCESS;
            }
            data.setOwnertype(ObjectTypeEnum.OBJ_ENTERPRISE.getShortValue());
            data.setStatus(DetectorDocStatusEnum.NORMAL.getShortValue());
            data.setUpdatetime(new Date());
            int count = detectorDataService.insertDetectorSelective(data);
            if (count > 0) {
                ESCacheUtil.setDcsDetectorUpdateTime(data.getUpdatetime());
                String content = JSONObject.fromObject(data, JsonUtil.returnJosnConfig(false, Date.class)).toString();
                content = "表名:ES_DETECTOR;" + content;
                HostLogUtils.recordDocumentlog(getSessionWebUser().getAccount(), LogOperatorEnum.ADD, getRemoteIP(), null, content);
            } else {
                waringParam("新增失败");
                return SUCCESS;
            }
        }
        return SUCCESS;
    }

    private void checkData(EsDetectorVo detector) {
        if (detector == null) {
            errorParam();
        } else if (StringUtil.isEmpty(detector.getName())) {
            waringParam("监测点名称不能为空");
        } else if (StringUtil.isEmpty(detector.getCommaddr())) {
            waringParam("通讯地址不能为空");
        } else if (detector.getOwnerid() == null || detector.getOwnerid() <= 0) {
            waringParam("请选择所属企业");
        }
    }

    /**
     * 删除监测点
     * 
     * @return
     * @throws Exception
     */
    public String delDetector() throws Exception {
        int detid = getParamInt("detid");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("detid", detid);
        map.put("eid", getSessionWebUser().getDepartmentid());
        map.put("status", DetectorDocStatusEnum.NORMAL.getShortValue());
        EsDetectorVo detectorData = detectorDataService.selectSingleDetector(map);
        if (detectorData == null) {
            waringParam("设备不存在或已经删除");
            return SUCCESS;
        }
        EsDetectorVo data = new EsDetectorVo();
        data.setId(detectorData.getId());
        data.setStatus(DetectorDocStatusEnum.DELETE.getShortValue());
        data.setUpdatetime(new Date());
        int count = detectorDataService.deleteDetectorByPrimaryKeySelective(data);
        if (count > 0) {
            ESCacheUtil.setDcsDetectorUpdateTime(data.getUpdatetime());
            String content = JSONObject.fromObject(data, JsonUtil.returnJosnConfig(false, Date.class)).toString();
            content = "表名:ES_DETECTOR;" + content;
            HostLogUtils.recordDocumentlog(getSessionWebUser().getAccount(), LogOperatorEnum.ADD, getRemoteIP(), null, content);
        }
        return SUCCESS;
    }

    /**
     * 查看详细监测点信息
     * 
     * @return
     */
    public String queryDetail() {
        int detid = getParamInt("detid");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("detid", detid);
        map.put("eid", getSessionWebUser().getDepartmentid());
        map.put("status", DetectorDocStatusEnum.NORMAL.getShortValue());
        EsDetectorVo detectorData = detectorDataService.selectSingleDetector(map);
        if (detectorData == null) {
            waringParam("设备不存在或已经删除");
            return SUCCESS;
        }
        this.detector = detectorData;
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

    public EsDetectorVo getDetector() {
        return detector;
    }

}

package com.holley.elecsafe.sys.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.holley.elecsafe.action.BaseAction;
import com.holley.elecsafe.common.JxlXlsUtil;
import com.holley.elecsafe.common.constants.EsGlobals;
import com.holley.platform.common.constants.LogTypeEnum;
import com.holley.platform.common.dataobject.Page;
import com.holley.platform.common.util.DateUtil;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.model.sys.SysHostLogVo;
import com.holley.platform.service.sys.LogService;

import jxl.write.WriteException;

/**
 * 操作日志
 * 
 * @author Shengjun
 */
public class HostLogAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private LogService        logService;
    private Page              page;

    public String init() {
        this.getRequest().setAttribute("LogTypeList", LogTypeEnum.values());
        return SUCCESS;
    }

    public String query() throws WriteException, IOException {
        String startDate = this.getParameter("startDate");
        String endDate = this.getParameter("endDate");
        String keyword = this.getParameter("keyword");
        String logtype = this.getParameter("logtype");
        String pageIndex = this.getParameter("pageIndex");
        String pageLimit = this.getParameter("pageLimit");

        Map<String, Object> params = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(startDate)) {
            params.put("startDate", DateUtil.ShortStrToDate(startDate));
        }
        if (StringUtil.isNotEmpty(endDate)) {
            params.put("endDate", DateUtil.ShortStrToDate(endDate));
        }
        if (StringUtil.isNotEmpty(keyword)) {
            params.put("keyword", keyword);
        }
        if (StringUtil.isNotEmpty(logtype) && !"0".equals(logtype)) {
            params.put("type", Short.valueOf(logtype));
        }
        params.put("systemid", EsGlobals.DEFAULTSYSTEM);
        if (isExportExcel()) {
            List<SysHostLogVo> sysHostLogList = logService.selectSysHostLog(params);
            String[] headsName = { "账号", "用户名称", "日志操作时间", "IP", "内容", "类型" };
            String[] properiesName = { "account", "name", "logtimeStr", "ip", "content", "logtypeStr" };
            JxlXlsUtil jxl = new JxlXlsUtil(this.getRequest(), this.getResponse());
            jxl.exportXLS(sysHostLogList, properiesName, headsName, SysHostLogVo.class);
            return null;
        } else {
            if (StringUtil.isNotDigits(pageIndex, pageLimit)) {
                this.success = false;
                this.message = "参数格式不正确.";
                return SUCCESS;
            }
        }
        Page page = this.returnPage(Integer.valueOf(pageIndex), Integer.valueOf(pageLimit));
        params.put("page", page);
        List<SysHostLogVo> sysHostLogList = logService.selectSysHostLog(params);
        page.setRoot(sysHostLogList);
        this.page = page;
        return SUCCESS;
    }

    public Page getPage() {
        return page;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

}

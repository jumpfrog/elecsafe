package com.holley.elecsafe.sys.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.holley.elecsafe.action.BaseAction;
import com.holley.elecsafe.common.JxlXlsUtil;
import com.holley.elecsafe.common.constants.EsGlobals;
import com.holley.platform.common.constants.Globals;
import com.holley.platform.common.dataobject.Page;
import com.holley.platform.common.util.DateUtil;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.model.sys.SysWeblog;
import com.holley.platform.model.sys.vo.WebLogVo;
import com.holley.platform.service.sys.LogService;

/**
 * 登录日志
 * 
 * @author tx
 */
public class WebLogAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private LogService        logService;
    private Page              page;
    private List<WebLogVo>    weLogList;
    private List<SysWeblog>   list;

    public String init() {
        return SUCCESS;
    }

    public String query() throws Exception {
        String startdate = this.getParameter("startdate");
        String enddate = this.getParameter("enddate");
        String keyword = this.getParameter("keyword");
        String pageLimit = this.getParameter("pageLimit");
        String pageIndex = this.getParameter("pageIndex");
        String account = getSessionWebUser().getAccount();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("systemid", EsGlobals.DEFAULTSYSTEM);
        if (StringUtil.isNotEmpty(startdate)) {
            params.put("startDate", DateUtil.ShortStrToDate(startdate));
        }
        if (StringUtil.isNotEmpty(enddate)) {
            params.put("endDate", DateUtil.ShortStrToDate(enddate));
        }
        if (StringUtil.isNotEmpty(keyword)) {
            params.put("keyword", StringUtil.trim(keyword));
        }
        if (isExportExcel()) {
            List<WebLogVo> weLogVos = logService.selectWebLogByPage(params, account);
            String[] headsName = { "用户账号", "用户名称", "账号类型", "所属企业", "登录次数", "最近登录时间" };
            String[] properiesName = { "account", "name", "accountTypeStr", "esname", "count", "logtimeStr" };
            JxlXlsUtil jxl = new JxlXlsUtil(this.getRequest(), this.getResponse());
            jxl.exportXLS(weLogVos, properiesName, headsName, WebLogVo.class);
            return null;
        } else {
            if (StringUtil.isNotDigits(pageIndex, pageLimit)) {
                this.success = false;
                this.message = "参数格式不正确.";
                return SUCCESS;
            }
        }
        Page page = this.returnPage(Integer.valueOf(pageIndex), Integer.valueOf(pageLimit));
        params.put(Globals.PAGE, page);
        List<WebLogVo> weLogVos = logService.selectWebLogByPage(params, account);
        this.weLogList = weLogVos;
        page.setRoot(weLogVos);
        this.page = page;
        return SUCCESS;
    }

    public String queryDetaiList() throws Exception {
        String account = this.getParameter("account");
        String startdate = this.getParameter("startdate");
        String enddate = this.getParameter("enddate");
        String pageLimit = this.getParameter("pageLimit");
        String pageIndex = this.getParameter("pageIndex");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("systemid", EsGlobals.DEFAULTSYSTEM);
        if (StringUtil.isNotEmpty(startdate)) {
            params.put("startDate", DateUtil.ShortStrToDate(startdate));
        }
        if (StringUtil.isNotEmpty(enddate)) {
            params.put("endDate", DateUtil.ShortStrToDate(enddate));
        }
        if (StringUtil.isNotEmpty(account)) {
            params.put("account", StringUtil.trim(account));
        }
        if (isExportExcel()) {
            List<SysWeblog> list = logService.selectWebLogDetailByPage(params);
            String[] headsName = { "用户账号", "登录时间" };
            String[] properiesName = { "account", "logtimeStr" };
            JxlXlsUtil jxl = new JxlXlsUtil(this.getRequest(), this.getResponse());
            jxl.exportXLS(list, properiesName, headsName, WebLogVo.class);
            return null;
        } else {
            if (StringUtil.isNotDigits(pageIndex, pageLimit)) {
                this.success = false;
                this.message = "参数格式不正确.";
                return SUCCESS;
            }
        }
        Page page = this.returnPage(Integer.valueOf(pageIndex), Integer.valueOf(pageLimit));
        params.put(Globals.PAGE, page);
        List<SysWeblog> list = logService.selectWebLogDetailByPage(params);
        this.list = list;
        page.setRoot(list);
        this.page = page;
        return SUCCESS;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public List<WebLogVo> getWeLogList() {
        return weLogList;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Page getPage() {
        return page;
    }

    public List<SysWeblog> getList() {
        return list;
    }

}

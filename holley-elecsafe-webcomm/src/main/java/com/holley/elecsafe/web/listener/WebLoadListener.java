package com.holley.elecsafe.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.holley.elecsafe.common.constants.EsGlobals;
import com.holley.elecsafe.common.constants.RuleConst;
import com.holley.elecsafe.common.util.CachedCityUtil;
import com.holley.elecsafe.model.dic.DicCity;
import com.holley.platform.common.constants.Globals;
import com.holley.platform.util.CacheSysHolder;

public class WebLoadListener implements ServletContextListener {

    private final static Logger logger = Logger.getLogger(WebLoadListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        context.setAttribute(Globals.WEB_ROOT, context.getContextPath());
        logger.info("webRoot=" + context.getContextPath());

        String imgUrl = CacheSysHolder.getRuleValueByRuleid(RuleConst.ES_IMG_URL);
        context.setAttribute(Globals.IMG_URL, imgUrl);

        List<DicCity> province = CachedCityUtil.getProvince();
        context.setAttribute(EsGlobals.PROVINCE, province);

        String excelTempPath = CacheSysHolder.getRuleValueByRuleid(RuleConst.ES_EXCEL_TEMP_PATH);
        EsGlobals.EXCEL_TEMP_PATH = excelTempPath;

    }
}

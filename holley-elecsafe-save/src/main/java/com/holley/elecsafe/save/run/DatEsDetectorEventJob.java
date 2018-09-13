package com.holley.elecsafe.save.run;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.holley.elecsafe.common.cache.CacheKeyProvide;
import com.holley.elecsafe.common.constants.EsGlobals;
import com.holley.elecsafe.model.dat.DatEsDetectorEvent;
import com.holley.elecsafe.save.dao.DatEsDetectorEventMapper;
import com.holley.elecsafe.save.pool.ThreadPoolUtil;
import com.holley.elecsafe.save.util.DateUtil;
import com.holley.elecsafe.save.util.ProtostuffUtil;
import com.holley.platform.common.cache.RedisUtil;

public class DatEsDetectorEventJob extends BaseComsJob {

    static Log                       logger = LogFactory.getLog(DatEsDetectorEventJob.class);
    @Autowired
    private DatEsDetectorEventMapper datEsDetectorEventMapper;

    public DatEsDetectorEventJob() {
        moduleName = "报警事件存储";
    }

    @Override
    public void execute() throws Exception {
        if (isRuning) {
            return;
        }
        isRuning = true;
        Date lastDate = new Date();
        List<DatEsDetectorEvent> eventList = new ArrayList<DatEsDetectorEvent>();
        while (isRuning) {
            byte[] objByte = RedisUtil.rpop(CacheKeyProvide.KEY_ES_DCS_DAT_ES_DETECTOR_EVENT.getBytes());

            if (objByte != null) { // 队列为空可以休息
                DatEsDetectorEvent event = (DatEsDetectorEvent) ProtostuffUtil.deserialize(objByte, DatEsDetectorEvent.class);
                eventList.add(event);
                if (eventList.size() >= EsGlobals.MAX_LIST_SIZE) {
                    // 存库
                    saveDate(eventList);
                    eventList = new ArrayList<DatEsDetectorEvent>();
                    lastDate = new Date();
                }
            } else {
                if (eventList.size() > 0) {
                    int detector = DateUtil.getDetectorSecond(lastDate, new Date());
                    if (detector >= EsGlobals.MAX_WAIT_TIME) {
                        // 存库
                        saveDate(eventList);
                        eventList = new ArrayList<DatEsDetectorEvent>();
                        lastDate = new Date();// 重新计时
                    }
                }
                // 队列为空可以休息
                Thread.sleep(15000);
                logger.info("--DatEsDetectorL1RealJob running");
            }

        }

    }

    private void saveDate(List<DatEsDetectorEvent> eventList) {
        DatEsDetectorEventRunable save = new DatEsDetectorEventRunable(eventList, datEsDetectorEventMapper);
        try {
            ThreadPoolUtil.execute(save, "saveDatEsDetectorEvent");
        } catch (Exception e) {
            logger.error("saveDatEsDetectorEvent" + e.getMessage());
        }
    }
}

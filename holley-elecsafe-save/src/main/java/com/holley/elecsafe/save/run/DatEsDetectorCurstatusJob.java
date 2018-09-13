package com.holley.elecsafe.save.run;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.holley.elecsafe.common.cache.CacheKeyProvide;
import com.holley.elecsafe.common.constants.EsGlobals;
import com.holley.elecsafe.model.dat.DatEsDetectorCurstatus;
import com.holley.elecsafe.save.dao.DatEsDetectorCurstatusMapper;
import com.holley.elecsafe.save.pool.ThreadPoolUtil;
import com.holley.elecsafe.save.util.DateUtil;
import com.holley.elecsafe.save.util.ProtostuffUtil;
import com.holley.platform.common.cache.RedisUtil;

public class DatEsDetectorCurstatusJob extends BaseComsJob {

    static Log                           logger = LogFactory.getLog(DatEsDetectorCurstatusJob.class);
    @Autowired
    private DatEsDetectorCurstatusMapper datEsDetectorCurstatusMapper;

    public DatEsDetectorCurstatusJob() {
        moduleName = "设备状态存储";
    }

    @Override
    public void execute() throws Exception {
        if (isRuning) {
            return;
        }
        isRuning = true;
        Date lastDate = new Date();
        List<DatEsDetectorCurstatus> curstatusList = new ArrayList<DatEsDetectorCurstatus>();
        while (isRuning) {
            byte[] objByte = RedisUtil.rpop(CacheKeyProvide.KEY_ES_DCS_DAT_ES_DETECTOR_CURSTATUS.getBytes());

            if (objByte != null) { // 队列为空可以休息
                DatEsDetectorCurstatus curstatus = (DatEsDetectorCurstatus) ProtostuffUtil.deserialize(objByte, DatEsDetectorCurstatus.class);
                curstatusList.add(curstatus);
                if (curstatusList.size() >= EsGlobals.MAX_LIST_SIZE) {
                    // 存库
                    saveDate(curstatusList);
                    curstatusList = new ArrayList<DatEsDetectorCurstatus>();
                    lastDate = new Date();
                }
            } else {
                if (curstatusList.size() > 0) {
                    int detector = DateUtil.getDetectorSecond(lastDate, new Date());
                    if (detector >= EsGlobals.MAX_WAIT_TIME) {
                        // 存库
                        saveDate(curstatusList);
                        curstatusList = new ArrayList<DatEsDetectorCurstatus>();
                        lastDate = new Date();// 重新计时
                    }
                }
                // 队列为空可以休息
                Thread.sleep(15000);
                logger.info("--DatEsDetectorCurstatusJob running");
            }

        }

    }

    private void saveDate(List<DatEsDetectorCurstatus> curstatusList) {
        DatEsDetectorCurstatusRunable save = new DatEsDetectorCurstatusRunable(curstatusList, datEsDetectorCurstatusMapper);
        try {
            ThreadPoolUtil.execute(save, "saveDatEsDetectorCurstatus");
        } catch (Exception e) {
            logger.error("saveDatEsDetectorCurstatus" + e.getMessage());
        }
    }
}

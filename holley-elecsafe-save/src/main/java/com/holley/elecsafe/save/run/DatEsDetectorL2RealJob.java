package com.holley.elecsafe.save.run;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.holley.elecsafe.common.cache.CacheKeyProvide;
import com.holley.elecsafe.common.constants.EsGlobals;
import com.holley.elecsafe.model.dat.DatEsDetectorL2Real;
import com.holley.elecsafe.save.dao.DatEsDetectorL2RealMapper;
import com.holley.elecsafe.save.pool.ThreadPoolUtil;
import com.holley.elecsafe.save.util.DateUtil;
import com.holley.elecsafe.save.util.ProtostuffUtil;
import com.holley.platform.common.cache.RedisUtil;

public class DatEsDetectorL2RealJob extends BaseComsJob {

    static Log                        logger = LogFactory.getLog(DatEsDetectorL2RealJob.class);
    @Autowired
    private DatEsDetectorL2RealMapper datEsDetectorL2RealMapper;

    public DatEsDetectorL2RealJob() {
        moduleName = "一级数据存储";
    }

    @Override
    public void execute() throws Exception {
        if (isRuning) {
            return;
        }
        isRuning = true;
        Date lastDate = new Date();
        List<DatEsDetectorL2Real> detectorL2RealList = new ArrayList<DatEsDetectorL2Real>();
        while (isRuning) {
            byte[] objByte = RedisUtil.rpop(CacheKeyProvide.KEY_ES_DCS_DAT_ES_DETECTOR_L2_REAL.getBytes());

            if (objByte != null) { // 队列为空可以休息
                DatEsDetectorL2Real detectorL2Real = (DatEsDetectorL2Real) ProtostuffUtil.deserialize(objByte, DatEsDetectorL2Real.class);
                detectorL2RealList.add(detectorL2Real);
                if (detectorL2RealList.size() >= EsGlobals.MAX_LIST_SIZE) {
                    // 存库
                    saveDate(detectorL2RealList);
                    detectorL2RealList = new ArrayList<DatEsDetectorL2Real>();
                    lastDate = new Date();
                }
            } else {
                if (detectorL2RealList.size() > 0) {
                    int detector = DateUtil.getDetectorSecond(lastDate, new Date());
                    if (detector >= EsGlobals.MAX_WAIT_TIME) {
                        // 存库
                        saveDate(detectorL2RealList);
                        detectorL2RealList = new ArrayList<DatEsDetectorL2Real>();
                        lastDate = new Date();// 重新计时
                    }
                }
                // 队列为空可以休息
                Thread.sleep(15000);
                logger.info("--DatEsDetectorL2RealJob running");
            }

        }

    }

    private void saveDate(List<DatEsDetectorL2Real> l2RealList) {
        DatEsDetectorL2RealRunable save = new DatEsDetectorL2RealRunable(l2RealList, datEsDetectorL2RealMapper);
        try {
            ThreadPoolUtil.execute(save, "saveDatEsDetectorL2Real");
        } catch (Exception e) {
            logger.error("saveDatEsDetectorL2Real" + e.getMessage());
        }
    }
}

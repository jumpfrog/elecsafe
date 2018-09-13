package com.holley.elecsafe.save.run;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.holley.elecsafe.common.cache.CacheKeyProvide;
import com.holley.elecsafe.common.constants.EsGlobals;
import com.holley.elecsafe.model.dat.DatEsDetectorL1Real;
import com.holley.elecsafe.save.dao.DatEsDetectorL1RealMapper;
import com.holley.elecsafe.save.pool.ThreadPoolUtil;
import com.holley.elecsafe.save.util.DateUtil;
import com.holley.elecsafe.save.util.ProtostuffUtil;
import com.holley.platform.common.cache.RedisUtil;

public class DatEsDetectorL1RealJob extends BaseComsJob {

    static Log                        logger = LogFactory.getLog(DatEsDetectorL1RealJob.class);
    @Autowired
    private DatEsDetectorL1RealMapper datEsDetectorL1RealMapper;

    public DatEsDetectorL1RealJob() {
        moduleName = "一级数据存储";
    }

    @Override
    public void execute() throws Exception {
        if (isRuning) {
            return;
        }
        isRuning = true;
        Date lastDate = new Date();
        List<DatEsDetectorL1Real> detectorL1RealList = new ArrayList<DatEsDetectorL1Real>();
        while (isRuning) {
            byte[] objByte = RedisUtil.rpop(CacheKeyProvide.KEY_ES_DCS_DAT_ES_DETECTOR_L1_REAL.getBytes());

            if (objByte != null) { // 队列为空可以休息
                DatEsDetectorL1Real detectorL1Real = (DatEsDetectorL1Real) ProtostuffUtil.deserialize(objByte, DatEsDetectorL1Real.class);
                detectorL1RealList.add(detectorL1Real);
                if (detectorL1RealList.size() >= EsGlobals.MAX_LIST_SIZE) {
                    // 存库
                    saveDate(detectorL1RealList);
                    detectorL1RealList = new ArrayList<DatEsDetectorL1Real>();
                    lastDate = new Date();
                }
            } else {
                if (detectorL1RealList.size() > 0) {
                    int detector = DateUtil.getDetectorSecond(lastDate, new Date());
                    if (detector >= EsGlobals.MAX_WAIT_TIME) {
                        // 存库
                        saveDate(detectorL1RealList);
                        detectorL1RealList = new ArrayList<DatEsDetectorL1Real>();
                        lastDate = new Date();// 重新计时
                    }
                }
                // 队列为空可以休息
                Thread.sleep(15000);
                logger.info("--DatEsDetectorL1RealJob running");
            }

        }

    }

    private void saveDate(List<DatEsDetectorL1Real> liRealList) {
        DatEsDetectorL1RealRunable save = new DatEsDetectorL1RealRunable(liRealList, datEsDetectorL1RealMapper);
        try {
            ThreadPoolUtil.execute(save, "saveDatEsDetectorL1Real");
        } catch (Exception e) {
            logger.error("saveDatEsDetectorL1Real" + e.getMessage());
        }
    }
}

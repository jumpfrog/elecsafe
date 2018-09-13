package com.holley.elecsafe.save.run;

import org.springframework.beans.factory.annotation.Autowired;

public class ExceteDbSchedule {

    private Thread            datEsDetectorL1RealThread    = null;

    private Thread            datEsDetectorL2RealThread    = null;

    private Thread            DatEsDetectorCurstatusThread = null;

    private Thread            datEsDetectorEventThread     = null;

    @Autowired
    DatEsDetectorL1RealJob    datEsDetectorL1RealJob;
    @Autowired
    DatEsDetectorL2RealJob    datEsDetectorL2RealJob;
    @Autowired
    DatEsDetectorCurstatusJob datEsDetectorCurstatusJob;
    @Autowired
    DatEsDetectorEventJob     datEsDetectorEventJob;

    public void init() throws Exception {
        // 一级数据
        if (datEsDetectorL1RealJob != null) {
            datEsDetectorL1RealThread = new Thread(datEsDetectorL1RealJob);
            datEsDetectorL1RealThread.start();
        }
        // 二级数据
        if (datEsDetectorL2RealJob != null) {
            datEsDetectorL2RealThread = new Thread(datEsDetectorL2RealJob);
            datEsDetectorL2RealThread.start();
        }
        // 设备状态
        if (datEsDetectorCurstatusJob != null) {
            DatEsDetectorCurstatusThread = new Thread(datEsDetectorCurstatusJob);
            DatEsDetectorCurstatusThread.start();
        }
        // 报警数据
        if (datEsDetectorEventJob != null) {
            datEsDetectorEventThread = new Thread(datEsDetectorEventJob);
            datEsDetectorEventThread.start();
        }
    }

}

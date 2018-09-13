package com.holley.elecsafe.service;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.holley.elecsafe.model.es.EsDetector;
import com.holley.elecsafe.thread.MainThread;

public class DeviceManagerService extends BaseService {

    private MainThread                       mainThread;
    static Log                               logger  = LogFactory.getLog(DeviceManagerService.class.getName());
    private Hashtable<String, EsDetector>    deviceMap;
    private Hashtable<String, DeviceService> onlineDeviceServiceMap;
    private static final String              headStr = "ETUNG:";

    public DeviceManagerService() {
        deviceMap = new Hashtable<String, EsDetector>();
        onlineDeviceServiceMap = new Hashtable<String, DeviceService>();
    }

    public void onTimer() {
        if (onlineDeviceServiceMap != null && onlineDeviceServiceMap.size() > 0) {
            Enumeration<DeviceService> psEnum = onlineDeviceServiceMap.elements();
            while (psEnum.hasMoreElements()) {
                DeviceService devService = psEnum.nextElement();
                devService.onTimer();
            }
        }
    }

    public synchronized void onLoadPvDevice(List<EsDetector> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        if (deviceMap == null) {
            deviceMap = new Hashtable<String, EsDetector>();
        }
        for (int i = 0; i < list.size(); i++) {
            EsDetector dev = new EsDetector();
            BeanUtils.copyProperties(list.get(i), dev);
            if (!StringUtils.isEmpty(dev.getCommaddr())) {
                String devKey = headStr + dev.getCommaddr().toUpperCase();
                if (deviceMap.containsKey(devKey)) {
                    if (onlineDeviceServiceMap.containsKey(devKey)) {
                        EsDetector oldDevice = deviceMap.get(devKey);
                        DeviceService ps = onlineDeviceServiceMap.get(devKey);
                        if (dev.getProtocolid() != oldDevice.getProtocolid()) {
                            ps.disconnect();
                        }
                        BeanUtils.copyProperties(dev, ps.getDevice());
                    }
                }
                deviceMap.put(devKey, dev);
            }
        }
    }

    public void setMainThread(MainThread mainThread) {
        this.mainThread = mainThread;
    }

    /**
     * @Title: getPvDeviceServiceByCommAddr @Description: TODO 初始化设备服务 @param @param commAddr @param @return
     * 设定文件 @return PvDeviceService 返回类型 @throws
     */
    public DeviceService getPvDeviceServiceByCommAddr(String commAddr) {
        String servicekey = commAddr.toUpperCase();
        DeviceService devService = null;
        EsDetector dev = deviceMap.get(servicekey);
        if (dev == null) {
            logger.info("非法设备接入！");
            return null;
        }
        if (onlineDeviceServiceMap.containsKey(servicekey)) {
            devService = onlineDeviceServiceMap.get(servicekey);
        } else {
            devService = new DeviceService(dev);
            devService.setProtocol(mainThread.getProtocolService().getProtocol(dev.getProtocolid(), this));
            devService.setDataBaseService(mainThread.getDataBaseService());
            devService.setManager(this);
            devService.init();
            onlineDeviceServiceMap.put(servicekey, devService);
        }
        return devService;
    }
}

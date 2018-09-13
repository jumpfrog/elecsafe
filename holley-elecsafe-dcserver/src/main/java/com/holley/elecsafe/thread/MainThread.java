package com.holley.elecsafe.thread;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.holley.elecsafe.channel.ChannelManager;
import com.holley.elecsafe.common.cache.ESCacheUtil;
import com.holley.elecsafe.dcserver.DCSConfig;
import com.holley.elecsafe.dcserver.DCSService;
import com.holley.elecsafe.dcserver.SpringSupport;
import com.holley.elecsafe.model.es.EsDetector;
import com.holley.elecsafe.protocol.ProtocolService;
import com.holley.elecsafe.service.DeviceManagerService;
import com.holley.elecsafe.service.IDataBaseService;
import com.holley.platform.dcs.media.MediaPara;

public class MainThread extends BaseThread {

    private DCSService            server;

    static Log                    logger              = LogFactory.getLog(MainThread.class.getName());

    private IDataBaseService      dataBaseService;

    private ProtocolService       protocolService;

    private DeviceManagerService  deviceManagerService;

    private ChannelManager        channelManager;

    private ServerMonitorThread[] serverMonitorThread = null;

    public MainThread(DCSService server) {
        this.server = server;

        dataBaseService = (IDataBaseService) SpringSupport.springHandle.getBean("dataBaseService");
        deviceManagerService = (DeviceManagerService) SpringSupport.springHandle.getBean("deviceManagerService");
        channelManager = (ChannelManager) SpringSupport.springHandle.getBean("channelManager");
        protocolService = (ProtocolService) SpringSupport.springHandle.getBean("protocolService");

        deviceManagerService.setMainThread(this);
    }

    @Override
    public void run() {
        loadDevices();
        // 启动服务端监听通讯管理线程池,一个端口一条线程
        List<MediaPara> list = DCSConfig.sysConfig.getServerMediaList(); // MediaPara para:port 3401:3:ProtocolID 101
        int threadNum = list.size();
        if (list != null && threadNum != 0) {// 1
            serverMonitorThread = new ServerMonitorThread[threadNum];
            for (int i = 0; i < list.size(); i++) {
                serverMonitorThread[i] = new ServerMonitorThread(this);
                serverMonitorThread[i].setMediaPara(list.get(i));
                serverMonitorThread[i].start();
            }
        }
        Date lastLoadDBTime = Calendar.getInstance().getTime();
        int k = 1; // 计数器
        while (!bStopThread) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
            k++;
            if (k < 0) k = 1;
            if (k % (15) == 0) { // 30秒，参数更新
                try {
                    Date devUpdateTime = ESCacheUtil.getDcsDetectorUpdateTime();// 从缓存拿
                    if (devUpdateTime != null && lastLoadDBTime.before(devUpdateTime)) {// 若30秒内有设备更新就重新加载
                        // 重新加载充电桩信息
                        List<EsDetector> pileList = dataBaseService.reLoadDevices(lastLoadDBTime);
                        deviceManagerService.onLoadPvDevice(pileList);
                        logger.info("发现设备参数修改！ 修改数量=" + pileList.size());
                        lastLoadDBTime = devUpdateTime;
                    }
                } catch (Exception e) {
                    logger.info(e.getMessage());
                }

            }
            try {
                // 监听服务
                for (int i = 0; i < threadNum; i++) {
                    if (serverMonitorThread[i] != null) {
                        if (serverMonitorThread[i].getState().equals(Thread.State.TERMINATED)) {// 如果发现acceptor线程终止就重新启动
                            ServerMonitorThread temp = serverMonitorThread[i];
                            serverMonitorThread[i] = new ServerMonitorThread(this);
                            serverMonitorThread[i].setMediaPara(temp.getMediaPara());
                            serverMonitorThread[i].start();
                        }
                    }
                }
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
            try {
                // 设备管理定时服务
                if (deviceManagerService != null) {
                    deviceManagerService.onTimer();// 实际上调用的是protocolHLPV的onTimer（）
                }
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
            try {
                if (k % (3600) == 0) { // 1小时，通道清理:清理五小时没工作的通道
                    if (channelManager != null) {
                        channelManager.onTimer();
                    }
                }
            } catch (Exception e) {
                logger.debug(e.toString());
            }

        }
    }

    /**
     * @Title: loadDevices @Description: TODO 加载设备 @param 设定文件 @return void 返回类型 @throws
     */
    protected void loadDevices() {
        List<EsDetector> esDetectorList = dataBaseService.loadDevices();
        deviceManagerService.onLoadPvDevice(esDetectorList);
    }

    public ProtocolService getProtocolService() {
        return protocolService;
    }

    public ChannelManager getChannelManager() {
        return channelManager;
    }

    public DeviceManagerService getDeviceManagerService() {
        return deviceManagerService;
    }

    public void exitThread() {
        super.exitThread();
    }

    public boolean isActiveStatus() {
        return true;
    }

    public IDataBaseService getDataBaseService() {
        return dataBaseService;
    }
}

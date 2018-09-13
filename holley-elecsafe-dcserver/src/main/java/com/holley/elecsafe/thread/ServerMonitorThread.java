package com.holley.elecsafe.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.holley.elecsafe.channel.IProtocol;
import com.holley.platform.dcs.constant.DcsGlobal;
import com.holley.platform.dcs.media.MediaPara;
import com.holley.platform.dcs.media.TcpServer;

public class ServerMonitorThread extends BaseThread {

    static Log         logger    = LogFactory.getLog(ServerMonitorThread.class.getName());
    private MainThread mainThread;
    private MediaPara  mediaPara = null;

    public ServerMonitorThread(MainThread mainThread) {
        this.mainThread = mainThread;
    }

    public MediaPara getMediaPara() {
        return mediaPara;
    }

    public void setMediaPara(MediaPara mediaPara) {
        this.mediaPara = mediaPara;
    }

    @Override
    public void run() {
        if (mediaPara == null || mainThread == null) {
            logger.error("system error!");
            super.run();
            return;
        }
        logger.info("ServerManThread start! (" + Thread.currentThread().getName() + ")");
        TcpServer server = new TcpServer();
        server.setMediaPara(mediaPara);
        IProtocol protocol = mainThread.getProtocolService().getProtocol(mediaPara.getProtocolID().shortValue(), mainThread.getDeviceManagerService());
        server.addListener(mainThread.getChannelManager().createMonitor(protocol));
        // 如果没有协议存在则不开启服务器端
        if (protocol == null) {
            logger.info("找不到ID为 " + mediaPara.getProtocolID() + "的协议");
            exitThread();
        }
        while (!bStopThread) {

            if (server.isOpen() == false) {
                server.openDev();
            }
            try {
                Thread.sleep(DcsGlobal.INTERVAL_CHAN_THREAD);
            } catch (InterruptedException e) {
                continue;
            }
        }
        logger.info("ServerManThread exit! (" + Thread.currentThread().getName() + ")");
        super.run();
    }

}

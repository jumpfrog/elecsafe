package com.holley.elecsafe.channel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.holley.platform.dcs.common.IMediaListener;

/**
 * 通道管理
 */
public class ChannelManager {

    static Log                    logger                = LogFactory.getLog(ChannelManager.class.getName());

    Hashtable<String, DCSChannel> unregisterClientTable = new Hashtable<String, DCSChannel>();
    Hashtable<String, DCSChannel> registerClientTable   = new Hashtable<String, DCSChannel>();
    List<ChannelServerMonitor>    monitorList           = new ArrayList<ChannelServerMonitor>();

    public ChannelManager() {

    }

    /**
     * @Title: onTimer @Description: TODO 定时清理1小时没数据的通道 @param 设定文件 @return void 返回类型 @throws
     */
    public void onTimer() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, -1); // TODO

        if (unregisterClientTable != null) {
            Iterator<Entry<String, DCSChannel>> iter = unregisterClientTable.entrySet().iterator();
            logger.debug("剔除前的unregisterClientTable连接个数为" + unregisterClientTable.size());
            while (iter.hasNext()) {
                Entry<String, DCSChannel> e = iter.next();
                DCSChannel cs = e.getValue();
                if (cs != null) {
                    if (cs.getLastDataTime().before(now.getTime())) {
                        iter.remove();
                        cs.closeChannel();
                    }
                }
            }
            logger.debug("剔除后的unregisterClientTable连接个数为" + unregisterClientTable.size());
        }
        if (registerClientTable != null) {
            logger.debug("Finding Zombie channel!  ");
            Iterator<Entry<String, DCSChannel>> iter = registerClientTable.entrySet().iterator();
            logger.debug("剔除前的registerClient连接个数为" + registerClientTable.size());

            while (iter.hasNext()) {
                Entry<String, DCSChannel> e = iter.next();
                DCSChannel cs = e.getValue();
                if (cs != null) {
                    if (cs.getLastDataTime().before(now.getTime())) {
                        logger.debug("Finded Zombie channel!  ID=" + cs.getChannelID() + "  lastdatatime=" + cs.getLastDataTime());
                        iter.remove();
                        cs.closeChannel();
                    }
                }
            }
            logger.debug("剔除后的registerClient连接个数为" + registerClientTable.size());
        }
    }

    public void linkDevice(IDeviceService devService, DCSChannel channelIO) {
        if (devService == null || channelIO == null) {
            return;
        }
        registerClientTable.put(channelIO.getChannelID(), channelIO);
        unregisterClientTable.remove(channelIO.getChannelID());

        logger.info("Device Register,DeviceCode =" + devService.getDeviceCode() + "  Channel=" + channelIO.getChannelID());
    }

    public void onCloseChannel(DCSChannel channelIO) {
        registerClientTable.remove(channelIO.getChannelID());
    }

    public IMediaListener createMonitor(IProtocol protocol) {
        ChannelServerMonitor monitor = new ChannelServerMonitor();
        monitor.setManager(this);
        if (protocol != null) {
            monitor.setProtocol(protocol);
        }
        monitorList.add(monitor);

        return monitor;
    }

    public void addUNRegisterClient(DCSChannel io) {
        unregisterClientTable.put(io.getChannelID(), io);
    }

    public void removeClient(String key) {
        DCSChannel cs = registerClientTable.get(key);
        if (cs != null) {
            cs.closeChannel();
        } else {
            unregisterClientTable.remove(key);
        }
    }
}

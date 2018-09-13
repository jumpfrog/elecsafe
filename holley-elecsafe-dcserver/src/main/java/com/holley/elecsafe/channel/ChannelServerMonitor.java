package com.holley.elecsafe.channel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.holley.platform.dcs.common.IMediaListener;
import com.holley.platform.dcs.common.ReceiveEventArgs;
import com.holley.platform.dcs.media.TcpClient;

public class ChannelServerMonitor implements IMediaListener {

    static Log             logger   = LogFactory.getLog(DCSChannel.class.getName());

    private ChannelManager manager  = null;
    private IProtocol      protocol = null;

    public IProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(IProtocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public void onReceived(Object sender, ReceiveEventArgs e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onActive(Object media, boolean active) {
        if (media instanceof TcpClient) {
            TcpClient client = (TcpClient) media;
            String key = client.getMediaPara().getID();//
            if (active == true) {// 必定为true
                DCSChannel io = new DCSChannel();
                io.setManager(manager);
                io.setChannelID(key);
                io.setMedia(client);
                io.setProtocol(protocol);
                client.addListener(io);
                manager.addUNRegisterClient(io);
                logger.info("设备" + ": connected!");
            } else {
                manager.removeClient(key);
                logger.info(key + ": disconnected!");
            }

        }
    }

    public ChannelManager getManager() {
        return manager;
    }

    public void setManager(ChannelManager manager) {
        this.manager = manager;
    }

}

package com.holley.elecsafe.service;

import com.holley.elecsafe.channel.DCSChannel;
import com.holley.elecsafe.channel.IDeviceService;
import com.holley.elecsafe.channel.IProtocol;
import com.holley.elecsafe.model.es.EsDetector;

public class DeviceService extends BaseService implements IDeviceService {

    IProtocol            protocol        = null;
    private EsDetector   device;
    DCSChannel           channel         = null;
    String               commAddr;
    IDataBaseService     dataBaseService = null;
    DeviceManagerService manager         = null;

    public DeviceService() {
    }

    public DeviceService(EsDetector dev) {
        this();
        this.device = dev;
        this.commAddr = dev.getCommaddr().toUpperCase();
    }

    @Override
    public String getDeviceCode() {
        return commAddr;
    }

    @Override
    public int onReceive(byte[] msg) {
        if (protocol == null) {
            return 0;
        } else {
            return protocol.onReceive(msg, this, channel);
        }
    }

    public void onTimer() {
        if (protocol != null) {
            protocol.onTimer(this, channel);
        }
    }

    public void disconnect() {
        if (channel != null && channel.isOpen()) {
            channel.closeChannel();
        }
    }

    public void setProtocol(IProtocol createProtocol) {
        this.protocol = createProtocol;

    }

    public void setDataBaseService(IDataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;

    }

    public void setManager(DeviceManagerService deviceManagerService) {
        this.manager = deviceManagerService;
    }

    public void init() {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyLogin(byte[] data) {
        if (channel == null || protocol == null) {
            return;
        }
        protocol.onLogin(data, this, channel);
    }

    @Override
    public void linkChannel(DCSChannel channel) {
        this.channel = channel;
    }

    @Override
    public boolean saveData(Object rec) {
        /*
         * if (dataBaseService != null) { return dataBaseService.saveData(rec); }
         */
        return false;
    }

    @Override
    public boolean sendBizCmd(Object data) {
        if (data == null) {
            return false;
        }
        /*
         * if (data instanceof MsgBase) { MsgBase msg = (MsgBase) data; if (protocol != null && channel != null) {
         * Enumeration<PvDevice> en2 = deviceMap.elements(); while (en2.hasMoreElements()) { PvDevice dev =
         * en2.nextElement(); protocol.sendBizData(msg.getCmdtype(), msg, dev.getId(), this, channel); break; } } }
         */
        return false;
    }

    public void setDevice(EsDetector device) {
        this.device = device;
    }

    @Override
    public EsDetector getDevice() {
        // TODO Auto-generated method stub
        return device;
    }

}

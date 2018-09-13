package com.holley.elecsafe.channel;

import com.holley.elecsafe.model.es.EsDetector;

public interface IDeviceService {

    public String getDeviceCode();

    public int onReceive(byte[] msg);

    public void notifyLogin(byte[] data);

    public void linkChannel(DCSChannel channel);

    public boolean saveData(Object rec);

    public boolean sendBizCmd(Object data);

    public EsDetector getDevice();
}

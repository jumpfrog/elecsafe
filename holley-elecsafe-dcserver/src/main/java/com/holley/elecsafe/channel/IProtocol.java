package com.holley.elecsafe.channel;

import com.holley.platform.dcs.protocol.IProtocolCallBack;

public interface IProtocol {

    public Short getProtocolID();

    public void addCallBack(IProtocolCallBack callBack);

    public IDeviceService registerProtocol(byte[] buffer, DCSChannel channel);

    public int onReceive(byte[] msg, IDeviceService devService, DCSChannel channel);

    public int onLogin(byte[] data, IDeviceService devService, DCSChannel channel);

    public int sendHeart(IDeviceService devService, DCSChannel channel);

    public int sendBizData(int cmdType, Object data, Integer deviceID, IDeviceService devService, DCSChannel channel);

    public void onTimer(IDeviceService devService, DCSChannel channel);

}

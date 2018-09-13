package com.holley.elecsafe.channel;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.holley.platform.common.util.ProtocolUtils;
import com.holley.platform.dcs.common.IMediaListener;
import com.holley.platform.dcs.common.ReceiveEventArgs;
import com.holley.platform.dcs.constant.DcsGlobal;
import com.holley.platform.dcs.media.Media;

public class DCSChannel implements IMediaListener {

    static Log                 logger        = LogFactory.getLog(DCSChannel.class.getName());

    protected ChannelRunStatus runStatus;
    protected Media            media         = null;
    protected IDeviceService   deviceService = null;
    protected String           channelID;

    protected int              readPtr       = 0;
    protected int              oldReadPtr    = 0;
    protected int              readDealPtr   = 0;

    protected int              sendPtr       = 0;
    protected int              sendDealPtr   = 0;

    protected byte             sendBuff[]    = new byte[DcsGlobal.MAX_LEN_RECV];
    protected byte             recvBuff[]    = new byte[DcsGlobal.MAX_LEN_RECV];

    protected volatile long    sendCounts    = 0;
    protected volatile long    recvCounts    = 0;

    protected Date             lastDataTime  = Calendar.getInstance().getTime();
    protected IProtocol        protocol      = null;
    protected ChannelManager   manager       = null;

    public DCSChannel() {
        runStatus = new ChannelRunStatus();
        runStatus.setCmdMaxWaitTime(DcsGlobal.MAX_CHANCMD_WAITTIME);
    }

    public final ChannelRunStatus getRunStatus() {
        return runStatus;
    }

    public final void setRunStatus(ChannelRunStatus runStatus) {
        this.runStatus = runStatus;
    }

    public void timeProc() {
        if (runStatus.getLinkWaitTimeout() != 0) {
            runStatus.setLinkWaitTimeout(runStatus.getLinkWaitTimeout() - 1);
        }

        if (isOpen()) {
            readDevice();
            writeData();
        }
    }

    protected void readDevice() {

    }

    public boolean isOpen() {
        return media == null ? false : media.isOpen();
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public void closeChannel() {
        sendBuff = null;
        recvBuff = null;

        try {
            if (media != null) {
                this.media.closeDev();
                media = null;
            }
        } catch (Exception e) {
            logger.info("关闭连接异常！");
        }

        manager.onCloseChannel(this);

        manager = null;
        protocol = null;
        deviceService = null;
        logger.info(this.channelID + ":---连接断开----");
    }

    protected void writeData() {
        if (this.media == null || media.isOpen() == false) {
            return;
        }
        if (sendDealPtr == sendPtr) {
            return;
        }
        byte data[] = new byte[ProtocolUtils.cyclenth(sendPtr, sendDealPtr)];
        int pos = 0, dealPtr = sendDealPtr;
        while (dealPtr != sendPtr) {
            data[pos] = sendBuff[dealPtr];
            dealPtr = ProtocolUtils.cycpos(dealPtr, 1);
            pos++;
            if (pos >= data.length) {
                break;
            }
        }
        int sendNum = writeData(data);
        runStatus.setSendNum(runStatus.getSendNum() + sendNum);
        sendDealPtr = ProtocolUtils.cycpos(sendDealPtr, sendNum);
        runStatus.setLastSendTime(Calendar.getInstance().getTime());
    }

    public int writeData(byte[] data) {
        if (data == null || this.media == null || media.isOpen() == false) {
            return 0;
        }
        logger.debug("Channel_Send:" + ProtocolUtils.printMsg(0, data.length, data));
        return media.writeDev(data, deviceService == null ? "" : deviceService.getDeviceCode());
    }

    public void onReceived(Object sender, ReceiveEventArgs e) {
        if (e == null || e.getData() == null) {
            return;
        }
        byte[] data = (byte[]) e.getData();
        int recvNum = data.length, i = 0;

        if (recvNum == 0) {
            return;
        }
        for (i = 0; i < recvNum; i++) {
            recvBuff[readPtr] = data[i];
            readPtr = ProtocolUtils.cycpos(readPtr, 1);
            if (readPtr == readDealPtr) {
                @SuppressWarnings("unused")
                int error = 1;
                error++;
            }
        }
        runStatus.setRecvNum(runStatus.getRecvNum() + recvNum);
        runStatus.setLastRecvTime(Calendar.getInstance().getTime());

        logger.debug("Channel_Recv:" + "(" + readPtr + ")" + ProtocolUtils.printMsg(0, recvNum, data));
        if (deviceService == null) {
            if (protocol != null) {
                int len = ProtocolUtils.cyclenth(readPtr, readDealPtr);
                byte[] buffer = getAbsoluteRecvData(len);
                deviceService = protocol.registerProtocol(data, this);
                manager.linkDevice(deviceService, this);
            } else {
                // handleNBLogin();
            }
        } else {
            int len = ProtocolUtils.cyclenth(readPtr, readDealPtr);
            byte[] msg = getAbsoluteRecvData(len);
            try {
                int dealLen = deviceService.onReceive(data);
                setReadDealPtr(getReadDealPtr() + dealLen);
            } catch (Exception exc) {
                logger.debug(exc.getMessage());
                this.closeChannel();
            }
        }

    }

    public final int getReadPtr() {
        return readPtr;
    }

    public final void setReadPtr(int readPtr) {
        this.readPtr = readPtr;
    }

    public final int getOldReadPtr() {
        return oldReadPtr;
    }

    public final void setOldReadPtr(int oldReadPtr) {
        this.oldReadPtr = oldReadPtr;
    }

    public final int getReadDealPtr() {
        return readDealPtr;
    }

    public final void setReadDealPtr(int readDealPtr) {
        this.readDealPtr = readDealPtr % DcsGlobal.MAX_LEN_RECV;
    }

    public final int getSendPtr() {
        return sendPtr;
    }

    public final void setSendPtr(int sendPtr) {
        this.sendPtr = sendPtr;
    }

    public final int getSendDealPtr() {
        return sendDealPtr;
    }

    public final void setSendDealPtr(int sendDealPtr) {
        this.sendDealPtr = sendDealPtr;
    }

    public long getCmdMaxWaitTime() {
        return runStatus.getCmdMaxWaitTime();
    }

    public byte[] getSendData() {
        return sendBuff;
    }

    public void sendData(byte[] data, int len) {
        for (int i = 0; i < len; i++) {
            sendBuff[sendPtr] = data[i];
            sendPtr = ProtocolUtils.cycpos(sendPtr, 1);
        }
    }

    public byte[] getRecvData() {
        return recvBuff;
    }

    public byte[] getAbsoluteRecvData(int len) {
        int curreadPtr = readPtr;
        int recvLen = ProtocolUtils.cyclenth(curreadPtr, readDealPtr);
        byte[] buf = new byte[Math.min(recvLen, len)];
        for (int i = 0; i < Math.min(recvLen, len); i++) {
            buf[i] = recvBuff[ProtocolUtils.cycpos(readDealPtr, i)];
        }
        return buf;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        DCSChannel io = new DCSChannel();
        io.setMedia(this.media);
        io.setRunStatus(this.getRunStatus());
        return io;
    }

    public final Media getMedia() {
        return media;
    }

    public final Date getLastDataTime() {
        return runStatus.lastRecvTime == null ? lastDataTime : runStatus.lastRecvTime;
    }

    public final void setLastDataTime(Date lastDataTime) {
        this.lastDataTime = lastDataTime;
    }

    public final long getSendCounts() {
        return sendCounts;
    }

    public final long getRecvCounts() {
        return recvCounts;
    }

    public void addRecvCounts(int num) {
        recvCounts += num;
        if (recvCounts < 0) {
            recvCounts = num;
        }
    }

    public void addSendCounts(int num) {
        sendCounts += num;
        if (sendCounts < 0) {
            sendCounts = num;
        }
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    @Override
    public void onActive(Object media, boolean active) {
        // TODO Auto-generated method stub
    }

    public void setManager(ChannelManager manager) {
        this.manager = manager;
    }

    public IProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(IProtocol protocol) {
        this.protocol = protocol;
    }

}

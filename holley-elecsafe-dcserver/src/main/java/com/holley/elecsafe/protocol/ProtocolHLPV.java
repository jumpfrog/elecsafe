package com.holley.elecsafe.protocol;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.holley.elecsafe.bqdevice.BQData;
import com.holley.elecsafe.bqdevice.BQFactory;
import com.holley.elecsafe.channel.DCSChannel;
import com.holley.elecsafe.channel.IDeviceService;
import com.holley.elecsafe.channel.IProtocol;
import com.holley.elecsafe.common.cache.CacheKeyProvide;
import com.holley.elecsafe.model.dat.DatEsDetectorCurstatus;
import com.holley.elecsafe.model.dat.DatEsDetectorEvent;
import com.holley.elecsafe.model.dat.DatEsDetectorL1Real;
import com.holley.elecsafe.model.dat.DatEsDetectorL2Real;
import com.holley.elecsafe.service.DeviceManagerService;
import com.holley.elecsafe.utils.ProtostuffUtil;
import com.holley.platform.common.cache.RedisUtil;
import com.holley.platform.dcs.BaseProtocol;

public class ProtocolHLPV extends BaseProtocol implements IProtocol {

    static Log                     logger         = LogFactory.getLog(ProtocolHLPV.class.getName());

    private DeviceManagerService   deviceManager  = null;
    // 最小帧长
    private final int              minDateLength  = 5;                                              // 最小帧为心跳包

    private Map<String, Object>    dataMap        = new ConcurrentHashMap<String, Object>();

    private Map<Short, Short>      eventMap       = new HashMap<Short, Short>();

    private Map<Short, Short>      faultMap       = new HashMap<Short, Short>();

    private Date                   lastrectime    = new Date();

    private DatEsDetectorCurstatus detectorStatus = new DatEsDetectorCurstatus();

    public ProtocolHLPV() {
    }

    public ProtocolHLPV(DeviceManagerService dm) {
        this.deviceManager = dm;
    }

    @Override
    public IDeviceService registerProtocol(byte[] buffer, DCSChannel channel) {
        // 注册帧长21
        if (buffer == null || buffer.length < 21) {
            return null;
        }
        // ETUNG:900000000000100
        if (buffer[0] == 0x45 && buffer[1] == 0x54 && buffer[2] == 0x55 && buffer[3] == 0x4E && buffer[4] == 0x47 && buffer[5] == 0x3A) {
            String CommAddr = new String(buffer);
            logger.info("-------收到注册帧:" + CommAddr);
            IDeviceService devService = deviceManager.getPvDeviceServiceByCommAddr(CommAddr);// 初始化pvdevService
            channel.setReadDealPtr(channel.getReadDealPtr() + 21);
            //
            if (devService != null) {
                devService.linkChannel(channel);
                detectorStatus.setDetid(devService.getDevice().getId());
                detectorStatus.setDatatime(new Date());
                detectorStatus.setIsoffline((short) 0);
                // 加入缓存
                RedisUtil.lpush(CacheKeyProvide.KEY_ES_DCS_DAT_ES_DETECTOR_CURSTATUS.getBytes(), ProtostuffUtil.serialize(detectorStatus));
                logger.info("设备在线状态存入缓存:" + detectorStatus.toString());
            }
            return devService;
        }
        return null;
    }

    @Override
    public int onReceive(byte[] msg, IDeviceService devService, DCSChannel channel) {
        lastrectime = new Date();
        logger.info("1---收到数据onReceive开始:" + msg);
        if (msg == null || msg.length < minDateLength) {
            return 0;
        }
        // ETUNG
        if (msg[0] == 0x45 && msg[1] == 0x54 && msg[2] == 0x55 && msg[3] == 0x4E && msg[4] == 0x47) {
            // 心跳包
            logger.info("-------收到心跳包:" + new String(msg));
            return 0;
        }
        int dealLen = 0;
        dealLen = dealLen + 4;
        int recvLen = msg.length;
        boolean flag = false;
        short deviceType = msg[dealLen + 4];// 设备类型 :2:BQ100;1:BQ200;4:BQ300
        dealLen = dealLen + 6;
        BQData bq = null;
        DatEsDetectorL1Real firstdata = (DatEsDetectorL1Real) dataMap.get("firstdata");
        DatEsDetectorL2Real seconddata = (DatEsDetectorL2Real) dataMap.get("seconddata");
        DatEsDetectorEvent eventdata = null;
        List<DatEsDetectorEvent> eventList = null;
        List<DatEsDetectorEvent> faultList = null;
        if (msg[dealLen] == 0x05) {// 05表示设备向服务器上传数据
            logger.info("2---进入05：设备向服务器上次数据");
            dealLen++;
            while (recvLen - dealLen > 8) {
                if (msg[dealLen] != 0x55) {// 数据起始标志
                    dealLen++;
                    continue;
                }
                logger.info("3---找到0x55：每条数据起始码");
                bq = BQFactory.createBMW(deviceType, msg, dealLen);
                if (bq.datatype == 6 || bq.datatype == 7 || bq.datatype == 8 || bq.datatype == 9) {// 6789为一级数据
                    if (eventList == null || eventList.size() == 0) {
                        if (firstdata == null) {
                            firstdata = new DatEsDetectorL1Real();
                            firstdata.setDetid(devService.getDevice().getId());
                            firstdata.setDatatime(new Date());
                            dataMap.put("firstdata", firstdata);
                        }
                        firstdata.setDatatime(new Date());
                        switch (bq.datatype) {
                            case 6:// 电压有效值
                                bq.dealUValue(firstdata);
                                break;
                            case 7:// 电流有效值
                                bq.dealIValue(firstdata);
                                break;
                            case 8:// 漏电流有效值
                                bq.dealILValue(firstdata);
                                break;
                            case 9:// 温度有效值
                                bq.dealTValue(firstdata);
                                break;
                        }
                        logger.info("4---收到一级数据" + bq.datatype);
                    }
                }
                if (bq.datatype == 10 || bq.datatype == 12 || bq.datatype == 13 || bq.datatype == 14 || bq.datatype == 15 || bq.datatype == 24 || bq.datatype == 25
                    || bq.datatype == 26) {// 二级数据
                    if (seconddata == null) {
                        seconddata = new DatEsDetectorL2Real();
                        seconddata.setDetid(devService.getDevice().getId());
                        seconddata.setDatatime(new Date());
                        dataMap.put("seconddata", seconddata);
                    }
                    seconddata.setDatatime(new Date());
                    switch (bq.datatype) {
                        case 10:// 电压频率
                            bq.dealUFreq(seconddata);
                            break;
                        case 12:// 有功功率
                            bq.dealPPower(seconddata);
                            break;
                        case 13:// 无功功率
                            bq.dealQPower(seconddata);
                            break;
                        case 14:// 视在功率
                            bq.dealAPPower(seconddata);
                            break;
                        case 15:// 功率因素
                            bq.dealPFValue(seconddata);
                            break;
                        case 24:// 有功电度
                            bq.dealEnergyP(seconddata);
                            break;
                        case 25:// 无功电度
                            bq.dealEnergyQ(seconddata);
                            break;
                        case 26:// 视在电度
                            bq.dealEnergyAP(seconddata);
                            break;
                    }
                    logger.info("4---收到二级数据" + bq.datatype);
                }
                if (bq.datatype == 31 || bq.datatype == 32 || bq.datatype == 33 || bq.datatype == 34 || bq.datatype == 35) {// 31-35为报警数据
                    eventdata = new DatEsDetectorEvent();
                    eventdata.setDetid(devService.getDevice().getId());
                    eventdata.setDatatime(new Date());
                    eventdata.setEvtdesc("");
                    switch (bq.datatype) {
                        case 31:// 电压报警值
                            bq.dealUFaultValue(eventdata);
                            for (int i = 0; i < eventList.size(); i++) {
                                if (eventList.get(i).getEvttype() == 3 || eventList.get(i).getEvttype() == 4) {
                                    eventList.get(i).setEvtdesc(eventdata.getEvtdesc());
                                    break;
                                }
                            }
                            break;
                        case 32:// 电流报警值
                            bq.dealIFaultValue(eventdata);
                            for (int i = 0; i < eventList.size(); i++) {
                                if (eventList.get(i).getEvttype() == 5) {
                                    eventList.get(i).setEvtdesc(eventdata.getEvtdesc());
                                    break;
                                }
                            }
                            break;
                        case 33:// 漏电流报警值
                            bq.dealILFaultValue(eventdata);
                            for (int i = 0; i < eventList.size(); i++) {
                                if (eventList.get(i).getEvttype() == 1) {
                                    eventList.get(i).setEvtdesc(eventdata.getEvtdesc());
                                    break;
                                }
                            }
                            break;
                        case 34:// 温度报警值
                            bq.dealTFaultValue(eventdata);
                            for (int i = 0; i < eventList.size(); i++) {
                                if (eventList.get(i).getEvttype() == 2) {
                                    eventList.get(i).setEvtdesc(eventdata.getEvtdesc());
                                    break;
                                }
                            }
                            break;
                        case 35:// 报警标志位
                            eventList = bq.dealFaultTypeValue(devService.getDevice().getId());
                            detectorStatus.setDetid(devService.getDevice().getId());
                            detectorStatus.setDatatime(new Date());
                            // 设备状态
                            if (detectorStatus.getIsalarm() == null) {// 第一次报警
                                flag = true;
                                detectorStatus.setIsalarm((short) (eventList.get(0).getEvttype() == 0 ? 0 : 1));
                            } else if (detectorStatus.getIsalarm() == 0 && eventList.get(0).getEvttype() != 0) {// 从正常状态变成报警
                                flag = true;
                                detectorStatus.setIsalarm((short) 1);
                            } else if (detectorStatus.getIsalarm() == 1 && eventList.get(0).getEvttype() == 0) {// 从报警状态变为正常
                                flag = true;
                                detectorStatus.setIsalarm((short) 0);
                            }
                            // 将detectorStatus存入缓存
                            // 写successful给终端
                            writeSuccessful(channel);
                            break;
                    }
                    logger.info("4---收到报警数据" + bq.datatype);
                }
                if (bq.datatype == 43 || bq.datatype == 44 || bq.datatype == 45 || bq.datatype == 46 || bq.datatype == 47) {// 43-47为设置数据,仅当设置参数发生变化时上传
                    logger.info("4---收到设置数据" + bq.datatype);
                    writeSuccessful(channel);
                }
                if (bq.datatype == 36) {// 36为故障数据
                    faultList = bq.dealBreakTypeValue(devService.getDevice().getId());
                    logger.info("4---收到故障数据" + bq.datatype);
                    detectorStatus.setDetid(devService.getDevice().getId());
                    detectorStatus.setDatatime(new Date());
                    if (detectorStatus.getIsfault() == null) {
                        flag = true;
                        detectorStatus.setIsfault((short) (faultList.get(0).getEvttype() == 0 ? 0 : 1));
                    } else if (detectorStatus.getIsfault() == 0 && faultList.get(0).getEvttype() != 0) {
                        flag = true;
                        detectorStatus.setIsfault((short) 1);
                    } else if (detectorStatus.getIsfault() == 1 && faultList.get(0).getEvttype() == 0) {
                        flag = true;
                        detectorStatus.setIsfault((short) 0);
                    }
                    // 写successful给终端
                    writeSuccessful(channel);
                }
                dealLen = dealLen + bq.length;
            }
            if (eventList != null && eventList.size() > 0) {// 报警
                if (eventList.get(0).getEvttype() == 0) {
                    eventMap = new HashMap<Short, Short>();
                } else {
                    for (int i = 0; i < eventList.size(); i++) {
                        Short eve = eventMap.get(eventList.get(i).getEvttype());
                        if (eve == null) {
                            eve = eventList.get(i).getEvttype();
                            eventMap.put(eve, eve);
                            RedisUtil.lpush(CacheKeyProvide.KEY_ES_DCS_DAT_ES_DETECTOR_EVENT.getBytes(), ProtostuffUtil.serialize(eventList.get(i)));
                        }
                    }
                }
            }
            if (faultList != null && faultList.size() > 0) {// 故障
                if (faultList.get(0).getEvttype() == 0) {
                    faultMap = new HashMap<Short, Short>();
                } else {
                    for (int i = 0; i < faultList.size(); i++) {
                        Short eve = faultMap.get(faultList.get(i).getEvttype());
                        if (eve == null) {
                            eve = faultList.get(i).getEvttype();
                            faultMap.put(eve, eve);
                            RedisUtil.lpush(CacheKeyProvide.KEY_ES_DCS_DAT_ES_DETECTOR_EVENT.getBytes(), ProtostuffUtil.serialize(faultList.get(i)));
                            logger.info("设备 故障 数据存入缓存:");
                        }
                    }
                }
            }
            if (flag) {
                RedisUtil.lpush(CacheKeyProvide.KEY_ES_DCS_DAT_ES_DETECTOR_CURSTATUS.getBytes(), ProtostuffUtil.serialize(detectorStatus));
                logger.info("设备报警及故障状态存入缓存:" + detectorStatus.getDatatime());
            }
            dealLen = dealLen + 2;// crc校验 2字节
        }
        return dealLen;
    }

    @Override
    public int onLogin(byte[] data, IDeviceService devService, DCSChannel channel) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int sendHeart(IDeviceService devService, DCSChannel channel) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int sendBizData(int cmdType, Object data, Integer deviceID, IDeviceService devService, DCSChannel channel) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void onTimer(IDeviceService devService, DCSChannel channel) {
        DatEsDetectorL1Real firstdata = (DatEsDetectorL1Real) dataMap.get("firstdata");
        DatEsDetectorL2Real seconddata = (DatEsDetectorL2Real) dataMap.get("seconddata");
        if (firstdata != null && new Date().getTime() - firstdata.getDatatime().getTime() >= 15000) {// 若15s内没收到数据则说明同级数据传送完毕
            // 1.存入缓存
            logger.info("设备一级数据存入缓存:");
            RedisUtil.lpush(CacheKeyProvide.KEY_ES_DCS_DAT_ES_DETECTOR_L1_REAL.getBytes(), ProtostuffUtil.serialize(firstdata));
            // 2.清除map
            dataMap.remove("firstdata");
        }
        if (seconddata != null && new Date().getTime() - seconddata.getDatatime().getTime() >= 15000) {// 若15s内没收到数据则说明同级数据传送完毕
            // 1.存入缓存
            logger.info("设备二级数据存入缓存:");
            RedisUtil.lpush(CacheKeyProvide.KEY_ES_DCS_DAT_ES_DETECTOR_L2_REAL.getBytes(), ProtostuffUtil.serialize(seconddata));
            // 2.清除map
            dataMap.remove("seconddata");
        }
        if (lastrectime != null && new Date().getTime() - lastrectime.getTime() >= 1000 * 60 * 15) {// 15分钟视为掉线
            DatEsDetectorCurstatus offdetectorStatus = new DatEsDetectorCurstatus();
            offdetectorStatus.setDatatime(new Date());
            offdetectorStatus.setIsoffline((short) 0);
            // 存缓存
            logger.info("设备离线状态存入缓存:");
            RedisUtil.lpush(CacheKeyProvide.KEY_ES_DCS_DAT_ES_DETECTOR_CURSTATUS.getBytes(), ProtostuffUtil.serialize(offdetectorStatus));
            lastrectime = null;// 离线只计算一次
        }
    }

    private void writeSuccessful(DCSChannel channel) {
        byte[] data = "successful".getBytes();
        channel.writeData(data);
    }

}

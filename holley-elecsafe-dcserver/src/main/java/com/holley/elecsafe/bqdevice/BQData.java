package com.holley.elecsafe.bqdevice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.holley.elecsafe.model.dat.DatEsDetectorEvent;

public abstract class BQData implements IBQInterface {

    public byte  startbyte;
    public short datatype;
    public short datadefinedtype;
    public short datarate;
    public short datalength;
    public int[] datavalue;
    public short length;

    public int[] getDatavalue() {
        return datavalue;
    }

    public void setDatavalue(int[] datavalue) {
        this.datavalue = datavalue;
    }

    public byte getStartbyte() {
        return startbyte;
    }

    public void setStartbyte(byte startbyte) {
        this.startbyte = startbyte;
    }

    public short getDatatype() {
        return datatype;
    }

    public void setDatatype(short datatype) {
        this.datatype = datatype;
    }

    public short getDatadefinedtype() {
        return datadefinedtype;
    }

    public void setDatadefinedtype(short datadefinedtype) {
        this.datadefinedtype = datadefinedtype;
    }

    public short getDatarate() {
        return datarate;
    }

    public void setDatarate(short datarate) {
        this.datarate = datarate;
    }

    public short getDatalength() {
        return datalength;
    }

    public void setDatalength(short datalength) {
        this.datalength = datalength;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }

    @Override
    public List<DatEsDetectorEvent> dealFaultTypeValue(Integer id) {
        List<DatEsDetectorEvent> eventList = new ArrayList<DatEsDetectorEvent>();
        for (int i = 0; i < datalength; i++) {
            DatEsDetectorEvent event = new DatEsDetectorEvent();
            event.setDetid(id);
            event.setDatatime(new Date());
            event.setDealstatus((short) 1);
            event.setEvttype((short) (datavalue[i] / datarate));// 报警标志位
            eventList.add(event);
        }
        return eventList;
    }

    @Override
    public List<DatEsDetectorEvent> dealBreakTypeValue(Integer id) {
        List<DatEsDetectorEvent> eventList = new ArrayList<DatEsDetectorEvent>();
        for (int i = 0; i < datalength; i++) {
            DatEsDetectorEvent event = new DatEsDetectorEvent();
            event.setDetid(id);
            event.setDatatime(new Date());
            event.setDealstatus((short) 1);
            event.setEvttype((short) (datavalue[i] / datarate));// 故障标志位
            switch (event.getEvttype()) {
                case 1:
                    event.setEvtdesc("漏电流故障");
                    event.setEvttype((short) 101);
                    break;
                case 2:
                    event.setEvtdesc("温度故障");
                    event.setEvttype((short) 102);
                    break;
                case 3:
                    event.setEvtdesc("供电中断");
                    event.setEvttype((short) 103);
                    break;
                case 4:
                    event.setEvtdesc("错相");
                    event.setEvttype((short) 104);
                    break;
                case 5:
                    event.setEvtdesc("缺相");
                    event.setEvttype((short) 105);
                    break;
                case 6:
                    event.setEvtdesc("电弧故障");
                    event.setEvttype((short) 106);
                    break;
                case 7:
                    event.setEvtdesc("负载故障");
                    event.setEvttype((short) 107);
                    break;
                case 8:
                    event.setEvtdesc("短路故障");
                    event.setEvttype((short) 108);
                    break;
                case 9:
                    event.setEvtdesc("断路故障");
                    event.setEvttype((short) 109);
                    break;
            }
            eventList.add(event);
        }
        return eventList;
    }

}

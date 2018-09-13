package com.holley.elecsafe.bqdevice;

import java.math.BigDecimal;

import com.holley.elecsafe.model.dat.DatEsDetectorEvent;
import com.holley.elecsafe.model.dat.DatEsDetectorL1Real;
import com.holley.elecsafe.model.dat.DatEsDetectorL2Real;
import com.holley.platform.common.util.ProtocolUtils;

public class BQ300 extends BQData {

    public BQ300() {

    }

    public BQ300(byte[] buf, int from) {
        this.startbyte = buf[from];// 0x55
        this.datatype = buf[from + 1];
        this.datadefinedtype = buf[from + 2];
        this.datarate = ProtocolUtils.byteToShort(buf[from + 4], buf[from + 3]);
        this.datalength = buf[from + 5];
        this.datavalue = new int[10];
        this.length = (short) (6 + 2 * datalength);
        for (int i = 0; i < datalength; i++) {
            datavalue[i] = ProtocolUtils.byteTolongShort(buf[from + 5 + (i * 2 + 2)], buf[from + 5 + (i * 2 + 1)]);
        }
    }

    @Override
    public void dealUValue(DatEsDetectorL1Real data) {
        data.setU(new BigDecimal((double) datavalue[0] / (double) datarate));
    }

    @Override
    public void dealIValue(DatEsDetectorL1Real data) {
        data.setU(new BigDecimal((double) datavalue[0] / (double) datarate));
    }

    @Override
    public void dealILValue(DatEsDetectorL1Real data) {
        data.setU(new BigDecimal((double) datavalue[0] / (double) datarate));
    }

    @Override
    public void dealTValue(DatEsDetectorL1Real data) {
        data.setTl(new BigDecimal((double) datavalue[0] / (double) datarate));
        data.setTn(new BigDecimal((double) datavalue[1] / (double) datarate));
        data.setTi(new BigDecimal((double) datavalue[2] / (double) datarate));
    }

    @Override
    public void dealUFreq(DatEsDetectorL2Real data) {
        data.setF(new BigDecimal((double) datavalue[0] / (double) datarate));
    }

    @Override
    public void dealPPower(DatEsDetectorL2Real data) {
        data.setP(new BigDecimal((double) datavalue[0] / (double) datarate));
    }

    @Override
    public void dealQPower(DatEsDetectorL2Real data) {
        data.setQ(new BigDecimal((double) datavalue[0] / (double) datarate));
    }

    @Override
    public void dealAPPower(DatEsDetectorL2Real data) {
        data.setAp(new BigDecimal((double) datavalue[0] / (double) datarate));
    }

    @Override
    public void dealPFValue(DatEsDetectorL2Real data) {
        data.setPf(new BigDecimal((double) datavalue[0] / (double) datarate));
    }

    @Override
    public void dealEnergyP(DatEsDetectorL2Real data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dealEnergyQ(DatEsDetectorL2Real data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dealEnergyAP(DatEsDetectorL2Real data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dealUFaultValue(DatEsDetectorEvent data) {
        data.setEvtdesc("电压报警值：" + ((double) datavalue[0] / (double) datarate));
    }

    @Override
    public void dealIFaultValue(DatEsDetectorEvent data) {
        data.setEvtdesc("电流报警值：" + ((double) datavalue[0] / (double) datarate));
    }

    @Override
    public void dealILFaultValue(DatEsDetectorEvent data) {
        data.setEvtdesc("漏电流报警值：" + ((double) datavalue[0] / (double) datarate));
    }

    @Override
    public void dealTFaultValue(DatEsDetectorEvent data) {
        data.setEvtdesc("L(火线)温度报警值：" + ((double) datavalue[0] / (double) datarate));
        data.setEvtdesc(data.getEvtdesc() + ";N(零线)温度报警值：" + ((double) datavalue[1] / (double) datarate));
        data.setEvtdesc(data.getEvtdesc() + ";内部温度温度报警值：" + ((double) datavalue[2] / (double) datarate));
    }

}

package com.holley.elecsafe.bqdevice;

import java.math.BigDecimal;

import com.holley.elecsafe.model.dat.DatEsDetectorEvent;
import com.holley.elecsafe.model.dat.DatEsDetectorL1Real;
import com.holley.elecsafe.model.dat.DatEsDetectorL2Real;
import com.holley.platform.common.util.ProtocolUtils;

public class BQ100 extends BQData {

    public BQ100() {

    }

    public BQ100(byte[] buf, int from) {
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
        data.setUa(new BigDecimal((double) datavalue[0] / (double) datarate));
        data.setUb(new BigDecimal((double) datavalue[1] / (double) datarate));
        data.setUc(new BigDecimal((double) datavalue[2] / (double) datarate));
    }

    @Override
    public void dealIValue(DatEsDetectorL1Real data) {
        data.setIa(new BigDecimal((double) datavalue[0] / (double) datarate));
        data.setIb(new BigDecimal((double) datavalue[1] / (double) datarate));
        data.setIc(new BigDecimal((double) datavalue[2] / (double) datarate));
        data.setInx(new BigDecimal((double) datavalue[3] / (double) datarate));
    }

    @Override
    public void dealILValue(DatEsDetectorL1Real data) {
        data.setIl(new BigDecimal((double) datavalue[0] / (double) datarate));
    }

    @Override
    public void dealTValue(DatEsDetectorL1Real data) {
        data.setTa(new BigDecimal((double) datavalue[0] / (double) datarate));
        data.setTb(new BigDecimal((double) datavalue[1] / (double) datarate));
        data.setTc(new BigDecimal((double) datavalue[2] / (double) datarate));
        data.setTn(new BigDecimal((double) datavalue[3] / (double) datarate));
    }

    @Override
    public void dealUFreq(DatEsDetectorL2Real data) {
        data.setFa(new BigDecimal((double) datavalue[0] / (double) datarate));
        data.setFb(new BigDecimal((double) datavalue[1] / (double) datarate));
        data.setFc(new BigDecimal((double) datavalue[2] / (double) datarate));
    }

    @Override
    public void dealPPower(DatEsDetectorL2Real data) {
        data.setPa(new BigDecimal((double) datavalue[0] / (double) datarate));
        data.setPb(new BigDecimal((double) datavalue[1] / (double) datarate));
        data.setPc(new BigDecimal((double) datavalue[2] / (double) datarate));
    }

    @Override
    public void dealQPower(DatEsDetectorL2Real data) {
        data.setQa(new BigDecimal((double) datavalue[0] / (double) datarate));
        data.setQb(new BigDecimal((double) datavalue[1] / (double) datarate));
        data.setQc(new BigDecimal((double) datavalue[2] / (double) datarate));
    }

    @Override
    public void dealAPPower(DatEsDetectorL2Real data) {
        data.setApa(new BigDecimal((double) datavalue[0] / (double) datarate));
        data.setApb(new BigDecimal((double) datavalue[1] / (double) datarate));
        data.setApc(new BigDecimal((double) datavalue[2] / (double) datarate));
    }

    @Override
    public void dealPFValue(DatEsDetectorL2Real data) {
        data.setPfa(new BigDecimal((double) datavalue[0] / (double) datarate));
        data.setPfb(new BigDecimal((double) datavalue[1] / (double) datarate));
        data.setPfc(new BigDecimal((double) datavalue[2] / (double) datarate));
    }

    @Override
    public void dealEnergyP(DatEsDetectorL2Real data) {
        data.setEnergypa(new BigDecimal((double) datavalue[0] / (double) datarate));
        data.setEnergypb(new BigDecimal((double) datavalue[1] / (double) datarate));
        data.setEnergypc(new BigDecimal((double) datavalue[2] / (double) datarate));
    }

    @Override
    public void dealEnergyQ(DatEsDetectorL2Real data) {
        data.setEnergyqa(new BigDecimal((double) datavalue[0] / (double) datarate));
        data.setEnergyqb(new BigDecimal((double) datavalue[1] / (double) datarate));
        data.setEnergyqc(new BigDecimal((double) datavalue[2] / (double) datarate));
    }

    @Override
    public void dealEnergyAP(DatEsDetectorL2Real data) {
        data.setEnergyapa(new BigDecimal((double) datavalue[0] / (double) datarate));
        data.setEnergyapb(new BigDecimal((double) datavalue[1] / (double) datarate));
        data.setEnergyapc(new BigDecimal((double) datavalue[2] / (double) datarate));
    }

    @Override
    public void dealUFaultValue(DatEsDetectorEvent data) {
        data.setEvtdesc("A相电压报警值：" + ((double) datavalue[0] / (double) datarate));
        data.setEvtdesc(data.getEvtdesc() + ";B相电压报警值：" + ((double) datavalue[1] / (double) datarate));
        data.setEvtdesc(data.getEvtdesc() + ";C相电压报警值：" + ((double) datavalue[2] / (double) datarate));
    }

    @Override
    public void dealIFaultValue(DatEsDetectorEvent data) {
        data.setEvtdesc("A相电流报警值：" + ((double) datavalue[0] / (double) datarate));
        data.setEvtdesc(data.getEvtdesc() + ";B相电流报警值：" + ((double) datavalue[1] / (double) datarate));
        data.setEvtdesc(data.getEvtdesc() + ";C相电流报警值：" + ((double) datavalue[2] / (double) datarate));
        data.setEvtdesc(data.getEvtdesc() + ";N相电流报警值：" + ((double) datavalue[3] / (double) datarate));
    }

    @Override
    public void dealILFaultValue(DatEsDetectorEvent data) {
        data.setEvtdesc("漏电电流报警值：" + ((double) datavalue[0] / (double) datarate));
    }

    @Override
    public void dealTFaultValue(DatEsDetectorEvent data) {
        data.setEvtdesc(" A相温度报警值：" + ((double) datavalue[0] / (double) datarate));
        data.setEvtdesc(data.getEvtdesc() + ";B相温度报警值：" + ((double) datavalue[1] / (double) datarate));
        data.setEvtdesc(data.getEvtdesc() + ";C相温度报警值：" + ((double) datavalue[2] / (double) datarate));
        data.setEvtdesc(data.getEvtdesc() + ";N相温度报警值：" + ((double) datavalue[3] / (double) datarate));
    }

}

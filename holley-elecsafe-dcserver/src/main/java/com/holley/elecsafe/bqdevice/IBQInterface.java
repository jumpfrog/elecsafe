package com.holley.elecsafe.bqdevice;

import java.util.List;

import com.holley.elecsafe.model.dat.DatEsDetectorEvent;
import com.holley.elecsafe.model.dat.DatEsDetectorL1Real;
import com.holley.elecsafe.model.dat.DatEsDetectorL2Real;

public interface IBQInterface {

    /**
     * @Title: dealUValue @Description: TODO 一级数据 @param @param data 设定文件 @return void 返回类型 @throws
     */
    public void dealUValue(DatEsDetectorL1Real data);// 电压有效值

    public void dealIValue(DatEsDetectorL1Real data);// 电流有效值

    public void dealILValue(DatEsDetectorL1Real data);// 漏电流有效值

    public void dealTValue(DatEsDetectorL1Real data);// 温度有效值

    /**
     * @Title: dealUFreq @Description: TODO 二级数据 @param @param data 设定文件 @return void 返回类型 @throws
     */
    public void dealUFreq(DatEsDetectorL2Real data);// 电压频率

    public void dealPPower(DatEsDetectorL2Real data);// 有功功率

    public void dealQPower(DatEsDetectorL2Real data);// 无功功率

    public void dealAPPower(DatEsDetectorL2Real data);// 视在功率

    public void dealPFValue(DatEsDetectorL2Real data);// 功率因素

    public void dealEnergyP(DatEsDetectorL2Real data);// 有功电度

    public void dealEnergyQ(DatEsDetectorL2Real data);// 无功电度

    public void dealEnergyAP(DatEsDetectorL2Real data);// 视在电度

    /**
     * @Title: dealEnergyAP @Description: TODO(报警数据) @param @param data 设定文件 @return void 返回类型 @throws
     */
    public void dealUFaultValue(DatEsDetectorEvent data);// 电压报警值

    public void dealIFaultValue(DatEsDetectorEvent data);// 电流报警值

    public void dealILFaultValue(DatEsDetectorEvent data);// 漏电流报警值

    public void dealTFaultValue(DatEsDetectorEvent data);// 温度报警值

    public List<DatEsDetectorEvent> dealFaultTypeValue(Integer id);// 报警标志位

    /**
     * @Title: dealBreakTypeValue @Description: TODO(故障数据) @param @param data 设定文件 @return void 返回类型 @throws
     */
    public List<DatEsDetectorEvent> dealBreakTypeValue(Integer id);// 故障标志位

}

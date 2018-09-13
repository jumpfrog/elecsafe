package com.holley.elecsafe.dao.dat;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.holley.elecsafe.model.dat.DatEsDetectorEventDeal;
import com.holley.elecsafe.model.dat.DatEsDetectorEventDealExample;
import com.holley.elecsafe.model.dat.DatEsDetectorEventDealKey;
import com.holley.elecsafe.model.def.DetEventDeal;

public interface DatEsDetectorEventDealMapper {

    int countByExample(DatEsDetectorEventDealExample example);

    int deleteByExample(DatEsDetectorEventDealExample example);

    int deleteByPrimaryKey(DatEsDetectorEventDealKey key);

    int insert(DatEsDetectorEventDeal record);

    int insertSelective(DatEsDetectorEventDeal record);

    List<DatEsDetectorEventDeal> selectByExample(DatEsDetectorEventDealExample example);

    DatEsDetectorEventDeal selectByPrimaryKey(DatEsDetectorEventDealKey key);

    int updateByExampleSelective(@Param("record") DatEsDetectorEventDeal record, @Param("example") DatEsDetectorEventDealExample example);

    int updateByExample(@Param("record") DatEsDetectorEventDeal record, @Param("example") DatEsDetectorEventDealExample example);

    int updateByPrimaryKeySelective(DatEsDetectorEventDeal record);

    int updateByPrimaryKey(DatEsDetectorEventDeal record);

    List<DetEventDeal> selectDetEventDealLog(Integer evtid);

    int insertDetectorEventDealBatch(List<DatEsDetectorEventDeal> list);

    /**
     * 统计现场服务次数
     * 
     * @param map
     * @return
     */
    int countService(Map<String, Object> param);
}

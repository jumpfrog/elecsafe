package com.holley.elecsafe.dao.dat;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.holley.elecsafe.model.dat.DatEsDetectorEvent;
import com.holley.elecsafe.model.dat.DatEsDetectorEventExample;
import com.holley.elecsafe.model.def.DetectorEvent;
import com.holley.elecsafe.model.def.HomePageDataVo;

public interface DatEsDetectorEventMapper {

    int countByExample(DatEsDetectorEventExample example);

    int deleteByExample(DatEsDetectorEventExample example);

    int deleteByPrimaryKey(Integer evtid);

    int insert(DatEsDetectorEvent record);

    int insertSelective(DatEsDetectorEvent record);

    List<DatEsDetectorEvent> selectByExample(DatEsDetectorEventExample example);

    DatEsDetectorEvent selectByPrimaryKey(Integer evtid);

    int updateByExampleSelective(@Param("record") DatEsDetectorEvent record, @Param("example") DatEsDetectorEventExample example);

    int updateByExample(@Param("record") DatEsDetectorEvent record, @Param("example") DatEsDetectorEventExample example);

    int updateByPrimaryKeySelective(DatEsDetectorEvent record);

    int updateByPrimaryKey(DatEsDetectorEvent record);

    List<DetectorEvent> selectDetectorEventByPage(Map<String, Object> param);

    int updateDetectorEventDealStatusBatch(List<DatEsDetectorEvent> list);

    List<HomePageDataVo> countHomePageData(Map<String, Object> param);

    DetectorEvent selectNewEventByMap(Map<String, Object> param);

    List<DetectorEvent> selectDetEventForStat(Map<String, Object> param);
}

package com.holley.elecsafe.dao.dat;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.holley.elecsafe.model.dat.DatEsDetectorL1His;
import com.holley.elecsafe.model.dat.DatEsDetectorL1HisExample;
import com.holley.elecsafe.model.dat.DatEsDetectorL1HisKey;
import com.holley.elecsafe.model.def.DatEsDetectorL1HisVo;

public interface DatEsDetectorL1HisMapper {

    int countByExample(DatEsDetectorL1HisExample example);

    int deleteByExample(DatEsDetectorL1HisExample example);

    int deleteByPrimaryKey(DatEsDetectorL1HisKey key);

    int insert(DatEsDetectorL1His record);

    int insertSelective(DatEsDetectorL1His record);

    List<DatEsDetectorL1His> selectByExample(DatEsDetectorL1HisExample example);

    DatEsDetectorL1His selectByPrimaryKey(DatEsDetectorL1HisKey key);

    int updateByExampleSelective(@Param("record") DatEsDetectorL1His record, @Param("example") DatEsDetectorL1HisExample example);

    int updateByExample(@Param("record") DatEsDetectorL1His record, @Param("example") DatEsDetectorL1HisExample example);

    int updateByPrimaryKeySelective(DatEsDetectorL1His record);

    int updateByPrimaryKey(DatEsDetectorL1His record);

    /**
     * 查询指定日期的历史数据
     * 
     * @param map
     * @return
     */
    List<DatEsDetectorL1HisVo> selectDetByIdAndDate(Map<String, Object> map);

    List<DatEsDetectorL1HisVo> selectDatEsDetectorL1HisByPage(Map<String, Object> map);
}

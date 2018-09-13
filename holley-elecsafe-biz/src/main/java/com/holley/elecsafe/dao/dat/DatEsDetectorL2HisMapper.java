package com.holley.elecsafe.dao.dat;

import com.holley.elecsafe.model.dat.DatEsDetectorL2His;
import com.holley.elecsafe.model.dat.DatEsDetectorL2HisExample;
import com.holley.elecsafe.model.dat.DatEsDetectorL2HisKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DatEsDetectorL2HisMapper {
    int countByExample(DatEsDetectorL2HisExample example);

    int deleteByExample(DatEsDetectorL2HisExample example);

    int deleteByPrimaryKey(DatEsDetectorL2HisKey key);

    int insert(DatEsDetectorL2His record);

    int insertSelective(DatEsDetectorL2His record);

    List<DatEsDetectorL2His> selectByExample(DatEsDetectorL2HisExample example);

    DatEsDetectorL2His selectByPrimaryKey(DatEsDetectorL2HisKey key);

    int updateByExampleSelective(@Param("record") DatEsDetectorL2His record, @Param("example") DatEsDetectorL2HisExample example);

    int updateByExample(@Param("record") DatEsDetectorL2His record, @Param("example") DatEsDetectorL2HisExample example);

    int updateByPrimaryKeySelective(DatEsDetectorL2His record);

    int updateByPrimaryKey(DatEsDetectorL2His record);
}
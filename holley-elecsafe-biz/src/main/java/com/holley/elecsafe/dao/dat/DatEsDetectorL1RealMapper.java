package com.holley.elecsafe.dao.dat;

import com.holley.elecsafe.model.dat.DatEsDetectorL1Real;
import com.holley.elecsafe.model.dat.DatEsDetectorL1RealExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DatEsDetectorL1RealMapper {
    int countByExample(DatEsDetectorL1RealExample example);

    int deleteByExample(DatEsDetectorL1RealExample example);

    int deleteByPrimaryKey(Integer detid);

    int insert(DatEsDetectorL1Real record);

    int insertSelective(DatEsDetectorL1Real record);

    List<DatEsDetectorL1Real> selectByExample(DatEsDetectorL1RealExample example);

    DatEsDetectorL1Real selectByPrimaryKey(Integer detid);

    int updateByExampleSelective(@Param("record") DatEsDetectorL1Real record, @Param("example") DatEsDetectorL1RealExample example);

    int updateByExample(@Param("record") DatEsDetectorL1Real record, @Param("example") DatEsDetectorL1RealExample example);

    int updateByPrimaryKeySelective(DatEsDetectorL1Real record);

    int updateByPrimaryKey(DatEsDetectorL1Real record);
}
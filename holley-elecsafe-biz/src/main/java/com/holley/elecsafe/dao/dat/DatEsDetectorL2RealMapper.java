package com.holley.elecsafe.dao.dat;

import com.holley.elecsafe.model.dat.DatEsDetectorL2Real;
import com.holley.elecsafe.model.dat.DatEsDetectorL2RealExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DatEsDetectorL2RealMapper {
    int countByExample(DatEsDetectorL2RealExample example);

    int deleteByExample(DatEsDetectorL2RealExample example);

    int deleteByPrimaryKey(Integer detid);

    int insert(DatEsDetectorL2Real record);

    int insertSelective(DatEsDetectorL2Real record);

    List<DatEsDetectorL2Real> selectByExample(DatEsDetectorL2RealExample example);

    DatEsDetectorL2Real selectByPrimaryKey(Integer detid);

    int updateByExampleSelective(@Param("record") DatEsDetectorL2Real record, @Param("example") DatEsDetectorL2RealExample example);

    int updateByExample(@Param("record") DatEsDetectorL2Real record, @Param("example") DatEsDetectorL2RealExample example);

    int updateByPrimaryKeySelective(DatEsDetectorL2Real record);

    int updateByPrimaryKey(DatEsDetectorL2Real record);
}
package com.holley.elecsafe.dao.dat;

import com.holley.elecsafe.model.dat.DatEsDetectorCurstatus;
import com.holley.elecsafe.model.dat.DatEsDetectorCurstatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DatEsDetectorCurstatusMapper {
    int countByExample(DatEsDetectorCurstatusExample example);

    int deleteByExample(DatEsDetectorCurstatusExample example);

    int deleteByPrimaryKey(Integer detid);

    int insert(DatEsDetectorCurstatus record);

    int insertSelective(DatEsDetectorCurstatus record);

    List<DatEsDetectorCurstatus> selectByExample(DatEsDetectorCurstatusExample example);

    DatEsDetectorCurstatus selectByPrimaryKey(Integer detid);

    int updateByExampleSelective(@Param("record") DatEsDetectorCurstatus record, @Param("example") DatEsDetectorCurstatusExample example);

    int updateByExample(@Param("record") DatEsDetectorCurstatus record, @Param("example") DatEsDetectorCurstatusExample example);

    int updateByPrimaryKeySelective(DatEsDetectorCurstatus record);

    int updateByPrimaryKey(DatEsDetectorCurstatus record);
}
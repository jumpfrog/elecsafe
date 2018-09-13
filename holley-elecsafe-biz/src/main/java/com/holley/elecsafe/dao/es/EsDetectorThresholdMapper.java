package com.holley.elecsafe.dao.es;

import com.holley.elecsafe.model.es.EsDetectorThreshold;
import com.holley.elecsafe.model.es.EsDetectorThresholdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsDetectorThresholdMapper {
    int countByExample(EsDetectorThresholdExample example);

    int deleteByExample(EsDetectorThresholdExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EsDetectorThreshold record);

    int insertSelective(EsDetectorThreshold record);

    List<EsDetectorThreshold> selectByExample(EsDetectorThresholdExample example);

    EsDetectorThreshold selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EsDetectorThreshold record, @Param("example") EsDetectorThresholdExample example);

    int updateByExample(@Param("record") EsDetectorThreshold record, @Param("example") EsDetectorThresholdExample example);

    int updateByPrimaryKeySelective(EsDetectorThreshold record);

    int updateByPrimaryKey(EsDetectorThreshold record);
}
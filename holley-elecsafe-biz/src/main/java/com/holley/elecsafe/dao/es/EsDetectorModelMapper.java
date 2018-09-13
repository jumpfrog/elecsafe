package com.holley.elecsafe.dao.es;

import com.holley.elecsafe.model.es.EsDetectorModel;
import com.holley.elecsafe.model.es.EsDetectorModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsDetectorModelMapper {
    int countByExample(EsDetectorModelExample example);

    int deleteByExample(EsDetectorModelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EsDetectorModel record);

    int insertSelective(EsDetectorModel record);

    List<EsDetectorModel> selectByExample(EsDetectorModelExample example);

    EsDetectorModel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EsDetectorModel record, @Param("example") EsDetectorModelExample example);

    int updateByExample(@Param("record") EsDetectorModel record, @Param("example") EsDetectorModelExample example);

    int updateByPrimaryKeySelective(EsDetectorModel record);

    int updateByPrimaryKey(EsDetectorModel record);
}
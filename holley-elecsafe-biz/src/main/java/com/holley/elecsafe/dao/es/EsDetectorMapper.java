package com.holley.elecsafe.dao.es;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.holley.elecsafe.model.def.EsDetectorVo;
import com.holley.elecsafe.model.def.RealDataDetVo;
import com.holley.elecsafe.model.def.RealDataEntVo;
import com.holley.elecsafe.model.es.EsDetector;
import com.holley.elecsafe.model.es.EsDetectorExample;

public interface EsDetectorMapper {

    int countByExample(EsDetectorExample example);

    int deleteByExample(EsDetectorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EsDetector record);

    int insertSelective(EsDetector record);

    List<EsDetector> selectByExample(EsDetectorExample example);

    EsDetector selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EsDetector record, @Param("example") EsDetectorExample example);

    int updateByExample(@Param("record") EsDetector record, @Param("example") EsDetectorExample example);

    int updateByPrimaryKeySelective(EsDetector record);

    int updateByPrimaryKey(EsDetector record);

    // ADD BY SC
    /**
     * 分页查询设备状态
     * 
     * @param map
     * @return
     */
    List<RealDataDetVo> selectDetStatusByPage(Map<String, Object> map);

    /**
     * 查询单一监测点状态
     * 
     * @param detid
     * @return
     */
    RealDataDetVo selectSingleDetStatus(Integer detid);

    /**
     * 查询单一设备信息
     * 
     * @param map
     * @return
     */
    List<EsDetectorVo> selectDetectorByPage(Map<String, Object> map);

    EsDetectorVo selectSingleDetector(Map<String, Object> map);

    /**
     * 统计首页数据
     * 
     * @param eid
     * @return
     */
    RealDataEntVo countHomePageDataByEid(Integer eid);

    List<EsDetectorVo> selectDetectorByEid(Map<String, Object> map);

}

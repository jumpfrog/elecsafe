package com.holley.elecsafe.service.obj;

import java.util.List;

import com.holley.elecsafe.model.def.Enterprise;
import com.holley.elecsafe.model.obj.ObjEnterprise;
import com.holley.elecsafe.model.obj.ObjEnterpriseExample;
import com.holley.platform.model.def.EntBaseInfo;
import com.holley.platform.model.def.LogInfo;

public interface EntDevService {

    // common--------------------------------

    // obj_enterprise--------------------------
    ObjEnterprise selectEnterpriseByPK(Integer eid);

    List<ObjEnterprise> selectEnterpriseByExample(ObjEnterpriseExample emp);

    int insertEnterprise(ObjEnterprise record, LogInfo logInfo);

    String deleteEnterprise(Integer eid);

    // 根据eid 查询子企业的所有信息 不包括自己
    List<Enterprise> selectChildEidSelf(Integer eid);

    // 根据eid，查询子企业，包括自己
    List<EntBaseInfo> selectGetChildEidSelf(Integer eid);

    // 根据eid，查询子企业，不包括自己
    List<EntBaseInfo> selectGetChildEid(Integer eid);

    int updateByPrimaryKeySelective(ObjEnterprise record, LogInfo logInfo);

    Enterprise selectSingleEntById(Integer eid);

    List<Enterprise> selectEntInfoByEid(Integer eid);

}

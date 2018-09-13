package com.holley.elecsafe.serviceimpl.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.holley.elecsafe.dao.obj.ObjEnterpriseMapper;
import com.holley.elecsafe.service.common.TreeCommService;
import com.holley.platform.model.def.EntBaseInfo;

public class TreeCommServiceImpl implements TreeCommService {

    @Autowired
    ObjEnterpriseMapper objEnterpriseMapper;

    @Override
    public List<EntBaseInfo> selectChildEntSelf(Integer eid) {
        return objEnterpriseMapper.selectChildEntSelf(eid);
    }

    @Override
    public List<EntBaseInfo> selectChildEnt(Integer eid) {
        return objEnterpriseMapper.selectChildEnt(eid);
    }

}

package com.holley.elecsafe.serviceimpl.common;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.holley.elecsafe.dao.obj.ObjEnterpriseMapper;
import com.holley.elecsafe.model.def.EntInfo;
import com.holley.elecsafe.service.common.SearchCommService;

public class SearchCommServiceImpl implements SearchCommService {

    @Autowired
    ObjEnterpriseMapper objEnterpriseMapper;

    @Override
    public List<EntInfo> searchEnterpriseByPage(Map<String, Object> param) {
        return objEnterpriseMapper.searchEnterpriseByPage(param);
    }

}

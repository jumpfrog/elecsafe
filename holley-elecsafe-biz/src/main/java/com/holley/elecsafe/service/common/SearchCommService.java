package com.holley.elecsafe.service.common;

import java.util.List;
import java.util.Map;

import com.holley.elecsafe.model.def.EntInfo;

public interface SearchCommService {

    List<EntInfo> searchEnterpriseByPage(Map<String, Object> param);

}

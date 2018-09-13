package com.holley.elecsafe.model.def;

import com.holley.elecsafe.common.constants.EnterpriseGradEnum;
import com.holley.elecsafe.model.obj.ObjEnterprise;

public class Enterprise extends ObjEnterprise {

    private String cityname;
    private String businessname;
    private String gradeStr;

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public String getGradeStr() {
        return gradeStr;
    }

    public void setGradeStr(String gradeStr) {
        this.gradeStr = gradeStr;
    }

    public String getGradeName() {
        Short grade = getGrade();
        return grade == null ? "" : EnterpriseGradEnum.getText(grade);
    }

}

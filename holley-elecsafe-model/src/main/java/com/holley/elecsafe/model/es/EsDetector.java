package com.holley.elecsafe.model.es;

import java.util.Date;

public class EsDetector {
    private Integer id;

    private String name;

    private Short type;

    private Short status;

    private Short modelid;

    private String commaddr;

    private Short protocolid;

    private Integer ownerid;

    private Short ownertype;

    private String installaddr;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getModelid() {
        return modelid;
    }

    public void setModelid(Short modelid) {
        this.modelid = modelid;
    }

    public String getCommaddr() {
        return commaddr;
    }

    public void setCommaddr(String commaddr) {
        this.commaddr = commaddr == null ? null : commaddr.trim();
    }

    public Short getProtocolid() {
        return protocolid;
    }

    public void setProtocolid(Short protocolid) {
        this.protocolid = protocolid;
    }

    public Integer getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(Integer ownerid) {
        this.ownerid = ownerid;
    }

    public Short getOwnertype() {
        return ownertype;
    }

    public void setOwnertype(Short ownertype) {
        this.ownertype = ownertype;
    }

    public String getInstalladdr() {
        return installaddr;
    }

    public void setInstalladdr(String installaddr) {
        this.installaddr = installaddr == null ? null : installaddr.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
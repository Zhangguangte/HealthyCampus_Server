package com.muyou.pojo;

import java.io.Serializable;

public class TbPart implements Serializable{
    private Integer id;

    private String partName;

    private String relatedOrgans;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName == null ? null : partName.trim();
    }

    public String getRelatedOrgans() {
        return relatedOrgans;
    }

    public void setRelatedOrgans(String relatedOrgans) {
        this.relatedOrgans = relatedOrgans == null ? null : relatedOrgans.trim();
    }
}
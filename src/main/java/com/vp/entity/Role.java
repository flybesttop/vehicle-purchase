package com.vp.entity;

import sun.security.provider.MD5;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Role implements Serializable {
    private Integer id;

    private String roleName;

    private Integer companyId;

    private String creatorId;

    private Integer level;

    private String describe;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public static Role createDefaultRole(String openId, Integer companyId) {
        Role defaultRole = new Role();
        defaultRole.setLevel(1);
        defaultRole.setRoleName("超级管理员");
        defaultRole.setDescribe(UUID.randomUUID().toString().replace("-",""));
        defaultRole.setCompanyId(companyId);
        defaultRole.setCreatorId(openId);
        return defaultRole;
    }
}
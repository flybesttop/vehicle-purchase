package com.vp.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户_企业组织关联
 * @author flybesttop
 * @date 2021-02-08
 */
@Data
public class UserCompany implements Serializable {
    private Integer id;

    private Integer companyId;

    private String openId;

    private Integer isDefault;

    private Integer roleId;

    private String roleName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
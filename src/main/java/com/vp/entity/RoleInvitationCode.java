package com.vp.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class RoleInvitationCode implements Serializable {
    private Integer id;

    private Integer roleId;

    private String code;

    private Integer effectiveTime;

    private String operatorId;

    private String operatorName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}
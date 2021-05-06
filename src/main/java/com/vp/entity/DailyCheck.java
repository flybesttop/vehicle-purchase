package com.vp.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DailyCheck implements Serializable {
    private Integer id;

    private LocalDate checkDate;

    private Integer companyId;

    private String operatorId;

    private String operatorName;

    private Integer status;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}
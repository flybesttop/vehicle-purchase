package com.vp.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DailyCheckInfo implements Serializable {
    private Integer id;

    private Integer dailyCheckId;

    private Integer companyId;

    private Integer carId;

    private Integer status;

    private String remark;

    private String operatorId;

    private String operatorName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}
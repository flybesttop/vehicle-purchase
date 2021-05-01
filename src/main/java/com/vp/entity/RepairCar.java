package com.vp.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RepairCar implements Serializable {
    private Integer id;

    private Integer carId;

    private String repairOrder;

    private String repairCompanyPhone;

    private String repairCompanyName;

    private Double repairPrice;

    private LocalDate repairDateStart;

    private LocalDate repairDateEnd;

    private String remark;

    private String operatorId;

    private String operatorName;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}
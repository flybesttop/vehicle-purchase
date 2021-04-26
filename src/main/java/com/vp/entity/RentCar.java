package com.vp.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class RentCar implements Serializable {
    private Integer id;

    private Integer carId;

    private String rentPeopleName;

    private String rentPeoplePhone;

    private String rentPeopleIdcard;

    private Double rentPrice;

    private LocalDate rentTimeStart;

    private LocalDate rentTimeEnd;

    private String remark;

    private String operatorId;

    private String operatorName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}
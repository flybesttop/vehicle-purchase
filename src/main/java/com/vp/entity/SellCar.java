package com.vp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SellCar implements Serializable {
    private Integer id;

    private Integer carId;

    private String sellOrder;

    private String sellPeopleName;

    private String sellPeoplePhone;

    private String sellPeopleIdcard;

    private Double sellPrice;

    private String remark;

    private String operatorId;

    private String operatorName;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}
package com.vp.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Car implements Serializable {
    private Integer id;

    private String carNumber;

    private String carImg;

    private String carModel;

    private String carColor;

    private String engineNumber;

    private String vim;

    private String annualReviewTime;

    private String brand;

    private String firstInsurancePurchaseTime;

    private Integer secondGuaranteeMileage;

    private String factoryNumber;

    private String ownerName;

    private String ownerPhone;

    private Integer seatNumber;

    private String purchaseTime;

    private Double purchasePrice;

    private Integer purchaseCompanyId;

    private String purchaseCompanyName;

    private String purchaseUserId;

    private String purchaseUserName;

    private Double purchaseTax;

    private Double licensingFee;

    private String certificateProcessingTime;

    private String remark;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    private String modelName;

    private String statusName;
}
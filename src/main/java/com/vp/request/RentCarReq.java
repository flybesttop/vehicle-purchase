package com.vp.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 租赁车辆
 *
 * @author flybesttop
 * @date 2021-04-26
 */
@Data
public class RentCarReq {

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

    private Integer newStatus;
}

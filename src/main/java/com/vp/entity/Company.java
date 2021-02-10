package com.vp.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公司model
 * @author flybesttop
 * @date 2021-02-08
 */
@Data
public class Company implements Serializable {
    private Integer id;

    private String companyName;

    private String avatarUrl;

    private String phone;

    private String province;

    private String city;

    private String area;

    private String postCode;

    private String introduction;

    private Integer creatorId;

    private String creatorPhone;

    private String creatorName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
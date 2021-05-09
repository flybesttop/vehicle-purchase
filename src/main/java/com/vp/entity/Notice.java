package com.vp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Notice implements Serializable {
    private Integer id;

    private Integer companyId;

    private String title;

    private String content;

    private String imgUrl;

    private String operatorId;

    private String operatorName;

    private Integer status;

    @JsonFormat( pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

    @JsonFormat( pattern = "yyyy-MM-dd")
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}
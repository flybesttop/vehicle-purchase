package com.vp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class StopCar implements Serializable {
    private Integer id;

    private Integer carId;

    private String stopReason;

    private String operatorId;

    private String operatorName;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}
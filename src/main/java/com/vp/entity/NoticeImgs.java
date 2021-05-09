package com.vp.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class NoticeImgs implements Serializable {
    private Integer id;

    private Integer noticeId;

    private String imgUrl;

    private String operatorId;

    private String operatorName;

    private Integer sort;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}
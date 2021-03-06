package com.vp.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 客户model
 * @author flybesttop
 * @date 2021-02-08
 */
@Data
public class User implements Serializable {
    private String openId;

    private String avatarUrl;

    private String username;

    private String phone;

    private String nickname;

    private Integer gender;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
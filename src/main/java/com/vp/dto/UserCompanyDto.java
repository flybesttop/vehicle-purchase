package com.vp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户企业/组织
 *
 * @author flybesttop
 * @date 2021-04-06
 */
@Data
public class UserCompanyDto {

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司头像
     */
    private String avatarUrl;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 公司简介
     */
    private String introduction;

    /**
     * 公司id
     */
    private Integer companyId;

    /**
     * 用户公司关系id
     */
    private Integer userCompanyId;

    /**
     * 是否默认显示
     */
    private Integer isDefault;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色id
     */
    private Integer roleId;
}

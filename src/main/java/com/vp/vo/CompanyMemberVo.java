package com.vp.vo;

import lombok.Data;

/**
 * 公司成员
 * @author flybesttop
 * @date 2021-04-16
 */
@Data
public class CompanyMemberVo {

    private String openId;

    private String memberName;

    private String avatar;

    private Integer roleId;

    private String roleName;

    private Integer userCompanyId;
}

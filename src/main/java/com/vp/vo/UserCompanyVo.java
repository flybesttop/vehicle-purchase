package com.vp.vo;

import com.vp.dto.UserCompanyDto;
import lombok.Data;

import java.util.List;

/**
 * 用户公司关系
 * @author flybesttop
 * @date 2021-04-07
 */
@Data
public class UserCompanyVo {

    /**
     * 用户公司关系列
     */
    private List<UserCompanyDto> userCompanyDtos;

    /**
     * 默认公司id
     */
    private Integer defaultUserCompanyId;

}

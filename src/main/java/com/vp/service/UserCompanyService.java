package com.vp.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.vp.dto.UserCompanyDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户公司映射-服务
 *
 * @author flybesttop
 * @date 2021-04-07
 */
@Service
public interface UserCompanyService {

    /**
     * 修改默认公司
     *
     * @param defaultCompanyId
     * @param oldDefaultCompanyId
     * @return
     */
    Boolean changeDefaultCompany(Integer defaultCompanyId, Integer oldDefaultCompanyId);

    /**
     * 删除用户公司
     * @param userCompanyId
     * @return
     */
    Boolean deleteMember(Integer userCompanyId, String openId);

    /**
     * 修改成员角色
     * @param userCompanyId
     * @param newRoleId
     * @return
     */
    Boolean changeUserRole(Integer userCompanyId, Integer newRoleId);
}

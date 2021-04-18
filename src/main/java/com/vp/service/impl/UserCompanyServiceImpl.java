package com.vp.service.impl;

import com.vp.dao.RoleMapper;
import com.vp.dao.UserCompanyMapper;
import com.vp.dto.UserCompanyDto;
import com.vp.entity.Role;
import com.vp.entity.UserCompany;
import com.vp.service.UserCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户公司映射-服务
 *
 * @author flybesttop
 * @date 2021-04-07
 */
@Slf4j
@Service
public class UserCompanyServiceImpl implements UserCompanyService {

    @Autowired
    private UserCompanyMapper userCompanyMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Boolean changeDefaultCompany(Integer defaultUserCompanyId, Integer oldDefaultUserCompanyId) {
        try {
            UserCompany userCompany = new UserCompany();
            userCompany.setId(defaultUserCompanyId);
            userCompany.setIsDefault(1);
            userCompanyMapper.updateByPrimaryKeySelective(userCompany);
            userCompany.setId(oldDefaultUserCompanyId);
            userCompany.setIsDefault(0);
            userCompanyMapper.updateByPrimaryKeySelective(userCompany);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteMember(Integer userCompanyId, String openId) {
        try {
            UserCompany userCompany = userCompanyMapper.selectByPrimaryKey(userCompanyId);
            if (openId.equals(userCompany.getOpenId())) {
                return false;
            }
            userCompanyMapper.deleteByPrimaryKey(userCompanyId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean changeUserRole(Integer userCompanyId, Integer newRoleId) {
        try {
            Role role = roleMapper.selectByPrimaryKey(newRoleId);
            UserCompany userCompany = new UserCompany();
            userCompany.setRoleId(role.getId());
            userCompany.setRoleName(role.getRoleName());
            userCompany.setId(userCompanyId);
            userCompanyMapper.updateByPrimaryKeySelective(userCompany);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}

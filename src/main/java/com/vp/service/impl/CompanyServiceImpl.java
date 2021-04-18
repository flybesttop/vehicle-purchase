package com.vp.service.impl;

import com.vp.dao.CompanyMapper;
import com.vp.dao.RoleMapper;
import com.vp.dao.UserCompanyMapper;
import com.vp.dto.UserCompanyDto;
import com.vp.entity.Company;
import com.vp.entity.Role;
import com.vp.entity.UserCompany;
import com.vp.service.CompanyService;
import com.vp.vo.CompanyMemberVo;
import com.vp.vo.UserCompanyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author flybesttop
 * @date 2020/11/26
 */
@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private UserCompanyMapper userCompanyMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Integer createCompany(Company company) {
        int sameNameCompany = companyMapper.selectSameNameCompany(company);
        if (sameNameCompany > 0) {
            //当前客户已创建同名的公司了
            return -1;
        }
        int result = companyMapper.insert(company);
        if (result != 0) {
            return company.getId();
        } else {
            return -1;
        }
    }

    @Override
    public Boolean updateCompany(Company company) {
        int sameNameCompany = companyMapper.selectSameNameCompany(company);
        if (sameNameCompany > 0) {
            //当前客户已创建同名的公司了
            return false;
        }
        Integer result = companyMapper.updateByPrimaryKeySelective(company);
        return result > 0;
    }

    @Override
    public List<UserCompanyDto> getUserCompanies(String userOpenId, String searchKey) {
        return companyMapper.getUserCompanies(userOpenId, searchKey);
    }

    @Override
    public List<UserCompanyDto> selectCompanyByInvitationCode(String invitationCode) {
        return companyMapper.selectCompanyByInvitationCode(invitationCode);
    }

    @Override
    public UserCompanyVo getUserCompaniesAndDefault(String userOpenId, String searchKey) {
        List<UserCompanyDto> userCompanyDtos = companyMapper.getUserCompanies(userOpenId, searchKey);
        Integer defaultUserCompanyId = userCompanyMapper.getUserDefaultCompany(userOpenId);
        UserCompanyVo userCompanyVo = new UserCompanyVo();
        userCompanyVo.setUserCompanyDtos(userCompanyDtos);
        userCompanyVo.setDefaultUserCompanyId(defaultUserCompanyId);
        return userCompanyVo;
    }

    @Override
    public UserCompanyDto getDefaultCompany(String openId) {
        UserCompanyDto u = companyMapper.getDefaultCompany(openId);
        return u;
    }

    @Override
    public Company getDefaultCompanyInfo(String openId) {
        Company c = companyMapper.getDefaultCompanyInfo(openId);
        return c;
    }

    @Override
    public Boolean joinCompany(String openId, Integer roleId) {
        try {
            Role role = roleMapper.selectByPrimaryKey(roleId);
            UserCompany userCompany = userCompanyMapper.selectUserCompany(openId, role.getCompanyId());
            Integer defaultUserCompanyId = userCompanyMapper.getUserDefaultCompany(openId);
            if (Objects.isNull(userCompany)) {
                userCompany = new UserCompany();
                userCompany.setRoleId(roleId);
                userCompany.setRoleName(role.getRoleName());
                userCompany.setCompanyId(role.getCompanyId());
                userCompany.setOpenId(openId);
                if (Objects.isNull(defaultUserCompanyId)) {
                    userCompany.setIsDefault(1);
                } else {
                    userCompany.setIsDefault(0);
                }
                userCompanyMapper.insertSelective(userCompany);
            } else {
                userCompany.setRoleId(roleId);
                userCompany.setRoleName(role.getRoleName());
                userCompanyMapper.updateByPrimaryKeySelective(userCompany);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<CompanyMemberVo> getCompanyMembers(String openId, String searchKey) {
        Company company=companyMapper.getDefaultCompanyInfo(openId);
        List<CompanyMemberVo> companyMemberVo = companyMapper.getCompanyMembers(company.getId(), searchKey);
        return companyMemberVo;
    }

}



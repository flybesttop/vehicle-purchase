package com.vp.service.impl;

import com.vp.dao.CompanyMapper;
import com.vp.dao.UserCompanyMapper;
import com.vp.dto.UserCompanyDto;
import com.vp.entity.Company;
import com.vp.entity.UserCompany;
import com.vp.service.CompanyService;
import com.vp.vo.UserCompanyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author flybesttop
 * @date 2020/11/26
 */
@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    public CompanyMapper companyMapper;
    @Autowired
    public UserCompanyMapper userCompanyMapper;

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
}


